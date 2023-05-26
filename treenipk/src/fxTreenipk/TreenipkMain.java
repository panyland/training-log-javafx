package fxTreenipk;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import luokat.Treenipk;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
 * @author Paavo
 * @version 4.10.2022
 *
 */
public class TreenipkMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    final FXMLLoader ldr = new FXMLLoader(getClass().getResource("TreeniGUIView.fxml"));
		    final Pane root = (Pane)ldr.load();
		    final TreeniGUIController treenipkCtrl = (TreeniGUIController)ldr.getController();
		    			
			Scene scene = new Scene(root,744,390);
			scene.getStylesheets().add(getClass().getResource("treenipk.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Treenipk treenipk = new Treenipk();
			treenipkCtrl.setTreenipk(treenipk);
			
			if ( !treenipkCtrl.avaa() ) Platform.exit();
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
