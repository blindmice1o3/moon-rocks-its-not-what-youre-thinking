package edu.pooh_farmer.model.entities;

import edu.pooh_farmer.Listener;
import edu.pooh_farmer.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player implements Subject {

    private ArrayList<Listener> listeners;

    private int x = 413;    // player's startingX
    private int y = 179;    // player's startingY

    private String pathPlayerImage = "resource_pooh_farmer/entities/Game Boy Advance - Kingdom Hearts Chain of Memories - Winnie the Pooh.png";
    //private String pathPlayerImage = "resource_pooh_farmer/entities/SNES - Harvest Moon - Jack.png";
    private BufferedImage playerBufferedImage;
    private Image playerImage;

    private int playerSpriteSheetX = 177;
    private int playerSpriteSheetY = 1060;
    private int playerSpriteSheetImageWidth = 21;
    private int playerSpriteSheetImageHeight = 33;
    //private int playerSpriteSheetX = 28;            // Harvest Moon - Jack
    //private int playerSpriteSheetY = 121;           // Harvest Moon - Jack
    //private int playerSpriteSheetImageWidth = 17;
    //private int playerSpriteSheetImageHeight = 25;

    public Player() {

        listeners = new ArrayList<Listener>();

        playerImage = new ImageIcon(pathPlayerImage).getImage();

        // Convert Image of sprite sheet to BufferedImage (need to use its getSubimage()).
        playerBufferedImage = new BufferedImage(playerImage.getWidth(null),
                playerImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = playerBufferedImage.createGraphics();
        g2d.drawImage(playerImage, 0, 0, null);
        g2d.dispose();

        // Crop ONE player image out of the sprite sheet.
        playerImage = playerBufferedImage.getSubimage(playerSpriteSheetX, playerSpriteSheetY,
                playerSpriteSheetImageWidth, playerSpriteSheetImageHeight);

    } // **** end Player() constructor ****

    // SUBJECT METHODS

    @Override
    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        // Find the specified listener object's index from the list of listeners
        int i = listeners.indexOf(listener);

        // If the specified listener object was found in the list, remove it.
        if (i >= 0) {
            listeners.remove(i);
        }
    }

    @Override
    public void notifyListeners() {
        for (Listener listener: listeners) {
            listener.update();
        }
    }

    // GETTERS & SETTERS METHODS

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Image getPlayerImage() {
        return playerImage;
    }

} // **** end Player class ****