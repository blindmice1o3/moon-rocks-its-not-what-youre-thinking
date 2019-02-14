package com.pooh.moonrocks.entities.statics;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.tiles.Tile;

import java.awt.*;

public class SignPost extends StaticEntity {

    public SignPost(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 15;
        bounds.y = 5;
        bounds.width = (int) (width / 2f);
        bounds.height = height - 5;
    } // **** end SignPost(Handler, float, float) constructor ****

    @Override
    public void tick() {

    }

    @Override
    public void die() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.signPost, (int)(x - handler.getGameCamera().getxOffset()),
                (int)(y - handler.getGameCamera().getyOffset()), width, height, null);

//        g.setColor(Color.RED);
//        g.fillRect( (int)( x + bounds.x - handler.getGameCamera().getxOffset() ),
//                (int)( y + bounds.y - handler.getGameCamera().getyOffset() ),
//                bounds.width, bounds.height);
    }

} // **** end SignPost class ****