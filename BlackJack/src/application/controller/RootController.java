package application.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RootController {
	@FXML
	public CheckMenuItem blackjack;
	@FXML
	public CheckMenuItem poker;
	
	private VBox primaryStage;
	private GridPane blackjackStage;
	private GridPane pokerStage;
	private StringProperty activeGame = new SimpleStringProperty("blackjack");
	
	public void setup(VBox primaryStage, GridPane blackjackStage, GridPane pokerStage) {		
		this.primaryStage = primaryStage;
		this.blackjackStage = blackjackStage;
		this.pokerStage = pokerStage;
		setActiveGame("blackjack");
	}
	
	@FXML
	public void handelBlackjack() {
		setActiveGame("blackjack");
		primaryStage.getChildren().remove(primaryStage.getChildren().size()-1);
		primaryStage.getChildren().add(blackjackStage);
		blackjack.setDisable(true);
		blackjack.setSelected(true);
		poker.setSelected(false);
		poker.setDisable(false);
	}
	
	@FXML
	public void handelPoker() {
		setActiveGame("poker");
		primaryStage.getChildren().remove(primaryStage.getChildren().size()-1);
		primaryStage.getChildren().add(pokerStage);
		blackjack.setDisable(false);
		blackjack.setSelected(false);
		poker.setSelected(true);
		poker.setDisable(true);
	}
	
	public String getActiveGame() {
		return activeGame.get();
	}
	
	private void setActiveGame(String game) {
		activeGame.set(game);
	}
}
