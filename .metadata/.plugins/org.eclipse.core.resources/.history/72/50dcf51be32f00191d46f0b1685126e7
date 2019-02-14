package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RootController {
	@FXML
	public CheckMenuItem blackjack;
	@FXML
	public CheckMenuItem poker;
	
	private Stage primaryStage;
	private VBox blackjackStage;
	private VBox pokerStage;
	
	public void setup(Stage primaryStage, VBox blackjackStage, VBox pokerStage) {		
		this.primaryStage = primaryStage;
		this.blackjackStage = blackjackStage;
		this.pokerStage = pokerStage;
	}
	
	@FXML
	public void handelBlackjack() {
		VBox newScene = (VBox)primaryStage.getScene().getRoot();
		newScene.getChildren().remove(1);
		newScene.getChildren().add(blackjackStage);
		blackjack.setDisable(true);
		blackjack.setSelected(true);
		poker.setSelected(false);
		poker.setDisable(false);
	}
	
	@FXML
	public void handelPoker() {
		VBox newScene = (VBox)primaryStage.getScene().getRoot();
		newScene.getChildren().remove(1);
		newScene.getChildren().add(pokerStage);
		blackjack.setDisable(false);
		blackjack.setSelected(false);
		poker.setSelected(true);
		poker.setDisable(true);
	}
}
