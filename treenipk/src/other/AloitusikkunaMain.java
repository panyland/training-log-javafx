package other;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Paavo
 * @version 17.10.2022
 *
 */
public class AloitusikkunaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("AloitusikkunaGUIView.fxml"));
            final Pane root = ldr.load();
            //final AloitusikkunaGUIController aloitusikkunaCtrl = (AloitusikkunaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("aloitusikkuna.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Aloitusikkuna");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}