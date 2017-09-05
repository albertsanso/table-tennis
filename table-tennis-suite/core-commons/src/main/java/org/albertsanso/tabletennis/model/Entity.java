package org.albertsanso.tabletennis.model;

import org.albertsanso.tabletennis.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    private final List<DomainEvent> events;

    public Entity() {
        this.events = new ArrayList<>();
    }

    protected void publishEvent(DomainEvent event) {
        this.events.add(event);
    }

    public boolean hasEvents() {
        return !events.isEmpty();
    }

    public List<DomainEvent> getEvents() {
        return events;
    }
}
