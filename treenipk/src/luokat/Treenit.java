package luokat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fxTreenipk.SailoException;

/**
 * @author Paavo
 * @version 11.11.2022
 *
 */
public class Treenit {
    
    //private String tiedostonNimi = "";
    private final Collection<Treeni> alkiot = new ArrayList<Treeni>();
    
    
    /**
     * Alustus
     */
    public Treenit() {
        
    }
    
    
    /**
     * Lisätään treeni listaan. 
     * @param treeni lisättävä treeni
     */
    public void lisaa(Treeni treeni) {
        alkiot.add(treeni);
    }
    
    
    /**
     * Lukee viikot tiedostosta.  
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/treenit.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = fi.nextLine();
                if (s == null || s.equals("") || s.charAt(0) == ';') continue;
                Treeni treeni = new Treeni();
                treeni.parse(s); 
                lisaa(treeni);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei voida lukea tiedostoa " + nimi);
        }
    }
    
    
    /**
     * Tallentaa viikkojen tiedostoon.  
     * @param hakemisto tallennettavan tiedoston hakemisto
     * @throws SailoException jos talletus epäonnistuu
     */
    public void talleta(String hakemisto) throws SailoException {
        File ftied = new File(hakemisto + "/treenit.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (var tr : alkiot) {
                fo.println(tr.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea");
        }
    }
    
    
    /**
     * Palauttaa treenien lukumäärän
     * @return harrastusten lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }

    
    /**
     * Iteraattori treenien läpikäymiseksi.
     * @return treenien iteraattori
     */
    public Iterator<Treeni> iterator() {
        return alkiot.iterator();
    }

    
    /**
     * Hakee kaikki viikolle kuuluvat treenit.
     * @param ID viikon ID, jonka treenit halutaan
     * @return lista, jossa viitteet treeneihin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Treenit harjoitukset = new Treenit();
     *  Treeni treeni1 = new Treeni(2); harjoitukset.lisaa(treeni1);
     *  Treeni treeni2 = new Treeni(1); harjoitukset.lisaa(treeni2);
     *  Treeni treeni3 = new Treeni(2); harjoitukset.lisaa(treeni3);
     *  Treeni treeni4 = new Treeni(1); harjoitukset.lisaa(treeni4);
     *  Treeni treeni5 = new Treeni(2); harjoitukset.lisaa(treeni5);
     *  Treeni treeni6 = new Treeni(5); harjoitukset.lisaa(treeni6);
     *  
     *  List<Treeni> loytyneet;
     *  loytyneet = harjoitukset.annaTreenit(3);
     *  loytyneet.size() === 0; 
     *  loytyneet = harjoitukset.annaTreenit(1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == treeni2 === true;
     *  loytyneet.get(1) == treeni4 === true;
     *  loytyneet = harjoitukset.annaTreenit(5);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == treeni6 === true;
     * </pre> 
     */
    public List<Treeni> annaTreenit(int ID) {
        List<Treeni> loydetyt = new ArrayList<Treeni>();
        for (Treeni tr : alkiot)
            if (tr.getVko() == ID) loydetyt.add(tr);
        return loydetyt;
    }
    
    
    /**
     * Poistaa viikon kaikki treenit.
     * @param id viikko kyseessä
     * @return montako poistettiin
     */
    public int poistaViikonTreenit(int id) {
        int n = 0;
        for (Iterator<Treeni> it = alkiot.iterator(); it.hasNext();) {
            Treeni tr = it.next();
            if (tr.getID() == id) {
                it.remove();
                n++;
            }
        }
        return n;
    }
    

    /**
     * @param args ei ole
     */
    public static void main(String[] args) {
        Treenit harjoitukset = new Treenit();
        

        
        Treeni treeni1 = new Treeni();
        treeni1.rekisteroi();
        treeni1.taytaTreenilla(2);
        Treeni treeni2 = new Treeni();
        treeni2.rekisteroi();
        treeni2.taytaTreenilla(2); 
        Treeni treeni3 = new Treeni();
        treeni3.rekisteroi();
        treeni3.taytaTreenilla(2);
        Treeni treeni4 = new Treeni();
        treeni4.rekisteroi();
        treeni4.taytaTreenilla(2);

        harjoitukset.lisaa(treeni1);
        harjoitukset.lisaa(treeni2);
        harjoitukset.lisaa(treeni3);
        harjoitukset.lisaa(treeni4);
                            

        System.out.println("============= Treenit testi =================");

        List<Treeni> harjoitukset2 = harjoitukset.annaTreenit(2);

        for (Treeni tr : harjoitukset2) {
            System.out.print(tr.getVko() + " ");
            tr.tulosta(System.out);
        }
        
        try {
            harjoitukset.talleta("paavon");
        } catch (SailoException e) {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }
        
        try {
            harjoitukset.lueTiedostosta("paavon");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
