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

    }
}