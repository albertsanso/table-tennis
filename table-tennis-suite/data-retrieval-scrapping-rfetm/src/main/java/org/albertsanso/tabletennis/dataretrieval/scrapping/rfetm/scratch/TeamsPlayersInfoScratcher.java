package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch;

import org.albertsanso.tabletennis.dataretrieval.scrapping.SeasonInfoScratcherImpl;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.mapper.TeamMapper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Named;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class TeamsPlayersInfoScratcher extends SeasonInfoScratcherImpl {

    private static final String ROOT_SELECTOR = "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > div:nth-child(5) > center > table > tbody";

    //2012-2013
    //private static final String ROOT_SELECTOR = "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > div:nth-child(7) > center > table > tbody";

    private String strCompetitionId = "";
    private String strCategoryId = "";
    private String strTeamId = "";

    @Override
    protected String getRootSelector() {
        return ROOT_SELECTOR;
    }

    @Override
    protected List<Map<String, String>> scratchFromRoot(Elements root) throws IOException {

        this.strCompetitionId = "liga_nacional_española";
        this.strTeamId = scratchTeamId();
        this.strCategoryId = scratchCategoryId();

        Elements rowsElements = root.select("tr");
        List<Map<String, String>> info = mapRowsElements(rowsElements);
        return info;
    }

    protected List<Map<String, String>> mapRowsElements(Elements elementRows) {
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();

        for (Element elementRow : elementRows.subList(1, elementRows.size())) {
            Map<String, String> row = mapRowElements(elementRow);
            rows.add(row);
        }
        return rows;
    }

    protected Map<String, String> mapRowElements(Element row) {
        Map<String, String> rowElementsMap = new HashMap<>();

        String strPlayerId = row.select("tr > td").get(0).text();
        String strPlayerName = row.select("tr > td").get(1).text();
        String strPlayerCategory = row.select("tr > td").get(2).text();
        String strPlayerNacionality = row.select("tr > td").get(3).text();
        String strPlayerProcess = row.select("tr > td").get(4).text();

        rowElementsMap.put(TeamMapper.PLAYER_ID.key, strPlayerId);
        rowElementsMap.put(TeamMapper.PLAYER_NAME.key, strPlayerName);
        rowElementsMap.put(TeamMapper.PLAYER_CATEGORY.key, strPlayerCategory);
        rowElementsMap.put(TeamMapper.PLAYER_NACIONALITY.key, strPlayerNacionality);
        rowElementsMap.put(TeamMapper.PLAYER_PROCESS.key, strPlayerProcess);

        rowElementsMap.put(TeamMapper.TEAM_ID.key, this.strTeamId);
        rowElementsMap.put(TeamMapper.TEAM_CATEGORY.key, this.strCategoryId);
        rowElementsMap.put(TeamMapper.COMPETITION_ID.key, this.strCompetitionId);

        rowElementsMap.put(TeamMapper.SEASON.key, this.season.seasonKey);

        return rowElementsMap;
    }

    private String scratchTeamId() throws IOException {
        return getParameterValueFromUrl("IDEQUIPO", this.fullUrl);
    }

    private String scratchCategoryId() throws IOException {
        return getParameterValueFromUrl("stridnivel", this.fullUrl);
    }

    private String getParameterValueFromUrl(String paramName, String strUrl) throws IOException {

        URL url = new URL(strUrl);
        return getParameterValueFromUrl(paramName, url);
    }

    private String getParameterValueFromUrl(String paramName, URL url) {
        String query = url.getQuery();
        String[] paramPairs = query.split("&");
        for (String paramPair : paramPairs) {
            String[] currentParamPair = paramPair.split("=");
            String currentParamName = currentParamPair[0];
            String currentParamValue = currentParamPair[1];
            if (paramName.equals(currentParamName)) {
                return currentParamValue;
            }
        }
        return null;
    }
}
