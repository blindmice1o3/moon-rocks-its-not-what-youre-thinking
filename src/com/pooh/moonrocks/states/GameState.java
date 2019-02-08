package com.pooh.moonrocks.states;

import com.pooh.moonrocks.gfx.Assets;

import java.awt.*;

public class GameState extends State {

    public GameState() {

    } // **** end GameState() constructor ****

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.walkDown1, 0, 0, null);
    }

} // **** end GameState class ****