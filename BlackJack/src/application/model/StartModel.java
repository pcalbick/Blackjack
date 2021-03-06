package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class StartModel {
	private List<GridPane> cards = new ArrayList<GridPane>();
	private List<Integer> pickedCards = new ArrayList<Integer>();
	private IntegerProperty numb = new SimpleIntegerProperty(0);
	private IntegerProperty money = new SimpleIntegerProperty(0);
	
	public void addCard(GridPane card) {
		cards.add(card);
	}
	
	public GridPane getCard() {
		Runnable r = new newRandom();
		Thread t = new Thread(r);
		t.start();
		try {
			t.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		pickedCards.add((Integer)numb.get());
		return cards.get(numb.get());
	}
	
	public void print() {
		for(Integer i : pickedCards) {
			GridPane card = (GridPane)cards.get(i.intValue());
			Text text = (Text)card.getChildren().get(0);
			System.out.print(text.getText() + " ");
		}
	}
	
	public void clearCards() {
		pickedCards.clear();
	}
	
	public void cardCount() {
		System.out.println(cards.size());
	}
	
	public int getMoney() {
		return money.get();
	}
	
	public void changeMoney(int newMoney) {
		money.set(money.get()+newMoney);
	}
	
	public void setMoney(int set) {
		money.set(set);
	}
	
	public void shuffle(GridPane[] cards) {
		Random rand = new Random();
		for(int i=0; i<cards.length; i++) {
			int numb = rand.nextInt(i+1);
			GridPane swap = cards[numb];
			cards[numb] = cards[i];
			cards[i] = swap;
		}
	}
	
	class newRandom implements Runnable{
		public void run() {
			Random rand = new Random();
			numb.set(rand.nextInt(51) + 0);
			recheck:
			for(Integer i : pickedCards) {
				if(numb.get() == i.intValue()) {
					synchronized (numb) {
						numb.set(rand.nextInt(51) + 0);
					}
					break recheck;
				}
			}
		}
	}
}
