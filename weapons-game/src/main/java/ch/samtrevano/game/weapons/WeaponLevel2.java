package ch.samtrevano.game.weapons;

import ch.samtrevano.game.Weapon;

public class WeaponLevel2 extends Weapon {

    public WeaponLevel2() {
        super(10, 10, 1.5f, 0.02f);
    }

    @Override
    protected void makeShootNoise() {
        System.out.println("Piu Piu (Arma 2)");
    }

    @Override
    protected void makeOutOfAmmoNoise() {
        System.out.println("Tzch Tzch (Arma 2 - out of ammo)");
    }

    @Override
    protected void makeReloadNoise() {
        System.out.println("Reloading... (Arma 2)");
    }
    
}
