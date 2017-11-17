package edu.ucsb.cs56.projects.games.name_memorization;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Dimension;
/**
 * A class that creates the GUI that allows the user to edit cards
 */
public class Menu extends JPanel{
    private JButton start;
    private JLabel instr0, instr1, instr2, instr3, instr4, instr5, instr6, instr7, instr8, instr9, mainInstr;
    private JPanel botPanel;
    private JPanel midPanel;
    private String instructions0, instructions1, instructions2, instructions3, instructions4, instructions5, instructions6, instructions7, instructions8, instructions9, mainInstruction;
    

    /**
     * Constructor for Menu
     * Creates the GUI 
     */
    public Menu(){
	mainInstruction = "Welcome to the name memorization game!";
	instructions0 = "Press start to launch the game in standard mode.";
	instructions1 = "Use the Add button to add cards to the current deck.";
	instructions2 = "Use the Edit button to edit the current card.";
	instructions5 = "Use the Delete button to delete the current card.";
	instructions3 = "Use the Select Deck button to select, save, or load decks.";
	instructions4 = "Use the Main Menu button to return to this page.";
	instructions6 = "Use the Show Front/Back buttons to flip the flash card.";
	instructions7 = "Use the Correct/Incorrect buttons to keep score.";
	instructions8 = "Use the Previous/Next buttons to move back and forth within the current deck.";
	instructions9 = "Use the Reset button to set the score back to 0.";
	this.setLayout(new BorderLayout());
	midPanel = new JPanel();
	midPanel.setBackground(Color.lightGray);
	botPanel = new JPanel();
	botPanel.setBackground(Color.lightGray);
	mainInstr = new JLabel(mainInstruction);
	instr0 = new JLabel(instructions0);
	instr1 = new JLabel(instructions1);
	instr2 = new JLabel(instructions2);
	instr5 = new JLabel(instructions5);
	instr3 = new JLabel(instructions3);
	instr4 = new JLabel(instructions4);
	instr6 = new JLabel(instructions6);
	instr7 = new JLabel(instructions7);
	instr8 = new JLabel(instructions8);
	instr9 = new JLabel(instructions9);
	mainInstr.setForeground(Color.WHITE);
	instr0.setForeground(Color.BLACK);
	instr1.setForeground(Color.BLACK);
	instr2.setForeground(Color.BLACK);
	instr5.setForeground(Color.BLACK);
	instr3.setForeground(Color.BLACK);
	instr4.setForeground(Color.BLACK);
	instr6.setForeground(Color.BLACK);
	instr7.setForeground(Color.BLACK);
	instr8.setForeground(Color.BLACK);
	instr9.setForeground(Color.BLACK);
	mainInstr.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
	instr0.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	instr1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	instr2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	instr5.setFont(new Font("Lucida Granda", Font.PLAIN, 18));
	instr3.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	instr4.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	instr6.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	instr7.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	instr8.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	instr9.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	//prompt1.setBounds(175,25,250,45);
	mainInstr.setHorizontalAlignment(SwingConstants.CENTER);
	instr0.setHorizontalAlignment(SwingConstants.CENTER);
	instr1.setHorizontalAlignment(SwingConstants.CENTER);
	instr2.setHorizontalAlignment(SwingConstants.CENTER);
	instr5.setHorizontalAlignment(SwingConstants.CENTER);
	instr3.setHorizontalAlignment(SwingConstants.CENTER);
	instr4.setHorizontalAlignment(SwingConstants.CENTER);
	instr6.setHorizontalAlignment(SwingConstants.CENTER);
	instr7.setHorizontalAlignment(SwingConstants.CENTER);
	instr8.setHorizontalAlignment(SwingConstants.CENTER);
	instr9.setHorizontalAlignment(SwingConstants.CENTER);
	midPanel.add(mainInstr);
	midPanel.add(instr0);
	midPanel.add(instr1);
	midPanel.add(instr2);
	midPanel.add(instr5);
	midPanel.add(instr3);
	midPanel.add(instr4);
	midPanel.add(instr6);
	midPanel.add(instr7);
	midPanel.add(instr8);
	midPanel.add(instr9);
	this.add(midPanel, BorderLayout.CENTER);
	this.add(botPanel, BorderLayout.SOUTH);
    }

    public JPanel getBotPanel(){
	return this.botPanel;
    }
}
