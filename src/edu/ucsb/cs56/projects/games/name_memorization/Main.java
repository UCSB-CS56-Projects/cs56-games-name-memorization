package edu.ucsb.cs56.projects.games.name_memorization;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.io.*;
/**
 *Main function which runs the preliminaries of a name memorization game
 *
 *@author Jasper Fredrickson
 *@author Domenic DiPeppe
 *@version for CS56 W16
 */
public class Main{

    public static DeckList decks;
    public static void main(String[] args){
	
	Deck d;
	decks = new DeckList();
	try {
	    FileInputStream fileStream = new FileInputStream("Deck.ser");
	    ObjectInputStream os = new ObjectInputStream(fileStream);

	    Object deckList = os.readObject();
	    decks = (DeckList) deckList ;
	    os.close();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}

	if(decks.size() !=0){
	    d = decks.get(0);
	}
	else{
	    d = new Deck("First Deck");
	    decks.add(d);
	}
	
        final NameGame game = new NameGame(decks);
	
	if(d.size() > 0) {
	    if(d.get(0).isPic()) {
		game.setPic(d.get(0));
	    }
	    else {
		game.setPrint(d.get(0),1);
	    }
	}
	
	game.updateSize(d.size());
	game.setCardNum();
        game.setTitle("FlashCard App");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(800, 600);
	game.setLocationRelativeTo(null);
	game.getContentPane().setBackground(Color.BLUE);
        game.setVisible(true);
	

	game.addWindowListener(new java.awt.event.WindowAdapter() {
		@Override 
		public void windowClosing(java.awt.event.WindowEvent windowEvent) { 
		    try {
			FileOutputStream fs = new FileOutputStream("Deck.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(game.getDeckList());
			os.close();
			
		    } catch(Exception ex) {
			ex.printStackTrace();
		    }
		}
	    });	
  
    }
}
