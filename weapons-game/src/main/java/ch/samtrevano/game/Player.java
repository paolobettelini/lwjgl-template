package ch.samtrevano.game;

import java.util.LinkedList;
import java.util.List;

public class Player {
    
    private List<Weapon> weapons;
    private Weapon currentWeapon;
    private int currentWeaponIndex;

    private float posX;
    private float posY;
    private float rotation;
    private int level;

    public Player() {
        this.weapons = new LinkedList<>();
        this.currentWeaponIndex = 0;
        this.level = 0;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void moveUp(float amount) {
        this.posY += amount;
    }

    public void moveDown(float amount) {
        this.posY -= amount;
    }

    public void moveLeft(float amount) {
        this.posX -= amount;
    }

    public void moveRight(float amount) {
        this.posX += amount;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public float getX() {
        return posX;
    }

    public float getY() {
        return posY;
    }

}