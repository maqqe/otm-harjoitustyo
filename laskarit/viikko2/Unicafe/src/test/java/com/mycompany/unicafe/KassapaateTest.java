package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
       
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    // Uuden kassan statuksen testit
    
    @Test
    public void uudessaKassassaTonniRahaa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void uudessaKassassaEiMyytyjaMaukkaita() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void uudessaKassassaEiMyytyjaEdullisia() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    // Käteismaksutestit
    
    // kassan rahamäärä kasvaa jos maksu ok?
    
    @Test
    public void syoEdullisestiKateisellaKasvattaaKassaaOikein() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKateisellaKasvattaaKassaaOikein() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    // vaihtoraha on ok?
    
    @Test
    public void syoEdullisestiPalauttaaOikeanVaihtorahanVitosesta() {
        
        assertEquals(260, kassa.syoEdullisesti(500));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaOikeanVaihtorahanVitosesta() {
        
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    // palautetaan oikea rahamäärä jos maksu liian pieni?
    
    @Test
    public void syoEdullisestiPalauttaaMaksunJosMaaraLiianPieni() {
        assertEquals(100, kassa.syoEdullisesti(100));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaMaksunJosMaaraLiianPieni() {
        assertEquals(100, kassa.syoMaukkaasti(100));
    }
    
    // kassan rahamäärä ei muutu jos maksu liian pieni?
    
    @Test
    public void syoEdullisestiEiKasvataKassaaJosMaksuLiianPieni() {
        kassa.syoEdullisesti(100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiEiKasvataKassaaJosMaksuLiianPieni() {
        kassa.syoMaukkaasti(100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    // myytyjen aterioiden määrä kasvaa jos maksu ok?
    
    @Test
    public void syoEdullisestiKasvattaaMyytyjenEdullistenMaaraa() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKasvattaaMyytyjenMaukkaidenMaaraa() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    // myytyjen aterioiden määrä ei kasva jos maksu ei ole ok?
    
    @Test
    public void syoEdullisestiEiKasvataMyytyjenMaaraaJosMaksuLiianPieni() {
        kassa.syoEdullisesti(199);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEiKasvataMyytyjenMaaraaJosMaksuLiianPieni() {
        kassa.syoMaukkaasti(199);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    // Korttimaksutestit
    
    
    
}
