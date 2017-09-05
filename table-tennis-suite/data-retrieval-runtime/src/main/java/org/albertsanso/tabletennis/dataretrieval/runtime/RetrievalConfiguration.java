package org.albertsanso.tabletennis.dataretrieval.runtime;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.URLQualificationsDepot;
import org.albertsanso.util.SimpleCsvMapWriter;
import org.albertsanso.util.YamlResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(RetrievalPropertiesConfiguration.class)
public class RetrievalConfiguration {

    private final RetrievalPropertiesConfiguration properties;

    @Autowired
    public RetrievalConfiguration(RetrievalPropertiesConfiguration properties) {
        this.properties = properties;
    }

    @Bean
    public YamlResourceReader yamlReader() {
        return new YamlResourceReader();
    }

    @Bean
    public URLQualificationsDepot organizationsUrls() throws IOException {

        RetrievalEntityConfiguration conf = getOrganizationRetrievalConf();
        URLQualificationsDepot urlDepot = new URLQualificationsDepot(yamlReader());
        urlDepot.readQualificationsUrls(conf.getUrls());
        return urlDepot;
    }

    @Bean
    public SimpleCsvMapWriter organizationWriter() throws IOException {

        RetrievalEntityConfiguration conf = getOrganizationRetrievalConf();
        SimpleCsvMapWriter writer = new SimpleCsvMapWriter();
        writer.setCsvFile(new File(conf.getOutputCsv()));
        return writer;
    }

    @Bean
    public List<Season> organizationSeasonsList() {
        List<Season> seasonsList = new ArrayList<Season>();

        RetrievalEntityConfiguration conf = getOrganizationRetrievalConf();
        String strSeasons = conf.getSeasons();
        if (strSeasons != null) {
            String[] arrSeasons = strSeasons.split(",");
            for (String seasonKey : arrSeasons) {
                Season season = Season.getByKey(seasonKey);
                if (season != null) {
                    seasonsList.add(season);
                }
            }
        }
        return seasonsList;
    }

    private RetrievalEntityConfiguration getOrganizationRetrievalConf() {
        RetrievalEntityConfiguration conf = this.properties.getOrganizationRetrievalConf();
        if (conf == null) {
            throw new IllegalStateException("No configuration found for Organization retrieval in application.yaml file.");
        }
        return conf;
    }
}
