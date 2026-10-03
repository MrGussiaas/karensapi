package com.gussiaasmedia.karensapi.config;

import com.gussiaasmedia.karensapi.model.GameServer;
import com.gussiaasmedia.karensapi.service.GameServerRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameServerInitializer implements CommandLineRunner {

    private final GameServerRegistry gameServerRegistry;

    public GameServerInitializer(GameServerRegistry gameServerRegistry) {
        this.gameServerRegistry = gameServerRegistry;
    }

    @Override
    public void run(String... args) {

        gameServerRegistry.registerServer(
                new GameServer("server-001", 27777, 2)
        );

        gameServerRegistry.registerServer(
                new GameServer("server-002", 27778, 2)
        );

        gameServerRegistry.registerServer(
                new GameServer("server-003", 27779, 2)
        );
    }
}