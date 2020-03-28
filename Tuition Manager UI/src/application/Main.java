/**
 * This class loads in the fxml document and starts running the UI for this program.
 * This program uses an Anchor Pane as the root for the UI.
 * @author Michael Loh
 * @author Varun Vasudevan
 */
package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	/**
	 * This method loads and displays the fxml file.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("UI.fxml"));
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is the main method.
	 * It starts the program by calling launch().
	 * @param args no command line args.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
