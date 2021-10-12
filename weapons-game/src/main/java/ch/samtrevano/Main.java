package ch.samtrevano;

import ch.samtrevano.game.Game;

public class Main {

    public static void main(String[] args) {
        System.out.println("Press 'W' 'A' 'S' 'D' to move");
        System.out.println("Press 'F' to change the weapon (there are three)");
        System.out.println("Press 'R' to reload the weapon");
        new Game("Weapons", 750, 750);
    }

}