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
    
    public Enemy(int x, int y, Player player) {
        super(x, y);
        this.player = player;
        this.hitpoints = 5;
    }
    
    
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
    
    public boolean isNextToPlayer() {
        return Math.abs(this.x - player.getX()) <= 1 && Math.abs(this.y - player.getY()) <= 1;
    }
    
    public void attack() {
        if (isNextToPlayer() && alive) player.setHitpoints(player.getHitpoints() - 1);
    }
    
}