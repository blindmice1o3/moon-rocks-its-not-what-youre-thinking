package com.pooh.moonrocks.entities;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;

    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
    } // **** end EntityManager(Handler, Player) constructor ****

    public void tick() {
        for (int i = 0; i < entities.size(); i++) { // Showing us regular for-loop
            Entity e = entities.get(i);             // Showing us ArrayList's get() method vs array's indexing [index].
            e.tick();                                   //FUTURE: collision stuff will probably depend on for-loop's index.
        }
        player.tick();
    }

    public void render(Graphics g) {                // Showing us for-each loop
        for (Entity e: entities) {                      //FUTURE: rendering collision stuff probably DON'T need index.
            e.render(g);
        }
        player.render(g);
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