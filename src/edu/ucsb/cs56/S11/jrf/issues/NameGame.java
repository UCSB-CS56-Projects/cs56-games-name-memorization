package edu.ucsb.cs56.S11.jrf.issues;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

  /**
   * Preliminary engine for running a name memorization game
   *
   *@author Jasper Fredrickson
   *@version Mantis Ticket 0000231 for cs56, Spring 2011
   */
public class NameGame extends JFrame{

    private JButton next;
    private DirectoryLister dir;
    private int current;
    private Image pic;


    public void paint(Graphics g){
        //get the picture to paint
        pic = Toolkit.getDefaultToolkit().getImage(dir.getFullFilenames().get(current));
        //call super's paint method
        super.paint(g);
        //draw designated picture in background
        g.drawImage(pic, 50, 60,300, 300, this);
    }

    /**
     * No arg constructor for the name game. Initializes everyting in a JFrame
     * (Buttons, pics, etc)
     */
    public NameGame(){
        dir = new DirectoryLister("src/people");
        current = 0;
        this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        HandlerClass handler = new HandlerClass();
        next = new JButton("next");
        next.setAlignmentX(Component.CENTER_ALIGNMENT);
        next.addActionListener(handler);
        this.add(next);
    }

    /**
     * Handler Class, used to give buttons functionality
     */
    private class HandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // iterate to next pic unless at end of array, then go to first pic
            if (current < dir.getNumFiles()-1){
          current++;
            }else{
          current = 0;
            }
            repaint();
        }
    }
}
