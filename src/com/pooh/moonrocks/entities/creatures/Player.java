package com.pooh.moonrocks.entities.creatures;

import com.pooh.moonrocks.gfx.Assets;

import java.awt.*;

public class Player extends Creature {



    public Player(float x, float y) {
        super(x, y);
    } // **** end Player(float, float) constructor ****

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.walkDown1, (int)x, (int)y, null);
    }

} // **** end Player class ****