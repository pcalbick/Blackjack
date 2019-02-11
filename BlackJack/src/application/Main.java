package application;
	
import java.io.IOException;

import application.controller.GameController;
import application.model.StartModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;


public class Main extends Application {
	
	private Stage primaryStage;
	
	private StartModel startMod;
	private GameController gameController;
	
	private int money = 2000;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Black Jack");
			initOverview();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initOverview() {
			primaryStage.setScene(initGame());
			startMod = new StartModel();
			createCards();
			startMod.setMoney(money);
			gameController.setModel(startMod);
			
			primaryStage.show();
	}
	
	public Scene initGame() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GameView.fxml"));
			GridPane game = (GridPane) loader.load();
			gameController = loader.getController();
			Scene scene = new Scene(game);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			return scene;
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createCards() {
		String[] letters = new String[] {"j","q","k","a"};
		for(int i=0; i<4; i++) {
			for(int x=2; x<11; x++) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("view/CardView.fxml"));
					GridPane card = (GridPane) loader.load();
					card.getChildren().add(new Text(Integer.toString(x)));
					card.getChildren().add(new Text(Integer.toString(x)));
					GridPane.setConstraints(card.getChildren().get(0), 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(2));
					GridPane.setConstraints(card.getChildren().get(1), 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(2));
					card.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
					card.getStyleClass().add("card");
					startMod.addCard(card);
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			for(int x=0; x<4; x++) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("view/CardView.fxml"));
					GridPane card = (GridPane) loader.load();
					card.getChildren().add(new Text(letters[x].toUpperCase()));
					card.getChildren().add(new Text(letters[x].toUpperCase()));
					GridPane.setConstraints(card.getChildren().get(0), 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(2));
					GridPane.setConstraints(card.getChildren().get(1), 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(2));
					card.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
					card.getStyleClass().add("card");
					startMod.addCard(card);
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}