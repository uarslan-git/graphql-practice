package com.umut.graphql_exercise.service;

import com.umut.graphql_exercise.model.Player;
import com.umut.graphql_exercise.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {
    private List<Player> players = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findOne(int id) {
        return players.stream()
                .filter(player -> player.Id() == id).findFirst();
    }
    public Player create(String name, Team team){
         Player player = new Player(id.incrementAndGet(), name, team);
         players.add(player);
         return player;
    }
    public Player delete(Integer id) {
        Player player = players.stream().filter(c -> c.Id() == id).findFirst().orElse(null);
        players.remove(player);
        return player;
    }
    public Player update(Integer id, String name, Team team) {
        Player updatePlayer = new Player(id, name, team);
        Optional<Player> optional = players.stream().filter(c -> c.Id() == id).findFirst();
        if (optional.isPresent()) {
            Player player = optional.get();
            int index = players.indexOf(player);
            players.set(index, updatePlayer);
        } else {
            throw new IllegalArgumentException("Player not found");
        }
        return updatePlayer;
    }
    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(), "Jason", Team.DC));
        players.add(new Player(id.incrementAndGet(), "frank", Team.DC));
        players.add(new Player(id.incrementAndGet(), "huston", Team.GT));
        players.add(new Player(id.incrementAndGet(), "ellena", Team.MI));
    }
}
