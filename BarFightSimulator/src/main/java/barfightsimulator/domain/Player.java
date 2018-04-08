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
public class Player extends Character {

    public Player(int x, int y) {
        super(x, y);
        this.hitpoints = 10;
    }
    
    
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    
    
    
}
