package com.pooh.moonrocks.states;

import com.pooh.moonrocks.entities.creatures.Player;
import com.pooh.moonrocks.gfx.Assets;

import java.awt.*;

public class GameState extends State {

    private Player player;

    public GameState() {
        player = new Player(100, 100);
    } // **** end GameState() constructor ****

    @Override
    public void tick() {
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }

} // **** end GameState class ****