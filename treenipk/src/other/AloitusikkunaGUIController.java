package other;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Kysytään kenen päiväkirja avataan ja luodaan tälle dialogi.
 * 
 * @author Paavo
 * @version 17.10.2022
 *
 */
public class AloitusikkunaGUIController implements ModalControllerInterface<String> {
    
    @FXML private TextField textVastaus;
    private String vastaus = null;
    
    @FXML private void handleAvaa() {
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
    }

    @Override public String getResult() {       
        return vastaus;
    }

    @Override public void handleShown() {
        textVastaus.requestFocus();       
    }

    @Override public void setDefault(String oletus) {
        textVastaus.setText("paavon");
        textVastaus.requestFocus();    
    }
    
    
    /**
     * Luodaan dialogi päiväkirjan nimen kysymistä varten.
     * @param modalityStage modaalisuus, null = modaalinen sovellukselle
     * @param oletus mitä käytetään oletuksena
     * @return null, jos peruutetaan, muuten kirjoitettu nimi
     */
    public static String kysyPk(Stage modalityStage, String oletus) {
        return ModalController.showModal(AloitusikkunaGUIController.class.getResource("AloitusikkunaGUIView.fxml"), "Treenipäiväkirja", modalityStage, oletus);        
    }
}