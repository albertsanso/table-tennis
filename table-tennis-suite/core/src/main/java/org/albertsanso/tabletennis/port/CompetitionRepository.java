package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.Competition;

public interface CompetitionRepository {
    Competition save(Competition competition);
    void remove(Competition team);
    Competition findById(Long id);
}
