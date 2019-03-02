package edu.pooh_farmer.view;

import edu.pooh_farmer.PoohFarmer;
import edu.pooh_farmer.controller.InputBehavior;

import javax.swing.*;

public class Displayer {

    private JFrame frame;
    private JPanel currentPanel;

    private PoohFarmer game;

    private InputBehavior inputBehavior;

    public Displayer(PoohFarmer game) {
        this.game = game;

        initFrame();
        initPanel();

        frame.setContentPane(currentPanel);

        frame.setVisible(true);

        System.out.println("frame's width: " + frame.getWidth() );
        System.out.println("frame's height: " + frame.getHeight() );
        System.out.println("GamePanel's width: " + currentPanel.getWidth() );
        System.out.println("GamePanel's height: " + currentPanel.getHeight() );
    } // **** end Displayer(PoohFarmer) constructor ****

    private void initFrame() {
        frame = new JFrame("Pooh Farmer!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 540);
        frame.setLocationRelativeTo(null);
    }

    public void initPanel() {
        currentPanel = new GamePanel(game);
    }



}   // **** end Displayer class ****