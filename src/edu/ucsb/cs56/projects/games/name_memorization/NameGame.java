package edu.ucsb.cs56.projects.games.name_memorization;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.JComponent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
  /**
   * Preliminary engine for running a name memorization game
   *
   *@author Anthony Hoang, Colin Biafore
   *@author Domenic DiPeppe
   *@version  for CS56, W16
   */

public class NameGame extends Application{

    //Main JPanel
    //private StackPane nameGame;

    //Top Control Panel
    private JPanel north;
    private Button add;
    private JButton edit;
    private JButton delete;
    private JButton next;
    private JButton previous;
    private JButton selectDeck;
    private JButton menu;

    //Bottom Control Panel
    private JPanel south;
    private JPanel southQuiz;
    private JButton toFront;
    private JButton toBack;
    private JButton guess;
    private JTextField guessText;

    //East Control Panel
    private JLabel deckName;
    private JButton restart;

    //West Control Panel
    JPanel west = new JPanel();
    JPanel westQuiz = new JPanel();
    private JLabel scoreLabel;
    private JLabel scoreNum;
    private JLabel scoreLabelQuiz;
    private JLabel scoreNumQuiz;
    private int score;
    private int scoreQuiz;

    private JLabel deckSize;
    private JLabel sizeLabel;

    private JButton correct;
    private JButton incorrect;
    private JButton correctQuiz;

    private Image pic;

    //DeckList for Decks
    private DeckList decks;

    //Current Card Viewer
    private JPanel currentCard;
    private JTextArea cardText;
    private int current;
    private Deck d;

    //Deck viewer
    private JPanel DeckEditor;

    //private Stage thisFrame = this;

    private JLabel picture;

    //Label for Card Number:
    private JLabel cardNum;
    //UI Card Index
    private JLabel cNum;

    private static JDialog myStart;

    /**
     * No arg constructor for the name game. Initializes everyting in a JFrame
     * (Buttons, pics, etc)
     */
    public NameGame(DeckList decks){
    }
    @Override 
    	public void start(Stage primaryStage) {
			//Set Frame Layout
			StackPane nameGame = new StackPane();
			//this.add(nameGame);
			nameGame.setLayout(new BorderLayout());
			score=0;
			//Initialize North Control Panel
			north = new Scene(thisFrame);
			//north.setVisible(true);
			add = new Button("Add");
		
			edit = new JButton("Edit");
			delete = new JButton("Delete");
			previous = new JButton("Previous");
			next = new JButton("Next");
			selectDeck = new JButton("Select Deck");
			menu = new JButton("Main Menu");
			north.add(add);
			north.add(Box.createRigidArea(new Dimension(20, 20)));
			north.add(edit);
			north.add(Box.createRigidArea(new Dimension(20, 20)));
			north.add(delete);
			north.add(Box.createRigidArea(new Dimension(20, 20)));
			north.add(previous);
			north.add(Box.createRigidArea(new Dimension(20, 20)));
			north.add(next);
			north.add(Box.createRigidArea(new Dimension(20, 20)));
			north.add(selectDeck);
			north.add(Box.createRigidArea(new Dimension(20,20)));
			north.add(menu);
			north.setBackground(Color.lightGray);
			nameGame.add(north,BorderLayout.NORTH);
		
			//Initialize South Control Panel
			south = new JPanel();
			south.setVisible(true);
			toFront = new JButton("Show Front");
			toBack = new JButton("Show Back");
			south.add(toFront);
			south.add(Box.createRigidArea(new Dimension(50, 50)));
			south.add(toBack);
			south.setBackground(Color.lightGray);
			nameGame.add(south, BorderLayout.SOUTH);
		
		  // South Quiz Mode
		  southQuiz = new JPanel();
		  southQuiz.setVisible(true);
		  guess = new JButton("Guess!");
		  guessText = new JTextField("Enter Guess Here", 30);
		  southQuiz.add(guessText);
		  southQuiz.add(Box.createRigidArea(new Dimension(10, 50)));
		  southQuiz.add(guess);
		  southQuiz.setBackground(Color.lightGray);
		
		
			//Initialize Card Viewer
			currentCard = new JPanel();
			currentCard.setVisible(true);
			cardText = new JTextArea();
			Font cardFont = new Font("Verdana",Font.BOLD,24);
			cardText.setFont(cardFont);
			cardText.setEditable(false);
			currentCard.add(cardText);
			currentCard.setBackground(Color.WHITE);
			nameGame.add(currentCard, BorderLayout.CENTER);
		
			//decks is set in Main
			this.decks = decks;
			this.d = decks.get(0);
			if(d.size() == 0) cardText.setText("Deck is Empty!");
		
			//West Panel Components
		
			JPanel westCenter = new JPanel();
			westCenter.setBackground(Color.BLUE);
			west.setLayout(new BorderLayout());
			west.setBackground(Color.BLUE);
		
			scoreLabel= new JLabel("Score:");
			scoreLabel.setForeground(Color.WHITE);
			scoreLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
			scoreNum = new JLabel(Integer.toString(score));
			scoreNum.setForeground(Color.WHITE);
			scoreNum.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
			JPanel westSouth = new JPanel();
			westSouth.setBackground(Color.BLUE);
			westSouth.setLayout(new BoxLayout(westSouth,BoxLayout.Y_AXIS));
			correct = new JButton("      Correct!      ");
			incorrect = new JButton("     Incorrect     ");
			westSouth.add(correct);
			westSouth.add(Box.createRigidArea(new Dimension(0, 50)));
			westSouth.add(incorrect);
			westSouth.add(Box.createRigidArea(new Dimension(0,200)));
		
			westCenter.add(scoreLabel);
			westCenter.add(scoreNum);
		
			west.add(westCenter,BorderLayout.CENTER);
			west.add(westSouth,BorderLayout.SOUTH);
		
			deckName = new JLabel(d.getName());
			deckName.setForeground(Color.WHITE);
			deckName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			west.add(deckName,BorderLayout.NORTH);
		
			nameGame.add(west,BorderLayout.WEST);
		
		  //West panel Quiz
		
		  JPanel westCenterQuiz = new JPanel();
			westCenterQuiz.setBackground(Color.BLUE);
			westQuiz.setLayout(new BorderLayout());
			westQuiz.setBackground(Color.BLUE);
		
			scoreLabelQuiz= new JLabel("Score:");
			scoreLabelQuiz.setForeground(Color.WHITE);
			scoreLabelQuiz.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
			scoreNumQuiz = new JLabel(Integer.toString(scoreQuiz));
			scoreNumQuiz.setForeground(Color.WHITE);
			scoreNumQuiz.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
			JPanel westSouthQuiz = new JPanel();
			westSouthQuiz.setBackground(Color.BLUE);
			westSouthQuiz.setLayout(new BoxLayout(westSouthQuiz,BoxLayout.Y_AXIS));
			correctQuiz = new JButton("Override Correct");
		  correctQuiz.setVisible(false);
		
		  JLabel space = new JLabel();
		  space= new JLabel("AAAAAAAAAAAA "); //set width of left panel
			space.setForeground(Color.BLUE);
			space.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		  westSouthQuiz.add(space);
		  westSouthQuiz.add(correctQuiz);
		
			westCenterQuiz.add(scoreLabelQuiz);
			westCenterQuiz.add(scoreNumQuiz);
		
			westQuiz.add(westCenterQuiz,BorderLayout.CENTER);
			westQuiz.add(westSouthQuiz,BorderLayout.SOUTH);
		
			deckName = new JLabel(d.getName());
			deckName.setForeground(Color.WHITE);
			deckName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			westQuiz.add(deckName,BorderLayout.NORTH);
		
			//East Panel
		
			JPanel east = new JPanel();
			east.setLayout(new BorderLayout());
			east.setBackground(Color.BLUE);
		
			sizeLabel = new JLabel("Deck Size :");
			sizeLabel.setForeground(Color.WHITE);
			sizeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			deckSize = new JLabel( Integer.toString(d.size()));
			deckSize.setForeground(Color.WHITE);
			deckSize.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		
			cardNum = new JLabel("Card Number:");
			cardNum.setForeground(Color.WHITE);
			cardNum.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			cNum = new JLabel(Integer.toString(current));
			cNum.setForeground(Color.WHITE);
			cNum.setFont(new Font("Lucida Grande",Font.PLAIN, 18));
		
		
		       	restart = new JButton(" Restart ");
			east.add(restart, BorderLayout.SOUTH);
		
		
			JPanel eastCenter = new JPanel();
		
			eastCenter.setBackground(Color.BLUE);
			eastCenter.add(cardNum);
			eastCenter.add(cNum);
		
		
			JPanel top = new JPanel();
			top.setBackground(Color.BLUE);
			top.add(sizeLabel, BorderLayout.NORTH);
			top.add(deckSize, BorderLayout.NORTH);
		
			east.add(eastCenter,BorderLayout.CENTER);
			east.add(top,BorderLayout.NORTH);
			nameGame.add(east,BorderLayout.EAST);
		
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
		
			//Initialize picture JLabel that is used in next listener
			picture = new JLabel();
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
		
		  myStart = new JDialog(thisFrame , "Welcome", true);
		  myStart.setLayout( new FlowLayout() );
		  JButton b = new JButton ("OK");
		  b.addActionListener ( new ActionListener()
		    {
		         public void actionPerformed( ActionEvent e )
		         {
		             NameGame.myStart.setVisible(false);
		         }
		     });
		     myStart.add( new JLabel ("Welcome to the Name Memorization Game."));
		     myStart.add( new JLabel ("For instructions and mode changing click Main Menu in the game."));
		     myStart.add(b);
		     myStart.add(b);
		     myStart.setSize(500,130);
		     myStart.setVisible(true);

    }


    /**
     * This method will be called with next/previous button if card has a pic
     *
     * @param c A card
     */
    public void setPic(Card c){
    	cardText.setVisible(false);
    	currentCard.remove(picture);
		picture=c.getPic();
		picture.setVisible(true);
		currentCard.add(picture, BorderLayout.CENTER);
		thisFrame.getContentPane().validate();
		thisFrame.getContentPane().repaint();
    }

    /**
     * This method will be called with next/previous if card is text
     *
     * @param c A card
     * @param side the side of the card
     */
    public void setPrint(Card c, int side){
    	picture.setVisible(false);
		cardText.setVisible(true);
		currentCard.remove(picture);
		if(side==1){
			cardText.setText(c.getSide1());
		}else if(side==2){
			cardText.setText(c.getSide2());
		}

    }

    /**
     * Sets the current deck
     *
     * @param d A deck
     */
    public void setDeck(Deck d) {
	this.d = d;
    }

    /**
     * Returns a deck
     *
     * @return d A deck
     */
    public Deck getDeck() {
	return this.d;
    }


    /**
     * Sets the current DeckList
     * @param decks A DeckList
     */
    public void setDeckList(DeckList decks){
	this.decks = decks;
    }

    /**
     * Returns the current deckList
     * @return decks a DeckList
     */
    public DeckList getDeckList(){
	return decks;
    }

    /**
     * Updates the size of the deck to be the value specified
     *
     * @param decksize The new size of the deck
     */
    public void updateSize(int deckSize) {

	this.deckSize.setText(Integer.toString(deckSize));
    }

    /**
     * Sets the index of the current card
     */
    public void setCardNum() {
	if (d.size() < 1) {
	    this.cNum.setText("0");
	} else
	    this.cNum.setText("1");
    }


    /**
     * addButtonListener, Brings up a window to add a card
     */
    private class addButtonListener implements ActionListener {

	CardEditor editor;


        public void actionPerformed(ActionEvent event) {

	    nameGame.setVisible(false);
	    //Creates a new card editor

	    Card c = new Card("Enter Text", "Enter Text", false);
	    editor = new CardEditor(c);
	    thisFrame.add(editor);

	    JButton confirm = new JButton("Confirm");
	    confirm.setBounds(260,400,100,30);
	    editor.getBotPanel().add(confirm);
	    editor.getBotPanel().add(Box.createRigidArea(new Dimension(40,0)));
	    confirmButtonListener confirmListener = new confirmButtonListener();
	    confirm.addActionListener(confirmListener);

	    JButton cancel = new JButton("Cancel");
	    editor.getBotPanel().add(cancel);
	    cancelButtonListener cancelListener = new cancelButtonListener();
	    cancel.addActionListener(cancelListener);
	}

		// Only adds a card once confirm has been pressed
	private class confirmButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		String side1 = editor.getFrontText();
		String side2 = editor.getBackText();

		d.addCard(side1,side2,editor.isPic());
		current = d.size() - 1;
		Card h = (Card) d.get(current);
		if(h.isPic()){
		    setPic(h);
		}
		else{
		    setPrint(h,1);
		}
		next.setEnabled(true);
		previous.setEnabled(true);
		deckSize.setText(Integer.toString(d.size()));
		cNum.setText(Integer.toString(current+1));
		thisFrame.remove(editor);
		nameGame.setVisible(true);

		    }
		}
	private class cancelButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		thisFrame.remove(editor);
		nameGame.setVisible(true);
	    }
	}

    }

    private class editButtonListener implements ActionListener {

	CardEditor editor;

	public void actionPerformed(ActionEvent e) {

	    if(d.size() == 0) {
		JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
		return;
	    }

	    nameGame.setVisible(false);
	    editor = new CardEditor(d.get(current));
	    thisFrame.add(editor);

	    JButton confirm = new JButton("Confirm");
	    confirm.setBounds(260,400,100,30);
	    editor.getBotPanel().add(confirm);
	    editor.getBotPanel().add(Box.createRigidArea(new Dimension(40,0)));
	    confirmButtonListener confirmListener = new confirmButtonListener();
	    confirm.addActionListener(confirmListener);

	    JButton cancel = new JButton("Cancel");
	    editor.getBotPanel().add(cancel);
	    cancelButtonListener cancelListener = new cancelButtonListener();
	    cancel.addActionListener(cancelListener);
	}

	private class confirmButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		String side1 = editor.getFrontText();
		String side2 = editor.getBackText();
		Card h = (Card) d.get(current);

		d.editCard(h, side1, side2);
		if(h.isPic()){
		   	setPic(h);
	    }
	    else{
	       	setPrint(h,1);
	    }

		thisFrame.remove(editor);
		nameGame.setVisible(true);

	    }

	}

	private class cancelButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		thisFrame.remove(editor);
		nameGame.setVisible(true);
	    }
	}
    }

    private class deleteButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if(d.size() == 0) {
		JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
		return;
	    }
	    if(d.size() == 1) {
		d.remove(0);
		currentCard.removeAll();
		cardText.setText("Deck is Empty!");
		currentCard.add(cardText);
		thisFrame.getContentPane().validate();
		thisFrame.getContentPane().repaint();

		current = 0;

	    }
	    if(d.size() > 1) {
		if(current == 0) {
		    Card h = (Card) d.get(current+1);

		    if(h.isPic()){
		    	setPic(h);
		    }
		    else{
		    	setPrint(h,1);
		    }
		    d.remove(current);


		}
		else {
		    d.remove(current);
		    current--;
		    Card h = (Card) d.get(current);

		    if(h.isPic()){
		    	setPic(h);
		    }
		    else{
		    	setPrint(h,1);
		    }
		}
	    }

	    if(d.size() == 0)
		cNum.setText("0");
	    else
		cNum.setText(Integer.toString(current+1));

	    deckSize.setText(Integer.toString(d.size()));

	}
    }

    	private class menuButtonListener implements ActionListener {

	Menu menu;

	public void actionPerformed(ActionEvent e) {

	    nameGame.setVisible(false);
	    menu = new Menu();
	    thisFrame.add(menu);

	    JButton start = new JButton("Normal Mode");
      JButton quiz = new JButton("Quiz Mode");
	    start.setBounds(260,400,100,30);
      quiz.setBounds(260,400,150,30);
	    menu.getBotPanel().add(start);
	    menu.getBotPanel().add(Box.createRigidArea(new Dimension(40,0)));
      menu.getBotPanel().add(quiz);
	    menu.getBotPanel().add(Box.createRigidArea(new Dimension(40,0)));
	    startButtonListener startListener = new startButtonListener();
	    start.addActionListener(startListener);
      quizButtonListener quizListener = new quizButtonListener();
	    quiz.addActionListener(quizListener);

	}

	private class startButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
        nameGame.remove(westQuiz);
        nameGame.remove(southQuiz);
        nameGame.add(west,BorderLayout.WEST);
        nameGame.add(south, BorderLayout.SOUTH);
        thisFrame.remove(menu);
    		nameGame.setVisible(true);

	    }

	}

  private class quizButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
        nameGame.remove(west);
        nameGame.remove(south);
        nameGame.add(westQuiz,BorderLayout.WEST);
        nameGame.add(southQuiz, BorderLayout.SOUTH);
        thisFrame.remove(menu);
    		nameGame.setVisible(true);

	    }

	}
	}


    //Creates the GUI that allows the user to selct decks or make a new one
    private class selectDeckButtonListener implements ActionListener {

	DeckEditor editor;
	JButton selectDeck = new JButton("Select");
	JButton cancel = new JButton("Cancel");

	public void actionPerformed(ActionEvent e) {

	    nameGame.setVisible(false);
	    editor = new DeckEditor(decks);

	    thisFrame.add(editor);

	    editor.getDataPanel().add(selectDeck);
	    editor.getDataPanel().add(Box.createRigidArea(new Dimension(20,0)));
	    SelectButtonListener selectListener = new SelectButtonListener();
	    selectDeck.addActionListener(selectListener);

	    editor.getDataPanel().add(cancel);
	    cancelButtonListener cancelListener = new cancelButtonListener();
	    cancel.addActionListener(cancelListener);
	}

	private class SelectButtonListener implements ActionListener {

	    public void actionPerformed(ActionEvent e){
		JList deckList = editor.getDeckList();
		int selection = deckList.getSelectedIndex();

		if(selection >= 0){
		    setDeck(decks.get(selection));

		    if(d.size() == 0){
			cardText.setText("Deck is Empty!");
			saveNewDeck(decks);
		    }
		    else
			setPrint(d.get(0),1);
		}

		thisFrame.remove(editor);

		deckSize.setText(Integer.toString(d.size()));
		deckName.setText(d.getName());
		setCardNum();

		nameGame.setVisible(true);
	    }
	}

	private class cancelButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		thisFrame.remove(editor);
		nameGame.setVisible(true);
	    }
	}

    }

    private class nextButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
      correctQuiz.setVisible(false);
      if(d.size() == 0) {
		return;
	    }
	    current++;
	    if(current == d.size()) {
		current = 0;
            }

	    Card h = (Card) d.get(current);
	    if(h.isPic()){
	    	setPic(h);
	    }
	    else{
	    	setPrint(h,1);
	    }
	    cNum.setText(Integer.toString(current+1));


	}
    }


    private class previousButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

	    if(d.size() == 0) {
		return;
	    }
	    current--;

	    if(current == -1) {
		current = d.size() - 1;
	    }

	    Card h = (Card) d.get(current);

	     if(h.isPic()){
	    	setPic(h);
	    }
	    else{
	    	setPrint(h,1);
	    }
	    cNum.setText(Integer.toString(current+1));
	}

    }

    private class guessButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
      if(d.size() == 0) {
        scoreQuiz = 0;
    		scoreNumQuiz.setText(Integer.toString(scoreQuiz));
        return;
      }
      Card h = (Card) d.get(current);
      if (h.getSide2().equals(guessText.getText())) {
        scoreQuiz = scoreQuiz + 1;
  	    current++;
  	    if(current == d.size()) {
  		    current = 0;
        }
        h = (Card) d.get(current);
  	    if(h.isPic()){
  	    	setPic(h);
  	    }
  	    else{
  	    	setPrint(h,1);
  	    }
  	    cNum.setText(Integer.toString(current+1));


  	    if(scoreQuiz > d.size()) {
  		scoreQuiz = d.size();
  	    }
  	    scoreNumQuiz.setText(Integer.toString(scoreQuiz));
      }
      else {
        correctQuiz.setVisible(true);
        setPrint(h,2);
      }

		}
    }

    private class overcorrectButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
      if(d.size() == 0) {
    return;
      }
      scoreQuiz = scoreQuiz + 1;
      current++;
      if(current == d.size()) {
    current = 0;
            }

      Card h = (Card) d.get(current);
      if(h.isPic()){
        setPic(h);
      }
      else{
        setPrint(h,1);
      }
      cNum.setText(Integer.toString(current+1));


      if(scoreQuiz > d.size()) {
    scoreQuiz = d.size();
      }
      scoreNumQuiz.setText(Integer.toString(scoreQuiz));
      correctQuiz.setVisible(false);
		}
    }

    private class frontButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    if(d.size() == 0) {
			return;
		    }
		    Card h = (Card) d.get(current);
		    if(h.isPic()){
		    	setPic(h);
		    }
		    else{
		    	setPrint(h,1);
		    }

		}
    }

    private class backButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    if(d.size() == 0) {
			return;
		    }
		    Card h = (Card) d.get(current);
		    setPrint(h,2);


		}
    }

    private class correctButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    score++;
	    if(d.size() == 0) {
		score = 0;
		scoreNum.setText(Integer.toString(score));
		return;
	    }
	    current++;
	    if(current == d.size()) {
		current = 0;
            }

	    Card h = (Card) d.get(current);
	    if(h.isPic()){
	    	setPic(h);
	    }
	    else{
	    	setPrint(h,1);
	    }
	    cNum.setText(Integer.toString(current+1));


	    if(score > d.size()) {
		score = d.size();
	    }
	    scoreNum.setText(Integer.toString(score));

	}


    }

    private class incorrectButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    score--;
	    if(d.size() == 0) {
		score = 0;
		scoreNum.setText(Integer.toString(score));
		return;
	    }
	    current++;
	    if(current == d.size()) {
		current = 0;
            }

	    Card h = (Card) d.get(current);
	    if(h.isPic()){
	    	setPic(h);
	    }
	    else{
	    	setPrint(h,1);
	    }
	    cNum.setText(Integer.toString(current+1));

	    if(score < 0 ) {
		score = 0;
	    }
	    scoreNum.setText(Integer.toString(score));


	}


    }

    private class restartButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    score = 0;
	    scoreNum.setText(Integer.toString(score));
      scoreQuiz = 0;
	    scoreNumQuiz.setText(Integer.toString(scoreQuiz));

	    if(d.size() == 0) {
		return;
	    }

	    Card h = (Card) d.get(0);
	    if(h.isPic()){
	    	setPic(h);
	    }
	    else{
	    	setPrint(h,1);
	    }

	    current = 0;
	    cNum.setText(Integer.toString(current+1));

	}
    }

    //Loads the Saved DeckList into the game
    private static DeckList LoadDecks(DeckList decks){
	try{
	    FileInputStream fileIn = new FileInputStream("Deck.ser");
	    ObjectInputStream in = new ObjectInputStream(fileIn);
	    decks = (DeckList)in.readObject();
	    in.close();
	    fileIn.close();

	}
	catch(ClassNotFoundException e){
	    e.printStackTrace();
	}
	catch(FileNotFoundException e){
	    e.printStackTrace();
	}
	catch(IOException e){
	    e.printStackTrace();
	}
	return decks;
    }

    //Saves new decks
    private void saveNewDeck(DeckList decks)
    {

	try{
	    FileOutputStream fileOut = new FileOutputStream("Deck.ser");
	    ObjectOutputStream out = new ObjectOutputStream(fileOut);
	    out.writeObject(decks);
	    out.close();
	    fileOut.close();
	}catch(FileNotFoundException e){
	    e.printStackTrace();
	}catch(IOException e){
	    e.printStackTrace();
	}
    }

}
