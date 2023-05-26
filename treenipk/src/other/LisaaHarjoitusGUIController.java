package other;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import luokat.Treeni;

/**
 * @author Paavo
 * @version 19.10.2022
 *
 */
public class LisaaHarjoitusGUIController implements ModalControllerInterface<Treeni> {
    
    @FXML private TextField textPaiva;
    @FXML private TextField textKesto;
    @FXML private TextField textRPE;

    
    @Override public Treeni getResult() {
        if (textPaiva.getText().equals("") || textKesto.getText().equals("") || textRPE.getText().equals("") || 
            tarkistaPaiva(textPaiva.getText()) != null     || 
            tarkistaKestoRPE(textKesto.getText()) != null  || 
            tarkistaKestoRPE(textRPE.getText()) != null) return null;
        Treeni uusi = new Treeni(textPaiva.getText().toLowerCase(), Integer.parseInt(textKesto.getText()), Integer.parseInt(textRPE.getText()));
        return uusi;
    }

    @Override public void handleShown() {
        // TODO Auto-generated method stub        
    }

    @Override public void setDefault(Treeni oletus) {
        // TODO Auto-generated method stub       
    }
    
    @FXML private void handleUusiharjoitus() {
        String paivaOikein = tarkistaPaiva(textPaiva.getText());
        String kestoOikein = tarkistaKestoRPE(textKesto.getText());
        String rpeOikein = tarkistaKestoRPE(textRPE.getText());        
        if (paivaOikein != null)  {Dialogs.showMessageDialog(paivaOikein); return;}
        if (kestoOikein != null) {Dialogs.showMessageDialog(kestoOikein); return;}
        if (rpeOikein != null) {Dialogs.showMessageDialog(rpeOikein); return;}
        ModalController.closeStage(textPaiva);
    }
    
    @FXML private void handlePeruuta(ActionEvent event) {
        textPaiva.setText("");
        ((Node)(event.getSource())).getScene().getWindow().hide();     
    }
    
    
    /**
     * Luodaan dialogi treenin tietojen kysymistä varten.
     * @param modalityStage modaalisuus, null = modaalinen sovellukselle
     * @param oletus mitä käytetään oletuksena
     * @return null, jos peruutetaan, muuten kirjoitettu nimi
     */
    public static Treeni kysyHarjoitus(Stage modalityStage, Treeni oletus) {
        return ModalController.showModal(LisaaHarjoitusGUIController.class.getResource("LisaaHarjoitusGUIView.fxml"), "Uusi harjoitus", modalityStage, oletus);        
    }
    
    
    private String tarkistaPaiva(String text) {
        if (text.toLowerCase().equals("ma") || text.toLowerCase().equals("ti") || text.toLowerCase().equals("ke") 
            || text.toLowerCase().equals("to") || text.toLowerCase().equals("pe") || text.toLowerCase().equals("la") || text.toLowerCase().equals("su")) return null;
        return "Kirjoita viikonpäivän lyhenne (ma, ti, ke...)";       
    }
    
    
    private String tarkistaKestoRPE(String text) {
        try {
            Integer.parseInt(text);
            return null;
        } catch (NumberFormatException ex) {
            return "Syötä kokonaisluku!";
        }
    }
    
    
}