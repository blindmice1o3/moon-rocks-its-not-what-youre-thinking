package com.pooh.moonrocks;

import com.pooh.moonrocks.display.Display;
import com.pooh.moonrocks.gfx.Assets;
import com.pooh.moonrocks.gfx.GameCamera;
import com.pooh.moonrocks.gfx.ImageLoader;
import com.pooh.moonrocks.gfx.SpriteSheet;
import com.pooh.moonrocks.input.KeyManager;
import com.pooh.moonrocks.input.MouseManager;
import com.pooh.moonrocks.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    // States
    public State gameState;     //Changed to public for ease of use (not best-practice!!!)
    public State menuState;     //Changed to public for ease of use (not best-practice!!!)
    private State settingState;

    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    // Camera
    private GameCamera gameCamera;

    // Handler
    private Handler handler;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    } // **** end Game(String, int, int) constructor ****

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);  // The JFrame composed in Display class will listen for keyboard input.
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        // At first it was mouseManager being used JUST for the JFrame (this could be glitchy depending on which object
        // has the INPUT FOCUS at the time). We're going to add MouseEvent Listeners to the Canvas object as well.
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        // When game starts, the xOffset and yOffset are not shifted in any x or y direction (i.e. offsets are 0).
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        // States
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        settingState = new SettingState(handler);
        StateManager.setCurrentState(menuState);    // Changing to menuState to CHECK if mouseManager is working.
    } // **** end init() ****

    private void tick() {
        // Update the KeyManager boolean variables for up, down, left, and right.
        keyManager.tick();

        // If we have a currentState that already exists, call whichever currentState's tick() method.
        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().tick();
        }
    } // **** end tick() ****

    private void render() {
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        // Clear screen everytime before drawing to the screen.
        g.clearRect(0, 0, width, height);


        // *** Draw here ***

        // If the currentState already exists, call whichever currentState's render(Graphics) method.
        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(g);
        }

        // *** End drawing ***


        // Move the buffer to the screen.
        bs.show();
        g.dispose();

    } // **** end render() ****

    // When we call thread.start(), it will call THIS (Game) class's run().
    public void run() {

        init();

        // @@@@ initializing bunch of variables to achieve CONSISTENT fps, no matter if running game on fast/slow computer @@@@
        int fps = 60;   // frames (or ticks) per second... Our tick() and render() will be called 60 times every single sec.
        double timePerTick = 1000000000 / fps;  // 1 billion nanoseconds == 1 second
        // 1 second / 60 fps ===> The max amount of time we have to run the tick() and render() to achieve 60 fps.
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();  // The current time of our computer, but in nanoseconds.
        // We need a visual fps counter to show to the screen.
        long timer = 0;
        int ticks = 0;

        // *** start of GAME-LOOP ***
        while (running) {
            now = System.nanoTime();    // Get the current time of our computer and store it.
            delta += (now - lastTime) / timePerTick;    // (now-lastTime) gets the the amount of time that passed between
                                                        // now and when we last ran this line of code.

                                                        // We divide that difference by the max amount of time we have to
                                                        // call the tick() and render(); and add to the delta variable.

                                                        // We're adding (to the delta variable) how much time we have
                                                        // until we have to call the tick() and render().
            // delta tells the computer when-to and when-not-to call the tick() and render().

            // Add the amount of time that has passed since the above code last ran.
            timer += now - lastTime;    // This will add to our timer variable the amount of nanoseconds that have passed
                                        // since we last called the above block of code.
            lastTime = now; // Reset the lastTime this block of code was run to when the block of code began.

            // Now we have to check IF we have to run our tick() and render().
            if (delta >= 1) {
                // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                tick();
                render();
                // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                ticks++;
                delta--;
            }

            // Check if our timer is greater than or equal to 1 sec... how many ticks did we do during that 1 sec?
            // This is our visual representation to check how many times we're calling tick() and render() each second.
            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;  // Reset ticks back to 0.
                timer = 0;  // Reset timer back to 0.
            }
        } // *** end of GAME-LOOP ***

        stop();

    } // **** end run() ****

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;

        // The new Thread is passed a Runnable (something that implements the interface Runnable ---> has a run() method).
        thread = new Thread(this);
        thread.start();
    } // **** end start() ****

    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;

        try {
            thread.join();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    } // **** end stop() ****

} // **** end Game class ****