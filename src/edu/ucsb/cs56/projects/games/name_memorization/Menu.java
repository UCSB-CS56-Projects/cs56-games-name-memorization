package edu.ucsb.cs56.projects.games.name_memorization;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Dimension;
/**
 * A class that creates the GUI that allows the user to edit cards
 */
public class Menu extends BorderPane{
    private Button start;
    private Label[] instr = new Label[10];
    public HBox botPanel;
    private VBox midPanel;
    private Label mainInstr;
    private String[] instructions = new String[10];

    /**
     * Constructor for Menu
     * Creates the GUI
     */
    public Menu() {
      String mainInstruction = "Welcome to the name memorization game!";
      instructions[0] = "Press Normal Mode or Quiz mode to start the game.";
      instructions[1] = "Use the Add button to add cards to the current deck.";
      instructions[2] = "Use the Edit button to edit the current card.";
      instructions[3] = "Use the Select Deck button to select, save, or load decks.";
      instructions[4] = "Use the Main Menu button to return to this page.";
      instructions[5] = "Use the Delete button to delete the current card.";
      instructions[6] = "Use the Show Front/Back buttons to flip the flash card.";
      instructions[7] = "Use the Correct/Incorrect buttons to keep score.";
      instructions[8] = "Use the Previous/Next buttons to move back and forth within the current deck.";
      instructions[9] = "Use the Reset button to set the score back to 0.";

      setPrefSize(800,600);
      setPadding(new Insets(10, 10, 10, 10));
      setStyle("-fx-background-color: #D3D3D3;");

      midPanel = new VBox(20);
      midPanel.setPadding(new Insets(10, 10, 10, 10));
      midPanel.setStyle("-fx-background-color: #D3D3D3;");
      midPanel.setAlignment(Pos.CENTER);

      botPanel = new HBox(10);
      botPanel.setPadding(new Insets(10, 10, 10, 10));
      botPanel.setStyle("-fx-background-color: #D3D3D3;");
      botPanel.setAlignment(Pos.CENTER);

      mainInstr = new Label(mainInstruction);

      for (int i = 0; i < 10; i++) {
        instr[i] = new Label(instructions[i]);
      }

      mainInstr.setTextFill(Color.web("#FFFFFF"));
      for (int i = 0; i < 10; i++) {
        instr[i].setTextFill(Color.web("#000000"));
      }

      mainInstr.setFont(new Font("Lucida Grande", 32));
      for (int i = 0; i < 10; i++) {
        instr[i].setFont(new Font("Lucida Grande", 18));
      }

      setAlignment(mainInstr, Pos.CENTER);
      setTop(mainInstr);
      for (int i = 0; i < 10; i++) {
        midPanel.getChildren().add(instr[i]);
      }
      setCenter(midPanel);
      setBottom(botPanel);
    }

    public HBox getBotPanel() {
	    return this.botPanel;
    }
}
