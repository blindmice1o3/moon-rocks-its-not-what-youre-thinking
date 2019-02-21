package com.pooh.moonrocks.inventory;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    // Hard-coded values for where (and how large) to display the inventoryScreen from Assets class.
    private int invX = 60, invY = 25, invWidth = 520, invHeight = 320;

    private int invListCenterX = invX + 171,
                invListCenterY = invY + invHeight / 2 + 5;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
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
        System.out.println("INVENTORY: ");
        for (Item i: inventoryItems) {
            System.out.println(i.getName() + "   " + i.getCount());
        }
    }

    public void render(Graphics g) {
        // The inventory screen is not always going be to on the screen the entire time, we check that the inventory
        // should actually be displayed and ticked.
        if (!active) {
            return;
        }
        // This is calls in EntityManager's render(Graphics) AFTER ALL THE ENTITYS in the game is looped/drawn.
        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
    }

    // Inventory methods

    public void addItem(Item item) {
        // If we already have the item, it'll just add to the count of THAT item. If not, it'll add the item separately
        // to the list.
        for (Item i: inventoryItems) {
            // If the item we're trying to add is already an item inside of our inventoryItems ArrayList... just increment count).
            if (i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount()); // the item in our inventory will increase by the count of the new item.
                return;                                     // and return right from here.
            }
        }
        // However, if we loop through all the Items in our inventory and DON'T ALREADY have that item...
        // Just add THAT item to the inventory.
        inventoryItems.add(item);
    }

    // GETTERS AND SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

} // **** end Inventory class ****