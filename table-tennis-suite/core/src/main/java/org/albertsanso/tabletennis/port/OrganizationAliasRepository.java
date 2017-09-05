package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.OrganizationAlias;

public interface OrganizationAliasRepository {
    OrganizationAlias save(OrganizationAlias organizationAlias);
    void remove(OrganizationAlias organizationAlias);
    OrganizationAlias findById(Long id);
}
