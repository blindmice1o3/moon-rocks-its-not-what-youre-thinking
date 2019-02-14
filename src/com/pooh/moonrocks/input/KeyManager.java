package com.pooh.moonrocks.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;
    // Attack and movement will use different keyboard keys. Movement will be WASD. Attack will be arrow keys.
    public boolean aUp, aDown, aLeft, aRight;

    public KeyManager() {
        keys = new boolean[256];
    } // **** end KeyManager() constructor ****

    // The tick() will be constantly called by the game-loop, if the designated up key is pressed we set the boolean up as true.
    public void tick() {
        // See if the corresponding virtual keyboard key stored in the keys array is being pressed and assign that
        // true/false value to the up, down, left, and right boolean variables.
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];

        // Direction of attacking will be set to the arrow keys.
        aUp = keys[KeyEvent.VK_UP];
        aDown = keys[KeyEvent.VK_DOWN];
        aLeft = keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_RIGHT];
    }

    // The key that's pressed ( identified by the getKeyCode() ) will stored in the corresponding index of keys array.
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        System.out.println("Pressed!");     // Console output to test if keyboard input is working.
    }

    // When the key is released, its corresponding index will be set to false.
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

} // **** end KeyManager class ****