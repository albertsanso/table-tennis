package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.Organization;

public interface OrganizationRepository {
    Organization save(Organization organization);
    void remove(Organization organization);
    Organization findById(Long id);
    Organization findByCodeName(String codeName);
}
