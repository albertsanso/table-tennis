package org.albertsanso.tabletennis.controller.rest;

import org.albertsanso.tabletennis.model.State;
import org.albertsanso.tabletennis.port.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api")
public class MainController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping("state/{id}")
    public ResponseEntity<State> getStateById(@PathVariable("id") Long id) {
        State state = stateRepository.findById(id);
        return new ResponseEntity<State>(state, HttpStatus.OK);
    }

    @GetMapping("states")
    public ResponseEntity<List<State>> getAllStates() {
        List<State> states = stateRepository.findAll();
        return new ResponseEntity<List<State>>(states, HttpStatus.OK);
    }
}
