package com.gussiaasmedia.karensapi.model;
public class GameServer {

    private String serverId;
    private int port;
    private int maxPlayers;
    private int currentPlayers;

    public GameServer(String serverId, int port, int maxPlayers) {
        this.serverId = serverId;
        this.port = port;
        this.maxPlayers = maxPlayers;
        this.currentPlayers = 0;
    }

    public String getServerId() {
        return serverId;
    }

    public int getPort() {
        return port;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}