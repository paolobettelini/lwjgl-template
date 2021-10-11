package ch.bettelini.game;

import java.util.LinkedList;
import java.util.List;

public class Player {
    
    private List<Weapon> weapons;
    private Weapon currentWeapon;

    public Player() {
        this.weapons = new LinkedList<>();
    }
    
    public void changeWeapon(int index) {
        currentWeapon = weapons.get(index);
    }
    
    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }
    
    public void shoot() {
        currentWeapon.shoot();
    }
     
    public void reloadWeapon(){
        currentWeapon.reload();
    }

    // roba in più

    private float posX;
    private float posY;
    private float rotation;
    private int level;

    public int getLevel() {
        return level;
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

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

}