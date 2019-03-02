package edu.pooh_farmer;

import edu.pooh_farmer.model.entities.Player;
import edu.pooh_farmer.view.Displayer;

/**
 * The actual GAME class.
 */
public class PoohFarmer {

    private Displayer displayer;
    private Player player;

    public PoohFarmer() {
        player = new Player();
        displayer = new Displayer(this);

    } // **** end PoohFarmer() constructor ****

    public Player getPlayer() {
        return player;
    }

} // **** end PoohFarmer class ****