package edu.ucsb.cs56.projects.games.name_memorization;

import java.io.*;
import java.util.ArrayList;

/**
 * A class that represents a collection of Card objects
 */
public class Deck extends ArrayList<Card> implements Serializable {
    
    private String deckName;

    /**
     * Constructor for objects of type Deck
     *
     * @param name the name of the deck
     */
    public Deck(String name) {
	deckName = name;
    }

    /**
     * Adds a card to the deck
     *
     * @param side1 the text on the front side
     * @param side2 the text on the back side
     * @param isPicture Tells us if the card has a picture
     */
    public void addCard(String side1, String side2, boolean isPicture) {
		Card c = new Card(side1,side2, isPicture);
		this.add(c);
    }
    /**
     * Removes a card from the deck at the specified index
     *
     * @param i the index of the card that is to be removed
     */
    public void removeCard(int i) {
	this.remove(i);
    }

    /**
     * Edits the text fields of a Card
     *
     * @param c the card that is to be edited
     * @param side1 the text on the front 
     * @param side2 the text on the back
     */
    public void editCard(Card c, String side1, String side2) {
		c.setSide1(side1);
		c.setSide2(side2);
    }

    /**
     * Returns the name of the deck
     *
     * @return deckName the name of the deck
     */
    public String getName(){
        return deckName;
    }
 
}
