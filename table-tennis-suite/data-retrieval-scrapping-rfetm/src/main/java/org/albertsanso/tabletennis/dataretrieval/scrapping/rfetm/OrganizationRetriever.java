package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm;

import org.albertsanso.tabletennis.data.OrganizationKeys;
import org.albertsanso.tabletennis.data.Season;
import org.albertsanso.util.SimpleCsvMapWriter;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
public class OrganizationRetriever {

    private URLQualificationsDepot organizationsUrls;
    private SimpleCsvMapWriter organizationWriter;

    private OrganizationInfoScratcher organizationInfoScratcher;
    private OrganizationUrlScratcher organizationUrlScratcher;
    private List<Season> organizationSeasonsList;
    private QualificationsInfoScratcher qualificationsInfoScratcher;

    private boolean isHeaderWritten = false;

    @Inject
    public OrganizationRetriever(
            URLQualificationsDepot organizationsUrls,
            SimpleCsvMapWriter writer,
            OrganizationInfoScratcher organizationInfoScratcher,
            OrganizationUrlScratcher organizationUrlScratcher,
            QualificationsInfoScratcher qualificationsInfoScratcher,
            List<Season> seasonsList) throws IOException {
        this.organizationsUrls = organizationsUrls;
        this.organizationWriter = writer;
        this.organizationInfoScratcher = organizationInfoScratcher;
        this.organizationUrlScratcher = organizationUrlScratcher;
        this.qualificationsInfoScratcher = qualificationsInfoScratcher;
        this.organizationSeasonsList = seasonsList;
    }

    private void resetWrittenHeaderCheck() {
        this.isHeaderWritten = false;
    }

    private void setWrittenHeaderCheck() {
        this.isHeaderWritten = true;
    }

    private boolean isWrittenHeaderCheck() {
        return this.isHeaderWritten;
    }

    public void retrieveOrganizations() throws IOException {
        for (Season currentSeason : organizationSeasonsList) {
            retrieveOrganizationsBySeason(currentSeason);
        }
        resetWrittenHeaderCheck();
    }

    public void retrieveOrganizationsBySeason(Season season) throws IOException {
        List<String> seasonQualificationsUrls = organizationsUrls.getQualificationsUrlsBySeason(season);
        for (String seasonedURL : seasonQualificationsUrls) {

            System.out.println("processing: "+seasonedURL);
            Map<String, String> qualificationsInfoMappings = qualificationsInfoScratcher.scratch(seasonedURL, season);
            List<String> organizationsUrls = organizationUrlScratcher.scratch(seasonedURL, season);

            List<Map<String, String>> mappings = new ArrayList<Map<String, String>>();
            for (String organizationUrl : organizationsUrls) {
                Map<String, String> mapping = organizationInfoScratcher.scratch(organizationUrl, season);
                mapping.putAll(qualificationsInfoMappings);
                mappings.add(mapping);
            }

            processMappingsBySeason(mappings, season);
        }
    }

    private void processMappingsBySeason(List<Map<String, String>> mappings, Season season) throws IOException {
        if (!isWrittenHeaderCheck()) {
            processMappingsHeaderBySeason(mappings);
            setWrittenHeaderCheck();
        }

        for (Map<String, String> mapping : mappings) {
            mapping.put(OrganizationKeys.SEASON.key, season.seasonKey);
            processMapping(mapping);
        }
    }

    private void processMappingsHeaderBySeason(List<Map<String, String>> mappings) throws IOException {
        if (mappings != null && mappings.size() > 0) {
            Map<String, String> headerCandidate = mappings.get(0);
            headerCandidate.put(OrganizationKeys.SEASON.key, OrganizationKeys.SEASON.key);
            organizationWriter.writeHeader(mappings.get(0));
        }
    }

    private void processMapping(Map<String, String> mapping) throws IOException {
        if (mapping != null && mapping.size() > 0) {
            organizationWriter.writeValues(mapping);
        }
    }
}
