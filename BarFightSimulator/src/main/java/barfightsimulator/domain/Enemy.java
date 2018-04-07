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
    }
    
    
    public void move(int x, int y) {
        if (this.x - x > 0) {
            this.x -= 1;
        } else if (this.x - x < 0) {
            this.x += 1;
        } else if (this.y - y > 0) {
            this.y -= 1;
        } else if (this.y - y < 0) {
            this.y += 1;
        }
    }
    
    
}