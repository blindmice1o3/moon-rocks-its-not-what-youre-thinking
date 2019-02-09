package com.pooh.moonrocks.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    } // **** end SpriteSheet(BufferedImage) constructor ****

    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    } // **** end crop(int, int, int, int) ****

} // **** end SpriteSheet class ****