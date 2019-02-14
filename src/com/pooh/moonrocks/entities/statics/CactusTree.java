package com.pooh.moonrocks.entities.statics;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.tiles.Tile;

import java.awt.*;

public class CactusTree extends StaticEntity {

    public CactusTree(Handler handler, float x, float y) {    // Like the Player class, width and height are DEFAULTS.
        // Unlike the Player class, height is TWICE a Tile.TILE_HEIGHT.
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);

        // @@@@@ Experiment with these values for our specific image. @@@@@
        bounds.x = 8;
        bounds.y = (int)(height / 1.5f)-35;
        bounds.width = width - 16;
        bounds.height = (int)(height - height / 1.5f)+7;

        // ENTITY COLLISION DETECTION video: this is by no means the most efficient way to implement this, but it's a
        // good starter to wrap our head around entity-collision detection. If we're creating a huge game where efficiency
        // matters, we can look up "quadtrees" which implements a different system.

    } // **** end CactusTree(Handler, float, float) constructor ****

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.cactusTree, (int)(x - handler.getGameCamera().getxOffset()),
                (int)(y - handler.getGameCamera().getyOffset()), width, height, null);

        g.setColor(Color.RED);
        g.fillRect( (int)( x + bounds.x - handler.getGameCamera().getxOffset() ),
                (int)( y + bounds.y - handler.getGameCamera().getyOffset() ),
                bounds.width, bounds.height);
    }

} // **** end CactusTree class ****