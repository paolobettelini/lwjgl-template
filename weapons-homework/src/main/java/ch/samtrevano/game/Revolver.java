package ch.samtrevano.game;

/**
 * Class representing a revolver 
 * 
 * @author Alessandro Aloise
 * @version 12.10.2021
 */
public abstract class Revolver extends Weapon {

    /**
     * Basic constructor with one parameter. 
     * @param ammoCapacity Revolver magazine capacity.
     */
    public Revolver(int ammoCapacity) {
        super(ammoCapacity);
    }

    /**
     * Revolver noise when firing.
     */
    @Override
    protected void makeShootNoise() {
        System.out.println("BANG");
    }

     /**
     * Revolver noise when out of ammo.
     */
    @Override
    protected void makeOutOfAmmoNoise() {
       System.out.println("TicTic");

    }

    /**
     * Revolver when sling is reloaded.
     */
    @Override
    protected void makeReloadNoise() {
        System.out.println("TacTacTac");
    }

}
