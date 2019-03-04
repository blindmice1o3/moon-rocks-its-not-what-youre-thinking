package edu.pooh_farmer.controller;

import edu.pooh_farmer.PoohFarmer;
import edu.pooh_farmer.model.entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class GamePanelKeyListener implements KeyListener {

    private PoohFarmer game;

    private int tileWidth;
    private int tileHeight;

    private Player player;

    public GamePanelKeyListener(PoohFarmer game) {
        this.game = game;

        tileWidth = game.getChapter1().getTileWidth();
        tileHeight = game.getChapter1().getTileHeight();

        player = game.getPlayer();
    } // **** end GamePanelKeyListener() constructor ****

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_W:
                player.setY( player.getY() - tileHeight );
                System.out.println("player's y DECREASED.");
                break;
            case VK_S:
                player.setY( player.getY() + tileHeight );
                System.out.println("player's y INCREASED.");
                break;
            case VK_A:
                player.setX( player.getX() - tileWidth );
                System.out.println("player's x DECREASED.");
                break;
            case VK_D:
                player.setX( player.getX() + tileWidth );
                System.out.println("player's x INCREASED.");
                break;
            default:
                break;
        }
        game.getDisplayer().getCurrentPanel().repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

} // **** end GamePanelKeyListener class ****