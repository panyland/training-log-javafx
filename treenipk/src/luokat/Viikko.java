package luokat;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Paavo
 * @version 29.10.2022
 *
 */
public class Viikko {
    
    private int         ID;
    private int         vkoNro      = 0;
    private String      pvmMaSU     = "";
    private static int  seuraavanID = 1;
    
    
    private void setID(int numero) {
        ID = numero;
        if (ID >= seuraavanID) seuraavanID = ID + 1;        
    }
    
    
    /**
     * Muodostaja Viikko-luokalle.
     */
    public Viikko() {
        
    }
    
    
    /**
     * Muodostaja Viikko-luokalle.
     * @param nro monesko viikko
     * @param pvm päiväys maanantaista perjantaihin
     */
    public Viikko(int nro, String pvm) {
        this.vkoNro = nro;
        this.pvmMaSU = pvm;
    }
    
    
    /**
     * Viikon tietojen tulostus
     * @param output tietovirta
     */
    public void tulosta(PrintStream output) {
        output.println(String.format("%03d", ID) + " viikko " + vkoNro + " | " + pvmMaSU); 
    }
    
    
    /**
     * Antaa viikolle ID:n.
     * @return viikolle annettu ID.
     * @example
     * <pre name="test">
     *  Viikko vko1 = new Viikko();
     *  Viikko vko2 = new Viikko();
     *  vko1.getID() === 0;
     *  vko1.rekisteroi();
     *  vko2.rekisteroi();
     *  int n1 = vko1.getID();
     *  int n2 = vko2.getID();
     *  n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.ID = seuraavanID;
        seuraavanID++;
        return this.ID;
    }
    
    
    /**
     * @return viikon ID-numero
     */
    public int getID() {
        return ID;
    }
    
    
    /**
     * @return viikon numero ja päivämäärät maanantaista sunnuntaihin
     */
    public String getTiedot() {
        return "Viikko " + vkoNro + " | " + pvmMaSU;
    }
    
    
    /**
     * @return viikon numero 
     */
    public int getNro() {
        return vkoNro;
    }
       
    
    /**
     * Rakennusteline, jonka avulla voidaan täyttää viikon tiedot.
     * TODO: poista, kun kaikki valmista
     */
    public void taytaTiedoilla() {     
        vkoNro = 1 + (int)(Math.random() * ((53 - 1) + 1));
        pvmMaSU = "31.10.2022 - 6.11.2022";       
    }
    
    
    @Override
    public String toString() {
        return "" + getID() + "|" + vkoNro + "|" + pvmMaSU;
    }
    
    
    /**
     * Selvittää viikon tiedot |-merkillä erotellusta jonosta
     * @param s jono, josta viikon tiedot tuodaan
     */
    public void parse(String s) {
        var sb = new StringBuilder(s);
        setID(Mjonot.erota(sb, '|', getID()));
        this.vkoNro = Mjonot.erota(sb,  '|', vkoNro);
        this.pvmMaSU = Mjonot.erota(sb,  '|', pvmMaSU);
    }

    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Viikko yksi = new Viikko();
        Viikko kaksi = new Viikko();
        
        yksi.rekisteroi();
        kaksi.rekisteroi();
        
        yksi.tulosta(System.out);
        
        yksi.taytaTiedoilla();
        kaksi.taytaTiedoilla();
        
        yksi.tulosta(System.out);
        kaksi.tulosta(System.out);

    }

}
