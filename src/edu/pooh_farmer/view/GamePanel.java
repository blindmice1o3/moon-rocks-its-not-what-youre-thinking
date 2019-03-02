package edu.pooh_farmer.view;

import edu.pooh_farmer.PoohFarmer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public static final int CAMERA_WIDTH = 256;
    public static final int CAMERA_HEIGHT = 224;
    private int cameraX = 0;
    private int cameraY = 224;

    private PoohFarmer game;
    private Image playerImage;
    private int playerX, playerY;

    private String pathBackgroundImage = "resource_pooh_farmer/world/Game Boy GBC - Harvest Moon GBC - Farm Spring.png";
    private Image backgroundImage;

    public GamePanel(PoohFarmer game) {
        this.game = game;

        backgroundImage = new ImageIcon(pathBackgroundImage).getImage();

        playerImage = game.getPlayer().getPlayerImage();
        playerX = game.getPlayer().getX();
        playerY = game.getPlayer().getY();
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
        g.drawImage(playerImage, playerX, playerY, playerX + 59, playerY + 36,
                0, 0, playerImage.getWidth(null), playerImage.getHeight(null), null);
    }

} // **** end GamePanel class ****