
package edu.ucsb.cs56.projects.games.name_memorization;
import javax.swing.JFrame;
import java.awt.Color;
/**
 *Main function which runs the preliminaries of a name memorization game
 *
 *@author Jasper Fredrickson
 *@version Mantis Ticket 0000231 for cs56, Spring 2011
 */
public class Main{
    public static void main(String[] args){
        JFrame game = new NameGame();
        game.setTitle("FlashCard App");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(800, 600);
	game.setLocationRelativeTo(null);
	game.getContentPane().setBackground(Color.BLUE);
        game.setVisible(true);
    }
}
