package org.albertsanso.tabletennis.dataretrieval.scrapping;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.iface.SeasonUrlScratcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public abstract class SeasonUrlScratcherImpl implements SeasonUrlScratcher {

    protected String baseUrl;

    @Override
    public List<String> scratch(String url, Season season) throws IOException {
        URL theURL = new URL(url);
        return scratch(theURL, season);
    }

    @Override
    public List<String> scratch(URL url, Season season) throws IOException {
        this.baseUrl = String.format("%s://%s/",
                url.getProtocol(),
                url.getHost());

        Document doc = Jsoup.connect(url.toString()).get();
        return scratch(doc, season);
    }

    public List<String> scratch(Document doc, Season season) throws IOException {
        Elements rootElement = doc.select(getRootSelectorBySeason(season));
        return scratchFromRoot(rootElement, season);
    }

    protected abstract List<String> scratchFromRoot(Elements root, Season season);

    protected abstract String getRootSelectorBySeason(Season season);
}