package edu.ucsb.cs56.projects.games.name_memorization;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Dimension;
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
 * A class that creates the GUI that allows the user to edit cards
 */
public class CardEditor extends BorderPane {
	private TextArea front;
	private TextArea back;
	private Button upload;
	private Button exit;
	private HBox topPanel;
	private BorderPane midPanel;
	public HBox botPanel;

	private Label prompt1;
	private Label FRONT;
	private Label BACK;
	private Deck deck;
	private String frontText;
	private String backText; 
	private String path; //path to people folder
	private Label frontPic;

	private boolean isPicture; 

	/**
	 * Constructor for CardEditor
	 * Creates the GUI in which the user can edit cards
	 * @param c A Card
	 */
	
	public CardEditor(Card c) {
		//sets path variable to directory of the people pictures
		path = System.getProperty("user.dir");
		path = path + "/src/edu/ucsb/cs56/projects/games/name_memorization/people/";

		isPicture = false;

		topPanel = new HBox();
		topPanel.setPadding(new Insets(10, 10, 10, 10));
		setTop(topPanel);
		topPanel.setStyle("-fx-background-color: #888888;");
		topPanel.setAlignment(Pos.CENTER);
		prompt1 = new Label("Edit Card");
		prompt1.setFont(new Font("Lucida Grande", 18));
		topPanel.getChildren().addAll(prompt1);

		midPanel = new BorderPane();
		midPanel.setStyle("-fx-background-color: #336699;");

		FRONT = new Label("FRONT");
		midPanel.getChildren().add(FRONT);

		front = new TextArea(c.getSide1());
		front.setWrapText(true);
		front.setPrefColumnCount(25);
		front.setPrefRowCount(35);
		midPanel.setLeft(front);
		
		
		back = new TextArea(c.getSide2());
		back.setWrapText(true);
		back.setPrefColumnCount(25);
		back.setPrefRowCount(35);
		midPanel.setRight(back);

		BACK = new Label("BACK");
		midPanel.getChildren().add(BACK);

		setCenter(midPanel);
		
		botPanel = new HBox(10);
		botPanel.setPadding(new Insets(10, 10, 10, 10));
		upload = new Button("Upload a Picture");
		botPanel.getChildren().add(upload);
		setBottom(botPanel);

		

		
	    EventHandler<ActionEvent> uploadHandler = new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event) {
	    		FileChooser fileChooser = new FileChooser();
	    		fileChooser.setTitle("Open Resource File");
	    		File selectedFile = fileChooser.showOpenDialog(Main.mainStage);

				if (selectedFile != null) {

					String name = selectedFile.getName();
					path = path + name; // path should now contain full location of chosen pic

					System.out.println("name: " + name);
					System.out.println("path: " + path);
					
					front.setVisible(false);
					try {
						BufferedImage unsized = ImageIO.read(new File(path));
						BufferedImage resized = resizeImage(unsized, 275, 250, unsized.getType());
						Image image = new Image(selectedFile.toURI().toString());
						ImageView iv = new ImageView(image);
						midPanel.setCenter(iv);
					} catch (IOException ex) {
						System.out.println("Trouble reading from the file: " + ex.getMessage());
					}
					front.setText(path); // because namegame screen takes the input from front Text area and sets it
					// area and sets it Text area and sets it front text
					isPicture = true;
				}
	    	}
	    };

	    upload.setOnAction(uploadHandler);
	}

	//method to resize the uploaded images to fit the screen.
	private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type){  
		BufferedImage resizedImage = new BufferedImage(width, height, type);  
		Graphics2D g = resizedImage.createGraphics();  
		g.drawImage(originalImage, 0, 0, width, height, null);  
		g.dispose();  

		return resizedImage;  
	}  

	/**
	 * Gets the text on the front of the card
	 *
	 * @return frontText the text on the front
	 */
	public String getFrontText() {
		frontText = front.getText();
		return frontText; 
	}

	/**
	 * Gets the text on the back of the card
	 *
	 * @return backText the text on the back
	 */
	public String getBackText() { 
		backText = back.getText();
		return backText; 
	}
	/**
	 * Gets the picture on the front card
	 *
	 * @return frontPic the picture on the front of the card
	 */
	public Label getPic(){
		return frontPic;
	}

/*
	private class uploadButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser(new File(path));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(thisPanel);
			if(returnVal == JFileChooser.APPROVE_OPTION) {

				String name = chooser.getSelectedFile().getName();
				path = path + name; // path should now contain full location of chosen pic

				front.setVisible(false);
				frontPic=new JLabel();
				frontPic.setBounds(25,100, 275,250);
				try {
					BufferedImage unsized = ImageIO.read(new File(path));
					BufferedImage resized = resizeImage(unsized,275,250,unsized.getType());
					frontPic.setIcon(new ImageIcon(resized));
					thisPanel.validate();
					thisPanel.repaint();
				}catch(IOException ex){
					System.out.println("Trouble reading from the file: " + ex.getMessage());
				}
				front.setText(path); // because namegame screen takes the input from front Text area and sets it
				// area and sets it Text area and sets it front text
				isPicture = true;
				thisPanel.add(frontPic);
			}
		}
	}

	*//**
	 * Tells us if the Card has a picture
	 *
	 * @return isPicture A boolean that is true if the card has a picture, false otherwise
	 */
	public boolean isPic(){
		return isPicture;
	}

	public HBox getBotPanel(){
		return botPanel;
	}
}
