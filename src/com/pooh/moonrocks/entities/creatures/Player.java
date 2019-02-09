package com.pooh.moonrocks.entities.creatures;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.gfx.Assets;

import java.awt.*;

public class Player extends Creature {

    private Game game;

    public Player(Game game, float x, float y) {
        super(x, y);
        this.game = game;
    } // **** end Player(float, float) constructor ****

    @Override
    public void tick() {
        // If the KeyManager's up variable is true, change the player's y-position.
        if (game.getKeyManager().up) {
            y -= 3;
        }
        if (game.getKeyManager().down) {
            y += 3;
        }
        if (game.getKeyManager().left) {
            x -= 3;
        }
        if (game.getKeyManager().right) {
            x += 3;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.walkDown1, (int)x, (int)y, null);
    }

} // **** end Player class ****