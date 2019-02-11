package com.pooh.moonrocks.entities;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.Handler;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    // Collision detection: checking a Rectangle's coordinates relative to the Player image's x, y, width, and height.
    protected Rectangle bounds;     // bounds stands for collision-bounds.

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // By default, the bounding box will have the exact same size as the Entity's image (the upper-left of the
        // image with NO offsets, and its image's width and height).
        bounds = new Rectangle(0, 0, width, height);
    } // **** end Entity(Handler, float, float, int, int) constructor ****

    public abstract void tick();

    public abstract void render(Graphics g);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

} // **** end Entity abstract-class ****