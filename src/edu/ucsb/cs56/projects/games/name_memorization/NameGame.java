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
  /**
   * Preliminary engine for running a name memorization game
   *
   *@author Anthony Hoang, Colin Biafore
   *@version  for cs56, Spring 2014
   */
public class NameGame extends JFrame{

    //Top Control Panel
    private JPanel north;
    private JButton add;
    private JButton edit;
    private JButton delete;
    private JButton next;
    private JButton previous;

    //Bottom Control Panel
    private JPanel south;
    private JButton toFront;
    private JButton toBack;
    
    private DirectoryLister dir;

    private Image pic;


    //Current Card Viewer
    private JPanel currentCard;
    private JTextArea cardText;
    private int current;
    private Deck d;

    //Anthony's Label's
    private JFrame thisframe = this;
    private JLabel deckName;
    private JLabel deckSize;
    private JLabel sizeLabel;
    private JPanel east;
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
	
	//Set Frame Layout
	this.getContentPane().setLayout(new BorderLayout());

	//Initialize North Control Panel
	north = new JPanel();
	north.setVisible(true);
	add = new JButton("Add");
	edit = new JButton("Edit");
	delete = new JButton("Delete");
	previous = new JButton("Previous");
	next = new JButton("Next");
	north.add(add);
	north.add(edit);
	north.add(delete);
	north.add(previous);
	north.add(next);
	north.setBackground(Color.ORANGE);
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
	

	//	dir = new DirectoryLister("src/people");
     
	
	
	
	
	deckName = new JLabel(d.getName());
	deckName.setForeground(Color.WHITE);
	deckName.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	this.add(deckName,BorderLayout.WEST);
	
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


	east = new JPanel();
	east.setLayout(new BorderLayout());
	east.setBackground(Color.BLUE);
	JPanel top = new JPanel();
	top.setBackground(Color.BLUE);
	top.add(sizeLabel, BorderLayout.NORTH);
	top.add(deckSize, BorderLayout.NORTH);
	
	//east.add(cardNum, BorderLayout.CENTER);
	east.add(eastCenter,BorderLayout.EAST);
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
	previous.setEnabled(false);
	
	//Initialize picture JLabel that is used in next listener
	picture = new JLabel();
	//Initialize Next Button Listener
	nextButtonListener nextListener = new nextButtonListener();
	next.addActionListener(nextListener);
	next.setEnabled(false);

	//Initialize Front Button Listener
	frontButtonListener frontListener = new frontButtonListener();
	toFront.addActionListener(frontListener);

	//Initialize Back Button Listener
	backButtonListener backListener = new backButtonListener();
	toBack.addActionListener(backListener);
	
	this.pack();
    }


    public void paintComponent(Graphics g){
	/*
        //get the picture to paint
        pic = Toolkit.getDefaultToolkit().getImage(dir.getFullFilenames().get(current));
        //call super's paint method
        super.paint(g);
        //draw designated picture in background
        g.drawImage(pic, 115, 90,300, 300, this);
	*/
	//	currentCard = d.get(current);
	//thisframe.add(currentCard, BorderLayout.CENTER);
	super.paint(g);
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
		if(editor.isPic()){
		    cardText.setVisible(false);
		    picture=editor.getPic();
		    picture.setVisible(true);		    
		    currentCard.add(picture, BorderLayout.CENTER);
		}//if(editor.isPic())
		else{
		    picture.setVisible(false);
		    cardText.setVisible(true);
		    currentCard.remove(picture);;
		    cardText.setText(h.getSide1());
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

	public void actionPerformed(ActionEvent e) {
	    
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
		cardText.setText(h.getSide1());
		editor.dispose();
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
		cardText.setText("Deck is Empty!");
		current = 0;
		next.setEnabled(false);
		previous.setEnabled(false);
	    }
	    if(d.size() > 1) {
		if(current == 0) {
		    Card h = (Card) d.get(current+1);
		    cardText.setText(h.getSide1());
		    d.remove(current);
		    
		    
		}
		else {
		    d.remove(current);
		    current--;
		    Card h = (Card) d.get(current);
		    cardText.setText(h.getSide1());
		}
	    }

	    deckSize.setText(Integer.toString(d.size()));
	    cNum.setText(Integer.toString(current+1));


	}
    }

    private class nextButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    current++;
	    if(current == d.size()) {
		current = 0;
            }
	    if(d.size() == 0) {
		JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
		return;
	    }
	    
	    Card h = (Card) d.get(current);
	    cardText.setText(h.getSide1());
	    cNum.setText(Integer.toString(current+1));
	    

	    
	}
    }

    private class previousButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    current--;
	    
	    if(current == -1) {
		current = d.size() - 1;
	    }

	    if(d.size() == 0) {
		JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
		return;
	    }

	    Card h = (Card) d.get(current);
	    cardText.setText(h.getSide1());
	    cNum.setText(Integer.toString(current+1));
	}
    }

    private class frontButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if(d.size() == 0) {
		return;
	    }
	    Card h = (Card) d.get(current);
	    cardText.setText(h.getSide1());
	}
    }

    private class backButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if(d.size() == 0) {
		return;
	    }
	    Card h = (Card) d.get(current);
	    cardText.setText(h.getSide2());

	}
    }














    private class blueButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if(d.size() == 0){
			JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(d.size() == 1){
			currentCard=d.get(0);
			thisframe.add(currentCard,BorderLayout.CENTER);
			currentCard.setVisible(true);
			thisframe.getContentPane().validate();
			thisframe.getContentPane().repaint();
		}
		else{
		    currentCard.setVisible(false);
		    if(current < d.size()-1)  {     
			current++;
		    }	else {
			current = 0;
		    }
		    currentCard=d.get(current);
		    thisframe.add(currentCard,BorderLayout.CENTER);
		    currentCard.setVisible(true);
		    thisframe.getContentPane().validate();
		    thisframe.getContentPane().repaint();
		}
	   
	}
	    
    }
    
    /**
     * Handler ClassP, used to give previous button functionality
     */
    private class HandlerClassP implements ActionListener {
		public void actionPerformed(ActionEvent event){
		    // iterate to previous picture unless at beginning
		    if (current > 0 ){
			current--;
		    }else{
			current = dir.getNumFiles()-1;
		    }
		    repaint();		    
		}
    }
    
}
