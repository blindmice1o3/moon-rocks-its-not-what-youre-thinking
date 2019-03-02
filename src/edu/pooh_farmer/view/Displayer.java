package edu.pooh_farmer.view;

import edu.pooh_farmer.controller.InputBehavior;

import javax.swing.*;

public class Displayer {

    private JFrame frame;
    private JPanel currentPanel;

    private InputBehavior inputBehavior;

    public Displayer() {
        initFrame();
        initPanel();

        frame.setContentPane(currentPanel);

        frame.setVisible(true);
    } // **** end Displayer() constructor ****

    private void initFrame() {
        frame = new JFrame("Pooh Farmer!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 540);
        frame.setLocationRelativeTo(null);
    }

    public void initPanel() {
        currentPanel = new GamePanel();
    }



}   // **** end Displayer class ****