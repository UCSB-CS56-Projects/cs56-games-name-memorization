package edu.ucsb.cs56.projects.games.name_memorization;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * A class that represents a card with two sides
 */
public class Card implements Serializable {

    private String side1;
    private String side2;
    private boolean isPic;
    private boolean isFavorite;

    /**
     * Constructor for Objects of class Card
     *
     * @param side1 the front side of the card
     * @param side2 the back side of the card
     * @param isPicture a boolean that tells us if there is a picture on the card
     */

    public Card(String side1, String side2, boolean isPicture) {
        this.isPic = isPicture;
        this.side1 = side1;
        this.side2 = side2;
    }

    /**
     * Sets the text on the front side
     * @param side1 the text that appears on the front
     */
    public void setSide1(String side1) {
    	this.side1 = side1;
    }

    /**
     * Sets the text on the back side
     * @param side2 the text that appears on the back
     */
    public void setSide2(String side2) {
    	this.side2 = side2;
    }

    /**
     * Gives us the text on the front side
     * @return side1 The text on the front side
     */
    public String getSide1() { return side1; }

    /**
     * Gives us the text on the back side
     * @return side2 The text on the back side
     */
    public String getSide2() { return side2; }
    
    /**
     * Tells us if the card has a picture
     * @return isPic A boolean that is true if there is a picture, false otherwise
     */
    public boolean isPic(){
        return this.isPic;
    }
    
    /**
     * Tells us if the card is favorited
     * @return isFavorite A boolean that is true if the card is favorited by the user, false otherwise
	 */
    public boolean isFavorite() {
    	return this.isFavorite;
    }
    
    /**
     * Sets whether the card is favorited or not
     * @param isFavorite is true or false
	 */
    public void setFavorite(boolean truefalse) {
    	this.isFavorite = truefalse;
    }
}
