package org.albertsanso.tabletennis.dataretrieval.scrapping.iface;

import org.albertsanso.tabletennis.data.Season;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public interface SeasonInfoScratcher {
    Map<String, String> scratch(String url, Season season) throws IOException;
    Map<String, String> scratch(URL url, Season season) throws IOException;
}
