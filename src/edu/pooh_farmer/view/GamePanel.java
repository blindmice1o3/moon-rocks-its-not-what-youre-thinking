package edu.pooh_farmer.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private String path;
    private Image backgroundImage;

    public GamePanel() {
        path = "resource_pooh_farmer/world/Game Boy GBC - Harvest Moon GBC - Farm Spring.png";
        backgroundImage = new ImageIcon(path).getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),
                0, 0, 784, 776, null);
    }

}
