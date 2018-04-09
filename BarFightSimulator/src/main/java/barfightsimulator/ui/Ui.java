/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barfightsimulator.ui;

import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.LocalizableObject;
import barfightsimulator.domain.Player;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Markus
 */
public class Ui {
    
    private Player player;
    private List<LocalizableObject> objects;
    private List<Enemy> enemies;
    private Scanner reader;
    private Map<String,String> orders;
    
    public Ui(Player player, List<LocalizableObject> objects, List<Enemy> enemies, Scanner reader) {
        this.player = player;
        this.objects = objects;
        this.enemies = enemies;
        this.reader = reader;
        
        orders = new TreeMap<>();
        
        orders.put("1", "1 - Move down and left");
        orders.put("2", "2 - Move down");
        orders.put("3", "3 - Move down and right");
        orders.put("4", "4 - Move left");
        orders.put("6", "6 - Move right");
        orders.put("7", "7 - Move up and left");
        orders.put("8", "8 - Move up");
        orders.put("9", "9 - Move up and right");
                
    }
    
    public void start() {
        System.out.println("Welcome, brawler!");
        System.out.println("");
        printCommands();
        System.out.println("");
        System.out.println("If you try to move into a tile occupied by an enemy, you will attack the enemy instead\n");
        
        while (true) {
            
            if (!player.isAlive()) {
                System.out.println("You're dead, chump! Game over!");
                break;
            }
            
            if (enemies.stream().filter(e -> e.isAlive()).count() == 0) {
                System.out.println("All your enemies have perished!"
                        + " You've survived to drink another day!");
                break;
            }
            
            System.out.println("Your hitpoints: " + player.getHitpoints());
            
            System.out.println("You are at: " + player);
            
            System.out.print("Enemies at: ");
            
            enemies.stream().forEach(e -> System.out.print(e + " "));
            
            System.out.println("");
            
            System.out.print("Command: ");
            String command = reader.nextLine();
            
            if (command.equals("1")) {
                player.move(player.getX() - 1, player.getY() - 1, enemies);
            } else if (command.equals("2")) {
                player.move(player.getX(), player.getY() - 1, enemies);
            } else if (command.equals("3")) {
                player.move(player.getX() + 1, player.getY() - 1, enemies);
            } else if (command.equals("4")) {
                player.move(player.getX() - 1, player.getY(), enemies);
            } else if (command.equals("6")) {
                player.move(player.getX() + 1, player.getY(), enemies);
            } else if (command.equals("7")) {
                player.move(player.getX() - 1, player.getY() + 1, enemies);
            } else if (command.equals("8")) {
                player.move(player.getX(), player.getY() + 1, enemies);
            } else if (command.equals("9")) {
                player.move(player.getX() + 1, player.getY() + 1, enemies);
            } else {
                continue;
            }
            
            for (Enemy e : enemies) {
                e.chase(player.getX(), player.getY());
                e.attack();
            }
        }
        
    }
    
    public void printCommands() {
        orders.values().stream().forEach(System.out::println);
        
    }
    
    
}
