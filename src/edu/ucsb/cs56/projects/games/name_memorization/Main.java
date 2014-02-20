package edu.ucsb.cs56.projects.games.name_memorization;
import javax.swing.JFrame;

/**
 *Main function which runs the preliminaries of a name memorization game
 *
 *@author Jasper Fredrickson
 *@version Mantis Ticket 0000231 for cs56, Spring 2011
 */
public class Main{
    public static void main(String[] args){
        JFrame game = new NameGame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(400, 500);
	game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
}
