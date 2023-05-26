package luokat;

import java.io.File;
import java.util.List;
import fxTreenipk.SailoException;

/**
 * @author Paavo
 * @version 29.10.2022
 *
 */
public class Treenipk {
    
     private Viikot viikot = new Viikot();
     private Treenit treenit = new Treenit();
     private Liikkeet liikkeet = new Liikkeet();
     
     private String hakemisto = "paavon";
     
     
    /**
     * Lisää uuden viikon
     * @param viikko Lisättävä viikko
     * @throws SailoException lisäämisen epäonnistuessa
     */
    public void lisaa(Viikko viikko) throws SailoException {
        viikot.lisaa(viikko);       
    }
    
    
    /**
     * Lisää uuden treenin
     * @param treeni Lisättävä treeni
     * @throws SailoException lisäämisen epäonnistuessa
     */
    public void lisaa(Treeni treeni) throws SailoException {
        treenit.lisaa(treeni);       
    }
    
    
    /**
     * Lisää uuden liikkeen
     * @param liike Lisättävä liike
     * @throws SailoException lisäämisen epäonnistuessa
     */
    public void lisaa(Liike liike) throws SailoException {
        liikkeet.lisaa(liike);       
    }
     
    
    /**
     * @return viikkojen määrä
     */
    public int getViikkoja() {
        return viikot.getLkm();
    }
    
    
    /**
     * @param i monesko viikko annetaan
     * @return monesko viikko
     */
    public Viikko annaViikko(int i) {      
        return viikot.anna(i);
    }
    
    
    /**
     * Palauttaa viikon treenit
     * @param viikko viikko, jonka treenit haetaan
     * @return viikon treenit
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Treenipk treenipk = new Treenipk();
     *  Viikko vko1 = new Viikko(), vko2 = new Viikko(), vko3 = new Viikko();
     *  vko1.rekisteroi(); vko2.rekisteroi(); vko3.rekisteroi();
     *  int id1 = vko1.getID();
     *  int id2 = vko2.getID();
     *  Treeni treeni1 = new Treeni(id1); treenipk.lisaa(treeni1); 
     *  Treeni treeni2 = new Treeni(id1); treenipk.lisaa(treeni2); 
     *  Treeni treeni3 = new Treeni(id2); treenipk.lisaa(treeni3); 
     *  Treeni treeni4 = new Treeni(id2); treenipk.lisaa(treeni4); 
     *  Treeni treeni5 = new Treeni(id2); treenipk.lisaa(treeni5);
     *  
     *  List<Treeni> loytyneet;
     *  loytyneet = treenipk.annaTreenit(vko3);
     *  loytyneet.size() === 0; 
     *  loytyneet = treenipk.annaTreenit(vko1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == treeni1 === true;
     *  loytyneet.get(1) == treeni2 === true;
     *  loytyneet = treenipk.annaTreenit(vko2);
     *  loytyneet.size() === 3; 
     *  loytyneet.get(0) == treeni3 === true;
     * </pre> 
     */
    public List<Treeni> annaTreenit(Viikko viikko) {
        return treenit.annaTreenit(viikko.getID());
    }
    
    
    /**
     * Palauttaa treenin liikkeet.
     * @param treeni treeni, jonka liikkeet haetaan
     * @return lista liikkeistä
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  Treenipk treenipk = new Treenipk();
     *  Treeni treeni1 = new Treeni(), treeni2 = new Treeni(), treeni3 = new Treeni();
     *  treeni1.rekisteroi(); treeni2.rekisteroi(); treeni3.rekisteroi();
     *  int id1 = treeni1.getID();
     *  int id2 = treeni2.getID();
     *  Liike liike1 = new Liike(id1); treenipk.lisaa(liike1); 
     *  Liike liike2 = new Liike(id1); treenipk.lisaa(liike2); 
     *  Liike liike3 = new Liike(id2); treenipk.lisaa(liike3); 
     *  Liike liike4 = new Liike(id2); treenipk.lisaa(liike4); 
     *  Liike liike5 = new Liike(id2); treenipk.lisaa(liike5);
     *  
     *  List<Liike> loytyneet;
     *  loytyneet = treenipk.annaLiikkeet(treeni3);
     *  loytyneet.size() === 0; 
     *  loytyneet = treenipk.annaLiikkeet(treeni1);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == liike1 === true;
     *  loytyneet.get(1) == liike2 === true;
     *  loytyneet = treenipk.annaLiikkeet(treeni2);
     *  loytyneet.size() === 3; 
     *  loytyneet.get(0) == liike3 === true;
     * </pre> 
     */
    public List<Liike> annaLiikkeet(Treeni treeni) {
        return liikkeet.annaLiikkeet(treeni.getID());
        
    }
    
    
    /**
     * Lukee päiväkirjan tiedot tiedostosta.
     * @param nimi tätä käytetään lukemisessa
     * @throws SailoException jos epäonnistuu
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        File ftied = new File(nimi);
        ftied.mkdir();
        viikot = new Viikot();
        treenit = new Treenit();
        liikkeet = new Liikkeet();
        
        hakemisto = nimi;
        viikot.lueTiedostosta(nimi);
        treenit.lueTiedostosta(nimi);
        liikkeet.lueTiedostosta(nimi);
    }
    
    
    /**
     * Tallentaa päiväkirjan tiedot tiedostoon.
     * @throws SailoException jos ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
           viikot.tallenna(hakemisto);
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }

        try {
            treenit.talleta(hakemisto);
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        
        try {
            liikkeet.talleta(hakemisto);
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        if ( !"".equals(virhe) ) throw new SailoException(virhe);
    }
    
    
    /**
     * Poistaa viikon ja kaikki sen treenit sekä treenien liikkeet.
     * @param viikko poistettava
     * @return //?
     */
    public int poista(Viikko viikko) {
        if (viikko == null) return 0;
        int ret = viikot.poista(viikko.getID());
        List<Treeni> poistettavat = treenit.annaTreenit(viikko.getID());
        for (Treeni treeni : poistettavat) {
            liikkeet.poistaTreeninLiikkeet(treeni.getID());
        }
        treenit.poistaViikonTreenit(viikko.getID());
        
        return ret;
    }

    
    /**
     * @param args ei käytössä 
     */
    public static void main(String[] args) {
        Treenipk treenipk = new Treenipk();
        
        try {
            treenipk.lueTiedostosta("paavontesti");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
        Viikko yksi = new Viikko();
        Viikko kaksi = new Viikko();
        
        yksi.rekisteroi();
        kaksi.rekisteroi();
        yksi.taytaTiedoilla();
        kaksi.taytaTiedoilla();
        
        try {
            treenipk.lisaa(yksi);
            treenipk.lisaa(kaksi);
            
            for(int i = 0; i < treenipk.getViikkoja(); i++) {
                Viikko viikko = treenipk.annaViikko(i);        
                viikko.tulosta(System.out);
            }
            
            treenipk.tallenna();
        } catch  (SailoException e) {
            System.err.println(e.getMessage());
        }             
    }

}
