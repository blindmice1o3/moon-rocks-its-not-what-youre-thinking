package com.pooh.moonrocks.states;

import com.pooh.moonrocks.Game;

import java.awt.*;

public abstract class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    } // **** end State(Game) constructor ****

    public abstract void tick();

    public abstract void render(Graphics g);

} // **** end State abstract-class ****