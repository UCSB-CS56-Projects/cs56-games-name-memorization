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
import java.awt.BorderLayout;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Hashtable;

  /**
   * Preliminary engine for running a name memorization game
   *
   *@author Anthony Hoang, Colin Biafore
   *@version  for cs56, Winter 2014
   */

public class NameGame extends JFrame{

    //Top Control Panel
    private JPanel north;
    private JButton add;
    private JButton edit;
    private JButton delete;
    private JButton next;
    private JButton previous;
    private JButton newDeck;

    //Bottom Control Panel
    private JPanel south;
    private JButton toFront;
    private JButton toBack;

    //East Control Panel
	private JLabel deckName;
	private JButton restart;

	//West Control Panel
	private JLabel scoreLabel;
	private JLabel scoreNum;
	private int score;

    private JLabel deckSize;
    private JLabel sizeLabel;

    private JButton correct;
    private JButton incorrect;
    


    private Image pic;


    //HashTable for decks
    Hashtable<String,Deck> data;

    //Current Card Viewer
    private JPanel currentCard;
    private JTextArea cardText;
    private int current;
    private Deck d;
    public static void main(String[] args){
	Deck d = new Deck("");
	try {


    
    private JFrame thisframe = this;
    


    private JLabel picture;

    //Label for Card Number:
    private JLabel cardNum;
    //UI Card Index
    private JLabel cNum;

    /**
     * No arg constructor for the name game. Initializes everyting in a JFrame
     * (Buttons, pics, etc)
     */
    public NameGame(){
	

	//menu options and buttons
	play = new JButton ("Start Game");
	info = new JButton (" How to Play");
	quit = new jButton ("Quit");


	//Set Frame Layout
	this.getContentPane().setLayout(new BorderLayout());
	score=0;
	//Initialize North Control Panel
	north = new JPanel();
	north.setVisible(true);
	add = new JButton("Add");
	edit = new JButton("Edit");
	delete = new JButton("Delete");
	previous = new JButton("P
        final NameGame game = newrevious");
	next = new JButton("Next");
	newDeck = new JButton("New Deck");
	north.add(add);
	north.add(edit);
	north.add(delete);
	north.add(previous);
	north.add(next);
	north.add(newDeck);
	north.setBackground(Color
        final NameGame game = new.ORANGE);
	this.add(north,BorderLayout.NORTH);
	
	//Initialize South Control Panel
	south = new JPanel();
	south.setVisible(true);
	toFront = new JButton("Show Front");
	toBack = new JButton("Show Back");
	south.add(toFront);
	south.add(toBack);
	south.setBackground(Color.ORANGE);
	this.add(south, BorderLayout.SOUTH);

	//Initialize Card Viewer
	currentCard = new JPanel();
	currentCard.setVisible(true);
	cardText = new JTextArea();
	Font cardFont = new Font("Verdana",Font.BOLD,24);
	cardText.setFont(cardFont);
	cardText.setEditable(false);
	currentCard.add(cardText);
	currentCard.setBackground(Color.WHITE);
	this.add(currentCard, BorderLayout.CENTER);
	

	//Create a new deck
	d = new Deck("First Deck");
	
	// initialize the hashtable of decks
	data = new Hashtable<String,Deck>();


     
	
	
	
	//West Panel Components

	JPanel west = new JPanel();
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
	correct = new JButton("Correct!");
	incorrect = new JButton("Incorrect");
	westSouth.add(correct);
	westSouth.add(incorrect);
	

	
	westCenter.add(scoreLabel);
	westCenter.add(scoreNum);

	west.add(westCenter,BorderLayout.CENTER);
	west.add(westSouth,BorderLayout.SOUTH);

	deckName = new JLabel(d.getName());
	deckName.setForeground(Color.WHITE);
	deckName.setFont(new Fon
        final NameGame game = newt("Lucida Grande", Font.PLAIN, 18));
	west.add(deckName,BorderLayout.NORTH);


	this.add(west,BorderLayout.WEST);
	
	//East Panel

	JPanel east = new JPanel();
	east.setLayout(new BorderLayout());
	east.setBackground(Colo
    public static void main(String[] args){
	Deck d = new Deck("");
	try {
r.BLUE);
	
	restart = new JButton("Restart");
	east.add(restart, BorderLayout.SOUTH);

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

	

	JPanel eastCenter = new JPanel();

	eastCenter.setBackground(Color.BLUE);
	eastCenter.add(cardNum);
	eastCenter.add(cNum);
	
1

        final NameGame game = new


	JPanel top = new JPanel();
	top.setBackground(Color
    public static void main(String[] args){
	Deck d = new Deck("");
	try {
.BLUE);
	top.add(sizeLabel, BorderLayout.NORTH);
	top.add(deckSize, BorderLayout.NORTH);
	
	east.add(eastCenter,BorderLayout.CENTER);
	east.add(top,BorderLayout.NORTH);
	this.add(east,BorderLayout.EAST);

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
	request can be automatically merged by project col
	
	//Initialize picture JLabel that is used in next listener
	picture = new JLabel();
	//Initialize Next Button
        final NameGame game = new Listener
	nextButtonListener nextListener = new nextButtonListener();
	next.addActionListener(nextListener);
	

	//Initialize Front Button Listener
	frontButtonListener frontListener = new frontButtonListener();
	toFront.addActionListener(frontListener);

	//Initialize Back Button Listener
	backButtonListener backListener = new backButtonListener();
	toBack.addActionListener(backListener);

	correctButtonListener correctListener = new correctButtonListener();
	correct.addActionListener(correctListener);
1
	incorrectButtonListener incorrectListener = new incorrectButtonListener();
	incorrect.addActionListener(incorrectListener);

	restartButtonListener restartListener = new restartButtonListener();
	restart.addActionListener(restartListener);

	//Initialize Add Button Listener
	addButtonListener addListener = new addButtonListener();
	add.addActionListener(addListener);
	

	//Initialize play Button Listener
	addButtonListener playListener = new addButtonListener();
	play.addActionListener(playListener);

	//Initialize info Button Listener
	addButtonListener infoListener = new addButtonListener();
	info.addActionListener(infoListener);

	//Initialize quit Button Listener
	addButtonListener quitListener = new addButtonListener();
	quit.addActionListener(quitListener);

	
	
	this.pack();
    }  

 	//this method will be called with next/previous button if card has a pic
    public void setPic(Card c){
    	cardText.setVisible(false);
    	currentCard.remove(picture);
		picture=c.getPic();
		picture.setVisible(true);		    
		currentCard.add(picture, BorderLayout.CENTER);
		thisframe.getContentPane().validate();
		thisframe.getContentPane().repaint();
    }

    //this method will be called with next/previous if card is text
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

    public void setDeck(Deck d) {
	this.d = d;
    }

    public Deck getDeck() {
	return d;
    }

    public void updateSize(int deckSize) {
	
	this.deckSize.setText(Integer.t//Initialize Add Button Listener
	addButtonListener addListener = new addButtonListener();
	add.addActionListener(addListener);oString(deckSize));
    }

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
	    
	    //Creates a new card editor

	    editor = new CardEditor();
	    JButton confirm = new JButton("Confirm");
	    confirm.setBounds(260,400,100,30);
	    editor.getContentPane().add(confirm);
	    confirmButtonListener confirmListener = new confirmButtonListener();
	    confirm.addActionListener(confirmListener);
	    
	}
	
		// Only adds a card once confirm has been pressed
	private class confirmButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		String side1 = editor.getFrontText();
		String side2 = editor.getBackText();
		
		d.addCard(side1,side2,editor.isPic());
		editor.dispose();
		current = d.size() - 1;
		Card h = (Card) d.get(current);
		if(h.isPic()){
		    setPic(h);
		}//if(h.isPic())
		else{
		    setPrint(h,1);
		}
		next.setEnabled(true);
		previous.setEnabled(true);
		deckSize.setText(Integer.toString(d.size()));
		cNum.setText(Integer.toString(current+1));
			
			
		    }
		}
    }

    private class editButtonListener implements ActionListener {

	CardEditor editor;

	public void actionPerformed(Ac//Initialize Add Button Listener
	addButtonListener addListener = new addButtonListener();
	add.addActionListener(addListener);tionEvent e) {
	    
	    if(d.size() == 0) {
		JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
		return;
	    }

	    editor = new CardEditor();
	    JButton confirm = new JButton("Confirm");
	    confirm.setBounds(260,400,100,30);
	    editor.getContentPane().add(confirm);
	    confirmButtonListener confirmListener = new confirmButtonListener();
	    confirm.addActionListener(confirmListener);
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

		editor.dispose();
	    }

	}
    }

    private class deleteButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    score=0;
	    {
	    if(d.size() == 0) {
		JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
		return;
	    }
	    if(d.size() == 1) {
		d.remove(0);
		currentCard.removeAll();
		cardText.setText("Deck is Empty!");
		currentCard.add(cardText);
		thisframe.getContentPane().validate();
		thisframe.getContentPane().repaint();

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
    }

    private class nextButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
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
	
private class playButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		


	}
    }
		


    private static Hashtable LoadTable(Hashtable data){
		try{
			FileInputStream fileIn = new FileInputStream("Savefiles/Saved_Decks.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			data = (Hashtable)in.readObject();
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
		return data;
	}

	//Saves new decks ---------------------
	private void SaveNewDeck(String name, Deck d, Hashtable<String, Deck> data)
	{
	
		data.put(name, d);
		
		try{
			FileOutputStream fileOut = new FileOutputStream("Savefiles/Saved_Decks.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
			}
	}

    
}
