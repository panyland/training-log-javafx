package fxTreenipk.test;
// Generated by ComTest BEGIN
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import fxTreenipk.*;
// Generated by ComTest END
import luokat.Treeni;
import luokat.Treenit;

/**
 * Test class made by ComTest
 * @version 2022.11.11 14:06:40 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TreenitTest {



  // Generated by ComTest BEGIN
  /** testAnnaTreenit81 */
  @Test
  public void testAnnaTreenit81() {    // Treenit: 81
    Treenit harjoitukset = new Treenit(); 
    Treeni treeni1 = new Treeni(2); harjoitukset.lisaa(treeni1); 
    Treeni treeni2 = new Treeni(1); harjoitukset.lisaa(treeni2); 
    Treeni treeni3 = new Treeni(2); harjoitukset.lisaa(treeni3); 
    Treeni treeni4 = new Treeni(1); harjoitukset.lisaa(treeni4); 
    Treeni treeni5 = new Treeni(2); harjoitukset.lisaa(treeni5); 
    Treeni treeni6 = new Treeni(5); harjoitukset.lisaa(treeni6); 
    List<Treeni> loytyneet; 
    loytyneet = harjoitukset.annaTreenit(3); 
    assertEquals("From: Treenit line: 94", 0, loytyneet.size()); 
    loytyneet = harjoitukset.annaTreenit(1); 
    assertEquals("From: Treenit line: 96", 2, loytyneet.size()); 
    assertEquals("From: Treenit line: 97", true, loytyneet.get(0) == treeni2); 
    assertEquals("From: Treenit line: 98", true, loytyneet.get(1) == treeni4); 
    loytyneet = harjoitukset.annaTreenit(5); 
    assertEquals("From: Treenit line: 100", 1, loytyneet.size()); 
    assertEquals("From: Treenit line: 101", true, loytyneet.get(0) == treeni6); 
  } // Generated by ComTest END
}