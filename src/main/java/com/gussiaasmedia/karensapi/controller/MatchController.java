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
        System.out.println("joining match: " + matchID);
        return gameServerRegistry.joinServer(matchID);
    }

    @PostMapping("/createGame")
    public GameServer createSession() {
        System.out.println(".createGame callede");
        return gameServerRegistry.createServer();
    }

    @PostMapping("/freeSession/{sessionGuid}")
    public void freeSession(@PathVariable String sessionGuid)
    {
        System.out.println("freeing session: " + sessionGuid);
        gameServerRegistry.unRegisterServer(sessionGuid);
    }

    @PostMapping("/{matchID}/leaveGame")
    public void leaveSession(@PathVariable String matchID){
        gameServerRegistry.leaveServer(matchID);

    }

}
