package org.albertsanso.tabletennis.event;

import java.util.List;

public interface EventBus {
    void publishEvents(final List<? extends DomainEvent> event);
    void subscribeTo(final Class<? extends DomainEvent> subscribedTo, final EventSubscriber subscriber);
}
