package com.pooh.moonrocks.entities.creatures;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.entities.Entity;
import com.pooh.moonrocks.tiles.Tile;

import java.awt.*;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    } // **** end Creature(Handler, float, float, int, int) constructor ****

    // This MOVES (i.e. actually changes) the x and y coordinates of the Creature. Used with subclass's getInput(), which
    // SETS the xMove and yMove variables (if no directional keys are pressed, xMove and yMove are set to 0).
    public void move() {
        // xMove and yMove are controlled by subclass (Player) when getInput() is called. xMove and yMove are 0 when NO
        // KEYS are pressed (i.e. when up, down, left, and right variables are all false... indication not pressed).

        moveX();
        moveY();


        // The following was a previous version. It's now being taken care of by the 2 new methods moveX() and moveY().
        //x += xMove;
        //y += yMove;
    }

    // !!! 2 new methods to get COLLISION DETECTION working !!!
    public void moveX() {   // Check for collision (solid tiles) when moving horizontally.
        // tx = our x position plus xMove (where we're trying to moving to), then we have to get to the very edge of our
        // bounding box (which means we have to add the bounds.x coordinate [the offset of the image]), and because
        // we're moving right we need to check the two upper and lower right corners of our bounding box (which
        // means we have to add bound.width to it).
        // There are many ways to do this, including using for-loops and more math, but this is what's best to learn at first.
        // This gets us position in pixels, but we want it in terms of tiles (so divide by Tile.TILE_WIDTH).
        // This gets us the x coordinate of the tile we're trying to move into.

        if (xMove > 0) {    // If the amount we should move by is positive, we are @@@@@@@ MOVING RIGHT @@@@@@@.
            // tx means temporary x (x coordinate of the tile we're trying to move into).
            int tx = (int)(x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

            // Here's the collision stuff. IF THE TILE WE'RE TRYING TO MOVE INTO is not SOLID then we're good to move.
            // The y parameter could be 1 of 2 thing (upper or lower right) (also need in terms of tile).
                        // If there's no collision in the upcoming tile, move the player.
            if ( !collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILE_HEIGHT) &&                      //upper-right
                    !collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILE_HEIGHT) ) {  //lower-right
                x += xMove;
            } else {    // Else there will be a collision. @@@ video23: Perfect Collision @@@ moving right
                // We going to COMPLETELY RESET the x position of the player (in a way that the bounding box is lined
                // directly up with the tile we tried to move into. That way (no matter what), we'll be standing right
                // next to the tile if there was a collision.
                    // Set the Creature's x-coordinate to the temporary x variable (tx) times Tile.TILE_WIDTH to convert
                    // it into pixel coordinate.
                        // We have to ALIGN the edge of the BOUNDING BOX with the tile (instead of the edge of the image).
                        // All we'll have to do is substract the x-coordinate AND width of our boundary.
                    // This leaves a glitch where we can't move vertical in this state, but fix by minusing 1 pixel.
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) { // If xMove is a negative number, that means we are @@@@@@@ MOVING LEFT @@@@@@@.
            // tx means temporary x (x coordinate of the tile we're trying to move into).
            int tx = (int)(x + xMove + bounds.x) / Tile.TILE_WIDTH;     // Moving left doesn't need bound.width.

            // Here's the collision stuff. IF THE TILE WE'RE TRYING TO MOVE INTO is not SOLID then we're good to move.
            // The y parameter could be 1 of 2 thing (upper or lower left) (also need in terms of tile).
            if ( !collisionWithTile(tx, (int)(y + bounds.y) / Tile.TILE_HEIGHT) &&                      //upper-left
                    !collisionWithTile(tx, (int)(y + bounds.y + bounds.height) / Tile.TILE_HEIGHT) ) {  //lower-left
                x += xMove;
            } else {    // Else there will be a collision. @@@ video23: Perfect Collision @@@ moving left
                // Convert to pixel coordinate. Add the width of one tile to move player out of the isSolid() tile then
                // subtract the x-portion of bounding box. This aligns the player's bounding box's left edge to the tile.
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }

    }

    public void moveY() {   // Check for collision (solid tiles) when moving vertically.
        if (yMove < 0) { // If yMove is negative, we're actually moving @@@@@@@ UP @@@@@@@ the screen.
            // ty means temporary y (y coordinate of the tile we're trying to move into).
            int ty = (int)(y + yMove + bounds.y) / Tile.TILE_HEIGHT; // Top portion of bounding box.

            if ( !collisionWithTile((int)(x + bounds.x) / Tile.TILE_WIDTH, ty) &&                       //upper-left
                    !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty) ) {    //upper-right
                y += yMove;
            } else {    // Else there will be a collision. @@@ video23: Perfect Collision @@@ moving up
                // Convert to pixel coordinate. Add Tile.TILE_HEIGHT to get us beside it and subtract bounds.y.
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        } else if (yMove > 0) { // If yMove is positive, we're actually moving @@@@@@@ DOWN @@@@@@@ the screen.
            // ty means temporary y (y coordinate of the tile we're trying to move into).
            int ty = (int)(y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT; // Lower portion of bounding box.

            if ( !collisionWithTile((int)(x + bounds.x) / Tile.TILE_WIDTH, ty) &&                       //lower-left
                    !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty) ) {    //lower-right
                y += yMove;
            } else {    // Else there will be a collision. @@@ video23: Perfect Collision @@@ moving down
                // Convert to pixel coordinate. Subtract out the bounds y offset and bounds height.
                // This leaves a glitch if we try to move horizontally from this position, but fix by minusing 1 pixel.
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    // HELPER METHOD TO MAKE collision detection EASIER
    protected boolean collisionWithTile(int x, int y) {
        // This method takes in some-tile-x and some-tile-y coordinates and if it's solid return true if not return false.
        // (i.e. calling the isSolid() method of whatever tile we are specifying by x and y.
        return handler.getWorld().getTile(x, y).isSolid();
    }

    // GETTERS and SETTERS

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getXMove() {
        return xMove;
    }

    public void setXMove(float xMove) {
        this.xMove = xMove;
    }

    public float getYMove() {
        return yMove;
    }

    public void setYMove(float yMove) {
        this.yMove = yMove;
    }

} // **** end Creature abstract-class ****