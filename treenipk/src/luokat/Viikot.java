package luokat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import fxTreenipk.SailoException;

/**
 * @author Paavo
 * @version 29.10.2022
 *
 */
public class Viikot {
    
    private static final int MAX_VIIKKOJA = 53;
    private int lkm = 0;
    private Viikko[] alkiot;
    
    
    /**
     * Muodostaja, jossa luodaan max_viikkoja -pitkä alkiot-taulukko
     */
    public Viikot() {
        alkiot = new Viikko[MAX_VIIKKOJA];
    }
    
    
    /**
     * Etsii id:tä vastaavan alkion
     * @param id mitä etsitään
     * @return indeksi listassa
     */
    public int etsiID(int id) {
        for (int i = 0; i < lkm; i++) {
            if (id == alkiot[i].getID()) return i;
        }
        return -1;
    }

    
    /**
     * Lisää alkioihin viikon.
     * @param viikko lisättävä viikko
     * @throws SailoException Poikkeus, jos alkiolle ei ole tilaa taulukossa.
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Viikot viikot = new Viikot();
     * Viikko vko1 = new Viikko(), vko2 = new Viikko();
     * viikot.getLkm() === 0;
     * viikot.lisaa(vko1); viikot.getLkm() === 1;
     * viikot.lisaa(vko2); viikot.getLkm() === 2;
     * viikot.lisaa(vko1); viikot.getLkm() === 3;
     * viikot.anna(0) === vko1;
     * viikot.anna(1) === vko2;
     * viikot.anna(2) === vko1;
     * viikot.anna(1) == vko1 === false;
     * viikot.anna(1) == vko2 === true;
     * viikot.anna(3) === vko1; #THROWS IndexOutOfBoundsException
     * viikot.lisaa(vko1); viikot.getLkm() === 4;
     * viikot.lisaa(vko1); viikot.getLkm() === 5;
     * </pre>
     */
    public void lisaa(Viikko viikko) throws SailoException {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm+10);
        this.alkiot[this.lkm] = viikko;
        lkm++;
    }
    
    
    /**
     * @return alkioiden lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/viikot.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = fi.nextLine();
                if (s == null || s.equals("") || s.charAt(0) == ';') continue;
                Viikko viikko = new Viikko();
                viikko.parse(s); 
                lisaa(viikko);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei voida lukea tiedostoa " + nimi);
        }
    }
    
    
    /**
     * Tallentaa viikot tiedostoon.
     * @param hakemisto tallennettavan tiedoston hakemisto
     * @throws SailoException jos tallennus ei onnistu
     */
    public void tallenna(String hakemisto) throws SailoException {
        File ftied = new File(hakemisto + "/viikot.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < this.getLkm(); i++) {
                Viikko viikko = this.anna(i);
                fo.println(viikko.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea");
        }
    }
    
    
    /**
     * @param i monesko listan alkio
     * @return viite alkioon indeksissä i
     * @throws IndexOutOfBoundsException jos i ei ole taulukon indeksi
     */
    public Viikko anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || this.lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Poistaa viikon.
     * @param id viikon ID
     * @return 1 jos poistui, 0 jos ei poistettu mitään
     */
    public int poista(int id) {
        int indeksi = etsiID(id);
        if (indeksi < 0) return 0;
        lkm--;
        for (int i = indeksi; i < lkm; i++) {
            alkiot[i] = alkiot[i + 1];
        }
        alkiot[lkm] = null;
        return 1;
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Viikot viikot = new Viikot();
        

        
        Viikko yksi = new Viikko();
        Viikko kaksi = new Viikko();
        
        yksi.rekisteroi();
        kaksi.rekisteroi();
        yksi.taytaTiedoilla();
        kaksi.taytaTiedoilla();
        
        try {
            viikot.lisaa(yksi);
            viikot.lisaa(kaksi);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        for(int i = 0; i < viikot.getLkm(); i++) {
            Viikko viikko = viikot.anna(i);
            System.out.println("Viikon indeksi: " + i);
            viikko.tulosta(System.out);
        }
        
        try {
            viikot.tallenna("paavon");
        } catch (SailoException e) {
            //e.printStackTrace();
            System.err.println(e.getMessage());
        }
        
        try {
            viikot.lueTiedostosta("paavon");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
}
