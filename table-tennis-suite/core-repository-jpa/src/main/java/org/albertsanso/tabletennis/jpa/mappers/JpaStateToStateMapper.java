package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaState;
import org.albertsanso.tabletennis.model.State;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class JpaStateToStateMapper implements Function<JpaState, State> {
    @Override
    public State apply(JpaState jpaState) {
        if (jpaState == null) return null;

        State.StateBuilder builder = new State.StateBuilder(jpaState.getCodeName(), jpaState.getName());
        State state = builder
                .withId(jpaState.getId())
                .create();
        return state;
    }
}
