package com.pooh.moonrocks.items;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
        // (Will look almost the same as the Tile class)
    // HANDLER

        // (Similar to the Tile class's Tile[] array)
    public static Item[] items = new Item[256];
    public static Item waterItem = new Item(Assets.water, "water", 0);

    // CLASS

    public static final int ITEM_WIDTH = 32, ITEM_HEIGHT = 32, PICKED_UP = -1;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    // If count EVER BECOMES the value of PICKED_UP constant (e.g. -1) we have to remove that item of the World and put
    // it into the player's inventory (which will be developed later).
    protected int x, y, count;

    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;

        // This count variable is going to store the amount of this item in the Item object (e.g. Wood item... instead
        // of creating 50 new instances of wood, we can just have one instance of that wood Item and set the count equal
        // to 50 indicating that the player has 50 of them.
        count = 1;
    } // **** end Item() constructor ****

    public void tick() {

    }

    // Calls the overloaded render(Graphics, int, int) and give it the Item's CURRENT x and y (INSTEAD OF THROUGH PARAMETERS).
    public void render(Graphics g) {
        if (handler == null) {
            return;
        }
        render(g, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, Item.ITEM_WIDTH, Item.ITEM_HEIGHT, null);
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
    }

    // GETTERS AND SETTERS

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