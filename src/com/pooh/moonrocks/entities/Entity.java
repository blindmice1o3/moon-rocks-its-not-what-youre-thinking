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

    // A helper method that tests every Entity in our game, and see if it collides with THIS ENTITY.
    // The parameters are where we're trying to move to (it's the Creature class's xMove and yMove variables [future position].
    // We have not actually changed our x or y position yet, so we have the OFFSETS telling the collision box where we
    // will be moving to!
    // @@@ Called in Creature class's move() method. @@@
    public boolean checkEntityCollision(float xOffset, float yOffset) {
        for (Entity e: handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {   // One problem: whatever entity is calling this method, it's looping through every
                continue;           // Entity in the game, including ITSELF!!! This if-statement checks if the Entity e
            }                       // we're looping through is ITSELF, if so, CONTINUE ONTO the next Entity in the loop.

            // If the "Entity e" that we're looping through INTERSECTS our (THIS ENTITY's) collision-bound...
            // @@@@@ Using "intersects()" method. @@@@@   IF: (((RECTANGLE1))) .intersects() (((RECTANGLE2)))
            // If this is true, the 2 rectangles are overlapping and there is a collision between these 2 entities.
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true;
            }
        }
        // If this for-loop exits, there was no collision, and we can just return false.
        return false;
    }

    // A helper method for ENTITY-COLLISION detection.
    // It returns the bounding rectangle (or, essentially, the area around this Entity) (the COLLISION-BOX of the entity).
    // The parameters are where we're trying to move to (it's the Creature class's xMove and yMove variables [future position].
    // @@@ Called in Creature class's move() method. @@@
    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        // We have not actually changed our x or y position yet, so we have the OFFSETS (the future position) telling
        // the collision box where we will be moving to! (OFFSETS are 0f if the player isn't pushing direction-keys.)
        return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
    }

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