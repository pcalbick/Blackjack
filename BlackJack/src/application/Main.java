package application;
	
import java.io.IOException;

import application.controller.GameController;
import application.controller.PanelController;
import application.controller.PokerController;
import application.controller.RootController;
import application.model.StartModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Main extends Application {
	
	private Stage primaryStage;
	
	private StartModel startMod;
	private GameController gameController;
	private PokerController pokerController;
	private RootController rootController;
	private PanelController panelController;
	private GridPane pokerStage;
	private GridPane blackjackStage;
	private VBox root;
	
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
		Scene game = new Scene(initGames());
		game.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		primaryStage.setScene(game);
		startMod = new StartModel();
		createCards();
		startMod.setMoney(money);
		gameController.setModel(startMod);
		pokerController.setModel(startMod);
		rootController.setup(root,blackjackStage,pokerStage);
		panelController.setRoot(rootController,gameController,pokerController);
		
		primaryStage.show();
	}
	
	public VBox initGames() {
		root = new VBox();
		try {
			FXMLLoader rootLoader = new FXMLLoader();
			rootLoader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			root = (VBox) rootLoader.load();
			root.getChildren().get(0).getStyleClass().add("menubar");
			root.getChildren().get(root.getChildren().size()-1).getStyleClass().add("region");
			rootController = rootLoader.getController();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		blackjackStage = getBlackjack();
		pokerStage = getPoker();
		root.getChildren().add(blackjackStage);
		return root;
	}
	
	private GridPane getBlackjack() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GameView.fxml"));
			GridPane game = (GridPane) loader.load();
			game.getChildren().add(addGamePanel());
			GridPane.setConstraints(game.getChildren().get(game.getChildren().size()-1), 0, 0, 1, 3, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.ALWAYS, null);
			gameController = loader.getController();
			return game;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private GridPane getPoker() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PokerView.fxml"));
			GridPane poker = (GridPane) loader.load();
			poker.getChildren().add(addGamePanel());
			GridPane.setConstraints(poker.getChildren().get(poker.getChildren().size()-1), 0, 0, 1, 3, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.ALWAYS, null);
			pokerController = loader.getController();
			return poker;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private GridPane addGamePanel() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GamePanel.fxml"));
			HBox panel = (HBox) loader.load();
			for(Node n : panel.getChildren()) {
				if(n instanceof Button)
					n.getStyleClass().add("button");
				if(n instanceof Separator)
					n.getStyleClass().add("separator");
				if(n instanceof Text || n instanceof Label)
					n.getStyleClass().add("text");
			}
			panel.getStyleClass().add("gamePanel");
			return panel;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void createCards() {
		String[] letters = new String[] {"j","q","k","a"};
		String[] suits = new String[] { "heart", "spade", "diamond", "club" };
		for(int i=0; i<4; i++) {
			for(int x=2; x<11; x++) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("view/CardView.fxml"));
					GridPane card = (GridPane) loader.load();
					Canvas canvas = getSuits(suits[i]);
					for(int y=0; y<2; y++) {
						Text text = new Text(Integer.toString(x));
						text.getStyleClass().add("bold");
						if(i == 0 || i == 2)
							text.getStyleClass().add("bust");
						card.getChildren().add(text);
					}
					card.getChildren().add(new Pane(canvas));
					GridPane.setConstraints(card.getChildren().get(0), 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(2));
					GridPane.setConstraints(card.getChildren().get(1), 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(2));
					GridPane.setConstraints(card.getChildren().get(2), 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(5));
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
					Canvas canvas = getSuits(suits[i]);
					for(int y=0; y<2; y++) {
						Text text = new Text(letters[x].toUpperCase());
						text.getStyleClass().add("bold");
						if(i == 0 || i == 2)
							text.getStyleClass().add("bust");
						card.getChildren().add(text);
					}
					card.getChildren().add(new Pane(canvas));
					GridPane.setConstraints(card.getChildren().get(0), 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(2));
					GridPane.setConstraints(card.getChildren().get(1), 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(2));
					GridPane.setConstraints(card.getChildren().get(2), 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, Priority.NEVER, Priority.NEVER, new Insets(5));
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
	
	public Canvas getSuits(String suit) {
		Canvas canvas = new Canvas(10,10);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		switch(suit){
			case "heart":
				gc.setFill(Color.RED);
				gc.fillOval(0, 0, 5.25, 5.25);
				gc.fillOval(4.75, 0, 5.25, 5.25);
				gc.fillPolygon(new double[] { 0,5,10 }, new double[] { 3.75,10,3.75 }, 3);
				break;
			case "spade":
				gc.setFill(Color.BLACK);
				gc.fillOval(0, 1.75, 10, 6.5);
				gc.fillPolygon(new double[] { 0,5,10 }, new double[] { 5,0,5 }, 3);
				gc.fillPolygon(new double[] { 2.5,5,7.5 }, new double[] { 10,8.5,10 }, 3);
				break;
			case "diamond":
				gc.setFill(Color.RED);
				gc.fillPolygon(new double[] { 0,5,10,5 }, new double[] { 5,0,5,10 }, 4);
				break;
			case "club":
				gc.setFill(Color.BLACK);
				gc.fillOval(2.5, 0, 5, 5);
				gc.fillOval(0, 3, 5, 5);
				gc.fillOval(5, 3, 5, 5);
				gc.fillPolygon(new double[] { 2.5,5,7.5 }, new double[] { 10,6,10 }, 3);
				break;
		}
		return canvas;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
