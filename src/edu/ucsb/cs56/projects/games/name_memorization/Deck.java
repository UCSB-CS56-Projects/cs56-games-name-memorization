

import java.util.ArrayList;
import java.util.Scanner;

public class Deck extends ArrayList<Card>{
    

    public void addCard(String side1, String side2) {
	Card c = new Card(side1,side2);
	this.add(c);
    }

    public void removeCard(int i) {
	this.remove(i);
    }

    public void editCard(Card c, String side1, String side2) {
	c.setSide1(side1);
	c.setSide2(side2);
	
    }
	
    
}
