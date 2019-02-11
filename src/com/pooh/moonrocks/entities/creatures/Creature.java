package com.pooh.moonrocks.entities.creatures;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.entities.Entity;

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
    public void moveX() {
        //x += xMove;   //was moved into this method from move() method... but now commented out for an if-else statement.

        // This will help us determine WHICH CORNERS WE NEED TO CHECK for a collision detection.
        if (xMove > 0) {    // If the amount we should move by is positive, we are MOVING RIGHT.

        } else if (xMove < 0) { // If xMove is a negative number, that means we are MOVING LEFT.

        }

    }

    public void moveY() {
        y += yMove;
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