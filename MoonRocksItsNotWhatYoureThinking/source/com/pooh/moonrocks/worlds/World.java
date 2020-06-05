package com.pooh.moonrocks.worlds;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.entities.EntityManager;
import com.pooh.moonrocks.entities.creatures.Player;
import com.pooh.moonrocks.entities.statics.CactusTree;
import com.pooh.moonrocks.entities.statics.SignPost;
import com.pooh.moonrocks.items.Item;
import com.pooh.moonrocks.items.ItemManager;
import com.pooh.moonrocks.tiles.Tile;
import com.pooh.moonrocks.utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    // In terms of Tile (i.e. if the size of the map/world is 5 x 5, it'll be 5 tiles across and 5 tiles down).
    private int width, height;
    private int spawnX, spawnY;
    // We also need a way to store all of the tiles in every single position. Multi-dimensional array. We'll store it
    // using int (for each type of tile's id), not actual Tile object. Multi-dimensional as in we'll have 2 indexes for
    // the array. The rows will be called x, and the columns/height will be called y.
    private int[][] tiles;

    // ENTITIES
    private EntityManager entityManager;

    // ITEMS
    private ItemManager itemManager;

    // constructor... 2 ways to create worlds... (1) randomly generated worlds... or (2) load in worlds from a file.
    // The second option will be the same world everytime, this is what we'll learn now. We'll need the location on our
    // computer of the file that we want to load, passed in as an argument to the constructor.
    public World(Handler handler, String path) {
        this.handler = handler;

        // Important to instantiate EntityManager before loadWorld().
        entityManager = new EntityManager(handler, new Player(handler, 100, 100)); // x and y doesn't matter for player,
        entityManager.addEntity( new SignPost(handler, 100, 250) );              // gets re-set after call to
        entityManager.addEntity( new CactusTree(handler, 100, 350) );              // loadWorld(String) to be coordinates
        entityManager.addEntity( new CactusTree(handler, 200, 350) );              // from the MAP text file.

        itemManager = new ItemManager(handler);
        itemManager.addItem(Item.woodItem.createNew(400, 140));
        itemManager.addItem(Item.waterItem.createNew(400, 390));
        itemManager.addItem(Item.woodItem.createNew(400, 440));

        loadWorld(path);

        // After we load the world... MAKE SURE IT'S after! WE NEED THE spawnX and spawnY FROM READING THE MAP file.
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    } // **** end World(Handler, String) constructor ****

    // loadWorld(String) method is responsible for getting the file that we want to load as our world and storing it in
    // the multi-dimensional array called tiles, so we'll know which tiles are in which position.
    private void loadWorld(String path) {
        // Using Utils class's loadFileAsString(String) method to store (in String called file) the location (on our
        // computer) of the world1.txt file.
        String file = Utils.loadFileAsString(path);

        // In our world1.txt file, we had split everything up either by a space or a newline.
        // What we want to do is convert all the Strings within our world1.txt into actual numbers (integers).
        // We have to split up the String that we loaded from world1.txt... we have to split up every individual number.

        // We'll take a String[] array (an array of every single number inside of world1.txt) called tokens
        // and call split on our String representation of the loaded world1.txt file (the String variable called file).

        // @@@@ To split it on any amount of white space (so a space character or a newline character) "\\s+" @@@@
        String[] tokens = file.split("\\s+");

        // What we just did was we took our file and we splitted each number into its own little String and stored each
        // of them into an index in the tokens array. That way we can access all of them separately.

        // Now we have to set the width and height of our world. (This is in terms of number of Tiles, NOT pixels.)
        width = Utils.parseInt( tokens[0] );    // The first number in our world1.txt file (aka first number in tokens).
        height = Utils.parseInt( tokens[1] );
        // Next will be WHERE the player will SPAWN at the start of the handler.
        spawnX = Utils.parseInt( tokens[2] );
        spawnY = Utils.parseInt( tokens[3] );   // We just read in the first four numbers of our file (world1.txt).


        // Now every single number after this is actual world data (id numbers of Tiles).
        // We have to read all that data into the World class's int[][] tiles array.

        // Initialize the multi-dimensional int array called tiles by the world's width and height (number of Tiles).
        tiles = new int[width][height];
        // Use nested for-loops (just like in the render() method) to store the int id into the tiles array.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Here's where it gets a bit tricky. First, it has to be an integer, so use the Utils class's parseInt().

                // Parsing something from the tokens array into an int, but the tokens is a 1-D array while tiles is 2-D.
                // We have to convert the x and y for-loop position into whatever proper position it is into the proper
                // position of our tokens array.
                    // @@@@ In parentheses... (x + y * width) and that will appropriately convert the x and y of the
                    // for-loop into the 1-dimensional array index. BUT ALSO HAVE TO ADD 4 because we are setting the
                    // first 4 elements in the world1.txt file (array indexes [0], [1], [2], [3]) into variables width,
                    // height, spawnX, spawnY... they're not actual world data!!!! @@@@
                tiles[x][y] = Utils.parseInt( tokens[ (x + (y * width)) + 4] );
            }
        }



        /*    ****************************************************************************
        // @@@ Will code this method in the next (video18) tutorial @@@

        // The following is temporary just so we have something to work with (loading the width, height, and tiles array).
                        // @@@ FOR TESTING PURPOSES @@@
        width = 5;
        height = 5;
        tiles = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = 0;    // temporarily setting all of the tile as GrassTile (whose id is 0)
            }
        }

        ***********************************************************************************    */
    }

    // To update the position of all the tiles and things like that.
    public void tick() {
        itemManager.tick();     // Update Items first, then Entitys.
        entityManager.tick();
    }

    // To draw all the tiles to the screen, that way we can see the world.
    // Similar to what we did to temporarily load the world... using nested for-loops to loop through every tile.

    // NOT efficient, redrawing ALL the tiles every time. Using 4 new variables so we limit what gets redrawn (what the
    // user actually sees. It's pretty easy, but requires quite a bit of math.
    public void render(Graphics g) {
        // TILES
        //3rd version
            // Want to prevent going into NEGATIVE numbers...
            // Using Math.max(int, int) which returns the greater of the 2 numbers (but we have to cast to int since
            // the GameCamera's offset uses a float).
        // Getting the xOffset of the GameCamera and divide by Tile's width to get it in terms of tiles and not pixels.
        // If we move left too far and the second argument's calculation returns a negative, Math.max() gives us 0.
        // Trying it without the Math.max() returning a lower bound of 0 (getting a neg number) == arrayindexoutofbound error.
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        // xEnd is trickier and uses Math.min()... the smaller of either width or some-calculation.
        // Adding the width because we're looking at far-right of our screen, not the far-left. Add 1 to include 1 more
        // tile to the far-right of the screen to get rid of the "weird effect"
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        // Exact same process for the y-axis.
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        // ITEMS
        itemManager.render(g);  // Update Items first, then Entitys. Entity drawn ON TOP OF Item.
        // ENTITIES
        entityManager.render(g);

        /*      @@@ 1st and 2nd version where we redraw the entire World every time. @@@
        // CodeNMore is starting with y first... it's suppose to prevent a few problems down the road.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                //1st version
                //getTile(x, y).render(g, x, y);

                // @@@@@@@@ Something went wrong @@@@@@@
                // Since we render in terms of pixels, and our tiles are 64x64 pixels in size, all of the tiles were
                // just drawn ontop of one another because the tiles[][] uses tile coordinates (5x5) - not pixels.

                // So we have to convert World's render(Graphics) method's usage of
                // Tile class's render(Graphics, int, int) method
                // (specifically the x and y we pass for the int, int)...
                // we have to convert these coordinates from tiles into pixels.
                // All we have to do is multiple the x coordinate by the Tile.TILE_WIDTH, and the same for y.

                    //2nd version
                    // Applying GameCamera's offsets variables when we draw each individual tile to the screen by
                    // subtracting the x and y offset values ( @@@ have to cast both arguments to an int now since
                    // GameCamera's offset variables are floats @@@ ).
                // We're just substracting whatever the x and y offsets are from the position in which (to the screen)
                // that we're rendering these tiles to.
                getTile(x, y).render(g, (int)(x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                                        (int)(y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        */
    }

    // To be used in the render(Graphics) method. Find the id of the Tile (stored in Tile class as a static variable)
    // using the indexes x and y of the multi-dimensional tiles array, and return that Tile.
    public Tile getTile(int x, int y) {
        // Check to make sure the player is not outside the map, if player is outside, return dirt tile to prevent crash.
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.dirtTile;
        }

        // Storing a Tile from the tiles array (which holds every single tile in our handler) inside the Tile class (at
        // so-and-so index). We're going to index it with whatever World's tiles array is at x and at y.
        Tile t = Tile.tiles[ tiles[x][y] ];     // (1) Tile.tiles[] is the array from Tile class that stores all the
                                                // different types of tiles that exist in the handler.
                                                // (2) tiles[x][y] is the array from this (World) class that stores the
                                                // id of the tile that's suppose to show up at position x and y.

        // Next we have to check whether or not it's equal to null, if it's null we'll get an error.
        // We'll return a DEFAULT tile (we're using dirt as the default) if t is equal to null.
        if (t == null) {
            return Tile.dirtTile;
        }

        return t;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

} // **** end World class ****