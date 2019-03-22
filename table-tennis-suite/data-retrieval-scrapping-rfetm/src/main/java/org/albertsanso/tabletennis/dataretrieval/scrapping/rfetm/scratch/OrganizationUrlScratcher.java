package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.SeasonUrlScratcherImpl;
import org.albertsanso.tabletennis.dataretrieval.scrapping.iface.SeasonUrlScratcher;
import org.jsoup.select.Elements;

import javax.inject.Named;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Named
public class OrganizationUrlScratcher extends SeasonUrlScratcherImpl implements SeasonUrlScratcher {

    private static final String ROOT_SELECTOR_2012_2013 = "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(4) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > div:nth-child(2) > center > table";
    //private static final String ROOT_SELECTOR = "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > div:nth-child(2) > center > table";
    private static final String ROOT_SELECTOR = "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(6) > tbody ";
                                                 //#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(6) > tbody > tr:nth-child(1) > td:nth-child(2) > b > a
    private static final String URL_SELECTOR = "td:nth-child(2) > b > a";

    @Override
    protected String getRootSelector() {
        String rootSelector = ROOT_SELECTOR;
        if (Season.SEASON_2012_2013.seasonKey.equals(season.seasonKey)) {
            rootSelector = ROOT_SELECTOR_2012_2013;
        }
        return rootSelector;
    }

    @Override
    protected List<String> scratchFromRoot(Elements root)
    {
        Elements rowsElements = root.select("tr");
        return mapRowElements(rowsElements);
    }

    private List<String> mapRowElements(Elements rows) {
        List<String> urls = new ArrayList();

        for (int i=0; i<rows.size(); i++) {
            String textUrl = rows.get(i).select(URL_SELECTOR).attr("href");
            /*
            urls.add(this.baseUrl+textUrl);
            */
            String strBaseUrl = this.baseUrl+textUrl;
            try {
                String strUrl = buildSeasonedURL(strBaseUrl, season);
                urls.add(strUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    private static String buildSeasonedURL(String url, Season season) throws MalformedURLException {
        URL sURL = new URL(url);
        String urlSeasonPart = "historico/"+season.seasonKey;
        String seasonedUrl = String.format("%s://%s/%s%s",
                sURL.getProtocol(),
                sURL.getHost(),
                urlSeasonPart,
                sURL.getFile()
        );
        return seasonedUrl;
    }
}
