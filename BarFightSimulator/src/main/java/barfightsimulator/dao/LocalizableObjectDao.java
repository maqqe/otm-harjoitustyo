/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barfightsimulator.dao;

import barfightsimulator.domain.Enemy;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Markus
 */
public class LocalizableObjectDao {
    
    private Properties properties;

    
    public LocalizableObjectDao() {
        this.properties = new Properties();
        
        try {
            this.properties.load(new FileInputStream("config.properties"));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }        
    }
    
    
    // figure out something better for this
    public int getNumberOfMissions() {
        String enemyLocations = properties.getProperty("enemyLocationsFile");        
        List<String> enemyLocationList = new ArrayList<>();
        
        try {
            Scanner reader = new Scanner(new File(enemyLocations));
            while (reader.hasNext()) {
                enemyLocationList.add(reader.next());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return enemyLocationList.size();
    }
    
    public String[] getEnemyLocations(int map) {
    
        String enemyLocations = properties.getProperty("enemyLocationsFile");        
        List<String> enemyLocationList = new ArrayList<>();
        
        try {
            Scanner reader = new Scanner(new File(enemyLocations));
            while (reader.hasNext()) {
                enemyLocationList.add(reader.next());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return enemyLocationList.get(map).split(";");
    }
    
    public List<String> getNames() {
        
        String names = properties.getProperty("enemyNameFile");
        List<String> nameList = new ArrayList<>();
        
        try {
            Scanner reader = new Scanner(new File(names));
            while (reader.hasNext()) {
                String name = reader.next();
                if (name.length() > 20) {
                    nameList.add(name.substring(0, 20));
                } else {
                    nameList.add(name);
                }                
            }
        } catch (Exception e) {
            System.out.println("Error: " + e + "\nAll enemies will be named Ilja");
            nameList.add("Ilja");
        }
        
        return nameList;
    }
    
    public String[] getItemLocations(int map) {
        String itemLocations = properties.getProperty("itemLocationsFile");
        List<String> itemLocationList = new ArrayList<>();
        
        try {
            Scanner reader = new Scanner(new File(itemLocations));
            while (reader.hasNext()) {
                itemLocationList.add(reader.next());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return itemLocationList.get(map).split(";");
    }
    
    
    
    
    
}
