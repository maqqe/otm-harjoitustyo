/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import barfightsimulator.dao.LocalizableObjectDao;
import java.io.FileNotFoundException;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Markus
 */
public class LocalizableObjectDaoTest {
    
    LocalizableObjectDao dao;
    
    public LocalizableObjectDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dao = new LocalizableObjectDao("config.properties");
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getNamesReturnsANonEmptyList() {
        assertFalse(dao.getNames().isEmpty());
    }
    
//    @Rule
//    public ExpectedException expectedEx = ExpectedException.none(); 
//    
//    @Test
//    public void errorPrintedOutIfProblemLoadingConfigFile() {
//        Throwable caught = null;
//        try {
//            new LocalizableObjectDao("");
//        } catch (Throwable t) {
//            caught = t;
//        }
//        
//        assertNotNull(caught);
//    } 
    
    @Test
    public void getNamesReturnsListContainingOnlyOneEntryIfErrorInLoadingEnemyNameFile() {
        dao.setProperties(new Properties());
        assertEquals(1, dao.getNames().size());
    }
    
    @Test
    public void getItemLocationsReturnsCorrectCoordinates() {
        assertEquals("noItems", dao.getItemLocations(0)[0]);
    }
    
    @Test
    public void getEnemyLocationsReturnsACorrectCoordinates() {
        assertEquals("1,1", dao.getEnemyLocations(0)[0]);
    }
    
    @Test
    public void getEnemyLocationsReturnsNoItemsIfErrorLoadingFile() {
        dao.setProperties(new Properties());
        assertEquals("noItems", dao.getItemLocations(0)[0]);
    }
    
    @Test
    public void getEnemyLocationsReturnsASingleDefaultEnemyIfErrorLoadingFile() {
        dao.setProperties(new Properties());
        assertEquals("1,1", dao.getEnemyLocations(0)[0]);
    }
    
    @Test
    public void getNumberOfMissionsReturnsCorrectInteger() {
        assertEquals(3, dao.getNumberOfMissions());
    }
    
    @Test
    public void getNumberOfMissionsReturnsCorrectIntegerIfErrorLoadingFile() {
        dao.setProperties(new Properties());
        assertEquals(1, dao.getNumberOfMissions());
    }
    
//    @Test
//    public void getNamesCropsNamesFromFileIfLongerThanTwentyCharacters() {
//        LocalizableObjectDao fakedao = new LocalizableObjectDao("test/config.properties");
//        assertEquals("aaaaaaaaaaaaaaaaaaaa", fakedao.getNames().get(0));
//    }
    
    
}
