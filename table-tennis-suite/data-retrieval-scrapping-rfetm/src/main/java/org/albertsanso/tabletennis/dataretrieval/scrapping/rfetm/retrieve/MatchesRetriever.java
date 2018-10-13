package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.retrieve;

import org.albertsanso.tabletennis.data.OrganizationKeys;
import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch.MatchesResultsInfoScratcher;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch.MatchesResultsUrlScratcher;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.URLQualificationsDepot;
import org.albertsanso.util.SimpleCsvMapWriter;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Named
public class MatchesRetriever {

    private List<Season> matchesSeasonsList;
    private URLQualificationsDepot qualificationsUrls;

    private SimpleCsvMapWriter matchesWriter;

    private MatchesResultsUrlScratcher matchesResultsUrlScratcher;
    private MatchesResultsInfoScratcher matchesResultsInfoScratcher;
    private boolean isHeaderWritten;

    @Inject
    public MatchesRetriever(
            List<Season> matchesSeasonsList,
            URLQualificationsDepot qualificationsUrls,
            MatchesResultsUrlScratcher matchesResultsUrlScratcher,
            MatchesResultsInfoScratcher matchesResultsInfoScratcher,
            SimpleCsvMapWriter matchesWriter
    ) {
        this.matchesSeasonsList = matchesSeasonsList;
        this.qualificationsUrls = qualificationsUrls;
        this.matchesResultsUrlScratcher = matchesResultsUrlScratcher;
        this.matchesResultsInfoScratcher = matchesResultsInfoScratcher;
        this.matchesWriter = matchesWriter;
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

    public void retrieveMatchesInfo() throws IOException {
        for (Season currentSeason : matchesSeasonsList) {
            retrieveMatchesBySeason(currentSeason);
        }
    }

    public void retrieveMatchesBySeason(Season season) throws IOException {
        List<String> seasonQualificationsUrls = qualificationsUrls.getQualificationsUrlsBySeason(season);
        for (String seasonedURL : seasonQualificationsUrls) {

            System.out.println("processing: "+seasonedURL);

            List<Map<String, String>> seasonMappings = retrieveByUrlAndSeason(seasonedURL, season);
            processMappingsBySeason(seasonMappings, season);
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
            headerCandidate.put(OrganizationKeys.SEASON.key, OrganizationKeys.SEASON.key);
            matchesWriter.writeHeader(mappings.get(0));
        }
    }

    private void processMapping(Map<String, String> mapping) throws IOException {
        if (mapping != null && mapping.size() > 0) {
            matchesWriter.writeValues(mapping);
        }
    }

    private List<Map<String, String>> retrieveByUrlAndSeason(String seasonedURL, Season season) throws IOException{
        URL url = new URL(seasonedURL);
        return retrieveByUrlAndSeason(url, season);
    }

    private List<Map<String, String>> retrieveByUrlAndSeason(URL seasonedURL, Season season) throws IOException{
        return crawlJornadasRoot(seasonedURL, season);
    }

    private List<Map<String, String>> crawlJornadasRoot(URL seasonedURL, Season season) throws IOException {
        List<Map<String, String>> mappings = new ArrayList<Map<String, String>>();

        List<String> jornadasUrls = matchesResultsUrlScratcher.scratch(seasonedURL, season);
        for (String jornadaUrl : jornadasUrls) {
            List<Map<String, String>> infoList = matchesResultsInfoScratcher.scratch(jornadaUrl, season);

            //Map<String, String> mapping = infoList.get(0);
            mappings.addAll(infoList);
        }

        return mappings;
    }

    private String getSelector(URL url) {
        return getSelector(url.toString());
    }

    private String getSelector(String url) {
        for (String[] selectorPair : selectors) {
            String regexp = selectorPair[0];

            if (Pattern.matches(regexp, url)) {
                return selectorPair[1];
            }
        }
        return null;
    }

    private String[][] selectors = {
            {"^.*/liga_eligamenuempate.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > a > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"},
            {"^.*/liga_eligajornadaempate.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(3) > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > strong:nth-child(4) > div > center > table"},
            {"^.*/liga_eligamenu.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"},
            {"^.*/liga_eligajornada.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(3) > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > strong:nth-child(5) > div:nth-child(4) > center > table"},

            {"^.*/historico/\\d{4}-\\d{4}/liga_eligamenuempate.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(4) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > a > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"},
            {"^.*/historico/\\d{4}-\\d{4}/liga_eligajornadaempate.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(5) > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > strong:nth-child(4) > div > center > table"},
            {"^.*/historico/\\d{4}-\\d{4}/liga_eligamenu.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(4) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"},
            {"^.*/historico/\\d{4}-\\d{4}/liga_eligajornada.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(5) > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > strong:nth-child(5) > div:nth-child(4) > center > table"}
    };
}
