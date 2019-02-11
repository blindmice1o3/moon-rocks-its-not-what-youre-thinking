package com.pooh.moonrocks.states;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.entities.creatures.Player;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.tiles.Tile;
import com.pooh.moonrocks.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 100, 100);
        world = new World(game, "resource/worlds/world1.txt");
    } // **** end GameState(Game) constructor ****

    @Override
    public void tick() {
        // Before we tick() the player, we want to tick() the world first, that way all the world moving stuff happens
        // first.
        world.tick();
        player.tick();

        // Moving the GameCamera by 1 and 1 on both axis (so add 1 to the x and y offsets) for every tick.
        // This will give us the illusion of our camera moving.
            // Except we see an instant problem here, the camera is moving and our world is moving behind it so we can
            // see different areas of it, except our player is also moving with the camera... if we move the player the
            // camera is clearly not following the player around, and we want it to do that... so we have to implement
            // a few more things.
        //game.getGameCamera().move(1, 1);
            // Going back to the GameCamera class to create a method that'll basically CENTER the camera on an entity
            // and follow the entity around.
    }

    @Override
    public void render(Graphics g) {
        // Before we render the player, we want to render the world first, that way all the player will be drawn on top
        // of the world (and not behind/under it).
        world.render(g);
        player.render(g);
    }

} // **** end GameState class ****