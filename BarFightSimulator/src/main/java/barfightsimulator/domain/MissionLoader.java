/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barfightsimulator.domain;

import barfightsimulator.dao.LocalizableObjectDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Markus
 */
public class MissionLoader {
    
    private LocalizableObjectDao localizableObjectDao;
    private Player player;
    
    public MissionLoader(LocalizableObjectDao dao) {
        localizableObjectDao = dao;
        this.player = new Player(10, 10);
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public List<Enemy> getEnemyList(String[] enemyLocations) {
        List<Enemy> enemies = new ArrayList<>();
        
        for (int i = 0; i < enemyLocations.length; i++) {
            String[] enemyLocation = enemyLocations[i].split(",");
            enemies.add(new Enemy(Integer.parseInt(enemyLocation[0]), Integer.parseInt(enemyLocation[1]), player, "dave"));
        }
        return enemies;
    }
    
    public List<Item> getItemList(String[] itemLocations) {
        List<Item> items = new ArrayList<>();
        
        if (!itemLocations[0].equals("noItems")) {
            for (int i = 0; i < itemLocations.length; i++) {
                String[] itemLocation = itemLocations[i].split(",");
                items.add(new Item(Integer.parseInt(itemLocation[0]), Integer.parseInt(itemLocation[1]), Itemtype.valueOf(itemLocation[2])));
            }
        }
        return items;
    }
    
    
}
