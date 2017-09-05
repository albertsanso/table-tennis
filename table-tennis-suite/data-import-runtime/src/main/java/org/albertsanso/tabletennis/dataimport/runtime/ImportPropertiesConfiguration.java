package org.albertsanso.tabletennis.dataimport.runtime;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix="import")
public class ImportPropertiesConfiguration {

    public static final String ORGANIZATIONS_ENTITY = "organizations";

    private List<ImportEntityConfiguration> entities;

    public List<ImportEntityConfiguration> getEntities() { return entities; }

    public void setEntities(List<ImportEntityConfiguration> entities) { this.entities = entities; }

    public ImportEntityConfiguration getOrganizationImportConf() { return findEntityByKey(ORGANIZATIONS_ENTITY);}

    private ImportEntityConfiguration findEntityByKey(String entityKey) {
        for (ImportEntityConfiguration currentConf : this.entities) {
            if (entityKey.equals(currentConf.getEntity())) {
                return currentConf;
            }
        }
        return null;
    }
}
