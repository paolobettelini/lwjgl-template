package ch.samtrevano.game.weapons;

import ch.samtrevano.game.Weapon;

/**
 * Class representing a slingshot. 
 *  
 * @author Alessandro Aloise
 * @version 12.10.2021
 */
public class Sling extends Weapon {

    /**
     * Basic constructor with one parameter. 
     * @param ammoCapacity Sling magazine capacity.
     */
    public Sling(int ammoCapacity) {
        super(ammoCapacity);
    }
    
    /**
     * Sling noise when firing.
     */
    @Override
    protected void makeShootNoise() {
        System.out.println("piu");
    }

    /**
     * Sling noise when out of ammo.
     */
    @Override
    protected void makeOutOfAmmoNoise() {
        System.out.println("___");

    }

    /**
     * Noise when sling is reloaded.
     */
    @Override
    protected void makeReloadNoise() {
        System.out.println("  ");
    }
    

}
