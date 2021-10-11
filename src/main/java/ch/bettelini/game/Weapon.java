package ch.bettelini.game;

/**
 *
 * @author alesa
 */
public abstract class Weapon {
    

    private int ammo = 0;
    private final int AMMO_CAPACITY =5 ;

    public int getAMMO_CAPACITY() {
        return AMMO_CAPACITY;
    }

    public Weapon(int ammoCapacity) {
        this.ammo= ammoCapacity;
    }
    
    protected abstract void makeShootNoise();
    protected abstract void makeOutOfAmmoNoise();
    protected abstract void makeReloadNoise();
    
    
    public final void shoot(){
        if (getAmmo() != 0) {
            ammo -=1;
            makeShootNoise();
        }else{
            makeOutOfAmmoNoise();
        }
    }
    
    
    public final void reload(){
        setAmmo(AMMO_CAPACITY);
        makeReloadNoise();
    }

    
    public int getAmmoCapacity(){
        return AMMO_CAPACITY;
    }
    
    public int getAmmo(){
        return this.ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    
    
    
    
    
    
    
    
    
    


    

    
    
}
