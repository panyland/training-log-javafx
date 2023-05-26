package luokat;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Paavo
 * @version 12.11.2022
 *
 */
public class Liike {

    private int ID;
    private int treeni;
    private String harjoite;
    private int sarjat;
    private int toistot;
    private int kuorma;
    private int rir;
    private static int seuraavanID = 1;
    
    private void setID(int numero) {
        ID = numero;
        if (ID >= seuraavanID) seuraavanID = ID + 1;        
    }
    
    
    /**
     * Liikkeen alustus.
     */
    public Liike() {
        //
    }
    
    
    /**
     * @param treeni treeni, jolle liike kuuluu
     */
    public Liike(int treeni) {
        this.treeni = treeni;       
    }
    
    
    /**
     * Alustus kaikilla attribuuteilla.
     * @param harjoite liike jota tehdään
     * @param sarjat monta sarjaa
     * @param toistot toistoja per sarja
     * @param kuorma pihvin määrä
     * @param rir reps in reserve
     */
    public Liike(String harjoite, int sarjat, int toistot, int kuorma, int rir) {
        this.harjoite = harjoite;
        this.sarjat = sarjat;
        this.toistot = toistot;
        this.kuorma = kuorma;
        this.rir = rir;
    }
    
    
    /**
     * Täyttää random liikkeitä treenille.
     * @param tr treeni, jolle liike kuuluu
     */
    public void taytaLiikkeella(int tr) {
        treeni = tr;
        harjoite = "Kirjoita harjoite...";
        sarjat = 0;
        toistot = 0;
        kuorma = 0;
        rir = 0;
    }
    
    
    /**
     * Tulostaa liikkeen tiedot
     * @param out tietovirta
     */
    public void tulosta(PrintStream out) {
        out.println(harjoite + " | " + sarjat + "*" + toistot + "*" + kuorma + "kg RIR " + rir);
    }
    
    
    /**
     * 
     * @param os tietovirta 
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Annetaan liikkeelle ID.
     * @return rekisteröidyn liikkeen ID
     */
    public int rekisteroi() {
        ID = seuraavanID;
        seuraavanID++;
        return ID;
    }
    
    
    /**
     * @param harjoite harjoite
     * @return olion harjoite
     */
    public String setHarjoite(String harjoite) {
        return this.harjoite = harjoite;
    }
    
    
    /**
     * @param sarjat sarjat
     * @return olion sarjat
     */
    public int setSarjat(int sarjat) {
        return this.sarjat = sarjat;
    }
    
    
    /**
     * @param toistot sarjat
     * @return olion toistot
     */
    public int setToistot(int toistot) {
        return this.toistot = toistot;
    }
    
    
    /**
     * @param kuorma sarjat
     * @return olion kuorma
     */
    public int setKuorma(int kuorma) {
        return this.kuorma = kuorma;
    }
    
    
    /**
     * @param rir sarjat
     * @return olion rir
     */
    public int setRir(int rir) {
        return this.rir = rir;
    }
    
    /**
     * @return liikkeen ID
     */
    public int getID() {
        return ID;
    }
    
    
    /**
     * @return treeni, jolle liike kuuluu
     */
    public int getTreeni() {
        return treeni;
    }
    
    
    /**
     * @return liike, jota tehdään
     */
    public String getHarjoite() {
        return harjoite;
    }
    
    
    /**
     * @return liikkeen sarjat
     */
    public int getSarjat() {
        return sarjat;
    }
    
    
    /**
     * @return sarjan toistot
     */
    public int getToistot() {
        return toistot;
    }
    
    
    /**
     * @return liikkeen kuorma
     */
    public int getKuorma() {
        return kuorma;
    }
    
    
    /**
     * @return sarjan reps in reverse
     */
    public int getRir() {
        return rir;
    }
    
    
    @Override
    public String toString() {
        return "" + getID() + "|" + treeni + "|" + harjoite + "|" + sarjat + "|" + toistot + "|" + kuorma + "|" + rir;
    }
    
    
    /**
     * Selvittää liikkeen tiedot |-merkillä erotellusta jonosta
     * @param s jono, josta viikon tiedot tuodaan
     */
    public void parse(String s) {
        var sb = new StringBuilder(s);
        setID(Mjonot.erota(sb, '|', getID()));
        this.treeni = Mjonot.erota(sb,  '|', treeni);
        this.harjoite = Mjonot.erota(sb,  '|', harjoite);
        this.sarjat = Mjonot.erota(sb,  '|', sarjat);
        this.toistot = Mjonot.erota(sb,  '|', toistot);
        this.kuorma = Mjonot.erota(sb,  '|', kuorma);
        this.rir = Mjonot.erota(sb,  '|', rir);
    }
    
     
    /**
     * @param args ei ole 
     */
    public static void main(String[] args) {
        Liike liike = new Liike();
        liike.taytaLiikkeella(1);
        liike.tulosta(System.out);

    }

}
