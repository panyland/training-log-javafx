package luokat;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Paavo
 * @version 11.11.2022
 *
 */
public class Treeni {
    
    private int ID;
    private int vko;
    private String paiva;
    private int kesto;
    private int rpe;
    private static int seuraavanID = 1;
    
    
    private void setID(int numero) {
        ID = numero;
        if (ID >= seuraavanID) seuraavanID = ID + 1;        
    }
    
    
    /**
     * @param viikko mikä viikko kysessä
     */
    public void setViikko(int viikko) {
        this.vko = viikko;        
    }
    
    
    /**
     * Treenin alustus.
     */
    public Treeni() {
        
    }
    
    
    /**
     * Treenin alustus.
     * @param pv päivä
     * @param kesto treenin kesto
     * @param rpe koettu kuormittavuus 0-10
     */
    public Treeni(String pv, int kesto, int rpe) {
        this.paiva = pv;
        this.kesto = kesto;
        this.rpe = rpe;       
    }
    
    
    /**
     * Treenin alustus tietylle viikolle.
     * @param vko viikko, jolle treeni kuuluu
     */
    public Treeni(int vko) {
        this.vko = vko;
    }
    
    
    /**
     * Täyttää random testiarvoja treenille.
     * @param nro viite viikkoon, jolle treeni kuuluu
     */
    public void taytaTreenilla(int nro) {
        vko = nro;
        paiva = "MA";
        kesto = 90;
        rpe = 8;
    }
    
    
    /**
     * Tulostaa treenin tiedot
     * @param out tietovirta
     */
    public void tulosta(PrintStream out) {
        out.println(paiva + " | " + kesto + " min | " + "RPE " + rpe);
    }
    
    
    /**
     * 
     * @param os tietovirta 
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Annetaan treenille ID.
     * @return rekisteröidyn treenin ID
     * @example
     * <pre name="test">
     * Treeni treeni1 = new Treeni();
     * treeni1.getID() === 0;
     * treeni1.rekisteroi();
     * Treeni treeni2 = new Treeni();
     * treeni2.rekisteroi();
     * int n1 = treeni1.getID();
     * int n2 = treeni2.getID();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        ID = seuraavanID;
        seuraavanID++;
        return ID;
    }
    
    
    /**
     * @return treenin ID
     */
    public int getID() {
        return ID;
    }

    
    /**
     * @return viikko, jolle treeni kuuluu
     */
    public int getVko() {
        return vko;
    }
    
    
    /**
     * @return treenin kesto
     */
    public int getKesto() {
        return kesto;
    }
    
    
    /**
     * @return treenin koettu kuormittavuus
     */
    public int getRPE() {
        return rpe;
    }
    
    
    /**
     * @return treenin koettu kuormittavuus
     */
    public String getPV() {
        return paiva;
    }
    
    
    @Override
    public String toString() {
        return "" + getID() + "|" + vko + "|" + paiva + "|" + kesto + "|" + rpe;
    }
    
    
    /**
     * Selvittää treenin tiedot |-merkillä erotellusta jonosta
     * @param s jono, josta viikon tiedot tuodaan
     */
    public void parse(String s) {
        var sb = new StringBuilder(s);
        setID(Mjonot.erota(sb, '|', getID()));
        this.vko = Mjonot.erota(sb,  '|', vko);
        this.paiva = Mjonot.erota(sb,  '|', paiva);
        this.kesto = Mjonot.erota(sb,  '|', kesto);
        this.rpe = Mjonot.erota(sb,  '|', rpe);
    }

    
    /**
     * @param args ei ole 
     */
    public static void main(String[] args) {
        Treeni treeni = new Treeni();
        treeni.taytaTreenilla(2);
        treeni.tulosta(System.out);

    }

}
