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

    
    public LocalizableObjectDao(String properties) {
        this.properties = new Properties();
        
        try {
            this.properties.load(new FileInputStream(properties));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }        
    }
    
    /**
     * Returns the number of maps in game. The number of maps is derived from
     * the number of entries in the file containing enemy locations.
     * @return The number of missions
     */
    
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
            return 1;
        }
        return enemyLocationList.size();
    }
    
    /**
     * A method for fetching the Enemy locations for a given map as an Array.
     * @param map the number of map
     * @return Enemy locations as a String array
     */
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
            String defaults[] = new String[1];
            defaults[0] = "1,1";
            return defaults;
        }
        
        return enemyLocationList.get(map).split(";");
    }
    
    /**
     * Fetches the names from the name file and puts them in a List. In case of
     * error, returns a list with a single entry, 'Ilja'.
     * @return A list containing enemy names
     */
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
    
    /**
     * Fetches Item locations for a given map from the Item location file and
     * puts them in a String array.
     * @param map The number of map
     * @return The Item locations as a String array.
     */
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
            String defaults[] = new String[1];
            defaults[0] = "noItems";
            return defaults;
        }
        
        return itemLocationList.get(map).split(";");
    }
    
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    
    
    
    
    
}
