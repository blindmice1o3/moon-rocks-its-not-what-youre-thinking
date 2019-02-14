package com.pooh.moonrocks.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    } // **** end UIImageButton(float, float, int, int, BufferedImage[], ClickListener) constructor ****

    @Override
    public void tick() {

    }

    // Default image of the Button will be at array index 0. If the mouse is hovering over the Button, the button is
    // drawn as the image in array index 1.
    @Override
    public void render(Graphics g) {
        if (hovering) {
            g.drawImage(images[1], (int)x, (int)y, width, height, null);
        } else {
            g.drawImage(images[0], (int)x, (int)y, width, height, null);
        }
    }

    // Whenever our Button is clicked, we want to perform some action (that code goes here).
    @Override
    public void onClick() {
        clicker.onClick();
    }


} // **** end UIImageButton class ****