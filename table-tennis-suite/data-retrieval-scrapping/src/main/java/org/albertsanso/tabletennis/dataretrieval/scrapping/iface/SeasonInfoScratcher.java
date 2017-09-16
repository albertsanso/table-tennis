package org.albertsanso.tabletennis.dataretrieval.scrapping.iface;

import org.albertsanso.tabletennis.data.Season;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public interface SeasonInfoScratcher {
    List<Map<String, String>> scratch(String url, Season season) throws IOException;
    List<Map<String, String>> scratch(URL url, Season season) throws IOException;
}
