package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.model.Team;
import org.albertsanso.tabletennis.port.TeamRepository;

import javax.inject.Named;

@Named
public class TeamJpaRepository implements TeamRepository {
    @Override
    public Team save(Team team) {
        return null;
    }

    @Override
    public void remove(Team team) {

    }

    @Override
    public Team findById(Long id) {
        return null;
    }
}
