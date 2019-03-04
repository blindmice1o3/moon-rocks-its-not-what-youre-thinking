package edu.pooh_farmer.model.world;

import javax.swing.*;
import java.awt.*;

public class Chapter1 {

    private String pathBackgroundImage = "resource_pooh_farmer/world/Game Boy GBC - Harvest Moon GBC - Farm Spring.png";
    private Image backgroundImage;

    private int tileWidth = 59;
    private int tileHeight = 36;

    public Chapter1() {

        backgroundImage = new ImageIcon(pathBackgroundImage).getImage();

    } // **** end Chapter1() constructor ****

    // GETTERS & SETTERS

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

} // **** end Chapter1 class ****