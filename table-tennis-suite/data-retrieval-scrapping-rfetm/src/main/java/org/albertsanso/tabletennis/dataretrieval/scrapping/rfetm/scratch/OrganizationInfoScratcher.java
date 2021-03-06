package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.tabletennis.dataretrieval.scrapping.SeasonInfoScratcherImpl;
import org.albertsanso.tabletennis.dataretrieval.scrapping.iface.SeasonInfoScratcher;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.mapper.OrganizationMapper;
import org.jsoup.select.Elements;

import javax.inject.Named;
import java.util.*;

@Named
public class OrganizationInfoScratcher extends SeasonInfoScratcherImpl implements SeasonInfoScratcher {

    private static final String ROOT_SELECTOR_2012_2013 = "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > div:nth-child(12) > table";
    private static final String ROOT_SELECTOR = "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > div:nth-child(10) > table";
    private static final String INFO_SELECTOR = "td:nth-child(2) > div > font";

    @Override
    protected String getRootSelector() {
        String rootSelector = ROOT_SELECTOR;
        if (Season.SEASON_2012_2013.seasonKey.equals(season.seasonKey)) {
            rootSelector = ROOT_SELECTOR_2012_2013;
        }
        return rootSelector;
    }

    @Override
    protected List<Map<String, String>> scratchFromRoot(Elements root)
    {
        Elements rowsElements = root.select("tr");
        Map<String, String> rowInfo = mapRowElements(rowsElements);
        List<Map<String, String>> rowsInfosList = new ArrayList<Map<String, String>>();
        rowsInfosList.add(rowInfo);
        return rowsInfosList;
    }

    protected Map<String, String> mapRowElements(Elements rows) {
        Map<String, String> rowElementsMap = new HashMap<>();

        for (int i=1; i<rows.size()-1; i++) {
            String text = rows.get(i).select(INFO_SELECTOR).text();
            OrganizationMapper mapping = OrganizationMapper.getByPosition(i);
            rowElementsMap.put(mapping.key, text);
        }
        return rowElementsMap;
    }
}
