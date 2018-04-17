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
public class Item extends LocalizableObject {
    
    private boolean equipped;
    private Itemtype type;
    
    public Item(int x, int y, Itemtype type) {
        super(x, y);
        this.type = type;
    }
    
    public boolean isEquipped() {
        return this.equipped;
    }
    
    public void setEquipped(boolean state) {
        this.equipped = state;
    }
    
    public Itemtype getItemtype() {
        return this.type;
    }
    
    public void setItemtype(Itemtype type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "] " + type + " ";
    }
}
