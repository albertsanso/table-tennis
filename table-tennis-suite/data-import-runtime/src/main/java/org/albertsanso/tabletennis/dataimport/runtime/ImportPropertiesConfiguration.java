package org.albertsanso.tabletennis.dataimport.runtime;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix="import")
public class ImportPropertiesConfiguration {

    public static final String ORGANIZATIONS_ENTITY = "organizations";
    public static final String TEAMS_ENTITY = "teams";
    public static final String PLAYERS_ENTITY = "players";
    public static final String RESULTS_ENTITY = "results";
    public static final String MATCHES_ENTITY = "matches";

    private List<ImportEntityConfiguration> entities;

    public List<ImportEntityConfiguration> getEntities() { return entities; }

    public void setEntities(List<ImportEntityConfiguration> entities) { this.entities = entities; }

    public ImportEntityConfiguration getOrganizationImportConf() { return findEntityByKey(ORGANIZATIONS_ENTITY);}

    public ImportEntityConfiguration getTeamImportConf() { return findEntityByKey(TEAMS_ENTITY); }

    public ImportEntityConfiguration getPlayerImportConf() { return findEntityByKey(PLAYERS_ENTITY); }

    public ImportEntityConfiguration getResultImportConf() { return findEntityByKey(RESULTS_ENTITY); }

    public ImportEntityConfiguration getMatchImportConf() { return findEntityByKey(MATCHES_ENTITY); }

    private ImportEntityConfiguration findEntityByKey(String entityKey) {
        for (ImportEntityConfiguration currentConf : this.entities) {
            if (entityKey.equals(currentConf.getEntity())) {
                return currentConf;
            }
        }
        return null;
    }
}
