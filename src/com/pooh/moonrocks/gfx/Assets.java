package com.pooh.moonrocks.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int WIDTH = 16, HEIGHT = 16;

    public static BufferedImage walkDown1, walkDown2,
                                walkLeft1, walkLeft2,
                                walkRight1, walkRight2,
                                walkUp1, walkUp2,
                                sailDown1, sailDown2,
                                sailLeft1, sailLeft2,
                                sailRight1, sailRight2,
                                sailUp1, sailUp2;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet( ImageLoader.loadImage("/textures/player.gif") );

        walkDown1 = sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT);
        walkDown2 = sheet.crop(WIDTH*1, HEIGHT*0, WIDTH, HEIGHT);

        walkLeft1 = sheet.crop(WIDTH*0, HEIGHT*1, WIDTH, HEIGHT);
        walkLeft2 = sheet.crop(WIDTH*1, HEIGHT*1, WIDTH, HEIGHT);

        walkRight1 = sheet.crop(WIDTH*0, HEIGHT*2, WIDTH, HEIGHT);
        walkRight2 = sheet.crop(WIDTH*1, HEIGHT*2, WIDTH, HEIGHT);

        walkUp1 = sheet.crop(WIDTH*0, HEIGHT*3, WIDTH, HEIGHT);
        walkUp2 = sheet.crop(WIDTH*1, HEIGHT*3, WIDTH, HEIGHT);

        sailDown1 = sheet.crop(WIDTH*0, HEIGHT*4, WIDTH, HEIGHT);
        sailDown2 = sheet.crop(WIDTH*1, HEIGHT*4, WIDTH, HEIGHT);

        sailLeft1 = sheet.crop(WIDTH*0, HEIGHT*5, WIDTH, HEIGHT);
        sailLeft2 = sheet.crop(WIDTH*1, HEIGHT*5, WIDTH, HEIGHT);

        sailRight1 = sheet.crop(WIDTH*0, HEIGHT*6, WIDTH, HEIGHT);
        sailRight2 = sheet.crop(WIDTH*1, HEIGHT*6, WIDTH, HEIGHT);

        sailUp1 = sheet.crop(WIDTH*0, HEIGHT*7, WIDTH, HEIGHT);
        sailUp2 = sheet.crop(WIDTH*1, HEIGHT*7, WIDTH, HEIGHT);
    } // **** end init() ****

} // **** end Assets class ****
