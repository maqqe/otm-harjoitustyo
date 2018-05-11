/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barfightsimulator.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Markus
 */
public class Enemy extends Character {

    private Player player;
    private String name;
    private List<Enemy> enemies;
    
    public Enemy(int x, int y, Player player, String name) {
        super(x, y);
        this.player = player;
        this.hitpoints = 2;
        this.name = name;
        this.enemies = new ArrayList<>();
    }
    
    /**
     * Moves the Enemy directly towards Player.
     * @param x Player's x-coordinate
     * @param y Player's y-coordinate
     */
    public void chase(int x, int y) {
        if (alive) {
            
            int newX = this.x;
            int newY = this.y;
            
            if (this.x < x - 1) {
                newX = this.x + 1;
            } else if (this.x > x + 1) {
                newX = this.x - 1;
            }

            if (this.y < y - 1) {
                newY = this.y + 1;
            } else if (this.y > y + 1) {
                newY = this.y - 1;
            }
            
            if (!checkIfTileOccupied(newX, newY)) {
                this.x = newX;
                this.y = newY;
            }
        }
    }
    
    /**
     * Checks whether Player Character is in an adjacent square.
     * @return true if Player is in an adjacent square, otherwise false.
     */
    public boolean isNextToPlayer() {
        return Math.abs(this.x - player.getX()) <= 1 && Math.abs(this.y - player.getY()) <= 1;
    }
    
    public void setEnemyList(List<Enemy> enemies) {
        this.enemies = enemies;
    }
    
    public boolean checkIfTileOccupied(int x, int y) {
        for (Enemy e : enemies) {
            if (e.getX() == x && e.getY() == y) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * First calls upon the isNextToPlayer method to ascertain whether player
     * is adjacent. If yes, reduces the Player's hit points by 1.
     * @see barfightsimulator.domain.Enemy#isNextToPlayer() 
     */
    public void attack() {
        if (isNextToPlayer() && alive) {
            player.setHitpoints(player.getHitpoints() - 1);
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + this.name + ", " + this.hitpoints + " hp";
    }
    
}