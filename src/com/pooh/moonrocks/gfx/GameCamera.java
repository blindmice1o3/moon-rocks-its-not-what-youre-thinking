package com.pooh.moonrocks.gfx;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.entities.Entity;
import com.pooh.moonrocks.tiles.Tile;

public class GameCamera {

    private Handler handler;
    // These xOffset and yOffset have an x and y in them, which implies they relate to position functionality.
    private float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    } // **** end GameCamera(Handler, float, float) constructor ****

    // Will check if the camera is currently showing a blank space or not, and if it is we're going to fix it so there
    // is no blank space.
    // The x-offset is equals to 0 at the edge of our map (the left side of the world bordering the blank space).
    // All we have to do is check if the x-offset is less than 0 (we're showing blank space), and RESET it back to 0.
    // The player can still move around, but the camera will stop scrolling pass the edge of the world.
    public void checkBlankSpace() {
        // Checks left edge of world.
        if (xOffset < 0) {
            xOffset = 0;
        }
        // Check right edge of world.
        // Check if x-offset greater than the world's width (in terms of pixels) minus the width of our window.
        else if (xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
            xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }

        // Checks top edge of world.
        if (yOffset < 0) {
            yOffset = 0;
        }
        // Check bottom edge of world.
        else if (yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()) {
            yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
        }
    }

    // This method is going to set the x and y offsets to the correct numbers, that way the camera is actually centered
    // around whatever entity we pass into the centerOnEntity(Entity) method.
    // USED IN PLAYER class: whenever the player tick() method is called, center camera on this player.
    public void centerOnEntity(Entity e) {
        // Set the offset as the Entity's x position minus the Game screen's width divide by 2 (divide by 2 so it's centered).
        // We were centering the GameCamera on the Entity's top-left... need a slight mathematical adjustment for center.
        xOffset = e.getX() - handler.getWidth() / 2 + (e.getWidth() / 2);
        yOffset = e.getY() - handler.getHeight() / 2 + (e.getHeight() / 2);
        checkBlankSpace();
    }

    // This method actually moves the camera.
    public void move(float xAmount, float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

} // **** end GameCamera class ****