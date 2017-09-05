package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.State;

public interface StateRepository {
    State save(State state);
    void remove(State state);
    State findById(Long id);
    State findByCodeName(String codeName);
}
