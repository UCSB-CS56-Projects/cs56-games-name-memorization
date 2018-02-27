package edu.ucsb.cs56.projects.games.name_memorization;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

/**
 * Preliminary engine for running a name memorization game
 *
 *@author Anthony Hoang, Colin Biafore
 *@author Domenic DiPeppe
 *@version  for CS56, W16
 */

public class Main extends Application {

	//Main pane
	private BorderPane pane;

	//Top Control Panel
	private Button add;
	private Button edit;
	private Button delete;
	private Button next;
	private Button previous;
	private Button selectDeck;
	private Button menu;

	//Bottom Control Panel
	private HBox southQuiz = new HBox();
	private Button toFront;
	private Button toBack;
	private Button guess;
	private TextField guessText;

	//East Control Panel
	private Label deckName;
	private Button restart;

	//West Control Panel
	private BorderPane westQuiz = new BorderPane();
	private Label scoreLabel;
	private Label scoreNum;
	private Label scoreLabelQuiz;
	private Label scoreNumQuiz;
	private int score = 0;
	private int scoreQuiz;

	private Label deckSize;
	private Label sizeLabel;

	private Button correct;
	private Button incorrect;
	private Button correctQuiz;

	private Label picture;
	private Image pic;

	//DeckList for Decks
	private DeckList decks;

	//Current Card Viewer
	//private JPanel currentCard;
	private TextArea cardText;
	private int current;
	private Deck d;

	//Deck viewer
	//private JPanel DeckEditor;

	//Label for Card Number:
	private Label cardNum;
	//UI Card Index
	private Label cNum;

	private static Alert myStart;
	
	public static Stage mainStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override 
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		primaryStage.setTitle("JavaFX attempt");

		//Setup pane
		pane = new BorderPane();

		Scene scene = new Scene(pane, 800, 600);
		primaryStage.setScene(scene);

		//Initialize North Control Panel
		HBox northBox = new HBox(10);
		pane.setTop(northBox);
		northBox.setPadding(new Insets(10, 10, 10, 10));
		northBox.setAlignment(Pos.CENTER);
		northBox.setStyle("-fx-background-color: #112233;");

		add = new Button("Add");
		edit = new Button("Edit");
		delete = new Button("Delete");
		previous = new Button("Previous");
		next = new Button("Next");
		selectDeck = new Button("Select Deck");
		menu = new Button("Main Menu");

		northBox.getChildren().addAll(add, edit, delete, previous, next, selectDeck, menu);

		//Initialize South Control Panel
		HBox southBox = new HBox(10);
		pane.setBottom(southBox);
		southBox.setPadding(new Insets(10, 10, 10, 10));
		southBox.setAlignment(Pos.CENTER);
		southBox.setStyle("-fx-background-color: #112233;");

		toFront = new Button("Show Front");
		toBack = new Button("Show Back");

		southBox.getChildren().addAll(toFront, toBack);

		// South Quiz Mode
		southQuiz = new HBox();
		southQuiz.setVisible(true);
		guess = new Button("Guess!");
		guessText = new TextField("Enter Guess Here", 30);
		southQuiz.getChildren().add(guessText);
		southQuiz.getChildren().add(Box.createRigidArea(new Dimension(10, 50)));
		southQuiz.getChildren().add(guess);
		//southQuiz.setBackground(Color.lightGray);


		//Initialize Card Viewer
		currentCard = new HBox();
		pane.setCenter(currentCard);
		currentCard.setVisible(true);
		cardText = new TextArea();
		Font cardFont = new Font("Verdana", 24);
		cardText.setFont(cardFont);
		cardText.setEditable(false);
		currentCard.getChildren().add(cardText);
		//currentCard.setBackground(Color.WHITE);

		//decks is set in Main
		this.decks = decks;
		this.d = decks.get(0);
		if (d.size() == 0) cardText.setText("Deck is Empty!");

		//West Panel Components
		BorderPane westBox = new BorderPane();
		pane.setLeft(westBox);
		westBox.setStyle("-fx-background-color: #336699;");

		VBox westNorth = new VBox(10);
		westNorth.setPadding(new Insets(10, 10, 10, 10));
		westNorth.setAlignment(Pos.CENTER);

		deckName = new Label("DECK NAME" /*d.getName()*/);
		deckName.setFont(new Font("Lucida Grande", 18));

		scoreLabel = new Label("Score: " + Integer.toString(score));
		scoreLabel.setFont(new Font("Lucida Grande", 18));

		westNorth.getChildren().addAll(deckName, scoreLabel);

		VBox westCenter = new VBox(10);
		westCenter.setPadding(new Insets(10, 10, 10, 10));
		westCenter.setAlignment(Pos.CENTER);

		correct = new Button("      Correct!      ");
		incorrect = new Button("     Incorrect     ");

		westCenter.getChildren().addAll(correct, incorrect);

		westBox.setTop(westNorth);
		westBox.setCenter(westCenter);

		//West panel Quiz
		westQuiz.setStyle("-fx-background-color: #117799;");

		VBox westCenterQuiz = new VBox();
		westQuiz.setCenter(westCenterQuiz);
		westCenterQuiz.setPadding(new Insets(10, 10, 10, 10));
		westCenterQuiz.setAlignment(Pos.CENTER);

		scoreLabelQuiz = new Label("Score: " + Integer.toString(scoreQuiz));
		scoreLabelQuiz.setFont(new Font("Lucida Grande", 18));

		westCenterQuiz.getChildren().addAll(scoreLabelQuiz);

		VBox westSouthQuiz = new VBox();
		westQuiz.setBottom(westSouthQuiz);
		westSouthQuiz.setPadding(new Insets(10, 10, 10, 10));
		westSouthQuiz.setAlignment(Pos.CENTER);

		correctQuiz = new Button("Override Correct");
		correctQuiz.setVisible(false);

		westSouthQuiz.getChildren().addAll(correctQuiz);

		deckName = new Label("DECK NAME"); //d.getName()
		deckName.setFont(new Font("Lucida Grande", 18));
		westQuiz.setTop(deckName);

		//East Panel
		VBox eastBox = new VBox(10);
		pane.setRight(eastBox);
		eastBox.setPadding(new Insets(10, 10, 10, 10));
		eastBox.setAlignment(Pos.CENTER);
		eastBox.setStyle("-fx-background-color: #336699;");

		sizeLabel = new Label("Deck Size:");
		sizeLabel.setFont(new Font("Lucida Grande", 18));

		deckSize = new Label("deckSize" /*Integer.toString(d.size())*/);
		deckSize.setFont(new Font("Lucida Grande", 18));

		cardNum = new Label("Card Number:");
		cardNum.setFont(new Font("Lucida Grande", 18));

		cNum = new Label("cNum" /*Integer.toString(current)*/);
		cNum.setFont(new Font("Lucida Grande", 18));

		restart = new Button("Restart");

		eastBox.getChildren().addAll(sizeLabel, deckSize, cardNum, cNum, restart);

		
		
		
		EventHandler<ActionEvent> addHandler = new EventHandler<ActionEvent>() {
	    	@Override
	    	public void handle(ActionEvent event) {
	    		TestPane test = new TestPane();
	    		Stage stage = new Stage();
	    		stage.setScene(new Scene(test));
	    		stage.show();
	    		primaryStage.hide();
	    	}
	    };

	    add.setOnAction(addHandler);
		
		
		
		/*JPanel eastCenter = new JPanel();
	eastCenter.setBackground(Color.BLUE);
	eastCenter.add(cardNum);
	eastCenter.add(cNum);

	JPanel top = new JPanel();
	top.setBackground(Color.BLUE);
	top.add(sizeLabel, BorderLayout.NORTH);
	top.add(deckSize, BorderLayout.NORTH);

	east.add(eastCenter,BorderLayout.CENTER);
	east.add(top,BorderLayout.NORTH);
	pane.add(east,BorderLayout.EAST);*/

		/*
	//BUTTON LISTENERS -- Uncommented = implemented and functioning!!
	//Currently you can add as many cards as you want,
	//Go to the next or previous card in the deck,
	//And see both sides of the current card

	//Initialize Add Button Listener
	addButtonListener addListener = new addButtonListener();
	add.addActionListener(addListener);

	//Initialize Edit Button Listener
	editButtonListener editListener = new editButtonListener();
	edit.addActionListener(editListener);

	//Initialize Delete Button Listener
	deleteButtonListener deleteListener = new deleteButtonListener();
	delete.addActionListener(deleteListener);

	//Initialize Previous Button Listener
	previousButtonListener previousListener = new previousButtonListener();
	previous.addActionListener(previousListener);

	//Initialize Next Button Listener
	nextButtonListener nextListener = new nextButtonListener();
	next.addActionListener(nextListener);

	//Initialize SelectDeck Button Listener
	selectDeckButtonListener selectDeckListener = new selectDeckButtonListener();
	selectDeck.addActionListener(selectDeckListener);

	menuButtonListener menuListener = new menuButtonListener();
	menu.addActionListener(menuListener);

	//Initialize Override correct Button Listener
	overcorrectButtonListener overcorrectListener = new overcorrectButtonListener();
	correctQuiz.addActionListener(overcorrectListener);

	//Initialize Guess Button Listener
	guessButtonListener guessListener = new guessButtonListener();
	guess.addActionListener(guessListener);

	//Initialize Front Button Listener
	frontButtonListener frontListener = new frontButtonListener();
	toFront.addActionListener(frontListener);

	//Initialize Back Button Listener
	backButtonListener backListener = new backButtonListener();
	toBack.addActionListener(backListener);

	correctButtonListener correctListener = new correctButtonListener();
	correct.addActionListener(correctListener);

	incorrectButtonListener incorrectListener = new incorrectButtonListener();
	incorrect.addActionListener(incorrectListener);

	restartButtonListener restartListener = new restartButtonListener();
	restart.addActionListener(restartListener);

	this.pack();

	myStart = new Alert(AlertType.CONFIRMATION);
	myStart.setTitle("Welcome");
	myStart.setHeaderText("Welcome to the Name Memorization Game.");
	myStart.setContentText("For instructions and mode changing click Main Menu in the game.");

	myStart.showAndWait();*/

		primaryStage.show();

	}








	//     /**
	//      * This method will be called with next/previous button if card has a pic
	//      *
	//      * @param c A card
	//      */
	//     public void setPic(Card c) {
	//     	cardText.setVisible(false);
	//     	currentCard.remove(picture);
	// 	picture = c.getPic();
	// 	picture.setVisible(true);
	// 	currentCard.add(picture, BorderLayout.CENTER);
	// 	thisFrame.getContentPane().validate();
	// 	thisFrame.getContentPane().repaint();
	//     }

	//     /**
	//      * This method will be called with next/previous if card is text
	//      *
	//      * @param c A card
	//      * @param side the side of the card
	//      */
	//     public void setPrint(Card c, int side) {
	//     	picture.setVisible(false);
	// 	cardText.setVisible(true);
	// 	currentCard.remove(picture);
	// 	if(side == 1) {
	// 	    cardText.setText(c.getSide1());
	// 	} else if(side == 2) {
	// 	    cardText.setText(c.getSide2());
	// 	}

	//     }

	//     /**
	//      * Sets the current deck
	//      *
	//      * @param d A deck
	//      */
	//     public void setDeck(Deck d) {
	// 	this.d = d;
	//     }

	//     /**
	//      * Returns a deck
	//      *
	//      * @return d A deck
	//      */
	//     public Deck getDeck() {
	// 	return this.d;
	//     }

	//     /**
	//      * Sets the current DeckList
	//      * @param decks A DeckList
	//      */
	//     public void setDeckList(DeckList decks) {
	// 	this.decks = decks;
	//     }

	//     /**
	//      * Returns the current deckList
	//      * @return decks a DeckList
	//      */
	//     public DeckList getDeckList() {
	// 	return decks;
	//     }

	//     /**
	//      * Updates the size of the deck to be the value specified
	//      *
	//      * @param decksize The new size of the deck
	//      */
	//     public void updateSize(int deckSize) {
	// 	this.deckSize.setText(Integer.toString(deckSize));
	//     }

	//     /**
	//      * Sets the index of the current card
	//      */
	//     public void setCardNum() {
	// 	if (d.size() < 1) {
	// 	    this.cNum.setText("0");
	// 	} else {
	// 	    this.cNum.setText("1");
	// 	}
	//     }


	/**
	 * addButtonListener, Brings up a window to add a card
	 */
	/*private class addButtonListener implements ActionListener {

    	CardEditor editor;

    	public void actionPerformed(ActionEvent event) {
    		pane.setVisible(false);

    		//Creates a new card editor
    		Card c = new Card("Enter Text", "Enter Text", false);
    		editor = new CardEditor(c);
    		thisFrame.add(editor);

    		Button confirm = new Button("Confirm");
    		editor.getBotPanel().add(confirm);
    		editor.getBotPanel().add(Box.createRigidArea(new Dimension(40, 0)));
    		confirmButtonListener confirmListener = new confirmButtonListener();
    		confirm.addActionListener(confirmListener);

    		Button cancel = new Button("Cancel");
    		editor.getBotPanel().add(cancel);
    		cancelButtonListener cancelListener = new cancelButtonListener();
    		cancel.addActionListener(cancelListener);
    	}

    	// Only adds a card once confirm has been pressed
    	private class confirmButtonListener implements ActionListener {
    		public void actionPerformed(ActionEvent e) {
    			String side1 = editor.getFrontText();
    			String side2 = editor.getBackText();

    			d.addCard(side1, side2, editor.isPic());
    			current = d.size() - 1;
    			Card h = (Card)d.get(current);
    			if (h.isPic()) {
    				setPic(h);
    			} else {
    				setPrint(h, 1);
    			}
    			next.setEnabled(true);
    			previous.setEnabled(true);
    			deckSize.setText(Integer.toString(d.size()));
    			cNum.setText(Integer.toString(current + 1));
    			thisFrame.remove(editor);
    			pane.setVisible(true);
    		}
    	}

    	private class cancelButtonListener implements ActionListener {
    		public void actionPerformed(ActionEvent e) {
    			thisFrame.remove(editor);
    			pane.setVisible(true);
    		}
    	}
    }*/

	//     private class editButtonListener implements ActionListener {

	// 	CardEditor editor;

	// 	public void actionPerformed(ActionEvent e) {

	// 	    if (d.size() == 0) {
	// 		JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
	// 		return;
	// 	    }

	// 	    pane.setVisible(false);
	// 	    editor = new CardEditor(d.get(current));
	// 	    thisFrame.add(editor);

	// 	    JButton confirm = new JButton("Confirm");
	// 	    confirm.setBounds(260, 400, 100, 30);
	// 	    editor.getBotPanel().add(confirm);
	// 	    editor.getBotPanel().add(Box.createRigidArea(new Dimension(40, 0)));
	// 	    confirmButtonListener confirmListener = new confirmButtonListener();
	// 	    confirm.addActionListener(confirmListener);

	// 	    JButton cancel = new JButton("Cancel");
	// 	    editor.getBotPanel().add(cancel);
	// 	    cancelButtonListener cancelListener = new cancelButtonListener();
	// 	    cancel.addActionListener(cancelListener);
	// 	}

	// 	private class confirmButtonListener implements ActionListener {
	// 	    public void actionPerformed(ActionEvent e) {
	// 		String side1 = editor.getFrontText();
	// 		String side2 = editor.getBackText();
	// 		Card h = (Card) d.get(current);

	// 		d.editCard(h, side1, side2);
	// 		if(h.isPic()){
	// 		    setPic(h);
	// 		} else {
	// 		    setPrint(h, 1);
	// 		}
	// 		thisFrame.remove(editor);
	// 		pane.setVisible(true);
	// 	    }
	// 	}

	// 	private class cancelButtonListener implements ActionListener {
	// 	    public void actionPerformed(ActionEvent e) {
	// 		thisFrame.remove(editor);
	// 		pane.setVisible(true);
	// 	    }
	// 	}
	//     }

	//     private class deleteButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    if (d.size() == 0) {
	// 		JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
	// 		return;
	// 	    }
	// 	    if (d.size() == 1) {
	// 		d.remove(0);
	// 		currentCard.removeAll();
	// 		cardText.setText("Deck is Empty!");
	// 		currentCard.add(cardText);
	// 		thisFrame.getContentPane().validate();
	// 		thisFrame.getContentPane().repaint();

	// 		current = 0;
	// 	    }
	// 	    if (d.size() > 1) {
	// 		if (current == 0) {
	// 		    Card h = (Card) d.get(current+1);

	// 		    if (h.isPic()) {
	// 		    	setPic(h);
	// 		    } else {
	// 		    	setPrint(h,1);
	// 		    }
	// 		    d.remove(current);
	// 		} else {
	// 		    d.remove(current);
	// 		    current--;
	// 		    Card h = (Card) d.get(current);

	// 		    if(h.isPic()){
	// 		    	setPic(h);
	// 		    }
	// 		    else{
	// 		    	setPrint(h,1);
	// 		    }
	// 		}
	// 	    }

	// 	    if (d.size() == 0) {
	// 		cNum.setText("0");
	// 	    } else {
	// 		cNum.setText(Integer.toString(current + 1));
	// 	    }

	// 	    deckSize.setText(Integer.toString(d.size()));
	// 	}
	//     }

	//     private class menuButtonListener implements ActionListener {

	// 	Menu menu;

	// 	public void actionPerformed(ActionEvent e) {
	// 	    pane.setVisible(false);
	// 	    menu = new Menu();
	// 	    thisFrame.add(menu);

	// 	    JButton start = new JButton("Normal Mode");
	// 	    JButton quiz = new JButton("Quiz Mode");
	// 	    start.setBounds(260,400,100,30);
	// 	    quiz.setBounds(260,400,150,30);
	// 	    menu.getBotPanel().add(start);
	// 	    menu.getBotPanel().add(Box.createRigidArea(new Dimension(40,0)));
	// 	    menu.getBotPanel().add(quiz);
	// 	    menu.getBotPanel().add(Box.createRigidArea(new Dimension(40,0)));
	// 	    startButtonListener startListener = new startButtonListener();
	// 	    start.addActionListener(startListener);
	// 	    quizButtonListener quizListener = new quizButtonListener();
	// 	    quiz.addActionListener(quizListener);
	// 	}

	// 	private class startButtonListener implements ActionListener {
	// 	    public void actionPerformed(ActionEvent e) {
	// 		pane.remove(westQuiz);
	// 		pane.remove(southQuiz);
	// 		pane.add(west,BorderLayout.WEST);
	// 		pane.add(south, BorderLayout.SOUTH);
	// 		thisFrame.remove(menu);
	//     		pane.setVisible(true);
	// 	    }
	// 	}

	// 	private class quizButtonListener implements ActionListener {
	// 	    public void actionPerformed(ActionEvent e) {
	// 		pane.remove(west);
	// 		pane.remove(south);
	// 		pane.add(westQuiz,BorderLayout.WEST);
	// 		pane.add(southQuiz, BorderLayout.SOUTH);
	// 		thisFrame.remove(menu);
	// 		pane.setVisible(true);
	// 	    }
	// 	}
	//     }


	//     //Creates the GUI that allows the user to selct decks or make a new one
	//     private class selectDeckButtonListener implements ActionListener {

	// 	DeckEditor editor;
	// 	JButton selectDeck = new JButton("Select");
	// 	JButton cancel = new JButton("Cancel");

	// 	public void actionPerformed(ActionEvent e) {
	// 	    pane.setVisible(false);
	// 	    editor = new DeckEditor(decks);

	// 	    thisFrame.add(editor);

	// 	    editor.getDataPanel().add(selectDeck);
	// 	    editor.getDataPanel().add(Box.createRigidArea(new Dimension(20, 0)));
	// 	    SelectButtonListener selectListener = new SelectButtonListener();
	// 	    selectDeck.addActionListener(selectListener);

	// 	    editor.getDataPanel().add(cancel);
	// 	    cancelButtonListener cancelListener = new cancelButtonListener();
	// 	    cancel.addActionListener(cancelListener);
	// 	}

	// 	private class SelectButtonListener implements ActionListener {
	// 	    public void actionPerformed(ActionEvent e) {
	// 		JList deckList = editor.getDeckList();
	// 		int selection = deckList.getSelectedIndex();

	// 		if (selection >= 0) {
	// 		    setDeck(decks.get(selection));

	// 		    if (d.size() == 0) {
	// 			cardText.setText("Deck is Empty!");
	// 			saveNewDeck(decks);
	// 		    } else {
	// 			setPrint(d.get(0),1);
	// 		    }
	// 		}

	// 		thisFrame.remove(editor);

	// 		deckSize.setText(Integer.toString(d.size()));
	// 		deckName.setText(d.getName());
	// 		setCardNum();

	// 		pane.setVisible(true);
	// 	    }
	// 	}

	// 	private class cancelButtonListener implements ActionListener {
	// 	    public void actionPerformed(ActionEvent e) {
	// 		thisFrame.remove(editor);
	// 		pane.setVisible(true);
	// 	    }
	// 	}

	//     }

	//     private class nextButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    correctQuiz.setVisible(false);
	// 	    if (d.size() == 0) {
	// 		return;
	// 	    }
	// 	    current++;
	// 	    if (current == d.size()) {
	// 		current = 0;
	//             }

	// 	    Card h = (Card)d.get(current);
	// 	    if(h.isPic()){
	// 	    	setPic(h);
	// 	    } else {
	// 	    	setPrint(h, 1);
	// 	    }
	// 	    cNum.setText(Integer.toString(current + 1));
	// 	}
	//     }


	//     private class previousButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    if (d.size() == 0) {
	// 		return;
	// 	    }
	// 	    current--;

	// 	    if (current == -1) {
	// 		current = d.size() - 1;
	// 	    }

	// 	    Card h = (Card)d.get(current);

	// 	    if(h.isPic()){
	// 	    	setPic(h);
	// 	    } else {
	// 	    	setPrint(h, 1);
	// 	    }
	// 	    cNum.setText(Integer.toString(current + 1));
	// 	}
	//     }

	//     private class guessButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    if (d.size() == 0) {
	// 		scoreQuiz = 0;
	//     		scoreNumQuiz.setText(Integer.toString(scoreQuiz));
	// 		return;
	// 	    }
	// 	    Card h = (Card)d.get(current);
	// 	    if (h.getSide2().equals(guessText.getText())) {
	// 		scoreQuiz = scoreQuiz + 1;
	// 		current++;
	// 		if(current == d.size()) {
	//   		    current = 0;
	// 		}
	// 		h = (Card) d.get(current);
	// 		if(h.isPic()){
	// 		    setPic(h);
	// 		} else {
	// 		    setPrint(h, 1);
	// 		}
	// 		cNum.setText(Integer.toString(current + 1));

	// 		if (scoreQuiz > d.size()) {
	// 		    scoreQuiz = d.size();
	// 		}
	// 		scoreNumQuiz.setText(Integer.toString(scoreQuiz));
	// 	    } else {
	// 		correctQuiz.setVisible(true);
	// 		setPrint(h, 2);
	// 	    }
	// 	}
	//     }

	//     private class overcorrectButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    if (d.size() == 0) {
	// 		return;
	// 	    }
	// 	    scoreQuiz = scoreQuiz + 1;
	// 	    current++;
	// 	    if (current == d.size()) {
	// 		current = 0;
	//             }

	// 	    Card h = (Card)d.get(current);
	// 	    if (h.isPic()) {
	// 		setPic(h);
	// 	    } else {
	// 		setPrint(h, 1);
	// 	    }
	// 	    cNum.setText(Integer.toString(current + 1));

	// 	    if (scoreQuiz > d.size()) {
	// 		scoreQuiz = d.size();
	// 	    }
	// 	    scoreNumQuiz.setText(Integer.toString(scoreQuiz));
	// 	    correctQuiz.setVisible(false);
	// 	}
	//     }

	//     private class frontButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    if (d.size() == 0) {
	// 		return;
	// 	    }
	// 	    Card h = (Card)d.get(current);
	// 	    if (h.isPic()) {
	// 		setPic(h);
	// 	    } else {
	// 		setPrint(h, 1);
	// 	    }

	// 	}
	//     }

	//     private class backButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    if (d.size() == 0) {
	// 		return;
	// 	    }
	// 	    Card h = (Card)d.get(current);
	// 	    setPrint(h, 2);
	// 	}
	//     }

	//     private class correctButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    score++;
	// 	    if (d.size() == 0) {
	// 		score = 0;
	// 		scoreNum.setText(Integer.toString(score));
	// 		return;
	// 	    }
	// 	    current++;
	// 	    if (current == d.size()) {
	// 		current = 0;
	//             }

	// 	    Card h = (Card)d.get(current);
	// 	    if (h.isPic()) {
	// 	    	setPic(h);
	// 	    } else {
	// 	    	setPrint(h,1);
	// 	    }
	// 	    cNum.setText(Integer.toString(current + 1));

	// 	    if (score > d.size()) {
	// 		score = d.size();
	// 	    }
	// 	    scoreNum.setText(Integer.toString(score));
	// 	}
	//     }

	//     private class incorrectButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    score--;
	// 	    if (d.size() == 0) {
	// 		score = 0;
	// 		scoreNum.setText(Integer.toString(score));
	// 		return;
	// 	    }
	// 	    current++;
	// 	    if (current == d.size()) {
	// 		current = 0;
	//             }

	// 	    Card h = (Card)d.get(current);
	// 	    if (h.isPic()) {
	// 	    	setPic(h);
	// 	    } else {
	// 	    	setPrint(h, 1);
	// 	    }
	// 	    cNum.setText(Integer.toString(current + 1));

	// 	    if (score < 0) {
	// 		score = 0;
	// 	    }
	// 	    scoreNum.setText(Integer.toString(score));
	// 	}
	//     }

	//     private class restartButtonListener implements ActionListener {
	// 	public void actionPerformed(ActionEvent e) {
	// 	    score = 0;
	// 	    scoreNum.setText(Integer.toString(score));
	// 	    scoreQuiz = 0;
	// 	    scoreNumQuiz.setText(Integer.toString(scoreQuiz));

	// 	    if (d.size() == 0) {
	// 		return;
	// 	    }

	// 	    Card h = (Card) d.get(0);
	// 	    if(h.isPic()) {
	// 	    	setPic(h);
	// 	    } else {
	// 	    	setPrint(h, 1);
	// 	    }

	// 	    current = 0;
	// 	    cNum.setText(Integer.toString(current + 1));
	// 	}
	//     }

	//     //Loads the Saved DeckList into the game
	//     private static DeckList LoadDecks(DeckList decks) {
	// 	try {
	// 	    FileInputStream fileIn = new FileInputStream("Deck.ser");
	// 	    ObjectInputStream in = new ObjectInputStream(fileIn);
	// 	    decks = (DeckList)in.readObject();
	// 	    in.close();
	// 	    fileIn.close();
	// 	} catch(ClassNotFoundException e) {
	// 	    e.printStackTrace();
	// 	} catch(FileNotFoundException e) {
	// 	    e.printStackTrace();
	// 	} catch(IOException e) {
	// 	    e.printStackTrace();
	// 	}
	// 	return decks;
	//     }

	//     //Saves new decks
	//     private void saveNewDeck(DeckList decks) {
	// 	try {
	// 	    FileOutputStream fileOut = new FileOutputStream("Deck.ser");
	// 	    ObjectOutputStream out = new ObjectOutputStream(fileOut);
	// 	    out.writeObject(decks);
	// 	    out.close();
	// 	    fileOut.close();
	// 	} catch(FileNotFoundException e) {
	// 	    e.printStackTrace();
	// 	} catch(IOException e) {
	// 	    e.printStackTrace();
	// 	}
	//     }

}
