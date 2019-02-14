package com.pooh.moonrocks.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int WIDTH = 16, HEIGHT = 16;

    public static BufferedImage dirt, grass, rock, cactusTree, wall;
    public static BufferedImage[] player_down, player_up, player_left, player_right,
                            player_sail_down, player_sail_up, player_sail_left, player_sail_right;
    public static BufferedImage[] startButton;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet( ImageLoader.loadImage("/textures/player.gif") );
        SpriteSheet sheetTile = new SpriteSheet( ImageLoader.loadImage("/textures/tile_map(B04505_03_03).jpg") );
        SpriteSheet sheetStartButton = new SpriteSheet( ImageLoader.loadImage("/textures/StartButtons(96x64).png") );

        dirt = sheetTile.crop(167, 101, 32, 32);
        grass = sheetTile.crop(167, 134, 32, 32);
        rock = sheetTile.crop(233, 101, 32, 32);
        cactusTree = sheetTile.crop(204, 105, 24, 22);
        wall = sheetTile.crop(35, 35, 32, 32);

        startButton = new BufferedImage[2];
        startButton[0] = sheetStartButton.crop(0, 0, 96, 31);
        startButton[1] = sheetStartButton.crop(0, 32, 95, 31);

        player_down = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_sail_down = new BufferedImage[2];
        player_sail_up = new BufferedImage[2];
        player_sail_left = new BufferedImage[2];
        player_sail_right = new BufferedImage[2];

        player_down[0] = sheet.crop(WIDTH*0, HEIGHT*0, WIDTH, HEIGHT);
        player_down[1] = sheet.crop(WIDTH*1, HEIGHT*0, WIDTH, HEIGHT);
        player_left[0] = sheet.crop(WIDTH*0, HEIGHT*1, WIDTH, HEIGHT);
        player_left[1] = sheet.crop(WIDTH*1, HEIGHT*1, WIDTH, HEIGHT);
        player_right[0] = sheet.crop(WIDTH*0, HEIGHT*2, WIDTH, HEIGHT);
        player_right[1] = sheet.crop(WIDTH*1, HEIGHT*2, WIDTH, HEIGHT);
        player_up[0] = sheet.crop(WIDTH*0, HEIGHT*3, WIDTH, HEIGHT);
        player_up[1] = sheet.crop(WIDTH*1, HEIGHT*3, WIDTH, HEIGHT);

        player_sail_down[0] = sheet.crop(WIDTH*0, HEIGHT*4, WIDTH, HEIGHT);
        player_sail_down[1] = sheet.crop(WIDTH*1, HEIGHT*4, WIDTH, HEIGHT);
        player_sail_left[0] = sheet.crop(WIDTH*0, HEIGHT*5, WIDTH, HEIGHT);
        player_sail_left[1] = sheet.crop(WIDTH*1, HEIGHT*5, WIDTH, HEIGHT);
        player_sail_right[0] = sheet.crop(WIDTH*0, HEIGHT*6, WIDTH, HEIGHT);
        player_sail_right[1] = sheet.crop(WIDTH*1, HEIGHT*6, WIDTH, HEIGHT);
        player_sail_up[0] = sheet.crop(WIDTH*0, HEIGHT*7, WIDTH, HEIGHT);
        player_sail_up[1] = sheet.crop(WIDTH*1, HEIGHT*7, WIDTH, HEIGHT);
    } // **** end init() ****

} // **** end Assets class ****