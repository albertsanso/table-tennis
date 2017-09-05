package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.Venue;

import java.util.List;

public interface VenueRepository {
    Venue save(Venue venue);
    void remove(Venue venue);
    Venue findById(Long id);
    List<Venue> findAll();
}
