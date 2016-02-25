package edu.ucsb.cs56.projects.games.name_memorization;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * A class that represents a card with two sides
 */
public class Card extends JPanel implements Serializable {


    private JTextArea cardText;
    private String side1;
    private String side2;
    private boolean isPic;
    private JLabel frontPic;
    private static final int xdim = 500;
    private static final int ydim = 250;


    /**
     * A method to resize the uploaded image to fit the screen
     * @return resizedImage a resized image of our original picture
     */
    //method to resize the uploaded images to fit the screen.
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type){  
        BufferedImage resizedImage = new BufferedImage(width, height, type);  
        Graphics2D g = resizedImage.createGraphics();  
        g.drawImage(originalImage, 0, 0, width, height, null);  
        g.dispose();  
        
        return resizedImage;  
    }  

    /**
     * Constructor for Objects of class Card
     *
     * @param side1 the front side of the card 
     * @param side2 the back side of the card
     * @param isPicture a boolean that tells us if there is a picture on the card
     */
    
    public Card(String side1, String side2, boolean isPicture) {
	isPic = isPicture;
	this.side1 = side1;
	this.side2 = side2;
	cardText = new JTextArea(side1);
	cardText.setPreferredSize(new Dimension(xdim,ydim));
    cardText.setEditable(false);
    cardText.setLineWrap(true);
    frontPic = new JLabel();
  
	this.add(cardText);
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
     * Gets the picture from the front side of the card
     * @return frontPic a JLabel that is a picture
     */
    public JLabel getPic(){
        try{//getPic is only called if isPic is true, so side1 would contain picture path
            BufferedImage unsized = ImageIO.read(new File(side1));
            BufferedImage resized = resizeImage(unsized,275,250,unsized.getType());
            frontPic.setIcon(new ImageIcon(resized));
        }catch(IOException ex){
            System.out.println("Trouble reading from the file: " + ex.getMessage());
        }
        return frontPic;
    }

    /**
     * Tells us if the card has a picture
     * @return isPic A boolean that is true if there is a picture, false otherwise
     */
    public boolean isPic(){
        return isPic;
    }


}
