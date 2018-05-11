/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import barfightsimulator.dao.LocalizableObjectDao;
import barfightsimulator.domain.Enemy;
import barfightsimulator.domain.Item;
import barfightsimulator.domain.Itemtype;
import barfightsimulator.ui.MissionLoader;
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
public class MissionLoaderTest {
    
    MissionLoader ml;
    
    public MissionLoaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ml = new MissionLoader(new LocalizableObjectDao("config.properties"));
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void loadMissionLoadsEnemies() {
        ml.loadMission(1);
        assertEquals(1, ml.getEnemies().size());
    }
    
    @Test
    public void getItemListReturnsNullIfFileReadoutForMissionIsnoItems() {
        assertNull(ml.getItemList(0));
    }
    
    @Test
    public void getItemListReturnsANonNullListIfItemsAvailableForMission() {
        assertEquals(2, ml.getItemList(1).size());
    }
    
    @Test
    public void getItemsReturnsItemList() {
        ml.loadMission(1);
        assertNull(ml.getItems());
    }

    
    @Test
    public void getPlayerReturnsPlayer() {
        ml.loadMission(1);
        assertEquals("class barfightsimulator.domain.Player", ml.getPlayer().getClass().toString());
    }
    
    @Test
    public void isAllMissionCompletedFalseByDefault() {
        assertFalse(ml.isAllMissionsCompleted());
    }
    
    @Test
    public void isongoingTrueByDefault() {
        assertTrue(ml.isOngoing());
    }
    
    @Test
    public void getNumberOfMissionsReturnsCorrectNumberOfMissions() {
        assertEquals(3, ml.getNumberOfMissions());
    }
    
    @Test
    public void setOngoingChangesOngoing() {
        ml.setOngoing(false);
        assertFalse(ml.isOngoing());
    }
    
    @Test
    public void playTurnMovesPlayerIfInputCorrect() {
        ml.loadMission(1);
        ml.playTurn("w");
        assertEquals(8, ml.getPlayer().getY());
    }
    
    @Test
    public void playTurnProceedsToNextMissionIfAllEnemiesEliminated() {
        ml.loadMission(1);
        ml.playTurn("q");
        ml.playTurn("q");
        ml.playTurn("q");
        ml.playTurn("q");
        ml.playTurn("q");
        ml.playTurn("q");
        ml.playTurn("q");
        ml.playTurn("q");
        ml.playTurn("q");
        ml.playTurn("q");       
        assertEquals(2, ml.getMission());
    }
    
    @Test
    public void playTurnCommandsCorrectlyMovePlayer() {
        
        ml.playTurn("q");
        ml.playTurn("w");
        ml.playTurn("e");
        ml.playTurn("a");
        ml.playTurn("d");
        ml.playTurn("z");
        ml.playTurn("x");
        ml.playTurn("c");
        assertEquals(12, ml.getPlayer().getX());
    }
    
    @Test
    public void playTurnSetsAllMissionsCompletedToTrueIfLastMissionCompleted() {
        ml.loadMission(3);
        
        for (Enemy e : ml.getEnemies()) {
            e.setAlive(false);
            
        }
        
        ml.playTurn("q");
        
        assertTrue(ml.isAllMissionsCompleted());
    }
    
    @Test
    public void playTurnSetsOngoingToFalseIfPlayerHitPointsUnderOne() {
        ml.loadMission(1);
        ml.getPlayer().setHitpoints(0);
        ml.playTurn("s");
        assertFalse(ml.isOngoing());
    }
    
    @Test
    public void playTurnUsesEnemyItemIfInputCorrect() {
        Item i = new Item(0, 0, Itemtype.BEER);
        ml.getPlayer().setItem(i);
        ml.playTurn("u");
        assertNull(ml.getPlayer().getItem());
    }
    
//    @Test
//    public void setEnemiesSetsEnemyList() {
//        ml.setEnemies(null);
//        assertNull(ml.getEnemies());
//    }
//    
//    @Test
//    public void setItemsSetsItemList() {
//        ml.setItems(null);
//        assertNull(ml.getItems());
//    }
    
    @Test
    public void faultyCommandInputDoesNotProgressGame() {
        ml.loadMission(1);
        ml.playTurn("å");
        assertEquals(9, ml.getPlayer().getX());
    }
            
    
}
