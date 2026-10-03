package com.gussiaasmedia.karensapi.model;

import java.util.List;

public class GameServerListResponse {
    private List<GameServer> lines;
    public GameServerListResponse(List<GameServer> lines) {
        this.lines = lines;
    }

    public List<GameServer> getLines() {
        return lines;
    }

    public void setLines(List<GameServer> lines) {
        this.lines = lines;
    }
}
