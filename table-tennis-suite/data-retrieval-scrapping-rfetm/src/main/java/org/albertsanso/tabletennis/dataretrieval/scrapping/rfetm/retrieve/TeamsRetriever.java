package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.retrieve;

import org.albertsanso.tabletennis.data.OrganizationKeys;
import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.URLQualificationsDepot;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch.OrganizationUrlScratcher;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch.TeamsPlayersInfoScratcher;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch.TeamsPlayersUrlScratcher;
import org.albertsanso.util.SimpleCsvMapWriter;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
public class TeamsRetriever {

    private List<Season> teamsSeasonsList;
    private URLQualificationsDepot qualificationsUrls;
    private TeamsPlayersInfoScratcher teamsPlayersInfoScratcher;
    private OrganizationUrlScratcher organizationUrlScratcher;
    private SimpleCsvMapWriter teamsWriter;

    private boolean isHeaderWritten = false;

    @Inject
    public TeamsRetriever(
            List<Season> teamsSeasonsList,
            URLQualificationsDepot qualificationsUrls,
            TeamsPlayersInfoScratcher teamsPlayersInfoScratcher,
            OrganizationUrlScratcher organizationUrlScratcher,
            SimpleCsvMapWriter teamsWriter
    ) {
        this.teamsSeasonsList = teamsSeasonsList;
        this.qualificationsUrls = qualificationsUrls;
        this.teamsPlayersInfoScratcher = teamsPlayersInfoScratcher;
        this.organizationUrlScratcher= organizationUrlScratcher;
        this.teamsWriter = teamsWriter;
    }

    public void retrieveTeamsInfo() throws IOException {
        for (Season season : teamsSeasonsList) {
            retrieveTeamsBySeason(season);
        }
    }

    public void retrieveTeamsBySeason(Season season) throws IOException {
        List<String> seasonQualificationsUrls = qualificationsUrls.getQualificationsUrlsBySeason(season);
        //if (seasonQualificationsUrls != null) {
            for (String seasonedURL : seasonQualificationsUrls) {
                retrieveByUrlAndSeason(seasonedURL, season);
            }
        //}
    }

    private void retrieveByUrlAndSeason(String seasonedURL, Season season) throws IOException{
        URL url = new URL(seasonedURL);
        retrieveByUrlAndSeason(url, season);
    }

    private void retrieveByUrlAndSeason(URL seasonedURL, Season season) throws IOException{
        crawlJornadasRoot(seasonedURL, season);
    }

    private void crawlJornadasRoot(URL seasonedURL, Season season) throws IOException {

        List<Map<String, String>> mappings = new ArrayList<Map<String, String>>();

        List<String> teamsUrls = organizationUrlScratcher.scratch(seasonedURL, season);
        for (String teamUrl : teamsUrls) {

            System.out.println("--> EQUIPO URL: "+teamUrl);
            List<Map<String, String>> seasonMappings = teamsPlayersInfoScratcher.scratch(teamUrl, season);
            processMappingsBySeason(seasonMappings, season);

            mappings.addAll(seasonMappings);
        }
    }

    private void processMappingsBySeason(List<Map<String, String>> mappings, Season season) throws IOException {

        if (!isWrittenHeaderCheck()) {
            processMappingsHeaderBySeason(mappings);
            setWrittenHeaderCheck();
        }

        for (Map<String, String> mapping : mappings) {
            mapping.put(OrganizationKeys.SEASON.key, season.seasonKey);
            processMapping(mapping);
        }
    }

    private void processMappingsHeaderBySeason(List<Map<String, String>> mappings) throws IOException {
        if (mappings != null && mappings.size() > 0) {
            Map<String, String> headerCandidate = mappings.get(0);
            //headerCandidate.put(TeamKeys.SEASON.key, TeamKeys.SEASON.key);
            teamsWriter.writeHeader(mappings.get(0));
        }
    }

    private void processMapping(Map<String, String> mapping) throws IOException {
        if (mapping != null && mapping.size() > 0) {
            teamsWriter.writeValues(mapping);
        }
    }

    private void resetWrittenHeaderCheck() {
        this.isHeaderWritten = false;
    }

    private void setWrittenHeaderCheck() {
        this.isHeaderWritten = true;
    }

    private boolean isWrittenHeaderCheck() {
        return this.isHeaderWritten;
    }
}
