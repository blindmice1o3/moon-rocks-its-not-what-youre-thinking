package edu.pooh_farmer;

import edu.pooh_farmer.model.entities.Player;
import edu.pooh_farmer.model.world.Chapter1;
import edu.pooh_farmer.view.Displayer;

/**
 * The actual GAME class.
 */
public class PoohFarmer {

    private Chapter1 chapter1;
    private Player player;
    private Displayer displayer;

    public PoohFarmer() {

        chapter1 = new Chapter1();
        player = new Player();
        displayer = new Displayer(this);

    } // **** end PoohFarmer() constructor ****

    // GETTERS & SETTERS

    public Chapter1 getChapter1() {
        return chapter1;
    }

    public Player getPlayer() {
        return player;
    }

    public Displayer getDisplayer() { return displayer; }

} // **** end PoohFarmer class ****