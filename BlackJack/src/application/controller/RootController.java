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
	@FXML
	public CheckMenuItem solitair;
	
	private HBox panelStage;
	private GridPane blackjackStage;
	private GridPane pokerStage;
	private GridPane solitairStage;
	private VBox rootStage;
	private StringProperty activeGame = new SimpleStringProperty("blackjack");
	
	public void setup(GridPane blackjackStage, GridPane pokerStage, GridPane solitairStage, VBox root, 
			HBox panelStage, PanelController panelController) {	
		this.rootStage = root;
		this.panelStage = panelStage;
		this.blackjackStage = blackjackStage;
		this.solitairStage = solitairStage;
		//this.pokerStage = pokerStage;
		setActiveGame("blackjack");
		
		activeGame.addListener((obs, ov, nv) -> {
			panelController.setGame(nv);
		});
	}
	
	@FXML
	public void handelBlackjack() {
		setActiveGame("blackjack");
		
		if(!rootStage.getChildren().contains(panelStage)) {
			rootStage.getChildren().remove(rootStage.getChildren().size()-1);
			rootStage.getChildren().add(panelStage);
		}
		panelStage.getChildren().remove(panelStage.getChildren().size()-1);
		panelStage.getChildren().add(blackjackStage);
		
		blackjack.setDisable(true);
		blackjack.setSelected(true);
		
		//Disable Non-selected Items
		solitair.setSelected(false);
		solitair.setDisable(false);
		//poker.setSelected(false);
		//poker.setDisable(false);
	}
	
	@FXML
	public void handelPoker() {
		setActiveGame("poker");
		
		if(!rootStage.getChildren().contains(panelStage)) {
			rootStage.getChildren().remove(rootStage.getChildren().size()-1);
			rootStage.getChildren().add(panelStage);
		}
		panelStage.getChildren().remove(panelStage.getChildren().size()-1);
		panelStage.getChildren().add(pokerStage);
		
		poker.setSelected(true);
		poker.setDisable(true);
		
		//Disable Non-selected Items
		blackjack.setDisable(false);
		blackjack.setSelected(false);
		solitair.setSelected(false);
		solitair.setDisable(false);
	}
	
	@FXML
	public void handelSolitair() {
		setActiveGame("solitair");
		
		rootStage.getChildren().remove(panelStage);
		rootStage.getChildren().add(solitairStage);
		
		solitair.setSelected(true);
		solitair.setDisable(true);
		
		//Disable Non-selected Items
		blackjack.setDisable(false);
		blackjack.setSelected(false);
		//poker.setSelected(false);
		//poker.setDisable(false);
	}
	
	public String getActiveGame() {
		return activeGame.get();
	}
	
	private void setActiveGame(String game) {
		activeGame.set(game);
	}
}
