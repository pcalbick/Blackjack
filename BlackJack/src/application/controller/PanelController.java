package application.controller;

import application.model.StartModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PanelController {
	private StartModel startMod;
	private RootController root;
	private GameController blackjackController;
	private PokerController pokerController;
	private BooleanProperty bettingOn = new SimpleBooleanProperty(true);
	
	@FXML
	Button deal;
	
	@FXML
	Button hit;
	
	@FXML
	Button hold;
	
	@FXML
	Button quit;
	
	@FXML
	Text money;
	
	@FXML
	Text bettingMoney;
	
	@FXML
	TextField placeBet;
	
	@FXML
	Button betButton;
	
	@FXML
	RadioMenuItem toggleBetting;
	
	//Poker interface
	@FXML
	Label playerTitle;
	
	@FXML
	Button add;
	
	@FXML
	Button remove;
	
	@FXML
	public void handleDeal() {
		if(root.getActiveGame().equals("blackjack")) {
			blackjackController.handleDeal();
			
			betButton.setDisable(true);
			money.setText(Integer.toString(startMod.getMoney()));
			hit.setDisable(false);
			hold.setDisable(false);
		}
	}
	
	@FXML
	public void handleHit() {
		if(root.getActiveGame().equals("blackjack")) {
			blackjackController.handleHit();
			if(blackjackController.getNumber() >= 21) {
				betButton.setDisable(false);
				hit.setDisable(true);
				hold.setDisable(true);
				reset(blackjackController.getHouse(),blackjackController.getNumber());
			}
		}
	}
	
	@FXML
	public void handleHold() {
		if(root.getActiveGame().equals("blackjack")) {
			blackjackController.handleHold();
			betButton.setDisable(false);
			hit.setDisable(true);
			hold.setDisable(true);
			reset(blackjackController.getHouse(),blackjackController.getNumber());
		}
	}
	
	@FXML
	public void handleQuit() {
		System.exit(0);
	}
	
	@FXML
	public void handleBet() {
		if(placeBet.getText().matches("\\d+") && Integer.parseInt(placeBet.getText()) <= startMod.getMoney()) {
			deal.setDisable(false);
			bettingMoney.setText(placeBet.getText());
			startMod.changeMoney(-Integer.parseInt(placeBet.getText()));
			money.setText(Integer.toString(startMod.getMoney()));
			placeBet.getStyleClass().removeAll("warning");
			betButton.setDisable(true);
			bettingOn.set(true);
			placeBet.setText("");
		}
		else {
			placeBet.setText("");
			placeBet.getStyleClass().add("warning");
		}
	}
	
	public void reset(int house, int number) {
		if(bettingOn.get()) {
			if((house > 21 || (house < number && house < 21)) && number < 21) {
				startMod.changeMoney(Integer.parseInt(bettingMoney.getText())*2);
				money.setText(Integer.toString(startMod.getMoney()));
			}
			if(house == number) {
				startMod.changeMoney((int)(Integer.parseInt(bettingMoney.getText())));
				money.setText(Integer.toString(startMod.getMoney()));
			}
			if(number == 21) {
				startMod.changeMoney((int)(Integer.parseInt(bettingMoney.getText())*2.5));
				money.setText(Integer.toString(startMod.getMoney()));
			}
			else {
				money.setText(Integer.toString(startMod.getMoney()));
			}
			bettingMoney.setText("0");
			bettingOn.set(false);
		}
		
		betButton.setDisable(false);
		hit.setDisable(true);
		hold.setDisable(true);
		
		if(root.getActiveGame().equals("blackjack"))
			blackjackController.reset();
	}
	
	public void setGame(String game) {
		if(game.equals("blackjack")) {
			playerTitle.setVisible(false);
			add.setVisible(false);
			remove.setVisible(false);
		}
		if(game.equals("poker")) {
			playerTitle.setVisible(true);
			add.setVisible(true);
			remove.setVisible(true);
		}	
	}
	
	public void setRoot(StartModel startMod, RootController root, GameController blackjackController, PokerController pokerController) {
		this.startMod = startMod;
		this.root = root;
		this.blackjackController = blackjackController;
		this.pokerController = pokerController;
		
		money.setText(Integer.toString(startMod.getMoney()));
	}
}
