package com.pooh.moonrocks.entities.creatures;

import com.pooh.moonrocks.entities.Entity;

import java.awt.*;

public abstract class Creature extends Entity {

    protected int health;

    public Creature(float x, float y) {
        super(x, y);
        health = 10;
    } // **** end Creature(float, float) constructor ****

} // **** end Creature abstract-class ****