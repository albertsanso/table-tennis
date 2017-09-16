package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaPlayer;
import org.albertsanso.tabletennis.model.Player;

import java.util.function.Function;

public class PlayerToJpaPlayerMapper implements Function<Player, JpaPlayer> {
    @Override
    public JpaPlayer apply(Player player) {
        JpaPlayer jpaPlayer = new JpaPlayer(
                player.getCodeName(),
                player.getName()
        );
        jpaPlayer.setId(player.getId());
        jpaPlayer.setExternalId(player.getExternalId());
        return jpaPlayer;
    }
}
