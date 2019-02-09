package com.pooh.moonrocks.states;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.entities.creatures.Player;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.tiles.Tile;

import java.awt.*;

public class GameState extends State {

    private Player player;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 100, 100);
    } // **** end GameState(Game) constructor ****

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
        Tile.tiles[2].render(g, 0, 0);
    }

} // **** end GameState class ****