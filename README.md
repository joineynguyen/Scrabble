# Scrabble (In Progress)
Welcome to my Scrabble board game project.  
This game will be single player vs computer.  

###### **Motivation:**  
I wanted to make this game because my predictions on the data structures used in this game will be fun to work and solidify with.

###### **Design/Classes**  
  
**Scrabble Board**  

![Scrabble Board Diagram](https://user-images.githubusercontent.com/54327713/94985023-256a2780-0518-11eb-8503-aa42e883ffca.jpg)  
The board will be made as a 15 x 15 array of Cell objects.  
It will show bonuses that the cells have. (e.g., starting center, double word, double letter, triple word, triple letter)
It will connect all of the cells to one another by each of their own set of pointers.

**Scrabble Cell**  

![Cell Adjacency Diagram](https://user-images.githubusercontent.com/54327713/94984360-d40a6a00-0510-11eb-9281-5bce37bad820.jpg)   
(UPDATE 10/3/2020) We are going to get rid of the diagonal cells since Scrabble doesn't allow diagonal plays.  
This diagram will help visualize the tile's adjacency to other tiles on the board.  
X refers to which row and Y is which column.
Notice the formula near the arrow, this is going to be used when connecting all cells when initializing the board.
Each cell has a top, left, right, and bottom pointer.
While connecting the cell with its neighbors, if the formula outputs an X or Y coordinate less than 0 or larger than 14 will cause the direction of that cell's neighbor to be null. For an example, a cell that is in the bottom right corner would have 14 for column and 14 row array index. When we calculate its bottom neighbor, we add 1 to its row index leading to a 15, and this would make the cell's bottom neighbor null because we only have 14 indexes in the board array.

  
**Scrabble Tile**  
    
Tiles are going to be the simplest design as it only holds a character and coresponding points. Characters are used to check if tiles played on board are legit words, and points will be added from all tiles played to form the new word in addition to any newer words completed with the initial word play.  
  
**Scrabble Bag**  
  
0 points:  Blank x2  
1 point:   E ×12, A ×9, I ×9, O ×8, N ×6, R ×6, T ×6, L ×4, S ×4, U ×4  
2 points:  D ×4, G ×3  
3 points:  B ×2, C ×2, M ×2, P ×2  
4 points:  F ×2, H ×2, V ×2, W ×2, Y ×2  
5 points:  K ×1  
8 points:  J ×1, X ×1  
10 points: Q ×1, Z ×1  
  
The bag is going to be in charge of creating tile objects identical to the list above in terms of quantity and property, place it into its Tile array, and then shuffle the array.  
The game will remove tiles from this bag's Tile array and move it to the player and computer's rack array to use.
  
**Player**  
  
The player will have a Tile array 'rack' for potential word play and an integer variable 'score'.  
They are able to shuffle the tiles in their rack as well as organize it so that tiles will hug the left side of the rack. These actions will be available on the GUI as a 'shuffle' button. The shuffle button will not do anything if the player's rack is empty or includes only one tile.  
The player's button 'undo' will obviously undo their tile placement on the board and bring it back onto their rack (both array and GUI).  
The button 'pass' will pass their turn and let the computer go if they have trouble making out words or for any strategic reason.  
Finally, the button 'submit' will check the player's tile placement on the board if it does make a word. If a legit word is played, then the score is calculated and computer's turn. If not, nothing will happen.  
  
**Computer**  
  
The computer class will inherit from the player class plus user automation methods.  
  
**Main/GUI**  
  
The GUI (currently called main) class deals with initializing all GUI panels, buttons, etc. After, it initializes the classes needed to run the game (e.g., Bag, Board, Cell, Player, Tile, Engine, Computer). For example, the 15 x 15 cell arraay located from the board object represents the scrabble board. Then, it connects these objects to the GUI, so when you press a button, an action is performed on the application and the object variables change. When the player clicks on the tile button in their rack panel, a variable "tileSelected" in the engine object copies the chosen element from the player object's rack array. After the player choses a tile from their rack, they may press any button on the scrabble board to transfer the "tileSelected" tile variable for the cell object located in the 15 x 15 cell matrix to hold as its own tile variable "Tile". This is only one action that the player may perform; please take a look at the GUI java file for other user actions as they are commented. 
  
**Engine**  
  
The engine class will mostly contain data structures and algorithms to verify user moves along with player, computer, and board objects to update scores/data. Are the tiles played all connected vertically or horizontally? Do the tiles form legit words? Do other words that the tiles we played connect to form legit words? What is the score of the word and/or connected words to the initial word play? Everything is calculated in this class. Tries are used to search up a dictionary to declare whether the word(s) played are legit. Stacks are used to keep up with the most recent buttons, tiles, and cells are played. When the player presses the 'undo' button, we pop any relevent stack and have undone an action. Hashmaps are used to locate which button on the scrabble board belongs to which cell in the cell matrix.  
  
**Dictionary**  
  
The dictionary class will store words able to play in scrabble and check if words played are in the dictionary.
This class will use the Trie and FileReader class.  
  
**Trie**  
  
The trie class will simply have the data structure of a trie along with inser and search methods.  
  
**FileReader**  
  
The FileReader class will extract each word per line in the official Scrabble word file called [Collins Scrabble Words (2019).txt](https://drive.google.com/file/d/1oGDf1wjWp5RF_X9C7HoedhIWMh5uJs8s/view).  




