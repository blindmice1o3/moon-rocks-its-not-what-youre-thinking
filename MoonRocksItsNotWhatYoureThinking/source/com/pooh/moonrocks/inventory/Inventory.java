package com.pooh.moonrocks.inventory;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.gfx.Text;
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

    private int invListCenterX = invX + 165,            // coordinates of center of the Item list.
                invListCenterY = invY + invHeight / 2 ;
    private int invListSpacing = 64;                    // number of pixels between Items listed in the inventoryScreen.
    private int invImageX = 450, invImageY = 60,        // coordinates to display selectedItem's Image.
                invImageWidth = 64, invImageHeight = 64;
    private int invCountX = 484, invCountY = 150;       // coordinates to display quantity (below selectedItem's Image).

    private int selectedItem = 0;                       // index of currently selected inventoryItem ArrayList<Item>.

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();

        addItem(Item.waterItem.createNew(5));
        addItem(Item.woodItem.createNew(3));

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

        // Get the displayed list to move around by the keyboard. Key VK_W and VK_S changes the INDEX of the currently
        // selected Item in the ArrayList<Item>. This by itself will produce a lot of error, need to make sure the
        // index is greater than 0 and less than the length of the item list.
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
            selectedItem--;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
            selectedItem++;
        }

        // Checks if we should LOOP around to the beginning of the ArrayList, or LOOP around to the end of the ArrayList.
        if (selectedItem < 0) {
            selectedItem = inventoryItems.size() - 1;   // -1 to switch it to be an index value rather than number of item.
        }
        else if (selectedItem >= inventoryItems.size()) {
            selectedItem = 0;
        }


        // TEST CODE: Checking to see if things are working properly.
        // This message will spam the console screen when inventory is TOGGLED ON, then stops spamming when TOGGLED OFF.
        //System.out.println("INVENTORY: ");
        //for (Item i: inventoryItems) {
        //    System.out.println(i.getName() + "   " + i.getCount());
        //}
    }

    public void render(Graphics g) {
        // The inventory screen is not always going be to on the screen the entire time, we check that the inventory
        // should actually be displayed and ticked.
        if (!active) {
            return;
        }
        // This is calls in EntityManager's render(Graphics) AFTER ALL THE ENTITYS in the game is looped/drawn.
        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

        // TEST CODE: After drawing the inventoryScreen, we draw all the TEXT on top of the inventoryScreen.
        //Text.drawString(g, "> Wood Item <", invListCenterX, invListCenterY, true, Color.WHITE, Assets.font28);

        // The length of our inventoryItems ArrayList<Item>.
        int len = inventoryItems.size();
        if (len == 0) { // If we have no Item, just return. Otherwise, we actually have to start displaying the Items.
            return;
        }
        // Starting at -2 because we have 2 rows to display BEFORE the center row, stopping < 3 because have 2 rows AFTER center.
        for (int i = -2; i < 3; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= len) {  // If index + i is less than 0 or greater than or
                continue;                                           // equal to length, we will skip the code once and
            }                                                       // continue with the for-loop.

            // To get the CENTER row item to stand out more.
            if (i == 0) {
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX,
                        invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
            } else {
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
                        invListCenterY + i * invListSpacing, true, Color.RED, Assets.font28);
            }
        }

        // After the for-loop, we just want to focus on the currently selected Item (we want to store it).
        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
        // Now to display the count variable of the selected Item.
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.YELLOW, Assets.font28);
    }

    // Inventory methods

    public void addItem(Item item) {
        // If we already have the item, it'll just add to the count of THAT item. If not, it'll add the item separately
        // to the list.
    //@@@@@ COMMENTED OUT THE FUNCTIONALITY OF STACKING ITEM'S count variable JUST TO TEST THE INVENTORY SCROLLING @@@@@
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

    public boolean isActive() {
        return active;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

} // **** end Inventory class ****