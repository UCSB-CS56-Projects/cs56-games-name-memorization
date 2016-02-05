package edu.ucsb.cs56.projects.games.name_memorization;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
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
import java.util.*;
import java.util.Hashtable;

/**
 * Window that lets player choose to create a new deck or to edit
 * an existing one.
 * 
 * @author Tamky Ngo, Kevin Lau
 * @version CS56, Winter 2015
 */

public class DeckEditor extends JFrame{

	private JPanel editor;
	private JButton createNew;
	private JButton edit;
	private JLabel deckEditor;
	private JFrame thisEditor = this;

	/**
	 * Constructor that sets up the Deck Editor menu.
	 */
	public DeckEditor(){

		this.getContentPane().setLayout(new BorderLayout());
		editor = new JPanel();
		createNew = new JButton("Create New Deck");
		edit = new JButton("Edit Deck");
		editor.add(createNew);
		editor.add(edit);
		editor.setBackground(Color.CYAN);
		this.add(editor,BorderLayout.CENTER);
		editor.setVisible(true);

		deckEditor = new JLabel("Deck Editor");
		deckEditor.setForeground(Color.BLACK);
		deckEditor.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		editor.add(deckEditor, BorderLayout.NORTH);

		createButtonListener createListener = new createButtonListener();
		createNew.addActionListener(createListener);

		editButtonListener editListener = new editButtonListener();
		edit.addActionListener(editListener);

		this.pack();
	}

	private class createButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Create button pushed");
			//this.createDeck();
		}
	}

	private class editButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Edit button pushed");			
			//this.editDeck(
		}
	}

	//Everything below was attempted to create/save/load multiple decks.
	//TODO Implement methods to create/save/load multiple decks.
	/*
	public void createDeck(){
		Deck d = new Deck("New Deck");
		CardEditor editor = new CardEditor();

	    	JButton confirm = new JButton("Confirm");
	    	JButton cancel = new JButton("Cancel");
	    	confirm.setBounds(200,400,100,30);
	    	cancel.setBounds(360,400,100,30);
	    	editor.getContentPane().add(confirm);
	    	editor.getContentPane().add(cancel);
	    	confirmButtonListener confirmListener = new confirmButtonListener();
	    	cancelButtonListener cancelListener = new cancelButtonListener();
	    	confirm.addActionListener(confirmListener);
	    	cancel.addActionListener(cancelListener);

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

		private class cancelButtonListener implements ActionListener{
		    public void actionPerformed(ActionEvent e){
			editor.dispose();
		    }
		}
	}

    	public void editDeck(Deck d) {
	    	CardEditor c = new CardEditor();
		if(d.size() == 0) {
		    JOptionPane.showMessageDialog(null, "Deck is currently empty","Error", JOptionPane.ERROR_MESSAGE);
		    return;
	    	}

	    	JButton confirm = new JButton("Confirm");
	    	JButton cancel = new JButton("Cancel");
	    	confirm.setBounds(200,400,100,30);
	    	cancel.setBounds(360,400,100,30);
	    	c.getContentPane().add(confirm);
	    	c.getContentPane().add(cancel);
	    	confirmButtonListener confirmListener = new confirmButtonListener();
	    	cancelButtonListener cancelListener = new cancelButtonListener();
	    	confirm.addActionListener(confirmListener);
	    	cancel.addActionListener(cancelListener);
	
		private class confirmButtonListener implements ActionListener {
	    		public void actionPerformed(ActionEvent e) {
		    		String side1 = editor.getFrontText();
		    		String side2 = editor.getBackText();
		    		Card h = d.get(current);

		    		d.editCard(h, side1, side2);
		    		if(h.isPic()){
		        		setPic(h);
				    	}
				else{
					setPrint(h,1);
				}

				c.dispose();
	    		}

		}
	
		private class cancelButtonListener implements ActionListener{
		    public void actionPerformed(ActionEvent e){
			c.dispose();
		    }
		}
	
    	}
*/
	
    
}
