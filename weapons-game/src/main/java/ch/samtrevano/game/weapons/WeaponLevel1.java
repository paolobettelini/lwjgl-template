package ch.samtrevano.game.weapons;

import ch.samtrevano.game.Weapon;

public class WeaponLevel1 extends Weapon {

    public WeaponLevel1() {
        super(20, 5, 2f, 0.01f);
    }

    @Override
    protected void makeShootNoise() {
        System.out.println("Piu Piu (Arma 1)");
    }

    @Override
    protected void makeOutOfAmmoNoise() {
        System.out.println("Tzch Tzch (Arma 1 - out of ammo)");
    }

    @Override
    protected void makeReloadNoise() {
        System.out.println("Reloading... (Arma 1)");
    }
    
}
