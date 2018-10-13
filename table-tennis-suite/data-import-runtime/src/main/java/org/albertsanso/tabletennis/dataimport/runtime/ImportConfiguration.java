package org.albertsanso.tabletennis.dataimport.runtime;

import org.albertsanso.tabletennis.dataimport.ImporterConfiguration;
import org.albertsanso.tabletennis.jpa.RepositorySpringJpaConfiguration;
import org.albertsanso.util.SimpleCsvMapReader;
import org.albertsanso.util.SimpleCsvMapWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.File;
import java.io.IOException;

@Configuration
@Import({RepositorySpringJpaConfiguration.class, ImporterConfiguration.class})
@EnableConfigurationProperties(ImportPropertiesConfiguration.class)
public class ImportConfiguration {

    private final ImportPropertiesConfiguration properties;

    @Autowired
    public ImportConfiguration(ImportPropertiesConfiguration properties) {
        this.properties = properties;
    }

    @Bean
    public SimpleCsvMapReader organizationReader() throws IOException {
        return buildReader(getOrganizationsConf());
    }

    @Bean
    public SimpleCsvMapReader teamReader() throws IOException {
        return buildReader(getTeamsConf());
    }

    @Bean
    public SimpleCsvMapReader playerReader() throws IOException {
        return buildReader(getPlayerConf());
    }

    @Bean
    public SimpleCsvMapReader resultReader() throws IOException {
        return buildReader(getResultConf());
    }

    @Bean
    public SimpleCsvMapReader matchReader() throws IOException {
        return buildReader(getMatchConf());
    }

    protected SimpleCsvMapReader buildReader(ImportEntityConfiguration iec) throws IOException {
        SimpleCsvMapReader reader = new SimpleCsvMapReader();
        reader.setCsvFile(new File(iec.getInputCsv()));
        return reader;
    }

    private ImportEntityConfiguration getOrganizationsConf() {
        ImportEntityConfiguration conf = this.properties.getOrganizationImportConf();
        if (conf == null) {
            throw new IllegalStateException("No configuration found for Organization retrieval in application.yaml file.");
        }
        return conf;
    }

    private ImportEntityConfiguration getTeamsConf() {
        ImportEntityConfiguration conf = this.properties.getTeamImportConf();
        if (conf == null) {
            throw new IllegalStateException("No configuration found for Teams retrieval in application.yaml file.");
        }
        return conf;
    }

    private ImportEntityConfiguration getPlayerConf() {
        ImportEntityConfiguration conf = this.properties.getPlayerImportConf();
        if (conf == null) {
            throw new IllegalStateException("No configuration found for Players retrieval in application.yaml file.");
        }
        return conf;
    }

    private ImportEntityConfiguration getResultConf() {
        ImportEntityConfiguration conf = this.properties.getResultImportConf();
        if (conf == null) {
            throw new IllegalStateException("No configuration found for Results retrieval in application.yaml file.");
        }
        return conf;
    }

    private ImportEntityConfiguration getMatchConf() {
        ImportEntityConfiguration conf = this.properties.getMatchImportConf();
        if (conf == null) {
            throw new IllegalStateException("No configuration found for Matches retrieval in application.yaml file.");
        }
        return conf;
    }
}
