package com.pooh.moonrocks.states;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.entities.creatures.Player;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.tiles.Tile;
import com.pooh.moonrocks.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    // Testing the World class
    private World world;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 100, 100);
        // Testing the World class
        world = new World("");
    } // **** end GameState(Game) constructor ****

    @Override
    public void tick() {
        // Testing the World class. Before we tick() the player, we want to tick() the world first, that way all the
        // world moving stuff happens first.
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        // Testing the World class. Before we render the player, we want to render the world first, that way all the
        // player will be drawn on top of the world (and not behind/under it).
        world.render(g);

        player.render(g);
    }

} // **** end GameState class ****