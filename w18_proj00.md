a) Description: Name Memorization is a web-application that allows users to enter information on two sided note cards,
memorize and quiz themselves with these note cards.
b) 
-As a user, I am able to enter text on both sides of a note card to memorize a definition of a word
-As a user, I am able to enter pictures on either side of a note card to memorize a person/place' name
-As a user, I am able to enter quiz mode to quiz myself and keep score
-As a user, I am able edit/delete existing note cards so that I do not have to add new ones
-As a user, I am able to create different decks and choose them for different topics/subjects 
c) The software runs quite smoothly. When we enter ant run in the command line, a popup appears in the left corner of the window that shows a little bit of instructions, and then the application pops up.
d)
-As a user, I am able see which cards I have the most problem with while I quiz myself since the application keeps record of every quiz
-As a user, I am able to see all cards in a deck in an expanded view
-As a user, I am able to play a game as a kind of quiz to keep myself entertained
-As a user, I am able to select important cards (favorites) in a deck 
e) The current README.md file contains sufficient information for us to start the program, know where things are, and what to do next. However, I would like information on the difficulty of fixing existing codes versus that of adding new code.
f) The current build.xml is a clean ant file with description for every targets.
g) Currently, the issues add up to 900 points. They do have a very clear explanation of how the points can be earned.
h) 
-https://github.com/ucsb-cs56-projects/cs56-games-name-memorization/issues/37
-https://github.com/ucsb-cs56-projects/cs56-games-name-memorization/issues/38
i) The code is organized very nicely. Different screens are organized as different classes in different files with their own test files, very easy to grasp. It is not too obvious what classes use other classes until you read into the code, maybe a comment line as to what class is included would be nice.
For anyone new to the code, I would describe what classes are contained in each files. Card.java and CardEditor.java is the most basic and structure building for the application. Then you have Deck.java and DeckEditor.java that contains the CardEditor class, Decklist.Java and Main.java contain the Deck class. This way, to make changes to any class, you can go from the most basic structure first and then into what you are trying to change. Each method in a class is described, so it's easy to make changes to any methods in a class. NameGame.java is the file that contains a lot of other classes because it needs them all to construct a quiz for the application. This file has more than a thousand line of codes so it is a little intimidating to attempt to fix what is already in there.
The main.java file is the base of the UI of the application. 
j) There are JUnit tests for every class that needs a test. Currently, aside from passing these tests, there should not be any more tests needed for this application.
