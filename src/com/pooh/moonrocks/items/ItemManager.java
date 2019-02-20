package com.pooh.moonrocks.items;

import com.pooh.moonrocks.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

// Will look similar to the EntityManager class.
// This is NOT the Inventory... this is only going to store Items that are IN THE GAME (LYING ON THE GROUND).
public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<Item>();
    } // **** end ItemManager(Handler) constructor ****

    public void tick() {    // Like the EntityManager... we're going to use an Iterator.
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            i.tick();
            // If the player has picked up that Item, we need to remove it from World and add it to Inventory.
            if (i.getCount() == Item.PICKED_UP) {
                it.remove();    // This removes it from the List (the game world). We haven't gotten to Inventory yet.
            }
        }
    }

    public void render(Graphics g) {
        for (Item i: items) {   // Item objects have overloaded render() methods...
            i.render(g);        // Using the Item's render() method that has only 1 parameter because we want it to use
        }                       // the item's x and y position in the world.
    }

    public void addItem(Item i) {
        // We want to set the handler of that item to the handler that this class has because items have their handler
        // as null to begin with, so whenever we add an Item to the ItemManager we want to make sure we set the handler
        // object of that item so that it's not null SO THAT IT'LL ACTUALLY RENDER TO THE SCREEN (see Item class's render()).
        i.setHandler(handler);
        items.add(i);
    }

    // GETTERS AND SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

} // **** end ItemManager class ****