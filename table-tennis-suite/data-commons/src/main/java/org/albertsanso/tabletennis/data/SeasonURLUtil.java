package org.albertsanso.tabletennis.data;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeasonURLUtil {

    public static boolean isSeasonedURL(String url) throws MalformedURLException {
        if (!"".equals(getSeasonFromURL(url))) {
            return true;
        }
        return false;
    }

    public static String buildSeasonedURL(String url, Season season) throws MalformedURLException {
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

    public static String getSeasonFromURL(String url) throws MalformedURLException {
        URL sURL = new URL(url);
        String path = sURL.getPath();
        String ret = "";

        Pattern p = Pattern.compile("/historico/(.*?)/");
        Matcher m = p.matcher(path);
        if (m.find() && m.groupCount() > 0) {
            ret = m.group(1);
        }

        return ret;
    }
}
