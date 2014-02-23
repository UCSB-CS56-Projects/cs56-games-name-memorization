package edu.ucsb.cs56.projects.games.name_memorization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component;

public class Card extends JPanel{


    private JTextArea cardText;
    private String side1;
    private String side2;


    public Card(String side1, String side2) {
	this.side1 = side1;
	this.side2 = side2;
	cardText = new JTextArea(side1);
    cardText.setLineWrap(true);
    cardText.setBounds(120,30,275,250);
    
 
	this.setLayout(null);
    this.setBackground(Color.RED);
	//this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	this.add(cardText);
    }

    public void setSide1(String side1) {
	this.side1 = side1;
    }

    public void setSide2(String side2) {
	this.side2 = side2;
    }

    public String getSide1() { return side1; }
    public String getSide2() { return side2; }

}
