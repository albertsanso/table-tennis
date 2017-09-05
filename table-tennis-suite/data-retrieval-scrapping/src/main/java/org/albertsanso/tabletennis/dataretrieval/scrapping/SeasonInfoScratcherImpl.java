package org.albertsanso.tabletennis.dataretrieval.scrapping;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.iface.SeasonInfoScratcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public abstract class SeasonInfoScratcherImpl implements SeasonInfoScratcher {

    protected String baseUrl;

    @Override
    public Map<String, String> scratch(String url, Season season) throws IOException {
        URL theURL = new URL(url);
        return scratch(theURL, season);
    }

    @Override
    public Map<String, String> scratch(URL url, Season season) throws IOException {
        this.baseUrl = String.format("%s://%s/",
                url.getProtocol(),
                url.getHost());

        Document doc = Jsoup.connect(url.toString()).get();
        return scratch(doc, season);
    }

    public Map<String, String> scratch(Document doc, Season season) throws IOException {
        Elements rootElement = doc.select(getRootSelectorBySeason(season));
        return scratchFromRoot(rootElement);
    }

    protected abstract Map<String, String> scratchFromRoot(Elements root);

    protected abstract String getRootSelectorBySeason(Season season);
}