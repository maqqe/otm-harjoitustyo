package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLisaysKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi() {
        kortti.otaRahaa(9);
        assertEquals("saldo: 0.01", kortti.toString());
        // Tästä testistä tulee hylsy, koska maksukorttiluokan toString on "väärin".
        // Saldo vähenee aivan oikein yhteen senttiin, mutta toString metodi on luotu siten,
        // että tässä saa kuvan, että saldoa on itseasiassa 10 senttiä.
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosRahaaTarpeeksi() {
        assertTrue(kortti.otaRahaa(10));
    } 
    
    @Test
    public void otaRahaaPalauttaaFalseJosRahaaLiianVahan() {
        assertFalse(kortti.otaRahaa(1000));
    }
    
}
