/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.Item;
import barfightsimulator.domain.Itemtype;
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
    List<Item> items;
    Item knife;
    Item beer;
    Enemy enemy;
    
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
        items = new ArrayList<>();
        knife = new Item(1, 0, Itemtype.KNIFE);
        beer = new Item(0, 1, Itemtype.BEER);
        enemy = new Enemy(1, 1, player, "test");
        enemies.add(enemy);
        items.add(beer);
        items.add(knife);
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
        player.move(1, 0, enemies, items);
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
        Enemy e = new Enemy(1, 1, player, "test");
        List<LocalizableObject> objects = new ArrayList<>();
        objects.add(e);
        objects.add(player);
        assertEquals(2, player.searchAdjacentTiles(objects).size());
    }
    
    @Test
    public void interactAttacksIfEnemyAtTile() {
        Enemy e = new Enemy(0, 1, player, "test");
        
        enemies.add(e);
        player.interact(0, 1, enemies);
        assertEquals(4, e.getHitpoints());
    }
    
    @Test
    public void ifPlayerHasKnifeEnemyLosesThreeHitpointsIfAttackedByPlayer() {
        Enemy e = new Enemy(0, 1, player, "test");
        player.equip(knife);
        enemies.add(e);
        player.interact(0, 1, enemies);
        assertEquals(2, e.getHitpoints());
    }
    
    @Test
    public void moveDoesNotChangePlayerCoordinatesIfEnemyAtTargetTile() {
        player.move(1, 1, enemies, items);
        assertEquals("[0, 0]", player.toString());
    }
    
    @Test
    public void movingIntoTileWithItemEquipsItem() {
        player.move(1, 0, enemies, items);
        assertEquals("KNIFE", player.getItem().toString());
    }
    
    @Test
    public void equipEquipsParameterItem() {
        player.equip(beer);
        assertEquals("BEER", player.getItem().toString());
    }
    
    @Test
    public void useIncreasesHealthIfBeerEquipped() {
        player.equip(beer);
        player.use();
        assertEquals(11, player.getHitpoints());
    }
    
    @Test
    public void getItemReturnsNullIfNoItemEquipped() {
        assertEquals(null, player.getItem());
    }
}
