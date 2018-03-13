package edu.ucsb.cs56.projects.games.name_memorization;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import java.util.*;
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




public class Main extends Application {
	public static Stage mainStage;	
	public static DeckList decks;
	
	public static void main(String[] args) {
		Deck d;
		decks = new DeckList();

		if (decks.size() != 0) {
			d = decks.get(0);
		} else {
			d = new Deck("First Deck");
			decks.add(d);
		}
		Application.launch(args);
	}
	@Override 
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		primaryStage.setTitle("JavaFX attempt");
		NameGame test = new NameGame(decks);
		primaryStage.setScene(new Scene(test, 800, 600));
		primaryStage.show();
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
