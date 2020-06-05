package com.pooh.moonrocks.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    // Need a way to see if a key HAS JUST BEEN PRESSED (and released), so calling that same key can turn off inventory-mode.
    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, left, right;
    // Attack and movement will use different keyboard keys. Movement will be WASD. Attack will be arrow keys.
    public boolean aUp, aDown, aLeft, aRight;

    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length]; // Using keys.length to make sure every array is the same size.
        cantPress = new boolean[keys.length];
    } // **** end KeyManager() constructor ****

    // The tick() will be constantly called by the game-loop, if the designated up key is pressed we set the boolean up as true.
    public void tick() {
        // Looping through every single possible key code that we're storing.
        for (int i = 0; i < keys.length; i++) {
            // cantPress[i] returns true means that value has already returned true for 1 tick frame, so we CAN'T press this particular key.
            // the keys array returns false means the key is no longer being pressed, player has just released the key,
            // should be able to press it again and have the just pressed array return true for that key.
            if (cantPress[i] && !keys[i]) {
                cantPress[i] = false;   // That way they are now able to press it again.
            } else if (justPressed[i]) {    // For 1 tick(), we've already returned true that this key has been pressed.
                cantPress[i] = true;    // We don't want them to press it until they've released the key.
                justPressed[i] = false; // It's no longer just been pressed, it's been pressed for more than one tick frame.
            }

            if (!cantPress[i] && keys[i]) { // If we are able to press the key AND it has just been pressed.
                justPressed[i] = true;
            }
        }

        if (keyJustPressed(KeyEvent.VK_E)) {    // TESTING OUR NEW METHOD: keyJustPressed(int keyCode).
            System.out.println("E JUST PRESSED");
        }

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

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) {
            return false;
        }
        return justPressed[keyCode];
    }

    // The key that's pressed ( identified by the getKeyCode() ) will stored in the corresponding index of keys array.
    @Override
    public void keyPressed(KeyEvent e) {
        // Including a check, to make sure the index isn't -1 or larger than 256. Will simply return, without setting the array.
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }

        keys[e.getKeyCode()] = true;
        System.out.println("Pressed!");     // Console output to test if keyboard input is working.
    }

    // When the key is released, its corresponding index will be set to false.
    @Override
    public void keyReleased(KeyEvent e) {
        // Including a check, to make sure the index isn't -1 or larger than 256. Will simply return, without setting the array.
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }

        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

} // **** end KeyManager class ****