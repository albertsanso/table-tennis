package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm;

import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.util.YamlResourceReader;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class URLQualificationsDepot {

    private YamlResourceReader yamlReader;
    private Map<Season, List<String>> seasonsUrls = new HashMap<Season, List<String>>();

    @Inject
    public URLQualificationsDepot(YamlResourceReader yamlReader) {
        this.yamlReader = yamlReader;
    }

    public List<String> getQualificationsUrlsBySeason(Season season) {
        return seasonsUrls.get(season);
    }

    public void readQualificationsUrls(String yamlQualificationsFilePath) throws IOException {

        //if (yamlQualificationsFilePath != null) {
            Map<Season, List<String>> urls = new HashMap<Season, List<String>>();

            List<Map<String, ?>> rawQualifications = null;
            try {
                rawQualifications = (List<Map<String, ?>>) yamlReader.readRawYamlFile(yamlQualificationsFilePath);
                for (Map<String, ?> seasonRow : rawQualifications) {
                    processQualificationsSeason(seasonRow);
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        //}
    }

    protected void processQualificationsSeason(Map<String, ?> seasonRow){
        String seasonKey = (String) seasonRow.get("season");
        Season season = Season.getByKey(seasonKey);

        List<Map<String, ?>> categories = (List<Map<String, ?>>) seasonRow.get("categories");
        for (Map<String, ?> category : categories) {
            processQualificationsCategory(category, season);
        }
    }

    protected void processQualificationsCategory(Map<String, ?> category, Season season) {
        List<String> urls = (List<String>) category.get("urls");

        if (seasonsUrls.containsKey(season)) {
            List<String> existingUrls = seasonsUrls.get(season);
            if (existingUrls != null && existingUrls.size() > 0) {
                urls.addAll(existingUrls);
            }
        }
        seasonsUrls.put(season, urls);
    }
}
