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
    
    
    public void move(int dx, int dy, List<Enemy> enemies, List<Item> items) {
        
        if (interact(dx, dy, enemies)) {
            return;
        }
        
        this.x = dx;
        this.y = dy;
        for (Item i : items) {
            if (i.getX() == dx && i.getY() == dy && this.item == null) {
                equip(i);
            }
        }
    }
    
    public boolean interact(int x, int y, List<Enemy> enemies) {
        
        for (Enemy e : enemies) {
            if (e.getX() == x && e.getY() == y) {
                attack(e);
                return true;
            }
        }
        return false;
    }
    
    public void attack(Enemy enemy) {
        enemy.setHitpoints(enemy.getHitpoints() - 1);
        if (this.item != null && this.item.getItemtype() == Itemtype.KNIFE) {
            enemy.setHitpoints(enemy.getHitpoints() - 2);
        }
    }

    
}