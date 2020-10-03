# Scrabble (In Progress)
Welcome to my Scrabble board game project.  
This game will be single player vs computer.  

###### **Motivation:**  
I wanted to make this game because my predictions on the data structures used in this game will be fun to work and solidify with.

###### **Design**  
  
**Scrabble Board**  

![Scrabble Board Diagram](https://user-images.githubusercontent.com/54327713/94985023-256a2780-0518-11eb-8503-aa42e883ffca.jpg)  
The board will be made as a 15 x 15 array of the object Cell.  
Cell objects contain a string called bonus and will be assigned accordingly. (e.g.: board[0]0].string = 3W)
  
**Scrabble Cell**  

![Cell Adjacency Diagram](https://user-images.githubusercontent.com/54327713/94984360-d40a6a00-0510-11eb-9281-5bce37bad820.jpg)   
This diagram will help visualize the tile's adjacency of each tile on the board.  
Notice the formula near the arrow, this is going to be used when connecting all cells when initializing the board.
While connecting the cell with its neighbors, if the formula outputs an x or y coordinate less than 0 or larger than 14 will cause the direction of that cells neighbor to be null.  
**Scrabble Tile**  

0 points: Blank x2
1 point: E ×12, A ×9, I ×9, O ×8, N ×6, R ×6, T ×6, L ×4, S ×4, U ×4
2 points: D ×4, G ×3
3 points: B ×2, C ×2, M ×2, P ×2
4 points: F ×2, H ×2, V ×2, W ×2, Y ×2
5 points: K ×1
8 points: J ×1, X ×1
10 points: Q ×1, Z ×1


