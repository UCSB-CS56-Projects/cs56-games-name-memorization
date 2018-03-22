package edu.ucsb.cs56.projects.games.name_memorization;
import java.util.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.io.*;
import java.lang.Integer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.io.*;
import java.awt.Dimension;

/**
 * An editor that allows the user to select, add, and delete decks
 *
 * @author Domenic DiPeppe
 * @version for CS56 W16
 */

public class DeckEditor extends BorderPane /*implements ActionListener, ListSelectionListener*/ {

    private BorderPane mainPanel;
    public HBox botPanel;
    private HBox infoPanel;

    private ListView deckList;
    private ObservableList<String> deckNames;
    private Button addDeck;
    private Button removeDeck;
    private Button copyDeck;
    private Button selectDeck;
    private Button saveDeck;
    private Button loadDeck;
    //private ScrollPane deckScroller;
    private TextField deckText;

    private Label deckSize;
    private Label currentName;

    private DeckList decks;
    private Deck deck;

    private String path;
    

	private ListView<String> myListView;

    /**
     * Constructor for Objects of type Editor
     * @param decks A DeckList
     */
    public DeckEditor(DeckList decks) {

	this.decks = decks;
	setPrefSize(800, 600);
	
	mainPanel = new BorderPane();
	setCenter(mainPanel);
	List<String> list = new ArrayList<String>();
	deckNames = FXCollections.observableList(list);
	for (int i = 0; i < decks.size(); i++)
	    deckNames.add(decks.get(i).getName());
	
	deckList = new ListView(deckNames);
	mainPanel.setCenter(deckList);
		
		

	deckList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    if (deckList.getSelectionModel().getSelectedIndices().size() > 0) {
	            	String deckName = (String)(decks.get((int)(deckList.getSelectionModel().getSelectedIndices().get(0))).getName());
			if (deckName != null) {
			    deckText.setText(deckName);
			    currentName.setText("Current Deck: " + decks.get((int)(deckList.getSelectionModel().getSelectedIndices().get(0))).getName() + "            ");
			    deckSize.setText("Size: " + decks.get((int)(deckList.getSelectionModel().getSelectedIndices().get(0))).size());
			}
		    }
		}
	    });
		
	path = System.getProperty("user.dir");
	path = path + "/src/edu/ucsb/cs56/projects/games/name_memorization/saves/";
	
	
	/*deckList.addListener(new ListChangeListener() {
	  @Override
	  public void onChanged(ListChangeListener.Change change) {
	  if (deckList.getSelectionModel().getSelectedIndices().size() > 0) {
	  //Integer test = (Integer)(deckList.getSelectionModel().getSelectedIndices().get(0));
	  String deckName = (String)(decks.get((int)(deckList.getSelectionModel().getSelectedIndices().get(0))).getName());
	  if (deckName != null) {
	  deckText.setText(deckName);
	  currentName.setText("Current Deck: " + decks.get((int)(deckList.getSelectionModel().getSelectedIndices().get(0))).getName() + "            ");
	  deckSize.setText("Size: " + decks.get((int)(deckList.getSelectionModel().getSelectedIndices().get(0))).size());
	  }
	  }
	  }
	  });*/
	
	/*deckScroller = new ScrollPane();
	  deckScroller.getViewport().add(deckList);
	  mainPanel.setTop(add(deckScroller));
	  path=System.getProperty("user.dir");
	  path=path + "/src/edu/ucsb/cs56/projects/games/name_memorization/saves/";
	*/
		
	
	
	
	
	EventHandler<ActionEvent> addHandler = new EventHandler<ActionEvent>() {
		@Override
		// Select button
		public void handle(ActionEvent event) {
		    String deckName = deckText.getText();
		    deckText.setText("");
		    boolean invalid = false;
		    
		    if (deckName != null){
			//Prevents the user from inputting a deck name that is a space or one that already exists
			for (int i = 0; i < deckNames.size(); i++) {
			    if (deckName.equals(deckNames.get(i))) {
				invalid = true;
				//JOptionPane.showMessageDialog(null, "Deck Name Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			    }
			}
			
			if (deckName.trim().length() == 0) {
			    invalid = true;
			    //JOptionPane.showMessageDialog(null, "Deck Name Must Contain A Charater","Error", JOptionPane.ERROR_MESSAGE);
			    return;
			} else if (!invalid) {
			    decks.add(new Deck(deckName));
			    deckNames.add(deckName);
			    deckList.setItems(deckNames);
			    //deckList.setSelectedIndex(decks.size() - 1);
			}
		    }
		}
	    };
	
	EventHandler<ActionEvent> removeHandler = new EventHandler<ActionEvent>() {
		@Override
		// Select button
		public void handle(ActionEvent event) {
		    int selection = deckList.getSelectionModel().getSelectedIndex();
		    if (selection >= 0) {
			decks.remove(selection);
			deckNames.remove(selection);
			
			if (selection >= deckNames.size())
			    selection = deckNames.size() - 1;
			
			//deckScroller.revalidate();
			//deckScroller.repaint();
		    }
		}
	    };
	
	EventHandler<ActionEvent> copyHandler = new EventHandler<ActionEvent>() {
		@Override
		// Select button
		public void handle(ActionEvent event) {
		    int selection = deckList.getSelectionModel().getSelectedIndex();
		    if (selection >= 0) {
			String deckName = decks.get(selection).getName();
			decks.add(decks.get(selection));
			deckNames.add(deckName);
			
			//deckScroller.revalidate();
			//deckScroller.repaint();
		    }
		}
	    };
	
	EventHandler<ActionEvent> saveHandler = new EventHandler<ActionEvent>() {
		@Override
		// Save button
		public void handle(ActionEvent event) {
		    int selection = deckList.getSelectionModel().getSelectedIndex();
		    if (selection >= 0){
			String deckName = decks.get(selection).getName();
			try {
			    FileOutputStream filestream = new FileOutputStream(path + deckName + ".ser");
			    ObjectOutputStream os = new ObjectOutputStream(filestream);
			    Deck dS = decks.get(selection);
			    os.writeObject(dS);
			    os.close();
			} catch(Exception ex) {
			    ex.printStackTrace();
			}
		    }
		}
	    };
	
	EventHandler<ActionEvent> loadHandler = new EventHandler<ActionEvent>() {
		@Override
		// Load button
		public void handle(ActionEvent event) {
		    FileChooser chooser = new FileChooser();
		    //chooser.setInitialDirectory(new File(path));
		    ExtensionFilter filter = new ExtensionFilter("SER files", "ser");
		    chooser.setSelectedExtensionFilter(filter);
		    File selectedFile = chooser.showOpenDialog(Main.mainStage);

		    if (selectedFile != null) {
			try {
			    String fileName = selectedFile.getName();
			    FileInputStream filestream = new FileInputStream(path + fileName);
			    ObjectInputStream os = new ObjectInputStream(filestream);
			    Object one = os.readObject();
			    Deck deckSave = (Deck) one;
			    os.close();
			
			    decks.add(deckSave);
			    deckNames.add(deckSave.getName());
			    //deckScroller.revalidate();
			    //deckScroller.repaint();
			} catch(Exception ex) {
			    ex.printStackTrace();
			}
		    }
		}
	    };
	
	
	
	CreateDeckEntryPanel();
	
	
	addDeck.setOnAction(addHandler);
	removeDeck.setOnAction(removeHandler);
	copyDeck.setOnAction(copyHandler);
	saveDeck.setOnAction(saveHandler);
	loadDeck.setOnAction(loadHandler);
    }
    
    /**
     * Creates the GUI for JButtons and JTextField
     */
    public void CreateDeckEntryPanel(){
        botPanel = new HBox(10);
	botPanel.setStyle("-fx-background-color: #336699;");
	setBottom(botPanel);
	
	HBox embossDeck = new HBox();
	embossDeck.setStyle("-fx-background-color: #112233;");
	embossDeck.setPrefWidth(100);
	embossDeck.setPrefHeight(100);
	mainPanel.setBottom(embossDeck);
	
	deckText = new TextField("Enter Deck Name: ");
	deckText.setPrefColumnCount(10);
	embossDeck.setAlignment(Pos.CENTER);
	embossDeck.getChildren().add(deckText);
	
	addDeck = new Button("Add");
	removeDeck = new Button("Remove");
	copyDeck = new Button("Copy");
	saveDeck = new Button("Save");
	loadDeck = new Button("Load");
	botPanel.setAlignment(Pos.CENTER);
	botPanel.getChildren().addAll(addDeck, removeDeck, copyDeck, saveDeck, loadDeck);
	
	infoPanel = new HBox();
	infoPanel.setStyle("-fx-background-color: #336699;");
	
	mainPanel.setTop(infoPanel);
	
	currentName = new Label("Current Deck: ");
	currentName.setTextFill(Color.web("#FFFFFF"));
	infoPanel.getChildren().add(currentName);
	
	deckSize = new Label("Size: ");
	deckSize.setTextFill(Color.web("#FFFFFF"));
	infoPanel.getChildren().add(deckSize);
	
    }

    //Checks button presses
   /* public void actionPerformed(ActionEvent event){

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

    public HBox getbotPanel(){
	return this.botPanel;
    }
}
