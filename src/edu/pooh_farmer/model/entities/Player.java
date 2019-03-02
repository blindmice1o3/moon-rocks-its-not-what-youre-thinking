package edu.pooh_farmer.model.entities;

import edu.pooh_farmer.model.gfx.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private int x, y;

    private String pathPlayerImage = "/entities/SNES - Harvest Moon - Jack.png";
    private BufferedImage playerImage;

    public Player() {
        x = 413;
        y = 179;

        playerImage = ImageLoader.loadImage(pathPlayerImage);
        playerImage = playerImage.getSubimage(28, 121, 17, 25);

    } // **** end Player() constructor ****

    // GETTERS & SETTERS

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getPlayerImage() {
        return playerImage;
    }

} // **** end Player class ****