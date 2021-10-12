package ch.samtrevano.game.weapons;

import ch.samtrevano.game.Weapon;

public class BrokenWeapon extends Weapon{

    /**
     * Basic constructor with one parameter. 
     * @param ammoCapacity Revolver magazine capacity.
     */
    public BrokenWeapon(int ammoCapacity) {
        super(ammoCapacity);
    }

    /**
     * BrokenWeapon noise when firing.
     */
    @Override
    protected void makeShootNoise() {
        System.out.println("BANGGGG");
        
    }

    /**
     * BrokenWeapon noise when out of ammo.
     */
    @Override
    protected void makeOutOfAmmoNoise() {
        System.out.println("TicTic");
    }

   /**
     * Noise when BrokenWeapon is reloaded.
     */
    @Override
    protected void makeReloadNoise() {
        System.out.println("TacTacTac");
        
    }

    /**
     * Rewrite getAmmo to try to break it.
     */
    @Override
    public int getAmmo() {
        return Integer.MAX_VALUE;
    }

    /**
     *  Rewrite getAmmoCapacity to try to break it.
     */
    @Override
    public int getAmmoCapacity() {
        return Integer.MAX_VALUE;
    }
    
}
