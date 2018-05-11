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
    private List<Enemy> enemies;
    private List<Item> items;
    private int mission;
    private boolean ongoing;
    private boolean allMissionsCompleted;
    
    public MissionLoader(LocalizableObjectDao dao) {
        localizableObjectDao = dao;
        this.player = new Player(10, 10);
        this.rng = new Random();
        this.names = dao.getNames();
        this.missions = dao.getNumberOfMissions();
        this.enemies = new ArrayList();
        this.items = new ArrayList();
        this.mission = 1;
        this.ongoing = true;
        this.allMissionsCompleted = false;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public boolean isAllMissionsCompleted() {
        return this.allMissionsCompleted;
    }
    
    public boolean isOngoing() {
        return this.ongoing;
    }
    
    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }
    
    public int getNumberOfMissions() {
        return this.missions;
    }
    
    /**
     * Plays a turn. First, moves player if possible or attacks enemy. Then, 
     * ends game if all enemies dead. Then, moves enemies and makes them 
     * attack if possible. Then ends game if player dead.
     * @param command requested command
     */
    
    public void playTurn(String command) {
        if (command.equals("q")) {
            
            getPlayer().move(getPlayer().getX() - 1, getPlayer().getY() - 1, enemies, items);
        } else if (command.equals("w")) {
            getPlayer().move(getPlayer().getX(), getPlayer().getY() - 1, enemies, items);
        } else if (command.equals("e")) {
            getPlayer().move(getPlayer().getX() + 1, getPlayer().getY() - 1, enemies, items);
        } else if (command.equals("a")) {
            getPlayer().move(getPlayer().getX() - 1, getPlayer().getY(), enemies, items);
        } else if (command.equals("s")) {
            getPlayer().move(getPlayer().getX(), getPlayer().getY(), enemies, items);
        } else if (command.equals("d")) {
            getPlayer().move(getPlayer().getX() + 1, getPlayer().getY(), enemies, items);
        } else if (command.equals("z")) {
            getPlayer().move(getPlayer().getX() - 1, getPlayer().getY() + 1, enemies, items);
        } else if (command.equals("x")) {
            getPlayer().move(getPlayer().getX(), getPlayer().getY() + 1, enemies, items);
        } else if (command.equals("c")) {
            getPlayer().move(getPlayer().getX() + 1, getPlayer().getY() + 1, enemies, items);
        } else if (command.equals("u")) {
            if (getPlayer().getItem() != null) {
                getPlayer().use();
            }
        } else {
            return;
        }
        
        if (enemies.stream().filter(Enemy::isAlive).count() == 0 && this.mission < getNumberOfMissions()) {
            this.mission++;
            enemies = getEnemyList(mission - 1);
            items = getItemList(mission - 1);
            getPlayer().setHitpoints(10);
            getPlayer().setX(10);
            getPlayer().setY(10);
            getPlayer().setItem(null);
            
        } else if ((enemies.stream().filter(Enemy::isAlive).count() == 0 && this.mission == getNumberOfMissions())) {
            this.allMissionsCompleted = true;
        }
        
        
        for (Enemy e : enemies) {
            e.attack();
            e.chase(getPlayer().getX(), getPlayer().getY());
        }
        
        if (getPlayer().getHitpoints() <= 0) {
            
            this.ongoing = false;
            
        }
    }
    
    public List<Enemy> getEnemies() {
        return this.enemies;
    }
    
    public List<Item> getItems() {
        return this.items;
    }
    
    /**
     * Loads a mission. Changes object parameters to reflect new mission.
     * @param mission number of mission
     */
    
    public void loadMission(int mission) {
        this.mission = mission;
        this.player = new Player(9, 9);
        this.enemies = getEnemyList(mission - 1);
        this.items = getItemList(mission - 1);
        
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
        
        for (Enemy e : enemies) {
            e.setEnemyList(enemies);
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
    
    public int getMission() {
        return this.mission;
    }
    
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
}
