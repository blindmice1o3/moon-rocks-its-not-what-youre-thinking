package com.pooh.moonrocks;

import com.pooh.moonrocks.gfx.GameCamera;
import com.pooh.moonrocks.input.KeyManager;
import com.pooh.moonrocks.worlds.World;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    } // **** end Handler(Game) constructor ****

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

} // **** end Handler class ****