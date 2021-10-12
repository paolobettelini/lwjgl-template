package ch.samtrevano.game;

/**
 * Weapon class - represents a weapon
 * 
 * @author Paolo Bettelini
 * @author Alessandro Aloise
 * @version 12.10.2021
 */
public abstract class Weapon {

    private final int AMMO_CAPACITY;
    private int ammo = 0;

    public Weapon(int ammoCapacity) {
        AMMO_CAPACITY = ammoCapacity;
    }
    
    protected abstract void makeShootNoise();
    protected abstract void makeOutOfAmmoNoise();
    protected abstract void makeReloadNoise();

    public final void shoot() {
        if (ammo == 0) {
            makeOutOfAmmoNoise();
        } else {
            makeShootNoise();
            --ammo;
        }
    }
    
    public final void reload() {
        ammo = AMMO_CAPACITY;
        makeReloadNoise();
    }
    
    public int getAmmo() {
        return this.ammo;
    }

    public int getAmmoCapacity() {
        return AMMO_CAPACITY;
    }

    @Override
    public String toString() {
        return ammo + "/" + AMMO_CAPACITY;
    }
    
}