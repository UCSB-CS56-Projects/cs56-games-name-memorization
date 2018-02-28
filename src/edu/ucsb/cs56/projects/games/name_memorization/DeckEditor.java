package edu.ucsb.cs56.projects.games.name_memorization;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Dimension;

/**
 * An editor that allows the user to select, add, and delete decks
 *
 * @author Domenic DiPeppe
 * @version for CS56 W16
 */

public class DeckEditor extends BorderPane /*implements ActionListener, ListSelectionListener*/ {

    private BorderPane topPanel;
    private HBox botPanel;
    public HBox dataPanel;
    private HBox infoPanel;

    private ListView deckList;
    private Vector deckNames;
    private Button addDeck;
    private Button removeDeck;
    private Button copyDeck;
    private Button selectDeck;
    private Button saveDeck;
    private Button loadDeck;
    private ScrollPane deckScroller;
    private TextField deckText;

    private Label deckSize;
    private Label currentName;

    private DeckList decks;
    private Deck deck;

    private String path;

    /**
     * Constructor for Objects of type Editor
     * @param decks A DeckList
     */
    public DeckEditor(/*DeckList decks*/){

	this.decks = decks;
	setPrefSize(800, 600);

	topPanel = new BorderPane();
	setCenter(topPanel);
	//topPanel.setAlignment(Pos.CENTER); 
	deckNames = new Vector();
	for(int i=0;i<decks.size();i++)
	    deckNames.addElement(decks.get(i).getName());

	//deckList = new ListView(deckNames);
	//deckList.addListSelectionListener(this);

	/*deckScroller = new ScrollPane();
	deckScroller.getViewport().add(deckList);
	topPanel.setTop(add(deckScroller));
  path=System.getProperty("user.dir");
  path=path + "/src/edu/ucsb/cs56/projects/games/name_memorization/saves/";
     */
	CreateDeckEntryPanel();
    }

    /**
     * Creates the GUI for JButtons and JTextField
     */
    public void CreateDeckEntryPanel(){
        dataPanel = new HBox();
		dataPanel.setStyle("-fx-background-color: #336699;");
		setBottom(dataPanel);

		HBox embossDeck = new HBox();
		embossDeck.setStyle("-fx-background-color: #112233;");
		embossDeck.setPrefWidth(100);
		embossDeck.setPrefHeight(100);
		topPanel.setBottom(embossDeck);

		deckText = new TextField("Enter Deck Name: ");
		deckText.setPrefColumnCount(10);
		embossDeck.setAlignment(Pos.CENTER);
		embossDeck.getChildren().add(deckText);

		addDeck = new Button("Add");
		//dataPanel.add(addDeck);
		//addDeck.addActionListener(this);
		removeDeck = new Button("Remove");
		//dataPanel.add(removeDeck);
		//removeDeck.addActionListener(this);
		copyDeck = new Button("Copy");
		//dataPanel.add(copyDeck);
		//copyDeck.addActionListener(this);
		saveDeck = new Button("Save");
		//dataPanel.add(saveDeck);
		////saveDeck.addActionListener(this);
		loadDeck = new Button("Load");
		//dataPanel.add(loadDeck);
		//loadDeck.addActionListener(this);
		dataPanel.setAlignment(Pos.CENTER);
		dataPanel.getChildren().addAll(addDeck, removeDeck, copyDeck, saveDeck, loadDeck);

		



		infoPanel = new HBox();
		infoPanel.setStyle("-fx-background-color: #336699;");

		topPanel.setTop(infoPanel);
		
		currentName = new Label("Current Deck: ");
		currentName.setTextFill(Color.web("#FFFFFF"));
		infoPanel.getChildren().add(currentName);

		deckSize = new Label("Size: ");
		deckSize.setTextFill(Color.web("#FFFFFF"));
		infoPanel.getChildren().add(deckSize);

    }

    //Handles list selection changes
    /*public void valueChanged(ListSelectionEvent event){

	if(event.getSource() == deckList && !event.getValueIsAdjusting()){
	    String deckName = (String)deckList.getSelectedValue();
	    if(deckName != null){
		deckText.setText(deckName);
		currentName.setText("Current Deck: " + decks.get(deckList.getSelectedIndex()).getName() + "            ");
		deckSize.setText("Size: " + decks.get(deckList.getSelectedIndex()).size());
	    }
	}
    }*/

    //Checks button presses
   /* public void actionPerformed(ActionEvent event){

	if(event.getSource() == addDeck){
	    String deckName = deckText.getText();
	    deckText.setText("");
	    boolean invalid = false;

	    if(deckName != null){
		//Prevents the user from inputting a deck name that is a space or one that already exists

		for(int i=0;i<deckNames.size();i++){
		    if(deckName.equals(deckNames.get(i))){
			invalid = true;
			JOptionPane.showMessageDialog(null, "Deck Name Already Exists","Error", JOptionPane.ERROR_MESSAGE);
			return;
		    }
		}

		if (deckName.trim().length() == 0){
		    invalid = true;
		    JOptionPane.showMessageDialog(null, "Deck Name Must Contain A Charater","Error", JOptionPane.ERROR_MESSAGE);
		    return;
		}

		else if(!invalid){
		    this.decks.add(new Deck(deckName));
		    deckNames.addElement(deckName);
		    deckList.setListData(deckNames);
		    deckList.setSelectedIndex(decks.size()-1);
		    deckScroller.revalidate();
		    deckScroller.repaint();

		}

	    }
	}

	if(event.getSource() == removeDeck){

	    int selection = deckList.getSelectedIndex();
	    if(selection >=0){

		decks.remove(selection);
		deckNames.removeElementAt(selection);
		deckList.setListData(deckNames);
		if(selection >= deckNames.size())
		    selection = deckNames.size() - 1;
		deckList.setSelectedIndex(selection);
		deckScroller.revalidate();
		deckScroller.repaint();
	    }
	}


	if(event.getSource() == copyDeck){
	    int selection = deckList.getSelectedIndex();
	    String deckName = this.decks.get(selection).getName();
	    if(selection >= 0){
	        this.decks.add(this.decks.get(selection));
		deckNames.add(deckName);
		deckList.setListData(deckNames);
		deckList.setSelectedIndex(decks.size()-1);
		deckScroller.revalidate();
		deckScroller.repaint();
	    }

	}

  if(event.getSource() == saveDeck){
	    int selection = deckList.getSelectedIndex();
	    String deckName = this.decks.get(selection).getName();
	    if(selection >= 0){
        try {
          FileOutputStream filestream = new FileOutputStream(path + deckName + ".ser");
          ObjectOutputStream os = new ObjectOutputStream(filestream);
          os.writeObject(this.decks.get(selection));
          os.close();
        } catch(Exception ex) {
          ex.printStackTrace();
        }
	    }

	}

  if(event.getSource() == loadDeck){
    JFileChooser chooser = new JFileChooser(new File(path));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SER files", "ser");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(thisPanel);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
      try {
        String fileName = chooser.getSelectedFile().getName();
        FileInputStream filestream = new FileInputStream(path + fileName);
        ObjectInputStream os = new ObjectInputStream(filestream);
        Object one = os.readObject();
        Deck deckSave = (Deck) one;
        os.close();

        this.decks.add(deckSave);
        deckNames.add(deckSave.getName());
    		deckList.setListData(deckNames);
    		deckList.setSelectedIndex(decks.size()-1);
    		deckScroller.revalidate();
    		deckScroller.repaint();
      } catch(Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}*/

    public ListView getDeckList(){
	return this.deckList;
    }

    public HBox getDataPanel(){
	return this.dataPanel;
    }
}
