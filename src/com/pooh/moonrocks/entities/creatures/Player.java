package com.pooh.moonrocks.entities.creatures;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.gfx.Assets;

import java.awt.*;

public class Player extends Creature {

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        // Entity had default bounding-box for collision detection, here it's more specific.
        // @@@ THESE VALUES WILL BE CUSTOMIZED TO FIT THE PLAYER OR CREATURE THAT we HAVE. @@@
            // We can't see this bounding box (collision detection) visually, so we'll do testing code in render().
        bounds.x = 16;
        bounds.y = 12;
        bounds.width = 32;
        bounds.height = 48;
    } // **** end Player(Handler, float, float) constructor ****

    @Override
    public void tick() {
        // Check for whether up, down, left, or right are pressed.
        getInput();
        // Where we actually change the x and y coordinate of the Creature.
        move();
        // Whenever the player's tick() method is called, center camera on this player.
        handler.getGameCamera().centerOnEntity(this);
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
        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        // We were applying the GameCamera offsets to the World's tiles, but haven't to the player yet. Doing it here.
        // Similar to what we did the the World class's render().
        g.drawImage(Assets.walkDown1, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()),
                width, height, null);

        // @@@ For TESTING PURPOSES we'll draw the visual of the bounding box (collision detection). @@@
        // Keep in mind that the bounds.x and bound.y is the starting point of pixels-shifted-into-the-image's x and y...
        // that's why there's the "x + " or "y + " in front of those arguments.
        g.setColor(Color.RED);
//        g.fillRect( (int)( x + bounds.x - handler.getGameCamera().getxOffset() ),
//                    (int)( y + bounds.y - handler.getGameCamera().getyOffset() ),
//                    bounds.width, bounds.height);
        // If we comment out the 4 bounds values in the Player's constructor, we'll see the default of the bounding box
        // is the full image of the player (i.e. the red filled rectangle covers the player's entire image).

        // !!! See Creature class for getting COLLISION DETECTION working !!!
    }

} // **** end Player class ****