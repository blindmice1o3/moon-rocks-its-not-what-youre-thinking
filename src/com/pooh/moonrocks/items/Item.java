package com.pooh.moonrocks.items;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

// (Will look almost the same as the Tile class)
public class Item {

    // HANDLER

        // (Similar to the Tile class's Tile[] array)
    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.woodItem, "woodItem", 0);
    public static Item waterItem = new Item(Assets.waterItem, "waterItem", 1);

    // CLASS

    public static final int ITEM_WIDTH = 32, ITEM_HEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    // If count EVER BECOMES the value of PICKED_UP constant (e.g. -1) we have to remove that item of the World and put
    // it into the player's inventory (which will be developed later).
    protected int x, y, count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;

        // This count variable is going to store the amount of this item in the Item object (e.g. Wood item... instead
        // of creating 50 new instances of wood, we can just have one instance of that wood Item and set the count equal
        // to 50 indicating that the player has 50 of them.
        count = 1;  // If count becomes -1 (PICKED_UP constant), it means have to remove Item from World, put into Inventory.

        // Our x and y haven't been set yet in the constructor. When we call createNew() it calls setPosition(), so we need
        // to set bounds.x and bounds.y inside of setPosition().
        bounds = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);

        // When we make an Item, we'll want to set the Item[] items array's element at index id equal to that item object.
        items[id] = this;
    } // **** end Item() constructor ****

    // ItemManager goes through every Item in the World in its tick() and removes if the Item's count variable is set
    // to -1 (PICKED_UP). But how do we know when to set the count variable to -1? If player's bounding rectangle
    // overlaps with the the current item's bounding rectangle, then that means the Item should be picked up (the player
    // has walked over the item. Every Item's tick() method is constantly being called by the ItemManager, the Item's tick()
    // is the perfect place to put the code to check if the Item should be picked up or not. And if it should be picked
    // up, all we have to do is set the count variable equal to -1 and everything will be handled for us.
    public void tick() {
        // If the boundary rectangle of the player (with offsets of 0f, 0f) INTERSECTS with the current Item's bounds...
        // that means the Item should be picked up.
        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
            pickedUp = true;  // The ItemManager will REMOVE THE item FROM THE WORLD for us when we walk over it!!!
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this); // THIS item instance is added
        }                                                                                   // to player's inventory.
    }

    // Items can be in 1 of 2 STATES:
    // (1) If in the game world (lying on the ground).
    // Calls the overloaded render(Graphics, int, int) and give it the Item's CURRENT x and y (INSTEAD OF THROUGH PARAMETERS).
    public void render(Graphics g) {
        // Didn't take in Handler object in constructor, so handler MAY BE null... must check!
        if (handler == null) {
            return;
        }
        render(g, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()));
    }

    // (2) If in the player's inventory.
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
    }

    // HELPER METHODS

    // Create a copy of the Item object that's at the x and y position passed in.
    public Item createNew(int x, int y) {
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    // GETTERS AND SETTERS

    public boolean isPickedUp() {
        return pickedUp;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
} // **** end Item class ****