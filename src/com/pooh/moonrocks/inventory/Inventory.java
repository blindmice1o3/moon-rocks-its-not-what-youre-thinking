package com.pooh.moonrocks.inventory;

import com.pooh.moonrocks.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Inventory {

    private Handler handler;
    private boolean active = false;

    public Inventory(Handler handler) {
        this.handler = handler;
    } // **** end Inventory(Handler) constructor ****

    public void tick() {
        // Check if the key that we're assigning to TOGGLE THE INVENTORY has been pressed... if so, !!!TOGGLE!!! active.
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;   // @@@ INVERTING whatever the value is @@@ (false to true, true to false)
        }                       // Essentially turning the inventory screen on and off (graphically displaying in next video).

        // The inventory screen is not always going be to on the screen the entire time, we check that the inventory
        // should actually be displayed and ticked. However we need a way to see if it is on (see lines ABOVE).
        if (!active) {
            return;
        }

        // Checking to see if things are working properly.
        // This message will spam the console screen when inventory is TOGGLED ON, then stops spamming when TOGGLED OFF.
        System.out.println("Inventory working.");
    }

    public void render(Graphics g) {
        // The inventory screen is not always going be to on the screen the entire time, we check that the inventory
        // should actually be displayed and ticked.
        if (!active) {
            return;
        }


    }

    // GETTERS AND SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

} // **** end Inventory class ****