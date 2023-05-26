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
public class UusiviikkoMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("UusiviikkoGUIView.fxml"));
            final Pane root = ldr.load();
            //final UusiviikkoGUIController uusiviikkoCtrl = (UusiviikkoGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("uusiviikko.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Uusiviikko");
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