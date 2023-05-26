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
 * @version 12.11.2022
 *
 */
public class Liikkeet {
    
    //private String tiedostonNimi = "";
    private final Collection<Liike> alkiot = new ArrayList<Liike>();
    
    
    /**
     * Alustus
     */
    public Liikkeet() {
        //
    }
    
    /**
     * Lisätään liike listaan. 
     * @param liike lisättävä liike
     */
    public void lisaa(Liike liike) {
        alkiot.add(liike);
    }
    
    
    /**
     * Lukee viikot tiedostosta.  
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException { 
        String nimi = hakemisto + "/liikkeet.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = fi.nextLine();
                if (s == null || s.equals("") || s.charAt(0) == ';') continue;
                Liike liike = new Liike();
                liike.parse(s); 
                lisaa(liike);
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
        File ftied = new File(hakemisto + "/liikkeet.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (var l : alkiot) {
                fo.println(l.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea");
        }
    }
    
    
    /**
     * Palauttaa liikkeiden lukumäärän
     * @return harrastusten lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }
    
   
    /**
     * Iteraattori liikkeiden läpikäymiseksi.
     * @return treenien iteraattori
     */
    public Iterator<Liike> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * @param ID treenin ID, jonka liikkeet halutaan
     * @return lista, jossa viitteet liikkeisiin
     */
    public List<Liike> annaLiikkeet(int ID) {
        List<Liike> loydetyt = new ArrayList<Liike>();
        for (Liike lk : alkiot)
            if (lk.getTreeni() == ID) loydetyt.add(lk);
        return loydetyt;
    }
    
    
    /**
     * Poistaa treenin kaikki liikkeet.
     * @param id treeni kyseessä
     * @return montako poistettiin
     */
    public int poistaTreeninLiikkeet(int id) {
        int n = 0;
        for (Iterator<Liike> it = alkiot.iterator(); it.hasNext();) {
            Liike liike = it.next();
            if (liike.getID() == id) {
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
        Liikkeet liikkeet = new Liikkeet();
        

        
        Liike liike1 = new Liike();
        liike1.rekisteroi();
        liike1.taytaLiikkeella(2);
        Liike liike2 = new Liike();
        liike2.rekisteroi();
        liike2.taytaLiikkeella(2);
        Liike liike3 = new Liike();
        liike3.rekisteroi();
        liike3.taytaLiikkeella(2);
        Liike liike4 = new Liike();
        liike4.rekisteroi();
        liike4.taytaLiikkeella(2);

        liikkeet.lisaa(liike1);
        liikkeet.lisaa(liike2);
        liikkeet.lisaa(liike3);
        liikkeet.lisaa(liike4);
                            

        System.out.println("============= Liikkeiden testi =================");

        List<Liike> liikkeet2 = liikkeet.annaLiikkeet(2);

        for (Liike l : liikkeet2) {
            System.out.print(l.getTreeni() + " ");
            l.tulosta(System.out);
        }
        
        try {
            liikkeet.talleta("paavon");
        } catch (SailoException e) {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }
        
        try {
            liikkeet.lueTiedostosta("paavon");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
