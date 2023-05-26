package other;

import java.time.Year;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import luokat.Viikko;

/**
 * @author Paavo
 * @version 19.10.2022
 *
 */
public class UusiviikkoGUIController implements ModalControllerInterface<Viikko>{
    
    @FXML private TextField textViikkonro;
    @FXML private TextField aloitusPv;
    @FXML private TextField aloitusKk;
    @FXML private TextField lopetusPv;
    @FXML private TextField lopetusKk;


    @Override public Viikko getResult() {
        if (textViikkonro.getText().equals("") || aloitusPv.getText().equals("") || 
            aloitusKk.getText().equals("")     || lopetusPv.getText().equals("") || lopetusKk.getText().equals("") || 
            tarkistaViikko(textViikkonro.getText()) != null ||
            tarkistaKk(aloitusKk.getText()) != null         ||
            tarkistaKk(lopetusKk.getText()) != null         ||
            tarkistaPv(aloitusPv.getText()) != null         ||
            tarkistaPv(lopetusPv.getText()) != null) return null;
        int vuosi = Year.now().getValue();
        String pvm = aloitusPv.getText() + "." + aloitusKk.getText() + "." + vuosi + " - " + lopetusPv.getText() + "." + lopetusKk.getText() + "." + vuosi;
        Viikko uusi = new Viikko(Integer.parseInt(textViikkonro.getText()), pvm);
        return uusi;
    }

    @Override public void handleShown() {
        // TODO Auto-generated method stub     
    }

    @Override public void setDefault(Viikko oletus) {
        // TODO Auto-generated method stub  
    }
    
    
    @FXML private void handleUusiviikko() {
        String viikkoOikein = tarkistaViikko(textViikkonro.getText());
        String Kkoikein1 = tarkistaKk(aloitusKk.getText());
        String Kkoikein2 = tarkistaKk(lopetusKk.getText());
        String Pvoikein1 = tarkistaPv(aloitusPv.getText());
        String Pvoikein2 = tarkistaPv(lopetusPv.getText());
        if (viikkoOikein != null) {Dialogs.showMessageDialog(viikkoOikein); return;}
        if (Kkoikein1 != null) {Dialogs.showMessageDialog(Kkoikein1); return;}
        if (Kkoikein2 != null) {Dialogs.showMessageDialog(Kkoikein2); return;}
        if (Pvoikein1 != null) {Dialogs.showMessageDialog(Pvoikein1); return;}
        if (Pvoikein2 != null) {Dialogs.showMessageDialog(Pvoikein2); return;}
        ModalController.closeStage(textViikkonro);
    }

    @FXML private void handlePeruuta(ActionEvent event) {
        textViikkonro.setText("");
        ((Node)(event.getSource())).getScene().getWindow().hide(); 
    }
    
    
    /**
     * Luodaan dialogi päiväkirjan nimen kysymistä varten.
     * @param modalityStage modaalisuus, null = modaalinen sovellukselle
     * @param oletus mitä käytetään oletuksena
     * @return null, jos peruutetaan, muuten kirjoitettu nimi
     */
    public static Viikko kysyViikko(Stage modalityStage, Viikko oletus) {
        return ModalController.showModal(UusiviikkoGUIController.class.getResource("UusiviikkoGUIView.fxml"), "Uusi viikko", modalityStage, oletus);        
    }
       
    
    private String tarkistaViikko(String text) {
        try {
            if (Integer.parseInt(text) > 52 || Integer.parseInt(text) < 1) return "Anna viikko välillä 1-52";
        } catch (NumberFormatException ex) {
            return "Syötä kokonaisluku!";
        }
        return null;
    }
    
    
    private String tarkistaKk(String text) {
        try {
            if (Integer.parseInt(text) > 12 || Integer.parseInt(text) < 1) return "Anna kuukausi välillä 1-12";
        } catch (NumberFormatException ex) {
            return "Syötä kokonaisluku!";
        }
        return null;
    }
    
    
    private String tarkistaPv(String text) {
        try {
            if (Integer.parseInt(text) > 31 || Integer.parseInt(text) < 1) return "Anna päivät välillä 1-31";
        } catch (NumberFormatException ex) {
            return "Syötä kokonaisluku!";
        }
        return null;
        
    }
    

}