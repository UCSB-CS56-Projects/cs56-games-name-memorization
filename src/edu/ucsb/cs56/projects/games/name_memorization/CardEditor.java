package edu.ucsb.cs56.projects.games.name_memorization;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Dimension;
/**
 * A class that creates the GUI that allows the user to edit cards
 */
public class CardEditor extends JPanel{
    private JTextArea front;
    private JTextArea back;
    private JButton upload;
    private JButton exit;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel botPanel;
   
    private JLabel prompt1;
    private JLabel FRONT;
    private JLabel BACK;
    private Deck deck;
    private String frontText;
    private String backText; 
    private String path; //path to people folder
    private JLabel frontPic;
    private JPanel thisPanel = this;
    
    private boolean isPicture; 

    /**
     * Constructor for CardEditor
     * Creates the GUI in which the user can edit cards
     */
    public CardEditor(){
	//sets path variable to directory of the people pictures
	path=System.getProperty("user.dir");
	path=path + "/src/people/";
	
	isPicture = false;

	this.setLayout(new BorderLayout());
	topPanel = new JPanel();
	topPanel.setBackground(Color.GREEN);
	prompt1 = new JLabel("Edit Card");
	prompt1.setForeground(Color.BLACK);
	prompt1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	//prompt1.setBounds(175,25,250,45);
	prompt1.setHorizontalAlignment(SwingConstants.CENTER);
	topPanel.add(prompt1);
	this.add(topPanel, BorderLayout.NORTH);

	midPanel = new JPanel();
	midPanel.setBackground(Color.GREEN);
	
	FRONT = new JLabel("FRONT");
	//FRONT.setBounds(155,80, 200, 19);
	midPanel.add(FRONT);

	front = new JTextArea("Enter Text");
	front.setPreferredSize(new Dimension(250,350));
	midPanel.add(front);

	back = new JTextArea("Enter Text");
	back.setLineWrap(true);
	back.setPreferredSize(new Dimension(250,350));
	midPanel.add(back);
	this.add(midPanel, BorderLayout.CENTER);

	BACK = new JLabel("BACK");
	BACK.setBounds(440,80, 200, 19);
	midPanel.add(BACK);
	
	botPanel = new JPanel();
	botPanel.setBackground(Color.GREEN);
	uploadButtonListener uploadListener = new uploadButtonListener();
	upload = new JButton("upload a picture");
	//upload.setBounds(110,360 ,150,25);
	upload.addActionListener(uploadListener);
	botPanel.add(upload);
	this.add(botPanel, BorderLayout.SOUTH);

    }

    //method to resize the uploaded images to fit the screen.
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type){  
    	BufferedImage resizedImage = new BufferedImage(width, height, type);  
    	Graphics2D g = resizedImage.createGraphics();  
        g.drawImage(originalImage, 0, 0, width, height, null);  
        g.dispose();  
        
        return resizedImage;  
    }  

    /**
     * Gets the text on the front of the card
     *
     * @return frontText the text on the front
     */
    public String getFrontText() {
	frontText = front.getText();
	return frontText; 
    }

    /**
     * Gets the text on the back of the card
     *
     * @return backText the text on the back
     */
    public String getBackText() { 
	backText = back.getText();
	return backText; 
    }

    /**
     * Gets the picture on the front card
     *
     * @return frontPic the picture on the front of the card
     */
    public JLabel getPic(){
	return frontPic;
    }


    private class uploadButtonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		
	    JFileChooser chooser = new JFileChooser(new File(path));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(thisPanel);
		if(returnVal == JFileChooser.APPROVE_OPTION) {

		    String name = chooser.getSelectedFile().getName();
		    path = path + name; // path should now contain full location of chosen pic
		    
		    front.setVisible(false);
		    frontPic=new JLabel();
		    frontPic.setBounds(25,100, 275,250);
	  	try{
	  		BufferedImage unsized = ImageIO.read(new File(path));
	  		BufferedImage resized = resizeImage(unsized,275,250,unsized.getType());
	  		frontPic.setIcon(new ImageIcon(resized));
			thisPanel.validate();
			thisPanel.repaint();
	  	}catch(IOException ex){
	  		System.out.println("Trouble reading from the file: " + ex.getMessage());
	  	}
	  	front.setText(path); // because namegame screen takes the input from front Text area and sets it
	  						// area and sets it Text area and sets it front text
		isPicture = true;
	  	thisPanel.add(frontPic);
		}
	}
    }

    /**
     * Tells us if the Card has a picture
     *
     * @return isPicture A boolean that is true if the card has a picture, false otherwise
     */
    public boolean isPic(){
	return isPicture;
    }

    public JPanel getBotPanel(){
	return this.botPanel;
    }
}
