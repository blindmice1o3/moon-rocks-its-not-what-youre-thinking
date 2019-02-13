package com.pooh.moonrocks.entities.statics;

import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.entities.Entity;

// A StaticEntity is an Entity that DOES NOT MOVE, like a CactusTree or Rock!
// Unlike a Creature entity, which does move, like the Player.
public abstract class StaticEntity extends Entity {     // Similar to the Creature abstract class.

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    } // **** end StaticEntity(Handler, float, float, int, int) constructor ****

} // **** end StaticEntity abstract class ****