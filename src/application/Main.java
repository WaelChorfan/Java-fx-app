package application;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	private static Stage stage;
	
	public void start(Stage primaryStage) {
		try {
			setStage(primaryStage);
			primaryStage.setTitle("Gestion De Location des Voitures");
				Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GestionDeLocation.fxml"));

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

	public static void main(String[] args) {
		System.out.println("ee");
		 
		launch(args);
	}
}
