package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaState;
import org.albertsanso.tabletennis.model.State;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class StateToJpaStateMapper implements Function<State, JpaState> {
    @Override
    public JpaState apply(State state) {
        if (state == null) return null;
        JpaState jpaState = new JpaState(state.getCodeName(), state.getName());
        jpaState.setId(state.getId());
        return jpaState;
    }
}
