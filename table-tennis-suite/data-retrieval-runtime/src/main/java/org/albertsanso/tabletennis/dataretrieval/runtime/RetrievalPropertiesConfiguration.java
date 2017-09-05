package org.albertsanso.tabletennis.dataretrieval.runtime;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix="retrieval")
public class RetrievalPropertiesConfiguration {

    public static final String ORGANIZATIONS_ENTITY = "organizations";

    private List<RetrievalEntityConfiguration> entities;

    public List<RetrievalEntityConfiguration> getEntities() {
        return entities;
    }

    public void setEntities(List<RetrievalEntityConfiguration> entities) {
        this.entities = entities;
    }

    public RetrievalEntityConfiguration getOrganizationRetrievalConf() {
        return findEntityByKey(ORGANIZATIONS_ENTITY);
    }

    private RetrievalEntityConfiguration findEntityByKey(String entityKey) {
        for (RetrievalEntityConfiguration currentConf : this.entities) {
            if (entityKey.equals(currentConf.getEntity())) {
                return currentConf;
            }
        }
        return null;
    }
}
