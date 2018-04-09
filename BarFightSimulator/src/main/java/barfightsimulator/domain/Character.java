/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barfightsimulator.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Markus
 */
public abstract class Character extends LocalizableObject {
    
    protected int hitpoints;
    protected boolean alive;
    
    public Character(int x, int y) {
        super(x, y);
        this.alive = true;
    }
    
    public List<LocalizableObject> searchAdjacentSquares(List<LocalizableObject> objects) {
        List<LocalizableObject> adjacentObjects = new ArrayList<>();
        
        int searchY = this.y - 1;
                
        for (int i = 0; i < 3; i++) {
            int searchX = this.x - 1;
                        
            for (int j = 0; j < 3; j++) {
                for (LocalizableObject object : objects) {
                    if (object.getX() == searchX && object.getY() == searchY) adjacentObjects.add(object);
                }
                searchX += 1;
            }
            searchY += 1;
        }
        return adjacentObjects;
    }
    
    public int getHitpoints() {
        return this.hitpoints;
    }
    
    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
        if (this.hitpoints <= 0) alive = false;
    }
    
    public boolean isAlive() {
        return this.alive;
    }
}
