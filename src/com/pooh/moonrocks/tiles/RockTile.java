package com.pooh.moonrocks.tiles;

import com.pooh.moonrocks.gfx.Assets;

public class RockTile extends Tile {

    public RockTile(int id) {
        super(Assets.rock, id);
    } // **** end RockTile(int) constructor ****

    @Override
    public boolean isSolid() {
        return true;
    }

} // **** end RockTile class ****