package com.pooh.moonrocks.entities.statics;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.tiles.Tile;

import java.awt.*;

public class CactusTree extends StaticEntity {

    public CactusTree(Handler handler, float x, float y) {    // Like the Player class, width and height are DEFAULTS.
        // Unlike the Player class, height is TWICE a Tile.TILE_HEIGHT.
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
    } // **** end CactusTree(Handler, float, float) constructor ****

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cactusTree, (int)(x - handler.getGameCamera().getxOffset()),
                (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
    }

} // **** end CactusTree class ****