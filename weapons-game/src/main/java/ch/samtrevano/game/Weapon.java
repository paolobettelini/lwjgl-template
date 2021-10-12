package ch.samtrevano.game;

public abstract class Weapon {

    private final int AMMO_CAPACITY;
    private int ammo = 0;
    private int damage;
    private float distance;
    private float bulletSpeed;

    public Weapon(int ammoCapacity, int damage, float distance, float bulletSpeed) {
        AMMO_CAPACITY = ammoCapacity;

        this.damage = damage;
        this.distance = distance;
        this.bulletSpeed = bulletSpeed;

        reload();
    }
    
    protected abstract void makeShootNoise();
    protected abstract void makeOutOfAmmoNoise();
    protected abstract void makeReloadNoise();

    public final boolean shoot() {
        if (ammo == 0) {
            makeOutOfAmmoNoise();
            return false;
        }

        makeShootNoise();
        --ammo;
        return true;
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

    public int getDamage() {
        return damage;
    }

    public float getDistance() {
        return distance;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }

    @Override
    public String toString() {
        return ammo + "/" + AMMO_CAPACITY;
    }
    
}