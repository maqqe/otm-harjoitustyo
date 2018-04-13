/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.LocalizableObject;
import barfightsimulator.domain.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Markus
 */
public class PlayerTest {
    
    Player player;
    List<Enemy> enemies;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        player = new Player(0, 0);
        enemies = new ArrayList<>();
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     
    @Test
    public void getXReturnsCorrectX() {
        assertEquals(player.getX(), 0);        
    }
    
    @Test
    public void getYReturnsCorrectY() {
        assertEquals(player.getY(), 0);   
    }
    
    @Test
    public void moveCorrectlyChangesPlayerPosition() {
        player.move(1, 0, enemies);
        assertEquals(player.getX(), 1);
    }
    
    @Test
    public void setXCorrectlySetsX() {
        player.setX(3);
        assertEquals(player.getX(), 3);
    }
    
    @Test
    public void setYCorrectlySetsY() {
        player.setY(3);
        assertEquals(player.getY(), 3);
    }
    
    @Test
    public void toStringReturnsCorrectString() {
        assertEquals(player.toString(), "[0, 0]");
    }
    
    @Test
    public void searchAdjacentTilesFindsAdjacentLocalizableObjects() {
        Enemy e = new Enemy(1, 1, player);
        List<LocalizableObject> objects = new ArrayList<>();
        objects.add(e);
        objects.add(player);
        assertEquals(2, player.searchAdjacentTiles(objects).size());
    }
    
    @Test
    public void interactAttacksIfEnemyAtTile() {
        Enemy e = new Enemy(0, 1, player);
        
        enemies.add(e);
        player.interact(0, 1, enemies);
        assertEquals(4, e.getHitpoints());
    }
}
