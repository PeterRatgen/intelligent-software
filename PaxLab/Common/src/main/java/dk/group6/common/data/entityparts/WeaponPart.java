/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;

/**
 *
 * @author peter
 */
public class WeaponPart implements EntityPart {
    private int damage;
    private int ammo;
    private boolean space;
    private boolean playerHasWeapon = false;
    
    public void setSpace(boolean space) {
        this.space = space;
    }
    
    public WeaponPart(int ammo, int damage) {
        this.damage = damage;
        this.ammo = ammo;
    }
    
    public void attack() {
        if (getAmmo() > 0) {
            // shoot, need bullet system here
        } else {
            System.out.println("No Ammo in weapon");
        }
    }
    
    public int getAmmo() {
        return this.ammo;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean hasWeapon() {
        return playerHasWeapon;
    }

    public void playerHasWeapon(boolean hasWeapon) {
        this.playerHasWeapon = hasWeapon;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (space) {
            System.out.println("shoot");
            // implement shoot
        }
    }
}
