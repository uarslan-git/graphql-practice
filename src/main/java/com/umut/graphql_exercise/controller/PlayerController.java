package com.umut.graphql_exercise.controller;

import com.umut.graphql_exercise.model.Player;
import com.umut.graphql_exercise.model.Team;
import com.umut.graphql_exercise.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll(){
        return playerService.findAll();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument int id){
        return playerService.findOne(id);
    }

    @MutationMapping
    public Player create(@Argument String name, @Argument Team team){
        return playerService.create(name, team);
    }
    @MutationMapping
    public Player update(@Argument int id, @Argument String name, @Argument Team team){
        return playerService.update(id, name, team);
    }
    @MutationMapping
    public Player delete(@Argument int id){
        return playerService.delete(id);
    }
}
