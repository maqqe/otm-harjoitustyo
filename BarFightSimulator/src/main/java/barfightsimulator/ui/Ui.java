/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barfightsimulator.ui;

import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.Item;
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
    private Map<String,String> commands;
    private List<Item> items;
    
    public Ui(Player player, List<LocalizableObject> objects, List<Enemy> enemies, Scanner reader, List<Item> items) {
        this.player = player;
        this.objects = objects;
        this.enemies = enemies;
        this.reader = reader;
        this.items = items;
        
        commands = new TreeMap<>();
        
        commands.put("1", "1 - Move down and left");
        commands.put("2", "2 - Move down");
        commands.put("3", "3 - Move down and right");
        commands.put("4", "4 - Move left");
        commands.put("5", "5 - Hold at current position");
        commands.put("6", "6 - Move right");
        commands.put("7", "7 - Move up and left");
        commands.put("8", "8 - Move up");
        commands.put("9", "9 - Move up and right");
        commands.put("0", "0 - Use item");
                
    }
    
    public void start() {
        System.out.println("Welcome, brawler!");
        System.out.println("");
        printCommands();
        System.out.println("");
        System.out.println("If you try to move into a tile occupied by an enemy, "
                + "you will attack the enemy instead. \n"
                + "If you move into a tile that contains an item, "
                + "you will pick up the item.\n");
        
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
            
            System.out.println("\nYour hitpoints: " + player.getHitpoints());
            
            System.out.println("You are at: " + player);
            
            if (player.getItem() != null) System.out.println("You hold a " + player.getItem());
            
            System.out.print("Enemies at: ");
            
            enemies.stream().filter(Enemy::isAlive).forEach(e -> System.out.print(e + " hp " + e.getHitpoints() + "  "));
            
            System.out.println("");
            System.out.print("Items at: ");
            
            items.stream().filter(i -> i.isEquipped() == false).forEach(i -> System.out.print(i + " "));
            
            System.out.println("");         
            
            System.out.print("Command: ");
            String command = reader.nextLine();
            
            if (command.equals("1")) {
                player.move(player.getX() - 1, player.getY() - 1, enemies, items);
            } else if (command.equals("2")) {
                player.move(player.getX(), player.getY() - 1, enemies, items);
            } else if (command.equals("3")) {
                player.move(player.getX() + 1, player.getY() - 1, enemies, items);
            } else if (command.equals("4")) {
                player.move(player.getX() - 1, player.getY(), enemies, items);
            } else if (command.equals("5")) {
                player.move(player.getX(), player.getY(), enemies, items);
            } else if (command.equals("6")) {
                player.move(player.getX() + 1, player.getY(), enemies, items);
            } else if (command.equals("7")) {
                player.move(player.getX() - 1, player.getY() + 1, enemies, items);
            } else if (command.equals("8")) {
                player.move(player.getX(), player.getY() + 1, enemies, items);
            } else if (command.equals("9")) {
                player.move(player.getX() + 1, player.getY() + 1, enemies, items);
            } else if (command.equals("0")) {
                player.move(player.getX() + 1, player.getY() + 1, enemies, items);
            } else {
                continue;
            }
            
            for (Enemy e : enemies) {
                e.attack();
                e.chase(player.getX(), player.getY());
            }
        }
        
    }
    
    public void printCommands() {
        commands.values().stream().forEach(System.out::println);
        
    }
    
    
}
