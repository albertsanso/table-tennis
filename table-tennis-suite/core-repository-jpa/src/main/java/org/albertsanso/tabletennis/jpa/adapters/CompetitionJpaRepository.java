package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.model.Competition;
import org.albertsanso.tabletennis.port.CompetitionRepository;

import javax.inject.Named;

@Named
public class CompetitionJpaRepository implements CompetitionRepository {

    @Override
    public Competition save(Competition competition) {
        return null;
    }

    @Override
    public void remove(Competition team) {

    }

    @Override
    public Competition findById(Long id) {
        return null;
    }
}
