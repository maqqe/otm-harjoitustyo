/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.Player;
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
public class EnemyTest {
    
    Player player;
    Enemy enemy;
    
    public EnemyTest() {
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
        enemy = new Enemy(2, 3, player);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void chaseMovesEnemyTowardsPlayerFromTopRight() {
        enemy.chase(player.getX(), player.getY());
        assertEquals(1, enemy.getX());
    }
    
    @Test
    public void chaseMovesEnemyTowardsPlayerFromBottomLeft() {
        enemy.setX(-3);
        enemy.setY(-5);
        enemy.chase(player.getX(), player.getY());
        assertEquals(-2, enemy.getX());
    }
    
    @Test
    public void attackLowersPlayerHitpointsIfAdjacent() {
        enemy.setX(1);
        enemy.setY(1);
        enemy.attack();
        assertEquals(9, player.getHitpoints());
    }
    
    @Test
    public void attackDoesNotLowerPlayerHitpointsIfNotAdjacent() {
        enemy.setX(1);
        enemy.attack();
        assertEquals(10, player.getHitpoints());
    }
    
    @Test
    public void setHitpointsChangesAliveToFalseIfNewHitpointsUnderOne() {
        enemy.setX(1);
        enemy.setY(1);
        player.setHitpoints(1);
        enemy.attack();
        assertFalse(player.isAlive());
    }
}
