package application.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RootController {
	@FXML
	public CheckMenuItem blackjack;
	@FXML
	public CheckMenuItem poker;
	
	private HBox panelStage;
	private PanelController panelController;
	private GridPane blackjackStage;
	private GridPane pokerStage;
	private StringProperty activeGame = new SimpleStringProperty("blackjack");
	
	public void setup(HBox panelStage, GridPane blackjackStage, GridPane pokerStage, PanelController panelController) {		
		this.panelStage = panelStage;
		this.blackjackStage = blackjackStage;
		this.pokerStage = pokerStage;
		this.panelController = panelController;
		setActiveGame("blackjack");
		
		activeGame.addListener((ods, ov, nv) -> {
			panelController.setGame(nv);
		});
	}
	
	@FXML
	public void handelBlackjack() {
		setActiveGame("blackjack");
		panelStage.getChildren().remove(panelStage.getChildren().size()-1);
		panelStage.getChildren().add(blackjackStage);
		blackjack.setDisable(true);
		blackjack.setSelected(true);
		poker.setSelected(false);
		poker.setDisable(false);
	}
	
	@FXML
	public void handelPoker() {
		setActiveGame("poker");
		panelStage.getChildren().remove(panelStage.getChildren().size()-1);
		panelStage.getChildren().add(pokerStage);
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
