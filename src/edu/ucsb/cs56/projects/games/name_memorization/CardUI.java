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
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class CardUI extends BorderPane {

    public TextArea cardText;
    private Label frontPic;
    
    private static final int xdim = 25;
    private static final int ydim = 35;

    private Card card;

    public CardUI(Card c) {
        cardText = new TextArea(c.getSide1());
        cardText.setPrefColumnCount(xdim);
        cardText.setPrefRowCount(ydim);
        cardText.setEditable(false);
        cardText.setWrapText(true);
        cardText.setStyle("-fx-text-alignment: center;");
        frontPic = new Label();
        setCenter(cardText);
        
        card = c;
    }

    /**
     * A method to resize the uploaded image to fit the screen
     * @return resizedImage a resized image of our original picture
     */
    //method to resize the uploaded images to fit the screen.
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    /**
     * Gets the picture from the front side of the card
     * @return a Label that is a picture
     */
    public Label getPic() {
	//getPic is only called if isPic is true, so side1 would contain picture path
        /*try {
	    BufferedImage unsized = ImageIO.read(new File(card.getSide1()));
	    BufferedImage resized = resizeImage(unsized,275,250, unsized.getType());
	    frontPic.setIcon(new ImageIcon(resized));
	    
	    Image image = new Image(new File(card.getSide1()).toURI().toString());
	    ImageView iv = new ImageView(image);
	    Label imageLabel = new Label("Image");
	    imageLabel.setGraphic(iv);
	    return imageLabel;
	    
        } catch (IOException ex) {
            System.out.println("Trouble reading from the file: " + ex.getMessage());
        }
        return frontPic;*/
    	return null;
    }

    public Card getCard() {
    	return card;
    }
    
}
