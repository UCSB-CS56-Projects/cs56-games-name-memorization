# W15_lab06 cs56-games-name-memorization Observations

Tamky Ngo, Kevin Lau

================

-What kind of JUnit tests could we make to test if the game works as it should be? I feel like the only way to test a game is actually by playing it.

-Should have a test target in the build.xml if junit is to be developed.

-Some buttons don't do anything, probably because they were in the process of being implemented.

-The game currently +1 point for every correct card and -1 for incorrect. There should be a way for the game to sort correct and incorrect cards so that the player can view incorrect cards, memorize them, and play the game again to get those cards correct.

-If all cards were played, it should say something like "end of deck."
Maybe add a counter.

-Cards could be randomly ordered when playing.

-The clean target in build.xml should delete Decks.ser (?)

-Implement new deck button and make it functional. Also, add switch deck if player wants to play with a different deck.

-When editing a card/deck, the editing window should have retained whatever data is on the card/deck.

-There should be cancel button when editing if the user decides not to create/edit a card/deck. Pop-up should come up if changes were made and user decides to cancel. User can decide whether to save changes or cancel without saving changes.

-If delete a card from deck, reset score and play again.

-edit the button listener, so that delete also triggers restart.

-Listed issue is to implement a menu screen instead of starting game instantly.

-Refactoring at this point doesn't seem necessary, as the classes are clearly in their respective files (subject to change)

 


