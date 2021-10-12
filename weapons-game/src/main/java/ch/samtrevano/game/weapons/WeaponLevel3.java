package ch.samtrevano.game.weapons;

import ch.samtrevano.game.Weapon;

public class WeaponLevel3 extends Weapon {

    public WeaponLevel3() {
        super(5, 20, 1.0f, 0.03f);
    }

    @Override
    protected void makeShootNoise() {
        System.out.println("Piu Piu (Arma 3)");
    }

    @Override
    protected void makeOutOfAmmoNoise() {
        System.out.println("Tzch Tzch (Arma 3 - out of ammo)");
    }

    @Override
    protected void makeReloadNoise() {
        System.out.println("Reloading... (Arma 3)");
    }
    
}
