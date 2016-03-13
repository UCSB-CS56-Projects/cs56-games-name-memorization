# cs56-games-name-memorization


project history
===============
```
YES | mastergberry | ahoang512,theredraid | (andrewberls) A name memorization game
 W14 | jstaahl 5pm | ahoang512,theredraid | (andrewberls) A name memorization game
 W14 | andrewberls | Anthony Hoang and Colin Biafore | Å name memorization game
```




 When the game starts up it loads the deck that was created the last time the program was ran.
 The program allows the user to add a card and edit the front and back of the card. Once
 the card is added, the screen updates to the added card. At the bottom of the window are show
 front/back buttons that allow you see what is on the back or front side of the card. At this
 stage of the project, the user can choose to upload a picture to the front side of the card,
 but not for the back. The future aims of the program is to have the ability to choose what deck
 the cards are being chosen from and to allow the user to create more decks. The ability to keep
 track of which card the user is able to  memorize is another addition that this memorize application
 should be able to do. There would be a button that the user can press to indicate if he got it wrong
 or right and would be based on the honor system, since the program is not designed to let people type
 in their answers.

Documentation:
	The main screen and much of the implementation is inside NameGame.java
	The pop up window to edit/add a card is implemented in CardEditor.java.
	Deck.java is an arraylist class of Cards (which is defined in Card.java)
	Main.java initiates the frame.
	Run by simply calling "ant run" in the root folder.


W16 Final Remarks (to help you get started):

Everything is handled in NameGame for the most part. The rest is done in Main. The best thing you can do to understand the code is to understand NameGame. Almost all of the functional code comes from the actionlisteners (which are tied to the editor classes). The rest of it is tied to the gui for the most part. At the time I'm writing this, the back end should have little to no errors. The GUI has a couple of bugs, most of them come from updating the GUI and from using the cancel button. You could probably get some points from updating the README.


	
Screen Shots:
##Main screen :
![] (http://i.imgur.com/v5kMnoq.png)

##Add/Edit card pop up Screen:
![](http://i.imgur.com/ImPA0L4.png)
