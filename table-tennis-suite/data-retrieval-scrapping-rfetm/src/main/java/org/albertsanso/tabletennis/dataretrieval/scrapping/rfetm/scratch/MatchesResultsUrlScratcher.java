package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.data.SeasonURLUtil;
import org.albertsanso.tabletennis.dataretrieval.scrapping.SeasonUrlScratcherImpl;
import org.jsoup.select.Elements;

import javax.inject.Named;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Named
public class MatchesResultsUrlScratcher extends SeasonUrlScratcherImpl {

    private String[][] selectorsMapping = {
            {"^.*/liga_eligamenuempate.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > a > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"},
            {"^.*/liga_eligamenu.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"},
            {"^.*/historico/\\d{4}-\\d{4}/liga_eligamenuempate.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(4) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > a > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"},
            {"^.*/historico/\\d{4}-\\d{4}/liga_eligamenu.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(4) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"},
    };

    @Override
    protected List<String> scratchFromRoot(Elements root) throws MalformedURLException {
        String jornadaUrl = arrangeUrl(root.attr("href"));
        if (!isCurrentSeason()) {
            if (!isSeasonedURL(jornadaUrl)) {
                jornadaUrl = buildSeasonedURL(jornadaUrl);
            }
        }
        return Arrays.asList(jornadaUrl);
    }

    private String arrangeUrl(String strCandidateUrl) {

        String strUrl = "";

        if (strCandidateUrl != null && !"".equals(strCandidateUrl) && !strCandidateUrl.contains("http://resultados.rfetm.es")) {

            if (strCandidateUrl.startsWith("/")) {
                strUrl = "http://resultados.rfetm.es" + strCandidateUrl;
            }
            else {
                strUrl = "http://resultados.rfetm.es/" + strCandidateUrl;
            }
        }
        else {
            strUrl = strCandidateUrl;
        }

        return strUrl;
    }

    @Override
    protected String getRootSelector() {
        return getSelector(fullUrl);
    }

    private String getSelector(URL url) {
        return getSelector(url.toString());
    }

    private String getSelector(String url) {
        for (String[] selectorPair : selectorsMapping) {
            String regexp = selectorPair[0];

            if (Pattern.matches(regexp, url)) {
                return selectorPair[1];
            }
        }
        return null;
    }
}
