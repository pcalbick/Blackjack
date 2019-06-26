package application.controller;

import application.model.StartModel;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class SolitairController {
	
	@FXML
	GridPane game;
	
	public void init(StartModel startMod) {
		for(int i=0; i<35; i++) {
			System.out.println(game.getChildren().size());
		}
	}
}
