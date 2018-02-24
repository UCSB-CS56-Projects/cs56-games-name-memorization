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
     * Sets the current deck
     * @param i the index of the deck
     */
    public void setCurrentDeck(int i){
	this.currentDeck = this.get(i);
    }
    
}

