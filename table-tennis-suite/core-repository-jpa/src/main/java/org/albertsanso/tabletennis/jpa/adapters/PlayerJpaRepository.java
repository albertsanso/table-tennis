package org.albertsanso.tabletennis.jpa.adapters;

import org.albertsanso.tabletennis.jpa.mappers.JpaPlayerToPlayerMapper;
import org.albertsanso.tabletennis.jpa.mappers.PlayerToJpaPlayerMapper;
import org.albertsanso.tabletennis.jpa.model.JpaPlayer;
import org.albertsanso.tabletennis.jpa.repository.PlayerJpaRepositoryHelper;
import org.albertsanso.tabletennis.model.Player;
import org.albertsanso.tabletennis.port.PlayerRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PlayerJpaRepository implements PlayerRepository{

    private PlayerToJpaPlayerMapper playerToJpaPlayerMapper;
    private JpaPlayerToPlayerMapper jpaPlayerToPlayerMapper;
    private PlayerJpaRepositoryHelper playerJpaRepositoryHelper;

    @Inject
    public PlayerJpaRepository(PlayerToJpaPlayerMapper playerToJpaPlayerMapper, JpaPlayerToPlayerMapper jpaPlayerToPlayerMapper, PlayerJpaRepositoryHelper playerJpaRepositoryHelper) {
        this.playerToJpaPlayerMapper = playerToJpaPlayerMapper;
        this.jpaPlayerToPlayerMapper = jpaPlayerToPlayerMapper;
        this.playerJpaRepositoryHelper = playerJpaRepositoryHelper;
    }

    @Override
    public Player save(Player player) {
        JpaPlayer jpaPlayer = playerToJpaPlayerMapper.apply(player);
        jpaPlayer = playerJpaRepositoryHelper.save(jpaPlayer);
        return jpaPlayerToPlayerMapper.apply(jpaPlayer);
    }

    @Override
    public void remove(Player player) {
        playerJpaRepositoryHelper.delete(player.getId());
    }

    @Override
    public Player findById(Long id) {
        JpaPlayer jpaPlayer = playerJpaRepositoryHelper.findOne(id);
        Player player = jpaPlayerToPlayerMapper.apply(jpaPlayer);
        return player;
    }

    @Override
    public Player findByExternalId(String externalId) {
        JpaPlayer jpaPlayer = playerJpaRepositoryHelper.findByExternalId(externalId);
        Player player = jpaPlayerToPlayerMapper.apply(jpaPlayer);
        return player;
    }
}
