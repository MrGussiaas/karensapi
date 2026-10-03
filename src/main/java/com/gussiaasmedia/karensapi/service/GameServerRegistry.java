package com.gussiaasmedia.karensapi.service;

import com.gussiaasmedia.karensapi.model.GameServer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServerRegistry {

    private final Map<String, GameServer> servers = new ConcurrentHashMap<>();

    public void registerServer(GameServer server) {
        servers.put(server.getServerId(), server);
    }

    public GameServer getServer(String serverId) {
        return servers.get(serverId);
    }

    public List<GameServer> getServers() {
        return new ArrayList<>(servers.values());
    }

    public GameServer createServer(){
        for(GameServer server : servers.values()){
            if(server.getCurrentPlayers() <= 0){
                return server;
            }
        }
        return null;
    }

    public GameServer joinServer(String matchID){
        GameServer server = servers.get(matchID);
        if(server == null){
            return null;
        }

        if (server.getCurrentPlayers() >= server.getMaxPlayers()) {
            return null;
        }
        server.setCurrentPlayers(server.getCurrentPlayers() + 1);
        System.out.println("joined a match on server: " + matchID + " player count: " + server.getCurrentPlayers());
        return server;
    }
}