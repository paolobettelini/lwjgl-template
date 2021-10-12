package ch.samtrevano;

import ch.samtrevano.game.Weapon;
import ch.samtrevano.game.weapons.BrokenWeapon;
import ch.samtrevano.game.weapons.ColtDragon;

public class Main {

    public static void main(String[] args) {
        Weapon weapon1 = new ColtDragon();
        
        for (int i = 0; i < 7; i++) {
            weapon1.shoot();
        }

        Weapon broken = new BrokenWeapon(0);
        broken.shoot();
        broken.shoot();

        System.out.println("Ammo Capacity:\t" + broken.getAmmoCapacity());
        System.out.println("Ammo:\t" + broken.getAmmo());
    }

}