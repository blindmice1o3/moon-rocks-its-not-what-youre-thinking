package com.pooh.moonrocks.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Like our base-Entity class, this will have all the core Menu-related objects (like buttons, sliders, etc) that we'll
 * use in the game. In this main base-class of all of our UI objects (buttons, sliders, etc), we made it so the methods
 * tick(), render(Graphics), and onClick() will be required by all its subclasses (they don't actually have to add
 * functionality to those methods, but will at the very least require the method signature and an empty body).
 */
public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;  // Since UI object will not be moving around a lot...
    protected boolean hovering = false;

    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int)x, (int)y, width, height);  // This is a Rectangle that ENCOMPASSES the UI object.
    } // **** end UIObject() constructor ****

    // EVERY UIObject (its subclasses) WILL BE ABLE TO RECOGNIZE the
    // following method calls: tick(), render(Graphic), and onClick().

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    // HELPER METHODS

    // When we move the mouse, we need to check if the user's mouse is currently hovering over this UI object (if we have
    // a button, we have to check if the user's mouse is hovering over that button or not, if it is, we'll set the
    // hovering variable equal to true... WHICH WE'LL USE TO CHANGE THE IMAGE THAT WE RENDER, if not, we'll set hovering
    // to false).
    // So if the object CONTAINS the mouse point (if the mouse is in the object's BOUNDING RECTANGLE).
    public void onMouseMove(MouseEvent e) {
        if (bounds.contains(e.getX(), e.getY())) {
            hovering = true;
        } else {
            hovering = false;
        }
    }

    // A mouse released WHEN IT'S HOVERING OVER A BUTTON means that button has been clicked.
    public void onMouseRelease(MouseEvent e) {
        if (hovering) {
            onClick();
        }
    }

    // GETTERS AND SETTERS

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

    public boolean getHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

} // **** end UIObject class ****