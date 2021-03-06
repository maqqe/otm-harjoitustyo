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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Markus
 */
public class Ui {
    
    
    private Scanner reader;
    private Map<String,String> commands;
    private MissionLoader loader;
    
    
    
    public Ui(Scanner reader, MissionLoader loader) {
        
        this.reader = reader;
        this.loader = loader;
        
        
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
    
    /**
     * Starts the text UI.
     */
    public void start() {
        System.out.println("Welcome, brawler!");
        System.out.println("");
        printCommands();
        System.out.println("");
        System.out.println("If you try to move into a tile occupied by an enemy, "
                + "you will attack the enemy instead. \n"
                + "If you move into a tile that contains an item, "
                + "you will pick up the item.\n");
        
        for (int i = 0; i < loader.getNumberOfMissions(); i++) {
            
            System.out.println("\nRound " + (i + 1) + "!");
            
            Player player = loader.getPlayer();
            List<Item> items = loader.getItemList(i);
            List<Enemy> enemies = loader.getEnemyList(i);

            while (true) {

                if (!player.isAlive()) {
                    System.out.println("You're dead, chump! Game over!");
                    return;
                }
    //            List<Enemy> enemies = loader.getEnemyList(1);

                System.out.println("\nYour hitpoints: " + player.getHitpoints());

                System.out.println("You are at: " + player + "\n");

                if (loader.getPlayer().getItem() != null) System.out.println("You hold a " + player.getItem());

                System.out.println("Enemies: ");

                enemies.stream().filter(Enemy::isAlive).forEach(System.out::println);

                System.out.println("");
                System.out.println("Items at: ");

                if (loader.getItems() != null) {
                    loader.getItems().stream().filter(item -> item.isEquipped() == false).forEach(System.out::println);
                }


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
                    if (player.getItem() != null) {
                        player.use();
                    }
                } else {
                    continue;
                }

                if (enemies.stream().filter(Enemy::isAlive).count() == 0) {
                    System.out.println("All your enemies have perished!"
                            + " You've survived to drink another day!");
                    break;
                }

                for (Enemy e : enemies) {
                    e.attack();
                    e.chase(player.getX(), player.getY());
                }
            }
        }
        
        System.out.println("\nAll missions successfully cleared!");
        
        
    }
    
    
    /**
     * Prints text UI commands.
     */
    public void printCommands() {
        commands.values().stream().forEach(System.out::println);
        
    }
}
