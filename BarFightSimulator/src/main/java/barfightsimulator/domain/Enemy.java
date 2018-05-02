/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barfightsimulator.domain;

/**
 *
 * @author Markus
 */
public class Enemy extends Character {

    private Player player;
    private String name;
    
    public Enemy(int x, int y, Player player, String name) {
        super(x, y);
        this.player = player;
        this.hitpoints = 5;
        this.name = name;
    }
    
    /**
     * Moves the Enemy directly towards Player.
     * @param x Player's x-coordinate
     * @param y Player's y-coordinate
     */
    public void chase(int x, int y) {
        if (alive) {
            if (this.x < x - 1) {
                this.x += 1;
            } else if (this.x > x + 1) {
                this.x -= 1;
            }

            if (this.y < y - 1) {
                this.y += 1;
            } else if (this.y > y + 1) {
                this.y -= 1;
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
    
    /**
     * First calls upon the isNextToPlayer() method to ascertain whether player
     * is adjacent. If yes, reduces the Player's hit points by 1.
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