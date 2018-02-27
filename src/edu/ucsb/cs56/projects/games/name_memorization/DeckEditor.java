package edu.ucsb.cs56.projects.games.name_memorization;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Dimension;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private JPanel thisPanel = this;

    private JList deckList;
    private Vector deckNames;
    private JButton addDeck;
    private JButton removeDeck;
    private JButton copyDeck;
    private JButton selectDeck;
    private JButton saveDeck;
    private JButton loadDeck;
    private JScrollPane deckScroller;
    private JTextField deckText;

    private JLabel deckSize;
    private JLabel currentName;

    private DeckList decks;
    private Deck deck;

    private String path;

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

  path=System.getProperty("user.dir");
  path=path + "/src/edu/ucsb/cs56/projects/games/name_memorization/saves/";

	CreateDeckEntryPanel();
    }

    /**
     * Creates the GUI for JButtons and JTextField
     */
    public void CreateDeckEntryPanel(){
        dataPanel = new JPanel();
		dataPanel.setBackground(Color.BLUE);
		this.add(dataPanel, BorderLayout.SOUTH);

		JPanel embossDeck = new JPanel();
		embossDeck.setBackground(Color.BLACK);
		embossDeck.setPreferredSize(new Dimension(100, 100));
		topPanel.add(embossDeck, BorderLayout.SOUTH);

		deckText = new JTextField("Enter Deck Name: ");
		deckText.setPreferredSize(new Dimension(780,90));
		embossDeck.add(deckText, BorderLayout.CENTER);

		addDeck = new JButton("Add");
		dataPanel.add(addDeck);
		addDeck.addActionListener(this);

		dataPanel.add(Box.createRigidArea(new Dimension(20,0)));

		removeDeck = new JButton("Remove");
		dataPanel.add(removeDeck);
		removeDeck.addActionListener(this);

		dataPanel.add(Box.createRigidArea(new Dimension(20,0)));

		copyDeck = new JButton("Copy");
		dataPanel.add(copyDeck);
		copyDeck.addActionListener(this);

		dataPanel.add(Box.createRigidArea(new Dimension(20,0)));

		saveDeck = new JButton("Save");
		dataPanel.add(saveDeck);
		saveDeck.addActionListener(this);

		dataPanel.add(Box.createRigidArea(new Dimension(20,0)));

		loadDeck = new JButton("Load");
		dataPanel.add(loadDeck);
		loadDeck.addActionListener(this);

		dataPanel.add(Box.createRigidArea(new Dimension(20,0)));



		infoPanel = new JPanel();
		infoPanel.setBackground(Color.BLUE);

		topPanel.add(infoPanel, BorderLayout.NORTH);

		currentName = new JLabel("Current Deck: ");
		currentName.setForeground(Color.WHITE);
		infoPanel.add(currentName);

		deckSize = new JLabel("Size: ");
		deckSize.setForeground(Color.WHITE);
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
}

    public JList getDeckList(){
	return this.deckList;
    }

    public JPanel getDataPanel(){
	return this.dataPanel;
    }
}
