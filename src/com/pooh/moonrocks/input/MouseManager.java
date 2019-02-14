package com.pooh.moonrocks.input;

import com.pooh.moonrocks.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// Main things we're going to do for now:
// Keep track of whether the LEFT or RIGHT mouse button is being pressed, and also the current position of our mouse
// in the game window.
public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private UIManager uiManager;

    public MouseManager() {

    } // **** end MouseManager() constructor ****

    public void setUIManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    // GETTER METHODS

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    // IMPLEMENTED METHODS

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {          // BUTTON1 is the LEFT mouse button.
            leftPressed = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {   // BUTTON3 is the RIGHT mouse button.
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {          // BUTTON1 is the LEFT mouse button.
            leftPressed = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {   // BUTTON3 is the RIGHT mouse button.
            rightPressed = false;
        }

        // Don't want to force the game to have a UIManager at all time, but when it does, we want a way to move the
        // MouseEvent on to the UIManager.
        if (uiManager != null) {
            uiManager.onMouseRelease(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        // Don't want to force the game to have a UIManager at all time, but when it does, we want a way to move the
        // MouseEvent on to the UIManager.
        if (uiManager != null) {
            uiManager.onMouseMove(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

} // **** end MouseManager class ****