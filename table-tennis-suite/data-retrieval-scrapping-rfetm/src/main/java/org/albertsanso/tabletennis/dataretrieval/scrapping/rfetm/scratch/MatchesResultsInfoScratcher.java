package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch;

import org.albertsanso.tabletennis.dataretrieval.scrapping.SeasonInfoScratcherImpl;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.mapper.ResultMapper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Named;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Named
public class MatchesResultsInfoScratcher extends SeasonInfoScratcherImpl {

    private static final String ROOT_SELECTOR = "";

    private String[][] selectors = {
            {"^.*/liga_eligajornadaempate.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(3) > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > strong:nth-child(4) > div > center > table"},
            {"^.*/liga_eligajornada.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(3) > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > strong:nth-child(5) > div:nth-child(4) > center > table"},
            {"^.*/historico/\\d{4}-\\d{4}/liga_eligajornadaempate.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(5) > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > strong:nth-child(4) > div > center > table"},
            {"^.*/historico/\\d{4}-\\d{4}/liga_eligajornada.php.*$", "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(5) > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td > strong:nth-child(5) > div:nth-child(4) > center > table"}
    };

    @Override
    protected String getRootSelector() {
        return getSelector(this.fullUrl);
    }

    @Override
    protected List<Map<String, String>> scratchFromRoot(Elements jornadasRows) {

        List<Map<String, String>> jornadasList = null;
        jornadasList = crawlJordanas(jornadasRows);
        return jornadasList;
    }

    private List<Map<String, String>> crawlJordanas(Elements jornadasRows) {

        //System.out.println("---> " + jornadasRows.size());

        List<Map<String, String>> jornadasList = new ArrayList<Map<String, String>>();

        int jornadaNumber = 1;

        for(Element jornadaElement : jornadasRows) {
            List<Map<String, String>> jornadaInfo  = crawlJornada(jornadaElement);
            jornadasList.addAll(jornadaInfo);

            //jornadaContent.put(TeamMapper.JORNADA_NUMBER.key, ""+jornadaNumber++);
        }

        return jornadasList;
    }

    private List<Map<String, String>> crawlJornada(Element jornadaElement) {

        List<Map<String, String>> jornadas = new ArrayList<Map<String, String>>();
        Elements jornadaRows = jornadaElement.select("tbody > tr");

        for (Element jornadaRow : jornadaRows.subList(1, jornadaRows.size())) {
            Map<String, String> jornadaContent = crawlJornadaRow(jornadaRow);
            jornadas.add(jornadaContent);
        }

        // Apply Jornada Name/ID
        String jornadaName = jornadaElement.select("tbody > tr:nth-child(1) > td:nth-child(1) > strong > font > b > font").text();
        for (Map<String, String> jornada : jornadas) {
            jornada.put(ResultMapper.JORNADA_NUMBER.key, jornadaName);
        }

        return jornadas;
    }


    private Map<String, String> crawlJornadaRow(Element jornadaRow) {
        String data = jornadaRow.select("td:nth-child(1) > p > font").text();
        String hora = jornadaRow.select("td:nth-child(2) > p > font").text();

        String local = jornadaRow.select("td:nth-child(3) > p > font > a").text();
        String localUrl = "";
        String tmpLocalUrl = jornadaRow.select("td:nth-child(3) > p > font > a").attr("href");

        try {
            localUrl = arrangeUrl(tmpLocalUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String localTeamId = "";

        try {
            localTeamId = getParameterValueFromUrl("IDEQUIPO", localUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String visitor = jornadaRow.select("td:nth-child(4) > p > font > a").text();
        String visitorUrl = "";
        String tmpVisitorUrl = jornadaRow.select("td:nth-child(4) > p > font > a").attr("href");

        try {
            visitorUrl = arrangeUrl(tmpVisitorUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String visitorTeamId = "";

        try {
            visitorTeamId = getParameterValueFromUrl("IDEQUIPO", visitorUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String strLocalPoints = jornadaRow.select("td:nth-child(5) > p > font").text();
        String strVisitorlPoints = jornadaRow.select("td:nth-child(6) > p > font").text();

        String minutesUrl = jornadaRow.select("td:nth-child(7) > p > font > a").attr("href");


        System.out.println(data);
        System.out.println(hora);
        System.out.println(local);
        System.out.println(localUrl);
        System.out.println(strLocalPoints);
        System.out.println(strVisitorlPoints);


        Map<String, String> jornadaContent = new HashMap<String, String>();
        jornadaContent.put(ResultMapper.DATE.key, data);
        jornadaContent.put(ResultMapper.TIME.key, hora);
        jornadaContent.put(ResultMapper.LOCAL_TEAM_NAME.key, local);
        jornadaContent.put(ResultMapper.LOCAL_TEAM_ID.key, localTeamId);
        jornadaContent.put(ResultMapper.VISITOR_TEAM_NAME.key, visitor);
        jornadaContent.put(ResultMapper.VISITOR_TEAM_ID.key, visitorTeamId);
        jornadaContent.put(ResultMapper.LOCAL_POINTS.key, strLocalPoints);
        jornadaContent.put(ResultMapper.VISITOR_POINTS.key, strVisitorlPoints);
        jornadaContent.put(ResultMapper.MINUTES_URL.key, minutesUrl);

        return jornadaContent;
    }


    private String getParameterValueFromUrl(String paramName, String strUrl) throws IOException {

        URL url = new URL(strUrl);
        return getParameterValueFromUrl(paramName, url);
    }

    private String arrangeUrl(String strCandidateUrl) throws MalformedURLException {

        String strUrl = "";

        if (strCandidateUrl != null && !"".equals(strCandidateUrl) && !strCandidateUrl.contains("http://resultados.rfetm.es")) {

            if (strCandidateUrl.startsWith("/")) {
                strUrl = "http://resultados.rfetm.es" + strCandidateUrl;
            }
            else {
                strUrl = "http://resultados.rfetm.es/" + strCandidateUrl;
            }
        }

        return buildSeasonedURL(strUrl);
    }

    private String buildSeasonedURL(String url/*, Season season*/) throws MalformedURLException {
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

    private String getSelector(URL url) {
        return getSelector(url.toString());
    }

    private String getSelector(String url) {
        for (String[] selectorPair : selectors) {
            String regexp = selectorPair[0];

            if (Pattern.matches(regexp, url)) {
                return selectorPair[1];
            }
        }
        return null;
    }
}
