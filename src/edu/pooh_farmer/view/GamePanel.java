package edu.pooh_farmer.view;

import edu.pooh_farmer.Listener;
import edu.pooh_farmer.PoohFarmer;
import edu.pooh_farmer.controller.GamePanelKeyListener;
import edu.pooh_farmer.model.entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Listener {

    public static final int CAMERA_WIDTH = 256;
    public static final int CAMERA_HEIGHT = 224;
    private int cameraX = 0;        // camera's startingX
    private int cameraY = 224;      // camera's startingY

    private PoohFarmer game;

    private Image backgroundImage;
    private int tileWidth, tileHeight;

    private Image playerImage;
    private Player player;

    public GamePanel(PoohFarmer game) {

        this.game = game;
        setFocusable(true);

        backgroundImage = game.getChapter1().getBackgroundImage();
        tileWidth = game.getChapter1().getTileWidth();
        tileHeight = game.getChapter1().getTileHeight();

        player = game.getPlayer();
        playerImage = player.getPlayerImage();

        // Register as a LISTENER to the Player object.
        player.registerListener(this);

    } // **** end GamePanel(PoohFarmer) constructor ****

    @Override
    public void paintComponent(Graphics g) {
        renderBackground(g);
        renderPlayer(g);
    }

    private void renderBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),
                cameraX, cameraY, cameraX + CAMERA_WIDTH, cameraY + CAMERA_HEIGHT, null);
    }

    private void renderPlayer(Graphics g) {
        g.drawImage(playerImage, player.getX(), player.getY(),
                player.getX() + tileWidth, player.getY() + tileHeight,
                0, 0, playerImage.getWidth(null), playerImage.getHeight(null), null);
    }

    // LISTENER METHOD (the subject is player object)

    @Override
    public void update() {

    }

    // GETTERS & SETTERS

    public int getCameraX() {
        return cameraX;
    }

    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void setCameraY(int cameraY) {
        this.cameraY = cameraY;
    }

} // **** end GamePanel class ****