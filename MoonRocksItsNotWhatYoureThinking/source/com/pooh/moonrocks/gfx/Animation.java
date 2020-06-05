package com.pooh.moonrocks.gfx;

import java.awt.image.BufferedImage;

public class Animation {

    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    } // **** end Animation(int, BufferedImage[]) constructor ****

    // This method will be called every time we tick() our game, it increments our index (moves our frame).
    public void tick() {
        // timer plus-equals (how much time has passed during the start of our programs - lastTime).
        // This gets us the time (in milliseconds) that has passed since this tick() method and the previous
        // tick() method have been call.
        timer += System.currentTimeMillis() - lastTime;
        // lastTime is being reset.
        lastTime = System.currentTimeMillis();

        // This is very similar to our game-loop.
        // We're adding to our timer the amount of milliseconds that have passed since the last tick() method call and
        // this tick() method call.

        if (timer > speed) { // speed was set (passed in) in the constructor
            index++;
            timer = 0;

            // This can cause an error (ArrayIndexOutOfBound) if we're at the last frame and have to loop back to the start.
            if (index >= frames.length) {   // greater-than-OR-EQUAL-TO (because arrays start at 0)
                index = 0;  // We finished our last frame, bring it back to the first frame.
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

} // **** end Animation class ****