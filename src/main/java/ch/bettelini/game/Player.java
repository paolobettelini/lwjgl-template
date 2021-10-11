package ch.bettelini.game;

import java.util.List;

public class Player {
    
    private List<Weapon> weaponList;
    private Weapon currentWeapon;
    private int numberWeapon=0;

    public Player() {
    }
    
    public void changeWeapon(){
       int size = weaponList.size();
        if (numberWeapon == size-1) {
            numberWeapon=0;
        }else{
            numberWeapon++;
        }
       
    } 
    
    public void  addWeapon(Weapon weapon){
        weaponList.add(weapon);
    }
    
    public void shoot(){
        currentWeapon.shoot();
    }
     
    public  void reloadWeapon(){
        currentWeapon.reload();
    }
}