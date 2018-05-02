/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barfightsimulator.domain;

import java.util.List;

/**
 *
 * @author Markus
 */
public class Player extends Character {
    
    

    public Player(int x, int y) {
        super(x, y);
        this.hitpoints = 10;
    }
    
    /**
     * Moves into location provided by given x and y coordinates. Checks if
     * an Enemy or Item resides at the given coordinates. If Enemy, proceeds to
     * attack the enemy. If Item, equips the item.
     * @param dx the x-coordinate to move to
     * @param dy the y-coordinate to move to
     * @param enemies List of all enemies on the map
     * @param items List of all items on the map
     */
    public void move(int dx, int dy, List<Enemy> enemies, List<Item> items) {
        
        if (interact(dx, dy, enemies)) {
            return;
        }
        
        this.x = dx;
        this.y = dy;
        if (items != null) {
            for (Item i : items) {
                if (i.getX() == dx && i.getY() == dy && this.item == null) {
                    equip(i);
                }
            }
        }
        
    }
    
    
    /**
     * Checks whether an Enemy resides at given coordinates. If yes, calls upon
     * attack method. 
     * @param x requested x-coordinate
     * @param y requested y-coordinate
     * @param enemies list of all enemies on current map.
     * @return 
     */
    public boolean interact(int x, int y, List<Enemy> enemies) {
        
        for (Enemy e : enemies) {
            if (e.getX() == x && e.getY() == y) {
                attack(e);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Lowers the hit points of a given Enemy by 1. If Player has knife equipped,
     * lowers the hit points by further 2.
     * @param enemy the Enemy that is to be attacked.
     */
    public void attack(Enemy enemy) {
        enemy.setHitpoints(enemy.getHitpoints() - 1);
        if (this.item != null && this.item.getItemtype() == Itemtype.KNIFE) {
            enemy.setHitpoints(enemy.getHitpoints() - 2);
        }
    }

    
}