package fxTreenipk;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import luokat.Liike;
import luokat.Treeni;
import luokat.Treenipk;
import luokat.Viikko;
import other.AloitusikkunaGUIController;
import other.LisaaHarjoitusGUIController;
import other.UusiviikkoGUIController;

/**
 * @author Paavo
 * @version 4.10.2022
 *
 */
public class TreeniGUIController implements Initializable {
    
    @FXML private TextField hakuehto;
    @FXML private ListChooser<Viikko> chooserViikot;
    @FXML private TextField fieldKesto;  @FXML private TextField fieldRPE;
    @FXML private TextField fieldKesto2; @FXML private TextField fieldRPE2;
    @FXML private TextField fieldKesto3; @FXML private TextField fieldRPE3;
    @FXML private TextField fieldKesto4; @FXML private TextField fieldRPE4;
    @FXML private TextField fieldKesto5; @FXML private TextField fieldRPE5;
    @FXML private TextField fieldKesto6; @FXML private TextField fieldRPE6;
    @FXML private TextField fieldKesto7; @FXML private TextField fieldRPE7;
    @FXML private TableView<Liike> trsisalto;
    @FXML private TableColumn<Liike, String> harjoite;
    @FXML private TableColumn<Liike, Integer> sarjat;
    @FXML private TableColumn<Liike, Integer> toistot;
    @FXML private TableColumn<Liike, Integer> kilot;
    @FXML private TableColumn<Liike, Integer> rir;
    @FXML private ToggleButton ma = new ToggleButton("MA"); 
    @FXML private ToggleButton ti = new ToggleButton("TI");
    @FXML private ToggleButton ke = new ToggleButton("KE"); 
    @FXML private ToggleButton to = new ToggleButton("TO");
    @FXML private ToggleButton pe = new ToggleButton("PE");
    @FXML private ToggleButton la = new ToggleButton("LA"); 
    @FXML private ToggleButton su = new ToggleButton("SU");
    private ToggleGroup group = new ToggleGroup();
    
    
    @Override public void initialize(URL url, ResourceBundle bundle) {
        alusta();       
    }
    
    @FXML private void handleAvaa() {
        avaa();
    }
    
    @FXML private void handleHakuehto() {
        hae(0);
    }
    
    @FXML private void handleUusiviikko() {
        uusiViikko();
    }
    
    @FXML private void handleLisaaharjoitus() {
        uusiHarjoitus();
    }
    
    @FXML private void handleTallenna() {
        tallenna();
        Dialogs.showMessageDialog("Tiedot tallennettu!");
    }
    
    @FXML private void handlePoista() {
        //Dialogs.showMessageDialog("Ei toimi vielä");
        poistaViikko();
    }
       
    @FXML private void handleLisaaLiike() {
        String paivat[] = {"ma", "ti", "ke", "to", "pe", "la", "su"};
        ToggleButton[] nappaimet = {ma, ti, ke, to, pe, la, su};
        for (int i = 0; i < nappaimet.length; i++) {
            if (nappaimet[i].isSelected()) {
                uusiLiike(paivat[i]);
                naytaLiikkeet(paivat[i]);
                return;
            }
        }        
    }
    
    @FXML private void handleNaytaLiike () {
        String paivat[] = {"ma", "ti", "ke", "to", "pe", "la", "su"};
        ToggleButton[] nappaimet = {ma, ti, ke, to, pe, la, su};      
        for (int i = 0; i < nappaimet.length; i++) {
            if (nappaimet[i].isSelected()) {naytaLiikkeet(paivat[i]); return;}
        }
    }
    
    // =========================================================================================
 
    private Treenipk treenipk;
    
    /**
     * Asetetaan käytettävä treenipäiväkirja
     * @param treenipk käytettävä tpk
     */
    public void setTreenipk(Treenipk treenipk) {
        this.treenipk = treenipk;
    }
    
    
    /**
     * Koko hoidon alustus.
     */
    private void alusta() {
        chooserViikot.clear();
        chooserViikot.addSelectionListener(e -> naytaViikko());
        
        ma.setToggleGroup(group);
        ti.setToggleGroup(group);
        ke.setToggleGroup(group);
        to.setToggleGroup(group);
        pe.setToggleGroup(group);
        la.setToggleGroup(group);
        su.setToggleGroup(group);
        
        harjoite.setCellValueFactory(new PropertyValueFactory<Liike, String>("harjoite"));
        harjoite.setCellFactory(TextFieldTableCell.forTableColumn());
        harjoite.setOnEditCommit(new EventHandler<CellEditEvent<Liike, String>>() {
            @Override
            public void handle(CellEditEvent<Liike, String> event) {
                Liike liike = event.getRowValue();
                liike.setHarjoite(event.getNewValue());               
            }
        });
               
        sarjat.setCellValueFactory(new PropertyValueFactory<Liike, Integer>("sarjat"));
        sarjat.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        sarjat.setOnEditCommit(new EventHandler<CellEditEvent<Liike, Integer>>() {
            @Override
            public void handle(CellEditEvent<Liike, Integer> event) {
                Liike liike = event.getRowValue();
                liike.setSarjat(event.getNewValue());               
            }
        });
        sarjat.setStyle("-fx-alignment: CENTER-RIGHT;");
        
        toistot.setCellValueFactory(new PropertyValueFactory<Liike, Integer>("toistot"));
        toistot.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        toistot.setOnEditCommit(new EventHandler<CellEditEvent<Liike, Integer>>() {
            @Override
            public void handle(CellEditEvent<Liike, Integer> event) {
                Liike liike = event.getRowValue();
                liike.setToistot(event.getNewValue());               
            }
        });
        toistot.setStyle("-fx-alignment: CENTER-RIGHT;");
        
        kilot.setCellValueFactory(new PropertyValueFactory<Liike, Integer>("kuorma"));
        kilot.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        kilot.setOnEditCommit(new EventHandler<CellEditEvent<Liike, Integer>>() {
            @Override
            public void handle(CellEditEvent<Liike, Integer> event) {
                Liike liike = event.getRowValue();
                liike.setKuorma(event.getNewValue());               
            }
        });
        kilot.setStyle("-fx-alignment: CENTER-RIGHT;");
        
        rir.setCellValueFactory(new PropertyValueFactory<Liike, Integer>("rir"));
        rir.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        rir.setOnEditCommit(new EventHandler<CellEditEvent<Liike, Integer>>() {
            @Override
            public void handle(CellEditEvent<Liike, Integer> event) {
                Liike liike = event.getRowValue();
                liike.setRir(event.getNewValue());               
            }
        });
        rir.setStyle("-fx-alignment: CENTER-RIGHT;");
    }
    
    
    /**
     * Avataan aloitusikkuna käyttäjän painaessa menu-palkista "avaa".
     * @return false, jos painetaan "peruuta"
     */
    public boolean avaa() {
        String uusinimi = AloitusikkunaGUIController.kysyPk(null, "paavon");
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    } 
      
        
    private void lueTiedosto(String nimi) {
        try {
            treenipk.lueTiedostosta(nimi);
            hae(0);            
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }       
    }
    
    
    private void tallenna() {
        try { 
            treenipk.tallenna();            
        } catch (SailoException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }        
    }
    
    
    private void poistaViikko() {
        Viikko viikonKohdalla = chooserViikot.getSelectedObject();
        if (viikonKohdalla == null) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko: " + viikonKohdalla.getTiedot(), "Kyllä", "Ei") ) return;
        treenipk.poista(viikonKohdalla);
        int index = chooserViikot.getSelectedIndex();
        hae(0);
        chooserViikot.setSelectedIndex(index);
    }
    

    /**
     * Lisätään uusi viikko listaan. 
     */
    public void uusiViikko() {       
        Viikko uusi = UusiviikkoGUIController.kysyViikko(null, null);
        if (uusi == null) return;
        uusi.rekisteroi();
        try {
            treenipk.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelma uuden viikon lisäämisessä " + e.getMessage());
        }
        hae(uusi.getID());
    }
    
    
    /**
     * Lisätään viikolle uusi harjoitus. 
     */
    public void uusiHarjoitus() {
        Viikko viikonKohdalla = chooserViikot.getSelectedObject();
        if (viikonKohdalla == null) return;
        Treeni uusi = LisaaHarjoitusGUIController.kysyHarjoitus(null, null);
        if (uusi == null) return;
        uusi.setViikko(viikonKohdalla.getID());
        uusi.rekisteroi();
        try {
            treenipk.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelma uuden treenin lisäämisessä " + e.getMessage());
        }
        hae(viikonKohdalla.getID());
    }
    
    
    /**
     * Lisätään treeniin liike maanantain treenille.
     * @param paiva mille päivälle lisätään
     */
    public void uusiLiike(String paiva) {
        Viikko viikonKohdalla = chooserViikot.getSelectedObject();
        List<Treeni> treenit = treenipk.annaTreenit(viikonKohdalla);
        if (treenit.size() == 0) return;            
        Liike uusi = null;
        for (Treeni treeni : treenit) {
            if (treeni.getPV().toLowerCase().equals(paiva)) {
                uusi = new Liike(treeni.getID());
                uusi.rekisteroi();
                uusi.taytaLiikkeella(treeni.getID());
            }
        }
        try {
            treenipk.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelma uuden treenin lisäämisessä " + e.getMessage());
        }
        hae(viikonKohdalla.getID());
    }        
    
    
    private void hae(int jnr) {
        int jnro = jnr;
        if (jnro == 0) {
            Viikko viikkoValittuna = chooserViikot.getSelectedObject();
            if (viikkoValittuna != null) jnro = viikkoValittuna.getID();
        }   
        chooserViikot.clear();
        String ehto = hakuehto.getText();
        int index = 0;
        for (int i=0; i < treenipk.getViikkoja(); i++) {
            Viikko viikko = treenipk.annaViikko(i);
            if (!viikko.getTiedot().contains(ehto)) continue;
            if (viikko.getID() == jnro) index = i;
            chooserViikot.add(""+viikko.getTiedot(), viikko);
        }
        chooserViikot.setSelectedIndex(index);
    }
    
    
    private void naytaViikko() {
        Viikko viikkoValittuna = chooserViikot.getSelectedObject();
        if (viikkoValittuna == null) return;
        
        TextField[] kestokentat = {fieldKesto, fieldKesto2, fieldKesto3, fieldKesto4, fieldKesto5, fieldKesto6, fieldKesto7};
        TextField[] rpekentat = {fieldRPE, fieldRPE2, fieldRPE3, fieldRPE4, fieldRPE5, fieldRPE6, fieldRPE7};
        for (int i = 0; i < kestokentat.length; i++) {
            kestokentat[i].setText("");
            rpekentat[i].setText("");
        }
        
        trsisalto.getItems().clear();
               
        List<Treeni> viikontreenit = treenipk.annaTreenit(viikkoValittuna);            
        if (viikontreenit.size() == 0) return; 
        String paivat[] = {"ma", "ti", "ke", "to", "pe", "la", "su"};
      
        for (int i = 0; i < kestokentat.length; i++) {
            if (viikontreenit.size() > 0) {
                for (int j = 0; j < viikontreenit.size(); j++) {
                    if (viikontreenit.get(j).getPV().toLowerCase().equals(paivat[i])) { 
                        kestokentat[i].setText(Integer.toString(viikontreenit.get(j).getKesto()));
                        rpekentat[i].setText(Integer.toString(viikontreenit.get(j).getRPE())); 
                    }                 
                } 
            }
        }
    }
   
      
    private void naytaLiikkeet(String paiva) {
        Viikko viikonKohdalla = chooserViikot.getSelectedObject();
        List<Treeni> treenit = treenipk.annaTreenit(viikonKohdalla);
        List<Liike> treeninliikkeet = null;
        if (treenit.size() == 0) {return;}
        
        trsisalto.getItems().clear();
        if (treenit.size() > 0) {
            for (Treeni treeni : treenit) {
                if (treeni.getPV().toLowerCase().equals(paiva)) {
                    treeninliikkeet = treenipk.annaLiikkeet(treeni);
                }
            }
            if (treeninliikkeet != null && treeninliikkeet.size() > 0) {
                for (int i = 0; i < treeninliikkeet.size(); i++) {
                    trsisalto.getItems().add(treeninliikkeet.get(i)); 
                }
            } else {
                trsisalto.getItems().clear();                
            }
        }        
    }
    
       
}

