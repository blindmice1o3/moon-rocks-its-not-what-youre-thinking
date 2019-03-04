package edu.pooh_farmer.view;

import edu.pooh_farmer.Listener;
import edu.pooh_farmer.PoohFarmer;
import edu.pooh_farmer.controller.GamePanelKeyListener;

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
    private int playerX, playerY;

    public GamePanel(PoohFarmer game) {

        this.game = game;

        backgroundImage = game.getChapter1().getBackgroundImage();
        tileWidth = game.getChapter1().getTileWidth();
        tileHeight = game.getChapter1().getTileHeight();

        playerImage = game.getPlayer().getPlayerImage();
        playerX = game.getPlayer().getX();
        playerY = game.getPlayer().getY();

        // Register a Keyboard listener (a new GamePanelKeyListener object) for this JPanel.
        addKeyListener(new GamePanelKeyListener(game));

        // Register as a LISTENER to the Player object.
        game.getPlayer().registerListener(this);

        setFocusable(true);
        requestFocus();
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
        g.drawImage(playerImage, playerX, playerY, playerX + tileWidth, playerY + tileHeight,
                0, 0, playerImage.getWidth(null), playerImage.getHeight(null), null);
    }

    // LISTENER METHOD (the subject is player object)

    @Override
    public void update() {

    }

} // **** end GamePanel class ****