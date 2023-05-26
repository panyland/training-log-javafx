package other;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Paavo
 * @version 19.10.2022
 *
 */
public class LisaaHarjoitusMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("LisaaHarjoitusGUIView.fxml"));
            final Pane root = ldr.load();
            //final LisaaHarjoitusGUIController lisaaharjoitusCtrl = (LisaaHarjoitusGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("lisaaharjoitus.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("LisaaHarjoitus");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}