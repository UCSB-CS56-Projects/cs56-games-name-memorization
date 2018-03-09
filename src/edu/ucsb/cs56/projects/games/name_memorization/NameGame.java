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
import javafx.scene.layout.Region;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

/**
 * Preliminary engine for running a name memorization game
 *
 * @author Anthony Hoang, Colin Biafore
 * @author Domenic DiPeppe
 * @version for CS56, W16
 */
public class NameGame extends BorderPane {
	// Main
	// private BorderPane pane;

	// Top Control Panel
	private Button add;
	private Button edit;
	private Button delete;
	private Button next;
	private Button previous;
	private Button selectDeck;
	private Button menu;

	// Bottom Control Panel
	private HBox southQuiz;
	private Button flip;
	private Button guess;
	private TextArea guessText;

	// East Control Panel
	private Button favorite;
	private Button unfavorite;
	private Label deckName;
	private Label deckNameQuiz;
	private Button restart;

	// West Control Panel
	private BorderPane westQuiz = new BorderPane();
	private Label scoreLabel;
	private Label scoreLabelQuiz;
	private int score;
	private int scoreQuiz;

	private Label deckSize;
	private Label sizeLabel;

	private Button correct;
	private Button incorrect;
	private Button correctQuiz;

	private Label picture;
	private Image pic;

	// DeckList for Decks
	private DeckList decks;

	// Current Card Viewer
	private HBox currentCard;
	private TextArea cardText;
	private int current;
	private Deck d;

	// Deck viewer
	// private JPanel DeckEditor;

	// Label for Card Number:
	private Label cardNum;
	// UI Card Index
	private Label cNum;

	private static Alert myStart;

	public NameGame(DeckList decks) {

		// Initialize North Control Panel
		HBox northBox = new HBox(10);
		setTop(northBox);
		northBox.setPadding(new Insets(10, 10, 10, 10));
		northBox.setAlignment(Pos.CENTER);
		northBox.setStyle("-fx-background-color: #112233;");

		add = new Button("Add");
		edit = new Button("Edit");
		delete = new Button("Delete");
		previous = new Button("Previous");
		next = new Button("Next");
		selectDeck = new Button("Select Deck");
		menu = new Button("Main Menu");

		northBox.getChildren().addAll(add, edit, delete, previous, next, selectDeck, menu);

		// Initialize South Control Panel
		HBox southBox = new HBox(10);
		setBottom(southBox);
		southBox.setPadding(new Insets(10, 10, 10, 10));
		southBox.setAlignment(Pos.CENTER);
		southBox.setStyle("-fx-background-color: #112233;");

		flip = new Button("Flip Card");

		southBox.getChildren().addAll(flip);

		// South Quiz Mode
		southQuiz = new HBox(20);
		southQuiz.setVisible(true);
		guess = new Button("Guess!");
		guessText = new TextArea("Enter Guess Here");
		guessText.setPrefColumnCount(35);
		guessText.setPrefRowCount(1);
		southQuiz.getChildren().add(guessText);
		// southQuiz.getChildren().add(Box.createRigidArea(new Dimension(10,
		// 50)));
		southQuiz.getChildren().add(guess);
		southQuiz.setAlignment(Pos.CENTER);
		// southQuiz.setBackground(Color.lightGray);

		// Initialize Card Viewer
		currentCard = new HBox();
		setCenter(currentCard);
		cardText = new TextArea();
		Font cardFont = new Font("Verdana", 24);
		cardText.setFont(cardFont);
		cardText.setEditable(false);
		currentCard.getChildren().add(cardText);
		this.decks = decks;
		this.d = decks.get(0);
		if (d.size() == 0) {
			cardText.setText("Deck is Empty!");
		}

		// West Panel Components
		BorderPane westBox = new BorderPane();
		setLeft(westBox);
		westBox.setStyle("-fx-background-color: #336699;");

		VBox westNorth = new VBox(10);
		westNorth.setPadding(new Insets(10, 10, 10, 10));
		westNorth.setAlignment(Pos.CENTER);

		deckName = new Label(d.getName());
		deckName.setFont(new Font("Lucida Grande", 18));

		scoreLabel = new Label("Score: " + Integer.toString(score));
		scoreLabel.setFont(new Font("Lucida Grande", 18));

		westNorth.getChildren().addAll(deckName, scoreLabel);

		VBox westCenter = new VBox(10);
		westCenter.setPadding(new Insets(10, 10, 10, 10));
		westCenter.setAlignment(Pos.CENTER);

		correct = new Button("      Correct!      ");
		incorrect = new Button("     Incorrect     ");

		westCenter.getChildren().addAll(correct, incorrect);

		westBox.setTop(westNorth);
		westBox.setCenter(westCenter);

		// West panel Quiz
		westQuiz.setStyle("-fx-background-color: #117799;");

		VBox westCenterQuiz = new VBox();
		westQuiz.setCenter(westCenterQuiz);
		westCenterQuiz.setPadding(new Insets(10, 10, 10, 10));
		westCenterQuiz.setAlignment(Pos.CENTER);

		scoreLabelQuiz = new Label("Score: " + Integer.toString(scoreQuiz));
		scoreLabelQuiz.setFont(new Font("Lucida Grande", 18));

		westCenterQuiz.getChildren().addAll(scoreLabelQuiz);

		VBox westSouthQuiz = new VBox();
		westQuiz.setBottom(westSouthQuiz);
		westSouthQuiz.setPadding(new Insets(10, 10, 10, 10));
		westSouthQuiz.setAlignment(Pos.CENTER);

		correctQuiz = new Button("Override Correct");
		correctQuiz.setVisible(false);

		westSouthQuiz.getChildren().addAll(correctQuiz);

		deckNameQuiz = new Label(d.getName()); // d.getName()
		deckNameQuiz.setFont(new Font("Lucida Grande", 18));
		// westQuiz.setAlignment(deckNameQuiz, CENTER);
		westQuiz.setTop(deckNameQuiz);
		westQuiz.setAlignment(deckNameQuiz, Pos.CENTER);

		// East Panel
		VBox eastBox = new VBox(20);
		setRight(eastBox);
		eastBox.setPadding(new Insets(10, 10, 10, 10));
		//eastBox.setAlignment(Pos.CENTER);
		eastBox.setStyle("-fx-background-color: #336699;");

		sizeLabel = new Label("Deck Size:");
		sizeLabel.setFont(new Font("Lucida Grande", 18));

		deckSize = new Label(Integer.toString(d.size()));
		deckSize.setFont(new Font("Lucida Grande", 18));

		cardNum = new Label("Card Number:");
		cardNum.setFont(new Font("Lucida Grande", 18));

		cNum = new Label(Integer.toString(current));
		cNum.setFont(new Font("Lucida Grande", 18));

		restart = new Button("Restart");
		favorite = new Button("Favorite Card");
		unfavorite = new Button("Unfavorite Card");
		
		eastBox.setAlignment(Pos.TOP_CENTER);
		eastBox.getChildren().addAll(favorite, unfavorite, sizeLabel, deckSize, cardNum, cNum, restart);
		

		// ADD BUTTON AND ITS ELEMENTS

		EventHandler<ActionEvent> addHandler = new EventHandler<ActionEvent>() {
			@Override
			// add button
			public void handle(ActionEvent event) {
				Card c = new Card("Enter Text", "Enter Text", false);
				CardEditor test = new CardEditor(c);
				Stage stage = new Stage();
				stage.setScene(new Scene(test));
				stage.show();
				Main.mainStage.hide();
				Button confirm = new Button("Confirm");
				Button cancel = new Button("Cancel");
				test.botPanel.getChildren().addAll(confirm, cancel);
				EventHandler<ActionEvent> confirmHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						String side1 = test.getFrontText();
						String side2 = test.getBackText();

						d.addCard(side1, side2, test.isPic());
						current = d.size() - 1;
						Card h = (Card) d.get(current);
						/*
						 * if(h.isPic()){ setPic(h); } else{ setPrint(h,1); }
						 * next.setEnabled(true); previous.setEnabled(true);
						 */
						cardText.setText(side1);
						deckSize.setText(Integer.toString(d.size()));
						cNum.setText(Integer.toString(current + 1));
						Main.mainStage.show();
						stage.hide();
					}
				};
				// Cancel button
				EventHandler<ActionEvent> cancelHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Main.mainStage.show();
						stage.hide();
					}
				};
				confirm.setOnAction(confirmHandler);
				cancel.setOnAction(cancelHandler);
			}
		};
		
		EventHandler<ActionEvent> favoriteHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (d.size() == 0) {
					return;
				}
				Card h = (Card) d.get(current);
				if (h.isFavorite() == true) {
					return;
				}
				else {
				h.setFavorite(true);
				Region region = ( Region ) cardText.lookup( ".content" );
			    region.setBackground( new Background( new BackgroundFill( Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY ) ) );
				}
			}
		};
		
		EventHandler<ActionEvent> unfavoriteHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (d.size() == 0) {
					return;
				}
				Card h = (Card) d.get(current);
				if (h.isFavorite() == false) {
					return;
				}
				else {
				h.setFavorite(false);
				Region region = ( Region ) cardText.lookup( ".content" );
			    region.setBackground( new Background( new BackgroundFill( Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY ) ) );
				}
			}
		};
		
		EventHandler<ActionEvent> flipHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (d.size() == 0) {
					return;
				}
				Card h = (Card) d.get(current);
				if (cardText.getText().equals(h.getSide1())) {
					cardText.setText(h.getSide2());
				}
				else {
					cardText.setText(h.getSide1());
			    }
			}
		};
		

		EventHandler<ActionEvent> nextHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (d.size() - 1 > current) {
					current++;
					Card h = (Card) d.get(current);
					cardText.setText(h.getSide1());
					if (h.isFavorite() == false) {
						Region region = ( Region ) cardText.lookup( ".content" );
					    region.setBackground( new Background( new BackgroundFill( Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY ) ) );
					}
					if (h.isFavorite() == true) {
						Region region = ( Region ) cardText.lookup( ".content" );
					    region.setBackground( new Background( new BackgroundFill( Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY ) ) );
					}
				}
			}
		};

		EventHandler<ActionEvent> previousHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (current > 0) {
					current--;
					Card h = (Card) d.get(current);
					if (h.isFavorite() == false) {
						Region region = ( Region ) cardText.lookup( ".content" );
					    region.setBackground( new Background( new BackgroundFill( Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY ) ) );
					}
					if (h.isFavorite() == true) {
						Region region = ( Region ) cardText.lookup( ".content" );
					    region.setBackground( new Background( new BackgroundFill( Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY ) ) );
					}
					cardText.setText(h.getSide1());
				}
			}
		};

		EventHandler<ActionEvent> restartHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				score = 0;
				scoreLabel.setText("Score: " + Integer.toString(score));
				scoreQuiz = 0;
				scoreLabelQuiz.setText("Score: " + Integer.toString(scoreQuiz));

				if (d.size() == 0) {
					return;
				}

				Card h = (Card) d.get(0);
				/*
				 * if(h.isPic()) { setPic(h); } else {
				 */
				cardText.setText(h.getSide1());
				// }

				current = 0;
				cNum.setText(Integer.toString(current + 1));
			}
		};

		EventHandler<ActionEvent> editHandler = new EventHandler<ActionEvent>() {
			@Override
			// edit button
			public void handle(ActionEvent event) {
				// if deck.size == 0, send an error
				Card c = d.get(current);
				CardEditor test = new CardEditor(c);
				Stage stage = new Stage();
				stage.setScene(new Scene(test));
				stage.show();
				Main.mainStage.hide();
				Button confirm = new Button("Confirm");
				Button cancel = new Button("Cancel");
				test.botPanel.getChildren().addAll(confirm, cancel);
				EventHandler<ActionEvent> confirmHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						String side1 = test.getFrontText();
						String side2 = test.getBackText();

						d.editCard(c, side1, side2);
						current = d.size() - 1;
						Card h = (Card) d.get(current);
						cardText.setText(h.getSide1());
						/*
						 * if(h.isPic()){ setPic(h); } else{ setPrint(h,1); }
						 * next.setEnabled(true); previous.setEnabled(true);
						 */
						Main.mainStage.show();
						stage.hide();
					}
				};
				//
				EventHandler<ActionEvent> cancelHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Main.mainStage.show();
						stage.hide();
					}
				};
				confirm.setOnAction(confirmHandler);
				cancel.setOnAction(cancelHandler);
			}
		};

		EventHandler<ActionEvent> mainMenuHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Menu test = new Menu();
				Stage stage = new Stage();
				stage.setScene(new Scene(test));
				stage.show();
				Main.mainStage.hide();

				Button cancel = new Button("Normal Mode");
				Button quiz = new Button("Quiz Mode");
				test.botPanel.getChildren().addAll(cancel, quiz);
				EventHandler<ActionEvent> cancelHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						setLeft(westBox);
						setBottom(southBox);
						Main.mainStage.show();
						stage.hide();
					}
				};
				EventHandler<ActionEvent> quizHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						setLeft(westQuiz);
						setBottom(southQuiz);
						Main.mainStage.show();
						stage.hide();
					}
				};
				cancel.setOnAction(cancelHandler);
				quiz.setOnAction(quizHandler);
			}
		};

		EventHandler<ActionEvent> guessHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (d.size() == 0) {
					scoreQuiz = 0;
					scoreLabelQuiz.setText("Score: " + Integer.toString(scoreQuiz));
					return;
				}
				Card h = (Card) d.get(current);
				if (h.getSide2().equals(guessText.getText())) {
					scoreQuiz = scoreQuiz + 1;
					current++;
					if (current == d.size()) {
						current = 0;
					}
					h = (Card) d.get(current);
					/*
					 * if (h.isPic()) { setPic(h); } else {
					 */
					cardText.setText(h.getSide1());
					if (h.isFavorite() == false) {
						Region region = ( Region ) cardText.lookup( ".content" );
					    region.setBackground( new Background( new BackgroundFill( Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY ) ) );
					}
					if (h.isFavorite() == true) {
						Region region = ( Region ) cardText.lookup( ".content" );
					    region.setBackground( new Background( new BackgroundFill( Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY ) ) );
					}
					// }
					cNum.setText(Integer.toString(current + 1));

					if (scoreQuiz > d.size()) {
						scoreQuiz = d.size();
					}
					scoreLabelQuiz.setText("Score: " + Integer.toString(scoreQuiz));
				} else {
					correctQuiz.setVisible(true);
					cardText.setText(h.getSide2());
				}
			}
		};

		EventHandler<ActionEvent> correctQuizHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (d.size() == 0) {
					return;
				}
				scoreQuiz = scoreQuiz + 1;
				current++;
				if (current == d.size()) {
					current = 0;
				}

				Card h = (Card) d.get(current);
				/*
				 * if (h.isPic()) { setPic(h); } else {
				 */
				cardText.setText(h.getSide1());
				if (h.isFavorite() == false) {
					Region region = ( Region ) cardText.lookup( ".content" );
				    region.setBackground( new Background( new BackgroundFill( Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY ) ) );
				}
				if (h.isFavorite() == true) {
					Region region = ( Region ) cardText.lookup( ".content" );
				    region.setBackground( new Background( new BackgroundFill( Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY ) ) );
				}
				// }
				cNum.setText(Integer.toString(current + 1));

				if (scoreQuiz > d.size()) {
					scoreQuiz = d.size();
				}
				scoreLabelQuiz.setText("Score: " + Integer.toString(scoreQuiz));
				correctQuiz.setVisible(false);
			}

		};

		// SELECT DECK BUTTON AND EVERYTHING INSIDE
		EventHandler<ActionEvent> selectDeckHandler = new EventHandler<ActionEvent>() {
			@Override
			// Select button
			public void handle(ActionEvent event) {
				DeckEditor deckEditor = new DeckEditor(decks);
				Stage stage = new Stage();
				stage.setScene(new Scene(deckEditor));
				stage.show();
				Main.mainStage.hide();
				// Confirm Button
				Button select = new Button("Select");
				Button cancel = new Button("Cancel");
				deckEditor.botPanel.getChildren().addAll(select, cancel);
				//
				EventHandler<ActionEvent> cancelHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Main.mainStage.show();
						stage.hide();
					}
				};
				EventHandler<ActionEvent> selectHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						int selection = deckEditor.getDeckList().getSelectionModel().getSelectedIndex();

						if (selection >= 0) {
							setDeck(decks.get(selection));
							if (d.size() == 0) {
								cardText.setText("Deck is Empty!");
								saveNewDeck(decks);
							} else {
								setPrint(d.get(0), 1);
							}
						}
						deckSize.setText(Integer.toString(d.size()));
						deckName.setText(d.getName());
						deckNameQuiz.setText(d.getName());
						setCardNum();

						Main.mainStage.show();
						stage.hide();
					}
				};
				cancel.setOnAction(cancelHandler);
				select.setOnAction(selectHandler);
			}
		};

		EventHandler<ActionEvent> correctHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				score++;
				if (d.size() == 0) {
					score = 0;
					scoreLabel.setText(Integer.toString(score));
					return;
				}
				current++;
				if (current == d.size()) {
					current = 0;
				}
				Card h = (Card) d.get(current);
				/*
				 * if (h.isPic()) { setPic(h); } else {
				 */
				cardText.setText(h.getSide1());
				// }
				cNum.setText(Integer.toString(current + 1));

				if (score > d.size()) {
					score = d.size();
				}
				scoreLabel.setText("Score: " + Integer.toString(score));
			}
		};

		EventHandler<ActionEvent> incorrectHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				score--;
				if (d.size() == 0) {
					score = 0;
					scoreLabel.setText(Integer.toString(score));
					return;
				}
				current++;
				if (current == d.size()) {
					current = 0;
				}
				Card h = (Card) d.get(current);
				/*
				 * if (h.isPic()) { setPic(h); } else {
				 */
				cardText.setText(h.getSide1());
				// }
				cNum.setText(Integer.toString(current + 1));

				if (score < 0) {
					score = 0;
				}
				scoreLabel.setText("Score: " + Integer.toString(score));
			}
		};

		add.setOnAction(addHandler);
		edit.setOnAction(editHandler);
		menu.setOnAction(mainMenuHandler);
		selectDeck.setOnAction(selectDeckHandler);
		flip.setOnAction(flipHandler);
		next.setOnAction(nextHandler);
		previous.setOnAction(previousHandler);
		restart.setOnAction(restartHandler);
		correct.setOnAction(correctHandler);
		guess.setOnAction(guessHandler);
		correctQuiz.setOnAction(correctQuizHandler);
		incorrect.setOnAction(incorrectHandler);
		favorite.setOnAction(favoriteHandler);
		unfavorite.setOnAction(unfavoriteHandler);

	}

	/**
	 * This method will be called with next/previous button if card has a pic
	 *
	 * @param c
	 *            A card
	 */
	// public void setPic(Card c) {
	// cardText.setVisible(false);
	// currentCard.remove(picture);
	// picture = c.getPic();
	// picture.setVisible(true);
	// currentCard.add(picture, BorderLayout.CENTER);
	// thisFrame.getContentPane().validate();
	// thisFrame.getContentPane().repaint();
	// }

	/**
	 * This method will be called with next/previous if card is text
	 *
	 * @param c
	 *            A card
	 * @param side
	 *            the side of the card
	 */
	public void setPrint(Card c, int side) {
		// picture.setVisible(false);
		cardText.setVisible(true);
		// currentCard.remove(picture);
		if (side == 1) {
			cardText.setText(c.getSide1());
		} else if (side == 2) {
			cardText.setText(c.getSide2());
		}
	}

	/**
	 * Sets the current deck
	 *
	 * @param d
	 *            A deck
	 */
	public void setDeck(Deck d) {
		this.d = d;
	}

	/**
	 * Returns a deck
	 *
	 * @return d A deck
	 */
	public Deck getDeck() {
		return this.d;
	}

	/**
	 * Sets the current DeckList
	 * 
	 * @param decks
	 *            A DeckList
	 */
	public void setDeckList(DeckList decks) {
		this.decks = decks;
	}

	/**
	 * Returns the current deckList
	 * 
	 * @return decks a DeckList
	 */
	public DeckList getDeckList() {
		return decks;
	}

	/**
	 * Updates the size of the deck to be the value specified
	 *
	 * @param deckSize
	 *            The new size of the deck
	 */
	public void updateSize(int deckSize) {
		this.deckSize.setText(Integer.toString(deckSize));
	}

	/**
	 * Sets the index of the current card
	 */
	public void setCardNum() {
		if (d.size() < 1) {
			this.cNum.setText("0");
		} else {
			this.cNum.setText("1");
		}
	}

	// Saves new decks
	private void saveNewDeck(DeckList decks) {
		try {
			FileOutputStream fileOut = new FileOutputStream("Deck.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(decks);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
