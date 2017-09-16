package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.retrieve;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch.MatchesResultsInfoScratcher;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch.MatchesResultsUrlScratcher;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.URLQualificationsDepot;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

@Named
public class MatchesRetriever {

    private List<Season> matchesSeasonsList;
    private URLQualificationsDepot qualificationsUrls;

    private MatchesResultsUrlScratcher matchesResultsUrlScratcher;
    private MatchesResultsInfoScratcher matchesResultsInfoScratcher;

    @Inject
    public MatchesRetriever(
            List<Season> matchesSeasonsList,
            URLQualificationsDepot qualificationsUrls,
            MatchesResultsUrlScratcher matchesResultsUrlScratcher,
            MatchesResultsInfoScratcher matchesResultsInfoScratcher
    ) {
        this.matchesSeasonsList = matchesSeasonsList;
        this.qualificationsUrls = qualificationsUrls;
        this.matchesResultsUrlScratcher = matchesResultsUrlScratcher;
        this.matchesResultsInfoScratcher = matchesResultsInfoScratcher;
    }

    public void retrieveMatchesInfo() throws IOException {
        for (Season currentSeason : matchesSeasonsList) {
            retrieveMatchesBySeason(currentSeason);
        }
    }

    public void retrieveMatchesBySeason(Season season) throws IOException {
        List<String> seasonQualificationsUrls = qualificationsUrls.getQualificationsUrlsBySeason(season);
        for (String seasonedURL : seasonQualificationsUrls) {
            retrieveByUrlAndSeason(seasonedURL, season);
        }
    }

    private void retrieveByUrlAndSeason(String seasonedURL, Season season) throws IOException{
        URL url = new URL(seasonedURL);
        retrieveByUrlAndSeason(url, season);
    }

    private void retrieveByUrlAndSeason(URL seasonedURL, Season season) throws IOException{
        crawlJornadasRoot(seasonedURL, season);
    }

    private void crawlJornadasRoot(URL seasonedURL, Season season) throws IOException {
        List<String> jornadasUrls = matchesResultsUrlScratcher.scratch(seasonedURL, season);
        for (String jornadaUrl : jornadasUrls) {
            matchesResultsInfoScratcher.scratch(jornadaUrl, season);
        }
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
