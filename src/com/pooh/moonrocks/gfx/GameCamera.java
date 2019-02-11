package com.pooh.moonrocks.gfx;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.entities.Entity;

public class GameCamera {

    private Game game;
    // These xOffset and yOffset have an x and y in them, which implies they relate to position functionality.
    private float xOffset, yOffset;

    public GameCamera(Game game, float xOffset, float yOffset){
        this.game = game;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    } // **** end GameCamera(Game, float, float) constructor ****

    // This method is going to set the x and y offsets to the correct numbers, that way the camera is actually centered
    // around whatever entity we pass into the centerOnEntity(Entity) method.
    // USED IN PLAYER class: whenever the player tick() method is called, center camera on this player.
    public void centerOnEntity(Entity e) {
        // Set the offset as the Entity's x position minus the Game screen's width divide by 2 (divide by 2 so it's centered).
        // We were centering the GameCamera on the Entity's top-left... need a slight mathematical adjustment for center.
        xOffset = e.getX() - game.getWidth() / 2 + (e.getWidth() / 2);
        yOffset = e.getY() - game.getHeight() / 2 + (e.getHeight() / 2);
    }

    public void move(float xAmount, float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;
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