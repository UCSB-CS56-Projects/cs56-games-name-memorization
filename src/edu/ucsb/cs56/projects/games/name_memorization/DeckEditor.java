package edu.ucsb.cs56.projects.games.name_memorization;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Dimension;

/**
 * An editor that allows the user to select, add, and delete decks
 *
 * @author Domenic DiPeppe
 * @version for CS56 W16
 */

public class DeckEditor extends JPanel implements ActionListener, ListSelectionListener {

    private JPanel topPanel;
    private JPanel botPanel;
    private JPanel dataPanel;
    private JPanel infoPanel;
    
    private JList deckList;
    private Vector deckNames;
    private JButton addDeck;
    private JButton removeDeck;
    private JButton copyDeck;
    private JButton selectDeck;
    private JScrollPane deckScroller;
    private JTextField deckText;

    private JLabel deckSize;
    private JLabel currentName;
    
    private DeckList decks;
    private Deck deck;

    /** 
     * Constructor for Objects of type Editor
     * @param decks A DeckList
     */
    public DeckEditor(DeckList decks){

	this.decks = decks;
	this.setLayout(new BorderLayout());
	
	topPanel = new JPanel();
	topPanel.setLayout(new BorderLayout());
	this.add(topPanel, BorderLayout.CENTER);
	
	deckNames = new Vector();
	for(int i=0;i<decks.size();i++)
	    deckNames.addElement(decks.get(i).getName());
	
	deckList = new JList(deckNames);
	deckList.addListSelectionListener(this);

	deckScroller = new JScrollPane();
	deckScroller.getViewport().add(deckList);
	topPanel.add(deckScroller, BorderLayout.CENTER);
	
	CreateDeckEntryPanel();	
    }

    /**
     * Creates the GUI for JButtons and JTextField
     */
    public void CreateDeckEntryPanel(){
        dataPanel = new JPanel();
	dataPanel.setBackground(Color.CYAN);
	this.add(dataPanel, BorderLayout.SOUTH);

	deckText = new JTextField("Enter Deck Name");
	topPanel.add(deckText, BorderLayout.SOUTH);

	addDeck = new JButton("Add");
	dataPanel.add(addDeck);
	addDeck.addActionListener(this);

	removeDeck = new JButton("Remove");
	dataPanel.add(removeDeck);
	removeDeck.addActionListener(this);

	copyDeck = new JButton("Copy");
	dataPanel.add(copyDeck);
	copyDeck.addActionListener(this);
	
	infoPanel = new JPanel();
	infoPanel.setBackground(Color.CYAN);

	topPanel.add(infoPanel, BorderLayout.NORTH);

	currentName = new JLabel("Current Deck: ");
	infoPanel.add(currentName);

	deckSize = new JLabel("Size: ");
	infoPanel.add(deckSize);
	
    }

    //Handles list selection changes
    public void valueChanged(ListSelectionEvent event){

	if(event.getSource() == deckList && !event.getValueIsAdjusting()){
	    String deckName = (String)deckList.getSelectedValue();
	    if(deckName != null){
		deckText.setText(deckName);
		currentName.setText("Current Deck: " + decks.get(deckList.getSelectedIndex()).getName() + "            ");
		deckSize.setText("Size: " + decks.get(deckList.getSelectedIndex()).size());		
	    }
	}
    }

    //Checks button presses
    public void actionPerformed(ActionEvent event){

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
    }
    
    public JList getDeckList(){
	return this.deckList;
    }

    public JPanel getDataPanel(){
	return this.dataPanel;
    }
}
