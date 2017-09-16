package org.albertsanso.tabletennis.dataretrieval.scrapping;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.data.SeasonURLUtil;
import org.albertsanso.tabletennis.dataretrieval.scrapping.iface.SeasonUrlScratcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public abstract class SeasonUrlScratcherImpl implements SeasonUrlScratcher {

    protected String baseUrl;
    protected String fullUrl;
    protected Season season;

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

        this.fullUrl = url.toString();
        this.season = season;

        Document doc = Jsoup.connect(url.toString()).get();
        return scratch(doc);
    }

    protected List<String> scratch(Document doc) throws IOException {
        Elements rootElement = doc.select(getRootSelector());
        return scratchFromRoot(rootElement);
    }

    protected boolean isCurrentSeason() {
        return Season.CURRENT_SEASON.equals(this.season);
    }

    protected String buildSeasonedURL(String theUrl) throws MalformedURLException {
        return SeasonURLUtil.buildSeasonedURL(theUrl, this.season);
    }

    protected abstract List<String> scratchFromRoot(Elements root) throws MalformedURLException;

    protected abstract String getRootSelector();
}