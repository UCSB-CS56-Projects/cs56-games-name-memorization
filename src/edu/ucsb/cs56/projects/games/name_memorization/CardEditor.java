package edu.ucsb.cs56.projects.games.name_memorization;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CardEditor extends JDialog{
    private JTextArea front;
    private JTextArea back;
    private JButton upload;
    private JButton exit;
    private JDialog window;
    private JLabel prompt1;
    private JLabel FRONT;
    private JLabel BACK;
    private Deck deck;
    private String frontText;
    private String backText; 
    private String path; //path to people folder
    private JLabel frontPic;

    private boolean isPicture;

 
 

    public CardEditor(){
	    //sets path variable to directory of the people pictures
	    path=System.getProperty("user.dir");
	    path=path + "/src/people/";
	
	    isPicture = false;
	    window = this;

	    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	    this.setBounds(300, 200, 640, 480);
	    this.setResizable(false);
	    this.getContentPane().setBackground(Color.GREEN);
	    this.getContentPane().setLayout(null);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	
	    prompt1 = new JLabel("Edit Card");
	    prompt1.setForeground(Color.BLACK);
	    prompt1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
	    prompt1.setBounds(175,25,250,45);
	    prompt1.setHorizontalAlignment(SwingConstants.CENTER);
	    getContentPane().add(prompt1);
	
	    front = new JTextArea("Enter Text");
	    front.setLineWrap(true);
	    front.setBounds(25,100, 275,250);
	    getContentPane().add(front);
	
	    back = new JTextArea("Enter Text");
	    back.setLineWrap(true);
	    back.setBounds(310, 100, 275, 250);
	    getContentPane().add(back);

	    FRONT = new JLabel("FRONT");
	    FRONT.setBounds(155,80, 200, 19);
	    getContentPane().add(FRONT);

	    uploadButtonListener uploadListener = new uploadButtonListener();
	    upload = new JButton("upload a picture");
	    upload.setBounds(110,360 ,150,25);
	    upload.addActionListener(uploadListener);
	    getContentPane().add(upload);


	    BACK = new JLabel("BACK");
	    BACK.setBounds(440,80, 200, 19);
	    getContentPane().add(BACK);



	
    }

    //method to resize the uploaded images to fit the screen.
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type){  
    	BufferedImage resizedImage = new BufferedImage(width, height, type);  
    	Graphics2D g = resizedImage.createGraphics();  
        g.drawImage(originalImage, 0, 0, width, height, null);  
        g.dispose();  
        
        return resizedImage;  
    }  


    public String getFrontText() {
	    frontText = front.getText();
	    return frontText;
    }
    public String getBackText() { 
	    backText = back.getText();
	    return backText;
    }

    public JLabel getPic(){
	return frontPic;
    }




    private class uploadButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		
	        JFileChooser chooser = new JFileChooser(new File(path));
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(window);
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
	  		    window.getContentPane().validate();
	  		    window.getContentPane().repaint();
	  	    }catch(IOException ex){
	  		    System.out.println("Trouble reading from the file: " + ex.getMessage());
	  	    }
	  	    front.setText(path); // because namegame screen takes the input from front Text area and sets it
	  						// area and sets it Text area and sets it front text
		    isPicture = true;
	  	    getContentPane().add(frontPic);
		    }
	    }
    }

    public boolean isPic(){
		return isPicture;
    }




    


}
