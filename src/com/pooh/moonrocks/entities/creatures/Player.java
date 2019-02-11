package com.pooh.moonrocks.entities.creatures;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.gfx.Assets;

import java.awt.*;

public class Player extends Creature {

    public Player(Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    } // **** end Player(Game, float, float) constructor ****

    @Override
    public void tick() {
        // Check for whether up, down, left, or right are pressed.
        getInput();
        // Where we actually change the x and y coordinate of the Creature.
        move();
        // Whenever the player's tick() method is called, center camera on this player.
        game.getGameCamera().centerOnEntity(this);
    }

    // This SETS the xMove and yMove variable if there's a directional key press.
    public void getInput() {
        // Everytime we call the getInput() method, we want to set the xMove and yMove variable to 0. Otherwise movement
        // will happen EVERYTIME (whether a key is pressed or not) tick() is called (because of the previous xMove and
        // yMove would still have a value).
        xMove = 0;
        yMove = 0;

        // Instead of directly affecting the x and y coordinates of our player, we're setting our xMove and yMove variables
        // equal to a certain speed (positive or negative, depending on the direction we should be moving along which axis).
        if (game.getKeyManager().up) {
            yMove = -speed;
        }
        if (game.getKeyManager().down) {
            yMove = speed;
        }
        if (game.getKeyManager().left) {
            xMove = -speed;
        }
        if (game.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        // We were applying the GameCamera offsets to the World's tiles, but haven't to the player yet. Doing it here.
        // Similar to what we did the the World class's render().
        g.drawImage(Assets.walkDown1, (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()),
                width, height, null);
    }

} // **** end Player class ****