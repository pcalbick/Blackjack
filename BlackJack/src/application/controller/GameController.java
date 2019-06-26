package application.controller;

import application.model.StartModel;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GameController {
	private int players = 2;
	private int dealTo = 1;
	private StartModel startMod;
	private GridPane hideCard;
	private int number;
	private int house;
	
	@FXML
	HBox dealerHand;
	
	@FXML
	HBox playerHand;
	
	@FXML
	Text dealerNumb;
	
	@FXML
	Text playerNumb;
	
	@FXML
	public void handleDeal() {
		if(hideCard != null) {
			hideCard.getChildren().get(0).getStyleClass().remove("hide");
			hideCard.getChildren().get(1).getStyleClass().remove("hide");
			hideCard.getChildren().get(2).getStyleClass().remove("hide");
		}
		
		house = 0;
		number = 0;
		startMod.clearCards();
		dealerHand.getChildren().clear();
		playerHand.getChildren().clear();
		dealerNumb.setText("Dealer");
		playerNumb.setText("Player");
		dealerNumb.getStyleClass().remove("bust");
		playerNumb.getStyleClass().remove("bust");
		
		hideCard = null;
		for(int i=0; i<players*2; i++) {
			if(dealTo < players) {
				//Deal to player
				playerHand.getChildren().add(startMod.getCard());
				dealTo++;
			}
			else {
				//Deal to dealer
				if(hideCard == null) {
					hideCard = startMod.getCard();
					hideCard.getChildren().get(0).getStyleClass().add("hide");
					hideCard.getChildren().get(1).getStyleClass().add("hide");
					hideCard.getChildren().get(2).getStyleClass().add("hide");
					dealerHand.getChildren().add(hideCard);
					dealTo = 1;
				}
				else {
					dealerHand.getChildren().add(startMod.getCard());
					dealTo = 1;
				}
			}
		}
		
		playerNumb.setText("0");
		int numb = 0;
		for(int i=0; i<2; i++) {
			GridPane card = (GridPane)playerHand.getChildren().get(i);
			numb += getNumber(card);
			playerNumb.setText(Integer.toString(numb));
		}
		
		if(number == 21) {
			reset();
		}
	}
	
	@FXML
	public void handleHit() {
		GridPane newCard = startMod.getCard();
		if(!playerHand.getChildren().contains(newCard))
			playerHand.getChildren().add(newCard);
		number = Integer.parseInt(playerNumb.getText()) + getNumber(newCard);
		if(number > 21) {
			playerNumb.setText("Bust");
			playerNumb.getStyleClass().add("bust");
			reset();
		}
		else {
			playerNumb.setText(Integer.toString(number));
		}
		
		if(number == 21) {
			reset();
		}
	}
	
	@FXML
	public void handleHold() {
		GridPane card1 = (GridPane) dealerHand.getChildren().get(0);
		GridPane card2 = (GridPane) dealerHand.getChildren().get(1);
		
		house = getNumber(card1) + getNumber(card2);
		
		while(house <= Integer.parseInt(playerNumb.getText())) {
			if(house == 21)
				break;
			GridPane newCard = startMod.getCard();
			dealerHand.getChildren().add(newCard);
			house += getNumber(newCard);
		}
		
		if(house > 21) {
			dealerNumb.setText("Bust");
			dealerNumb.getStyleClass().add("bust");
		}
		else {
			dealerNumb.setText(Integer.toString(house));
		}

		reset();
	}
	
	@FXML
	public void handleQuit() {
		System.exit(0);
	}
	
	public void reset() {
		if(hideCard != null) {
			hideCard.getChildren().get(0).getStyleClass().remove("hide");
			hideCard.getChildren().get(1).getStyleClass().remove("hide");
			hideCard.getChildren().get(2).getStyleClass().remove("hide");
		}
	}
	
	public int getNumber(GridPane card) {
		int numb = 0;
		Text number = (Text) card.getChildren().get(0);
		if(number.getText().matches("\\d+")) {
			numb += Integer.parseInt(number.getText());
		}
		else {
			if(number.getText().contains("J") || number.getText().contains("Q") || number.getText().contains("K")) {
				numb += 10;
			}
			if(number.getText().contains("A")) {
				if(playerNumb.getText().matches("\\d+") && Integer.parseInt(playerNumb.getText()) + 11 > 21) {
					numb += 1;
				}
				else if(dealerNumb.getText().matches("\\d+") && Integer.parseInt(dealerNumb.getText()) + 11 > 21) {
					numb += 1;
				}
				else {
					numb += 11;
				}
			}
		}
		return numb;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getHouse() {
		return house;
	}
	
	public void setModel(StartModel startMod) {
		this.startMod = startMod;
		dealerNumb.getStyleClass().add("bold");
		playerNumb.getStyleClass().add("bold");
	}
}
