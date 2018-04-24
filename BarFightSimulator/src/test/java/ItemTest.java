/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import barfightsimulator.domain.Item;
import barfightsimulator.domain.Itemtype;
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
public class ItemTest {
    
    Item knife;
    Item beer;
    
    public ItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        knife = new Item(0, 0, Itemtype.KNIFE);
        beer = new Item(0, 0, Itemtype.BEER);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void createdItemNotEquippedByDefault() {
        assertFalse(knife.isEquipped());
    }
    
    @Test
    public void getItemTypeReturnsCorrectItemType() {
        assertEquals("BEER", beer.getItemtype().toString());
    }
    
    @Test
    public void setItemTypeCorrectlySetsItemType() {
        knife.setItemtype(Itemtype.BEER);
        assertEquals("BEER", knife.getItemtype().toString());
    }
    
    @Test
    public void setEquippedCorrectlySetsEquipped() {
        knife.setEquipped(true);
        assertTrue(knife.isEquipped());
    }
    
    @Test
    public void toStringLeavesOutCoordinatesIfEquipped() {
        knife.setEquipped(true);
        assertEquals("KNIFE", knife.toString());
    }
    
    @Test
    public void toStringPrintsCoordinatesAndItemTypeIfNotEquipped() {
        assertEquals("[0, 0] KNIFE ", knife.toString());
    }
}
