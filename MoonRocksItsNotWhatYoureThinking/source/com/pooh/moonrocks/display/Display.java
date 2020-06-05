package com.pooh.moonrocks.display;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    } // **** end Display(String, int, int) constructor ****

    public void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize( new Dimension(width, height) );
        canvas.setMaximumSize( new Dimension(width, height) );
        canvas.setMinimumSize( new Dimension(width, height) );
        canvas.setFocusable(false); // Needed to do this so JFrame listens for keyboard input (JFrame is the sole
                                    // component that'll be able to have focus).

        frame.add(canvas);
        frame.pack();
    } // **** end createDisplay() ****

    public Canvas getCanvas() {
        return canvas;
    } // **** end getCanvas() ****

    public JFrame getFrame() {
        return frame;
    } // **** end getFrame() ****

} // **** end Display class ****
