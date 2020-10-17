# Scrabble (In Progress)
Welcome to my Scrabble board game project.  
This game will be single player vs computer.  

###### **Motivation:**  
I wanted to make this game because my predictions on the data structures used in this game will be fun to work and solidify with.

###### **Design**  
  
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



