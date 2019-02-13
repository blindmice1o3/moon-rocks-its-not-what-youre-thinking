package com.pooh.moonrocks.states;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.Handler;

import java.awt.*;

public class MenuState extends State {

    public MenuState(Handler handler) {
        super(handler);
    } // **** end MenuState(Handler) constructor ****

    @Override
    public void tick() {
        //At first we printed the x, y coordinates of the mouse to the console.
        // Now we're going to make the MenuState SWITCH STATE if both the left AND right mouse buttons are being pressed.
        if (handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
            StateManager.setCurrentState(handler.getGame().gameState);
        }


    }

    @Override
    public void render(Graphics g) {
        // A visual cue to demonstrate that our mouseManager is working properly.
        // This will draw a little red rectangle where ever the mouse goes.
        g.setColor(Color.RED);
        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
    }

} // **** end MenuState class ****