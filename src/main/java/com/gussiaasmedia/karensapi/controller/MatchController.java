package com.gussiaasmedia.karensapi.controller;

import com.gussiaasmedia.karensapi.model.GameServer;
import com.gussiaasmedia.karensapi.model.GameServerListResponse;
import com.gussiaasmedia.karensapi.service.GameServerRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController {

    private final GameServerRegistry gameServerRegistry;

    public MatchController(GameServerRegistry gameServerRegistry) {
        this.gameServerRegistry = gameServerRegistry;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from 2 Karens matchmaking!";
    }

    @GetMapping("/listServerStatus")
    public GameServerListResponse listServers() {
        return new GameServerListResponse(gameServerRegistry.getServers());
    }

    @PostMapping("/{matchID}/joinGame")
    public GameServer joinSession(@PathVariable String matchID) {
        return gameServerRegistry.joinServer(matchID);
    }

    @PostMapping("/createGame")
    public GameServer createSession() {
        return gameServerRegistry.createServer();
    }
}
