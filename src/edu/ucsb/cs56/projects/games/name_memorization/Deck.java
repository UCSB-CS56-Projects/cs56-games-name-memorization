package edu.ucsb.cs56.projects.games.name_memorization;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Deck extends ArrayList<Card> implements Serializable {
    
    private String deckName;

    public Deck(String name) {
	deckName = name;
    }

    public void addCard(String side1, String side2, boolean isPicture) {
	Card c = new Card(side1,side2, isPicture);
	this.add(c);
    }

    public void removeCard(int i) {
	this.remove(i);
    }

    public void editCard(Card c, String side1, String side2) {
	c.setSide1(side1);
	c.setSide2(side2);
	
    }

    public String getName(){
        return deckName;
    }
	
    
}
