package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.JpaStateToStateMapper;
import org.albertsanso.tabletennis.jpa.mappers.StateToJpaStateMapper;
import org.albertsanso.tabletennis.jpa.model.JpaState;
import org.albertsanso.tabletennis.jpa.repository.StateJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.State;
import org.albertsanso.tabletennis.port.StateRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class StateJpaRepository implements StateRepository {

    private StateJpaRepositoryHelper stateJpaRepositoryHelper;

    private JpaStateToStateMapper jpaStateToStateMapper;
    private StateToJpaStateMapper stateToJpaStateMapper;

    @Inject
    public StateJpaRepository(StateJpaRepositoryHelper stateJpaRepositoryHelper, JpaStateToStateMapper jpaStateToStateMapper, StateToJpaStateMapper stateToJpaStateMapper) {
        this.stateJpaRepositoryHelper = stateJpaRepositoryHelper;
        this.jpaStateToStateMapper = jpaStateToStateMapper;
        this.stateToJpaStateMapper = stateToJpaStateMapper;
    }

    public State save(State state) {
        JpaState jpaState = stateToJpaStateMapper.apply(state);
        jpaState = stateJpaRepositoryHelper.save(jpaState);
        return jpaStateToStateMapper.apply(jpaState);
    }

    public void remove(State state) {}

    public State findById(Long id) {
        JpaState jpaState = stateJpaRepositoryHelper.findOne(id);
        State state = jpaStateToStateMapper.apply(jpaState);
        return state;
    }

    @Override
    public State findByCodeName(String codeName) {
        JpaState jpaState = stateJpaRepositoryHelper.findByCodeName(codeName);
        State state = jpaStateToStateMapper.apply(jpaState);
        return state;
    }

    @Override
    public List<State> findAll() { return mapList(stateJpaRepositoryHelper.findAll()); }

    private List<State> mapList(List<JpaState> list) {
        List<State> states = new ArrayList<>();
        for (JpaState jpaState : list) {
            State state = jpaStateToStateMapper.apply(jpaState);
            states.add(state);
        }
        return states;
    }
}
