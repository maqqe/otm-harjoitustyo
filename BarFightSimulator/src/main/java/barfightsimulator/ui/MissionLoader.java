/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barfightsimulator.ui;

import barfightsimulator.dao.LocalizableObjectDao;
import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.Item;
import barfightsimulator.domain.Itemtype;
import barfightsimulator.domain.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Markus
 */
public class MissionLoader {
    
    private LocalizableObjectDao localizableObjectDao;
    private Player player;
    private Random rng;
    private List<String> names;
    private int missions;
    
    public MissionLoader(LocalizableObjectDao dao) {
        localizableObjectDao = dao;
        this.player = new Player(10, 10);
        this.rng = new Random();
        this.names = dao.getNames();
        this.missions = dao.getNumberOfMissions();
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public int getNumberOfMissions() {
        return this.missions;
    }
    
    /**
     * Returns a list containing Enemy coordinates for a given map. 
     * 
     * @param map The number of map
     * @return List of enemies.
     */
    public List<Enemy> getEnemyList(int map) {
        List<Enemy> enemies = new ArrayList<>();
        List<String> names = localizableObjectDao.getNames();
        
        String[] enemyLocations = localizableObjectDao.getEnemyLocations(map);
        
        for (int i = 0; i < enemyLocations.length; i++) {
            String[] enemyLocation = enemyLocations[i].split(",");
            enemies.add(new Enemy(Integer.parseInt(enemyLocation[0]), Integer.parseInt(enemyLocation[1]), player, drawName()));
        }
        return enemies;
    }
    
    
    /**
     * Returns a list containing Item coordinates for a given map. If there
     * are no items on the map, returns null.
     * 
     * 
     * @param map The number of map
     * @return If Items exist, return a List. If not, return null.
     */
    public List<Item> getItemList(int map) {
        List<Item> items = new ArrayList<>();
        
        String[] itemLocations = localizableObjectDao.getItemLocations(map);
        
        if (!itemLocations[0].equals("noItems")) {
            for (int i = 0; i < itemLocations.length; i++) {
                String[] itemLocation = itemLocations[i].split(",");
                items.add(new Item(Integer.parseInt(itemLocation[0]), Integer.parseInt(itemLocation[1]), Itemtype.valueOf(itemLocation[2])));
            }
        } else {
            return null;
        }
        return items;
    }
    
    /**
     * Fetches a random name from the name list.
     * @return A name
     */
    public String drawName() {
        return names.get(rng.nextInt(names.size()));
    }
    
    
}
