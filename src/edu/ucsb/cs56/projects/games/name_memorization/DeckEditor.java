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
	this.setBackground(Color.CYAN);

	topPanel = new JPanel();
	topPanel.setLayout(new BorderLayout());
	topPanel.setPreferredSize(new Dimension(300,500));
	this.add(topPanel);
	
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
	dataPanel.setLayout(new BorderLayout());
	topPanel.add(dataPanel, BorderLayout.SOUTH);

	addDeck = new JButton("Add");
	dataPanel.add(addDeck, BorderLayout.WEST);
	addDeck.addActionListener(this);

	removeDeck = new JButton("Remove");
	dataPanel.add(removeDeck, BorderLayout.EAST);
	removeDeck.addActionListener(this);
	
	deckText = new JTextField("Enter Deck Name");
	dataPanel.add(deckText, BorderLayout.NORTH);

	infoPanel = new JPanel();
	infoPanel.setLayout(new BorderLayout());
	topPanel.add(infoPanel, BorderLayout.NORTH);

	currentName = new JLabel("Name");
	infoPanel.add(currentName, BorderLayout.EAST);

	deckSize = new JLabel("Size");
	infoPanel.add(deckSize, BorderLayout.WEST);
	
    }

    //Handles list selection changes
    public void valueChanged(ListSelectionEvent event){

	if(event.getSource() == deckList && !event.getValueIsAdjusting()){
	    String deckName = (String)deckList.getSelectedValue();
	    if(deckName != null){
		deckText.setText(deckName);
		currentName.setText(decks.get(deckList.getSelectedIndex()).getName());
		deckSize.setText("Size: " + decks.get(deckList.getSelectedIndex()).size());		
	    }
	}
    }

    //Checks button presses
    public void actionPerformed(ActionEvent event){

	if(event.getSource() == addDeck){
	    String deckName = deckText.getText();
	    deckText.setText("");

	    if(deckName != null){
		this.decks.add(new Deck(deckName));
		deckNames.addElement(deckName);
		deckList.setListData(deckNames);
		deckScroller.revalidate();
		deckScroller.repaint();	 
		
	    }
	}

	if(event.getSource() == removeDeck){

	    int selection = deckList.getSelectedIndex();
	    if(selection >=0){

		decks.remove(selection);
		deckNames.removeElementAt(selection);
		deckList.setListData(deckNames);
		deckScroller.revalidate();
		deckScroller.repaint();
		
		if(selection >= deckNames.size())
		    selection = deckNames.size() - 1;
		deckList.setSelectedIndex(selection);
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
