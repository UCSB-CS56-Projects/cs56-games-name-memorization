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
   * Main menu page for the Name Memorization Game.
   *
   *@author Tamky Ngo, Kevin Lau
   *@version cs56, Winter 2015
   */

public class Menu extends JFrame{
	
	private JPanel title;
	private JButton play;
	private JButton practice;
	private JButton deck;
	private JButton howTo;
	private JButton quit;
	private JLabel nameGame;
	private JFrame thisMenu = this;
	private JLabel howToPlay;
	
	public Menu(){

		this.getContentPane().setLayout(new BorderLayout());
		title = new JPanel();
		play = new JButton("Play");
		practice = new JButton("Practice Mode");
		deck = new JButton("Create/Edit Deck");
		howTo = new JButton("How To Play");
		quit = new JButton("Quit");
		title.add(play);
		title.add(practice);
		title.add(deck);
		title.add(howTo);
		title.add(quit);
		title.setBackground(Color.CYAN);
		this.add(title,BorderLayout.CENTER);
		title.setVisible(true);

		nameGame = new JLabel("Name Memorization Game");
		nameGame.setForeground(Color.BLACK);
		nameGame.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		title.add(nameGame, BorderLayout.NORTH);

		playButtonListener playListener = new playButtonListener();
		play.addActionListener(playListener);

		practiceButtonListener practiceListener = new practiceButtonListener();
		practice.addActionListener(practiceListener);

		deckButtonListener deckListener = new deckButtonListener();
		deck.addActionListener(deckListener);

		howToButtonListener howToListener = new howToButtonListener();
		howTo.addActionListener(howToListener);

		quitButtonListener quitListener = new quitButtonListener();
		quit.addActionListener(quitListener);

		this.pack();
	}

	private class playButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			thisMenu.setVisible(false);
			Deck d = new Deck("");
	    		try {
	        		FileInputStream fileStream = new FileInputStream("Deck.ser");
	        		ObjectInputStream os = new ObjectInputStream(fileStream);

	        		Object deck = os.readObject();
	        		d = (Deck) deck;
	        		os.close();
	    		} catch (Exception ex) {
	        		ex.printStackTrace();
	    		}
        		final NameGame game = new NameGame();
	    		game.setDeck(d);
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
        		game.setTitle("Name Memorization Game");
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
						os.writeObject(game.getDeck());
						os.close();
			
		    			} catch(Exception ex) {
						ex.printStackTrace();
		    			}
				}
	    		});
			//System.out.println("Play button pressed");
		}
	}

	private class practiceButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Practice button pressed");
		}
	}

	private class deckButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Deck button pressed");
		}
	}

	private class howToButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try {
	        		BufferedReader in = new BufferedReader(new FileReader("HowToPlay.txt"));
				ArrayList<JLabel> howTo = new ArrayList<JLabel>();
	        		String instruct;
				JPanel instructions = new JPanel();
				instructions.setLayout(new BoxLayout(instructions, BoxLayout.Y_AXIS));
				instructions.setBackground(Color.GREEN);
				title.add(instructions,BorderLayout.SOUTH);
				while((instruct = in.readLine()) != null){
					howTo.add(new JLabel(instruct));
				}
				for(JLabel line : howTo){
					line.setForeground(Color.BLACK);
					line.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
					instructions.add(line);
				}
				instructions.setVisible(true);
				title.revalidate();
	        		in.close();
	    		} catch (Exception ex) {
	        		ex.printStackTrace();
	    		}
			//System.out.println("How to play button pressed");
		}
	}

	private class quitButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(1);
		}
	}
}


























