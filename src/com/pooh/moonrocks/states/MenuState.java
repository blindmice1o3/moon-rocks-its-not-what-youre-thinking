package com.pooh.moonrocks.states;

import com.pooh.moonrocks.Game;
import com.pooh.moonrocks.Handler;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.ui.ClickListener;
import com.pooh.moonrocks.ui.UIImageButton;
import com.pooh.moonrocks.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject( new UIImageButton(200, 200, 128, 64, Assets.player_down, new ClickListener() {
            @Override
            public void onClick() {
                // We don't want the gameState to respond to The MouseManager's uiManager, so we have to un-set it.
                handler.getMouseManager().setUIManager(null);
                // When the user clicks this UIImageButton (displaying player_down BufferedImage[] array),
                // change our currentState to gameState. SHOULD TURN OFF menuState's MouseManager (see above) BEFOREHAND.
                StateManager.setCurrentState(handler.getGame().gameState);
            }
        } ) );
    } // **** end MenuState(Handler) constructor ****

    @Override
    public void tick() {
        uiManager.tick();

        //At first we printed the x, y coordinates of the mouse to the console.
        // Now we're going to make the MenuState SWITCH STATE if both the left AND right mouse buttons are being pressed.
        //if (handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()) {
        //    StateManager.setCurrentState(handler.getGame().gameState);
        //}
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);

        // A visual cue to demonstrate that our mouseManager is working properly.
        // This will draw a little red rectangle where ever the mouse goes.
        //g.setColor(Color.RED);
        //g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 8, 8);
    }

} // **** end MenuState class ****