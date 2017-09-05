package org.albertsanso.tabletennis.dataimport.runtime;

import org.albertsanso.tabletennis.dataimport.ImporterConfiguration;
import org.albertsanso.tabletennis.jpa.RepositorySpringJpaConfiguration;
import org.albertsanso.util.SimpleCsvMapReader;
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
        ImportEntityConfiguration conf = getImportEntityConf();
        SimpleCsvMapReader reader = new SimpleCsvMapReader();
        reader.setCsvFile(new File(conf.getInputCsv()));
        return reader;
    }

    private ImportEntityConfiguration getImportEntityConf() {
        ImportEntityConfiguration conf = this.properties.getOrganizationImportConf();
        if (conf == null) {
            throw new IllegalStateException("No configuration found for Organization retrieval in application.yaml file.");
        }
        return conf;
    }
}
