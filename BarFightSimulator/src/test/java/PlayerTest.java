/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class PlayerTest {
    
    Player player;
    
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
        player.move(1, 0);
        assertEquals(player.getX(), 1);
    }
}
