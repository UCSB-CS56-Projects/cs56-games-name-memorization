package edu.ucsb.cs56.projects.games.name_memorization;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

/**
 * A class that represents a card with two sides
 */
public class Card extends BorderPane implements Serializable {


    private TextArea cardText;
    private String side1;
    private String side2;
    private boolean isPic;
    private Label frontPic;
    private static final int xdim = 25;
    private static final int ydim = 35;


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
        this.isPic = isPicture;
        this.side1 = side1;
        this.side2 = side2;
        cardText = new TextArea(side1);
        cardText.setPrefColumnCount(xdim);
        cardText.setPrefRowCount(ydim);
        cardText.setEditable(false);
        cardText.setWrapText(true);
        frontPic = new Label();

        setCenter(cardText);
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
    public Label getPic(){
        try {//getPic is only called if isPic is true, so side1 would contain picture path
            /*BufferedImage unsized = ImageIO.read(new File(side1));
            BufferedImage resized = resizeImage(unsized,275,250, unsized.getType());
            frontPic.setIcon(new ImageIcon(resized));*/

			Image image = new Image(new File(side1).toURI().toString());
			ImageView iv = new ImageView(image);
			Label imageLabel = new Label("Image");
			imageLabel.setGraphic(iv);
			return imageLabel;
        } catch(IOException ex) {
            System.out.println("Trouble reading from the file: " + ex.getMessage());
        }
        return frontPic;
    }

    /**
     * Tells us if the card has a picture
     * @return isPic A boolean that is true if there is a picture, false otherwise
     */
    public boolean isPic(){
        return this.isPic;
    }


}
