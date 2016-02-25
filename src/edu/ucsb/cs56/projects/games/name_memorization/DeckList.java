package edu.ucsb.cs56.projects.games.name_memorization;

import java.io.*;
import java.util.ArrayList;

/**
 * A class that represents a collection of Deck objects
 *
 *@author Domenic DiPeppe
 *@version for CS56 W16
 */
public class DeckList extends ArrayList<Deck> implements Serializable {

    private Deck currentDeck;
    
    /**
     * No-arg Constructor for objects of type Decks
     */
    public DeckList() {
    }

    /**
     * Adds a deck to the DeckList
     */
    public void addDeck(Deck d) {
	this.add(d);
    }
    /**
     * Removes a card from the deck at the specified index
     *
     * @param i the index of the card that is to be removed
     */
    public void removeDeck(int i) {
	this.remove(i);
    }

    
    /**
     * Returns a deck at the specified Index
     * @param i the index
     * @return the deck 
     */
    public Deck getDeck(int i){
	return this.get(i);
    }

    /**
     * Sets the current deck
     * @param i the index of the deck
     */
    public void setCurrentDeck(int i){
	this.currentDeck = this.get(i);
    }
    
}

