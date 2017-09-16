package org.albertsanso.tabletennis.jpa.mappers;

import org.albertsanso.tabletennis.jpa.model.JpaPlayer;
import org.albertsanso.tabletennis.model.Player;

import java.util.function.Function;

public class JpaPlayerToPlayerMapper implements Function<JpaPlayer, Player> {
    @Override
    public Player apply(JpaPlayer jpaPlayer) {
        Player.PlayerBuilder builder = new Player.PlayerBuilder(
                jpaPlayer.getCodeName(),
                jpaPlayer.getName()
        );
        Player player = builder
                .withId(jpaPlayer.getId())
                .withExternalId(jpaPlayer.getExternalId())
                .create();
        return player;
    }
}
