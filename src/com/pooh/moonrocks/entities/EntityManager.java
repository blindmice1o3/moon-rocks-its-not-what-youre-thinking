package com.pooh.moonrocks.entities;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        // Negative number if a should be rendered BEFORE b (a is smaller, closer to 0, top of the screen).
        // Positive number if a should be rendered AFTER b (a is larger, closer to bottom of screen).
        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
                return -1;
            } else {
                return 1;
            }
        }

        // @@@ SHOULD be checking based on BOTTOM y-coordinate of bounding-box rather than the top-y. @@@
        // That's why + a.getHeight().
    };

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    } // **** end EntityManager(Handler, Player) constructor ****

    public void tick() {
        // video32: Item Drop. Updated way of traversing the ArrayList of Entity is to use an iterator and a while-loop.
        Iterator<Entity> iterator = entities.iterator();
        // Previous way we looped it was for-loop, which CAN CAUSE PROBLEMS (dynamically removing/altering ArrayList during
        // a traversal cycle... every now and then the if-state will be true and we'll randomly remove while traversing).
        while (iterator.hasNext()) { // Using an Iterator in a while-loop as of video32: Item Drops.
            Entity e = iterator.next(); // Instead of a get(index) call to the ArrayList (ArrayList is altering itself),
            e.tick();                   // we're using an Iterator (external object) to get the next object in the list.

            if (!e.isActive()) {                    // After tick() [updating method], see if Entity is NOT ACTIVE...
                iterator.remove();                 // remove Entity from the game if no longer active (USING ITERATOR).
            } // @@@ USING ITERATOR OBJECT TO REMOVE the entity NOW RATHER THAN ArrayList's remove(Entity), SAFER! @@@
        }
        // After updating the positions and etc for all the Entity objects in the game, SORT them based on y-coordinate
        // to figure out what should be drawn on top of what (RENDERING ORDER).
        entities.sort(renderSorter); // Pass in the customized anonymous Comparator class to sort based on y-coordinate.
    }

    // RENDERING ORDER: CactusTree on top or Player on top? It depends on y-coordinate. If Player's y-coordinate is bigger
    // than CactusTree's y-coordinate, then the Player's image should be drawn on top of (or after) the CactusTree's image.
    // The larger the y-coordinate, the further down the screen it is. The closer to 0, the further up the screen.
    // @@@ SHOULD be checking based on BOTTOM y-coordinate of bounding-box rather than the top-y. @@@
    // @@@@@ NEED TO SORT BASED ON y-coordinate OF THE ENTITY. USE A Comparator! @@@@@
    // Array of Entity will be sorted inside tick() before we render() the Entity.
    public void render(Graphics g) {                // Showing us for-each loop
        for (Entity e: entities) {                      //FUTURE: rendering collision stuff probably DON'T need index.
            e.render(g);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    // GETTERS AND SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

} // **** end EntityManager class ****