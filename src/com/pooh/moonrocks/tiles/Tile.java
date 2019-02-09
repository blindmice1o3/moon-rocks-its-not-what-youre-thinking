package com.pooh.moonrocks.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    // STATIC STUFF HERE

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);// The reference variable is type Tile. The object type is GrassTile.
    public static Tile dirtTile = new DirtTile(1);  // Make sure to give every single Tile a different id.
    public static Tile rockTile = new RockTile(2);

    // CLASS

    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        // Whenever a subclass's (i.e. GrassTile, RockTile, DirtTile) constructor is called, Tile's constructor is called.
        // So when we make an instance of a subclass, we want to store that instance into Tile class's tiles array, using
        // the id variable as an index for the tiles array.
        tiles[id] = this;
    } // **** end Tile(BufferedImage, int) constructor ****

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    // Can you NOT walk through it? (i.e. grass == can walk on top of it, rock == can NOT walk on top of it)
    // Returns false by default (i.e. Tile is NOT solid, you can walk on top of it).
    // Any subclass of Tile that should NOT be walked on top of will OVERRIDE this method to return true.
    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }

} // **** end Tile class ****