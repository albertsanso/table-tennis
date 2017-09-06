package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.Team;

public interface TeamRepository {
    Team save(Team team);
    void remove(Team team);
    Team findById(Long id);
}
