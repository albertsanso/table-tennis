package org.albertsanso.tabletennis.port;

import org.albertsanso.tabletennis.model.Player;

public interface PlayerRepository {
    Player save(Player player);
    void remove(Player player);
    Player findById(Long id);
    Player findByExternalId(String externalId);
}
