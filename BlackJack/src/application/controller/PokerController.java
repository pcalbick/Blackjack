package application.controller;

import application.model.StartModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

//Currently Blackjack - Must convert to poker
public class PokerController {
	@FXML
	public Text dealerNumb;
	@FXML
	public Text playerNumb;
	@FXML
	public Text comp1;
	@FXML
	public Text comp2;
	
	private StartModel startMod;
	
	public void setModel(StartModel startMod) {
		this.startMod = startMod;
		dealerNumb.getStyleClass().add("bold");
		playerNumb.getStyleClass().add("bold");
		comp1.getStyleClass().add("bold");
		comp2.getStyleClass().add("bold");
	}
	
	public void handleAdd() {
		if(comp1 == null) {
			comp1.setText("Player 1");
		}
		else {
			comp2.setText("Player 2");
		}
	}
}

