package ch.samtrevano.game;

import java.util.LinkedList;
import java.util.List;

public class Player {
    
    private List<Weapon> weapons;
    private Weapon currentWeapon;
    private int currentWeaponIndex;

    public Player() {
        this.weapons = new LinkedList<>();
        this.currentWeaponIndex = 0;
    }
    
    public void changeWeapon() {
        if (++currentWeaponIndex >= weapons.size()) {
            currentWeaponIndex = 0;
        }

        currentWeapon = weapons.get(currentWeaponIndex);
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }
    
    public void shoot() {
        if (currentWeapon == null) {
            return;
        }

        currentWeapon.shoot();
    }
     
    public void reloadWeapon(){
        if (weapons == null) {
            return;
        }

        currentWeapon.reload();
    }

}