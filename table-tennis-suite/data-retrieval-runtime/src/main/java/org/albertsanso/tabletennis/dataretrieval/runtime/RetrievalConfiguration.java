package org.albertsanso.tabletennis.dataretrieval.runtime;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.URLQualificationsDepot;
import org.albertsanso.util.SimpleCsvMapWriter;
import org.albertsanso.util.YamlResourceReader;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(RetrievalPropertiesConfiguration.class)
public class RetrievalConfiguration {

    private final RetrievalPropertiesConfiguration properties;

    @Inject
    public RetrievalConfiguration(
            RetrievalPropertiesConfiguration properties) {
        this.properties = properties;
    }

    @Bean
    public YamlResourceReader yamlReader() {
        return new YamlResourceReader();
    }

    @Bean
    public URLQualificationsDepot qualificationsUrls() throws IOException {

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
    public SimpleCsvMapWriter matchesWriter() throws IOException {

        RetrievalEntityConfiguration conf = getMatchesRetrievalConf();
        SimpleCsvMapWriter writer = new SimpleCsvMapWriter();
        writer.setCsvFile(new File(conf.getOutputCsv()));
        return writer;
    }

    @Bean
    public SimpleCsvMapWriter teamsWriter() throws IOException {

        RetrievalEntityConfiguration conf = getTeamsRetrievalConf();
        SimpleCsvMapWriter writer = new SimpleCsvMapWriter();
        writer.setCsvFile(new File(conf.getOutputCsv()));
        return writer;
    }

    @Bean
    public List<Season> organizationSeasonsList() {
        RetrievalEntityConfiguration conf = getOrganizationRetrievalConf();
        List<Season> seasonsList = findSeasonsList(conf);
        return seasonsList;
    }

    @Bean
    public List<Season> matchesSeasonsList() {
        RetrievalEntityConfiguration conf = getMatchesRetrievalConf();
        List<Season> seasonsList = findSeasonsList(conf);
        return seasonsList;
    }

    @Bean
    public List<Season> teamsSeasonsList() {
        RetrievalEntityConfiguration conf = getTeamsRetrievalConf();
        List<Season> seasonsList = findSeasonsList(conf);
        return seasonsList;
    }

    private List<Season> findSeasonsList(RetrievalEntityConfiguration conf) {
        List<Season> seasonsList = new ArrayList<Season>();

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

    private RetrievalEntityConfiguration getMatchesRetrievalConf() {
        RetrievalEntityConfiguration conf = this.properties.getMatchesRetrievalConf();
        if (conf == null) {
            throw new IllegalStateException("No configuration found for Matches retrieval in application.yaml file.");
        }
        return conf;
    }

    private RetrievalEntityConfiguration getTeamsRetrievalConf() {
        RetrievalEntityConfiguration conf = this.properties.getTeamsRetrievalConf();
        if (conf == null) {
            throw new IllegalStateException("No configuration found for Teams retrieval in application.yaml file.");
        }
        return conf;
    }
}
