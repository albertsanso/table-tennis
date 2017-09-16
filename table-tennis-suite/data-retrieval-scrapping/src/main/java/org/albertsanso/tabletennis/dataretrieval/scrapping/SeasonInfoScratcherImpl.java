package org.albertsanso.tabletennis.dataretrieval.scrapping;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.iface.SeasonInfoScratcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class SeasonInfoScratcherImpl implements SeasonInfoScratcher {

    protected String baseUrl;
    protected String fullUrl;
    protected Season season;

    @Override
    public List<Map<String, String>> scratch(String url, Season season) throws IOException {
        URL theURL = new URL(url);
        return scratch(theURL, season);
    }

    @Override
    public List<Map<String, String>> scratch(URL url, Season season) throws IOException {
        this.baseUrl = String.format("%s://%s/",
                url.getProtocol(),
                url.getHost());

        this.fullUrl = url.toString();
        this.season = season;

        Document doc = Jsoup.connect(url.toString()).get();
        return scratch(doc);
    }

    protected List<Map<String, String>> scratch(Document doc) throws IOException {
        Elements rootElement = doc.select(getRootSelector());
        return scratchFromRoot(rootElement);
    }

    protected abstract List<Map<String, String>> scratchFromRoot(Elements root) throws IOException;

    protected abstract String getRootSelector();
}