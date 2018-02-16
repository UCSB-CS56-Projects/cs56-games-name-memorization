package edu.ucsb.cs56.projects.games.name_memorization;
import javax.swing.*;
import javafx.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Dimension;
/**
 * A class that creates the GUI that allows the user to edit cards
 */
public class Menu extends JPanel{
    private JButton start;
    private JLabel[] instr = new JLabel[10];
    private JPanel botPanel;
    private JPanel midPanel;
    private String[] instructions = new String[10];

    /**
     * Constructor for Menu
     * Creates the GUI
     */
    public Menu() {
	String mainInstruction = "Welcome to the name memorization game!";
	instructions[0] = "Press Normal Mode or Quiz mode to start the game.";
	instructions[1] = "Use the Add button to add cards to the current deck.";
	instructions[2] = "Use the Edit button to edit the current card.";
	instructions[3] = "Use the Select Deck button to select, save, or load decks.";
	instructions[4] = "Use the Main Menu button to return to this page.";
	instructions[5] = "Use the Delete button to delete the current card.";
	instructions[6] = "Use the Show Front/Back buttons to flip the flash card.";
	instructions[7] = "Use the Correct/Incorrect buttons to keep score.";
	instructions[8] = "Use the Previous/Next buttons to move back and forth within the current deck.";
	instructions[9] = "Use the Reset button to set the score back to 0.";
	
	this.setLayout(new BorderLayout());
	midPanel = new JPanel();
	midPanel.setBackground(Color.lightGray);
	botPanel = new JPanel();
	botPanel.setBackground(Color.lightGray);
	JLabel mainInstr = new JLabel(mainInstruction);
	
	for (int i = 0; i < 10; i++) {
	    instr[i] = new JLabel(instructions[i]);
	}
	
	mainInstr.setForeground(Color.WHITE);
	for (int i = 0; i < 10; i++) {
	    instr[i].setForeground(Color.BLACK);
	}
	
	mainInstr.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
	for (int i = 0; i < 10; i++) {
	    instr[i].setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	}
	
	mainInstr.setHorizontalAlignment(SwingConstants.CENTER);
	for (int i = 0; i < 10; i++) {
	    instr[i].setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	midPanel.add(mainInstr);
	for (int i = 0; i < 10; i++) {
	    midPanel.add(instr[i]);
	}
	
	this.add(midPanel, BorderLayout.CENTER);
	this.add(botPanel, BorderLayout.SOUTH);
    }

    public JPanel getBotPanel(){
	return this.botPanel;
    }
}
