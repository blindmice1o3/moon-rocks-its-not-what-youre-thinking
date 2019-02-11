package com.pooh.moonrocks.states;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.Handler;

import java.awt.*;

public abstract class State {

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    } // **** end State(Handler) constructor ****

    public abstract void tick();

    public abstract void render(Graphics g);

} // **** end State abstract-class ****