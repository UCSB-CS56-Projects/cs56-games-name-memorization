package edu.ucsb.cs56.projects.games.name_memorization;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AddCard extends JDialog{
    private JTextArea front;
    private JTextArea back;
    private JButton confirm;
    private JButton exit;
    private JDialog window;
    private JLabel prompt1;
    private JLabel FRONT;
    private JLabel BACK;

    public AddCard(){
	window = this;
	this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	this.setBounds(300, 200, 640, 480);
	this.setResizable(false);
	this.getContentPane().setBackground(Color.GREEN);
	this.getContentPane().setLayout(null);
	this.setLocationRelativeTo(null);
	
	prompt1 = new JLabel("Please edit the card to add");
	prompt1.setForeground(Color.BLACK);
	prompt1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	prompt1.setBounds(221,25,250,45);
       	prompt1.setHorizontalAlignment(SwingConstants.CENTER);
	getContentPane().add(prompt1);
	
	front = new JTextArea("FRONT");
	front.setVisible(true);
	front.setBounds(25,100, 275,250);
	getContentPane().add(front);
	
	back = new JTextArea("BACK");
	back.setVisible(true);
	back.setBounds(310, 100, 275, 250);
	getContentPane().add(back);

	FRONT = new JLabel("FRONT");
	FRONT.setVisible(true);
	FRONT.setBounds(155,80, 200, 19);
	getContentPane().add(FRONT);

	BACK = new JLabel("BACK");
	BACK.setVisible(true);
	BACK.setBounds(440,80, 200, 19);
	getContentPane().add(BACK);

	confirm = new JButton("confirm");
	confirm.addActionListener(new confirmListener());
	confirm.setVisible(true);
	confirm.setBounds(260,400,100,30);
        getContentPane().add(confirm);

    }

    public class confirmListener implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    window.dispose();
	}
    }
}
