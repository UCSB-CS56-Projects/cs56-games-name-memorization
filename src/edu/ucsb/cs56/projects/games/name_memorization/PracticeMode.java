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
 * Practice mode that lets the player practice memorizing names without
 * keeping score.
 * 
 * @author Tamky Ngo, Kevin Lau
 * @version CS56, Winter 2015
 */


public class PracticeMode extends JFrame{

    //Top Control Panel
    private JPanel north;
    private JButton add;
    private JButton edit;
    private JButton delete;
    private JButton next;
    private JButton previous;
    private JButton newDeck;
    private JButton returnMenu;
    private JFrame thisPractice = this;

    //Bottom Control Panel
    private JPanel south;
    private JButton toFront;
    private JButton toBack;

    //East Control Panel
    private JLabel deckName;

    //West Control Panel
    private JLabel scoreLabel;

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
    public PracticeMode(){

	//Set Frame Layout
	this.getContentPane().setLayout(new BorderLayout());
	score=0;
	//Initialize North Control Panel
	north = new JPanel();
	north.setVisible(true);
	
	previous = new JButton("Previous");
	next = new JButton("Next");
	

	north.add(previous);
	north.add(next);

	north.setBackground(Color.ORANGE);
	this.add(north,BorderLayout.NORTH);
	
	//Initialize South Control Panel
	south = new JPanel();
	south.setVisible(true);
	returnMenu = new JButton("Return to Menu");
	toFront = new JButton("Show Front");
	toBack = new JButton("Show Back");
	south.add(toFront);
	south.add(toBack);
	south.add(returnMenu);
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

	scoreLabel= new JLabel("PRACTICE MODE");
	scoreLabel.setForeground(Color.WHITE);
	scoreLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));



	JPanel westSouth = new JPanel();
	westSouth.setBackground(Color.BLUE);
	westSouth.setLayout(new BoxLayout(westSouth,BoxLayout.Y_AXIS));


	
	westCenter.add(scoreLabel);
	

	west.add(westCenter,BorderLayout.CENTER);
	west.add(westSouth,BorderLayout.SOUTH);

	deckName = new JLabel(d.getName());
	deckName.setForeground(Color.WHITE);
	deckName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	west.add(deckName,BorderLayout.NORTH);


	this.add(west,BorderLayout.WEST);
	
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
	this.add(east,BorderLayout.EAST);

	//BUTTON LISTENERS -- Uncommented = implemented and functioning!!
	//Currently you can add as many cards as you want,
	//Go to the next or previous card in the deck,
	//And see both sides of the current card 

	
	//Initialize Previous Button Listener
	previousButtonListener previousListener = new previousButtonListener();
	previous.addActionListener(previousListener);
	
	//Initialize picture JLabel that is used in next listener
	picture = new JLabel();
	//Initialize Next Button
	nextButtonListener nextListener = new nextButtonListener();
	next.addActionListener(nextListener);
	

	//Initialize Front Button Listener
	frontButtonListener frontListener = new frontButtonListener();
	toFront.addActionListener(frontListener);

	//Initialize Back Button Listener
	backButtonListener backListener = new backButtonListener();
	toBack.addActionListener(backListener);

	returnButtonListener returnListener = new returnButtonListener();
	returnMenu.addActionListener(returnListener);

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
	
	this.deckSize.setText(Integer.toString(deckSize));
    }

    public void setCardNum() {
	if (d.size() < 1) {
	    this.cNum.setText("0");
	} else
	    this.cNum.setText("1");
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

   
    private class returnButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			thisPractice.dispose();
		}
    }
    



    

    
}
