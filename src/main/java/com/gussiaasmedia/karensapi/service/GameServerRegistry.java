package com.gussiaasmedia.karensapi.service;

import com.gussiaasmedia.karensapi.model.GameServer;
import com.gussiaasmedia.karensapi.model.GameServerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServerRegistry {

    private final Map<String, GameServer> servers = new ConcurrentHashMap<>();
    private final RestClient restClient;
    public GameServerRegistry(@Value("${gameserver.manager.url}") String managerUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(managerUrl)
                .build();
    }

    public void registerServer(GameServer server) {
        servers.put(server.getServerId(), server);
    }

    public GameServer getServer(String serverId) {
        return servers.get(serverId);
    }

    public List<GameServer> getServers() {
        return new ArrayList<>(servers.values());
    }

    public void unRegisterServer(String sessionGuid){
        servers.remove((sessionGuid));
    }

    public GameServer createServer(){
        for (GameServer server : servers.values()) {
            if (server.getCurrentPlayers() <= 0) {
                return server;
            }
        }

        int nextAvailablePort = determineNextPort();
        System.out.println("te next port is: " + nextAvailablePort);
        if(nextAvailablePort <= 0){
            return null;
        }
        try {
            GameServerResponse managerResponse = restClient.post()
                    .uri("/server/start/{port}", nextAvailablePort)
                    .retrieve()
                    .body(GameServerResponse.class);
            System.out.println("the manager response is: " + managerResponse + " with guid: " + managerResponse.getSessionGuid());
            if (managerResponse != null && managerResponse.getSessionGuid() != null) {
                GameServer newServer = new GameServer(managerResponse.getSessionGuid(), managerResponse.getPort(), 2);
                newServer.setServerId(managerResponse.getSessionGuid());
                newServer.setPort(managerResponse.getPort());
                newServer.setCurrentPlayers(0);
                newServer.setMaxPlayers(2);

                registerServer(newServer);
                System.out.println("returning new server guid: " + newServer.getServerId());
                return newServer;
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.err.println("Failed to reach Game Server Manager service: " + e.getMessage());
        }

        return null;
    }

    private int determineNextPort() {
        return restClient.get()
                .uri("/server/reservePort")
                .retrieve()
                .body(Integer.class);
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

    public void leaveServer(String matchID){
        GameServer server = servers.get(matchID);
        if(server == null){
            return;
        }

        if (server.getCurrentPlayers() <= 0) {
            return;
        }
        server.setCurrentPlayers(server.getCurrentPlayers() - 1);
    }
}