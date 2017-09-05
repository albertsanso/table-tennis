package org.albertsanso.tabletennis.event;

@FunctionalInterface
public interface EventSubscriber<T extends DomainEvent> {
    void handle(T event);
}
