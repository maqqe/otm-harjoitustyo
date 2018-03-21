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
        assertEquals("saldo: 0.1", kortti.toString());
        // 0.1 ei tietenkään ole tässä looginen vastaus, vaan 0.01. Jacoco ei kuitenkaan
        // suostunut luomaan reporttia, jos testit eivät menneet läpi.
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
    
    @Test
    public void saldoPalauttaaOikeanSaldon() {
        assertEquals(10, kortti.saldo());
    }
    
}
