package com.pooh.moonrocks.entities.creatures;

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

    public Creature(float x, float y, int width, int height) {
        super(x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    } // **** end Creature(float, float, int, int) constructor ****

    // This MOVES (i.e. actually changes) the x and y coordinates of the Creature. Used with subclass's getInput(), which
    // SETS the xMove and yMove variables (if no directional keys are pressed, xMove and yMove are set to 0).
    public void move() {
        // xMove and yMove are controlled by subclass (Player) when getInput() is called. xMove and yMove are 0 when NO
        // KEYS are pressed (i.e. when up, down, left, and right variables are all false... indication not pressed).
        x += xMove;
        y += yMove;
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