package org.albertsanso.tabletennis.dataretrieval.scrapping.iface;

import org.albertsanso.tabletennis.data.Season;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface SeasonUrlScratcher {
    List<String> scratch(String url, Season season) throws IOException;
    List<String> scratch(URL url, Season season) throws IOException;
}
