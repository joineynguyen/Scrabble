package scrabble;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;

//Mainly used to verify moves on board and score them
public class Engine 
{
	Player player;
	Board board;
	Dictionary dict;
	
	//keep chronological order of recent tile/cell/cell button played
	Stack<Tile> recentlyPlayedTileStack = new Stack<>();
	Stack<Cell> recentlyPlayedCellStack = new Stack<>(); 
	Stack<JButton> recentlyPlayedCellButtonStack = new Stack<>();
	ArrayList<Cell> occupiedCells = new ArrayList<Cell>(); //helps determine if recent move is connected to past moves
	
	Tile rackTileSelected; //when players clicks on a tile button in their rack to play on board, this variable stores the tile to copy onto board 
	boolean initialMove = true;
	
	public Engine(Player player, Board board)
	{
		this.player = player;
		
		this.board = board;
		
		dict = new Dictionary();
		
		rackTileSelected = null;
		player.setScore(0);
		
	}
	
	/*Checks board if center cell is occupied, recent move is all connected either vertically/horizontally and is connected to a pre-existing word, 
	and check if all words on board are legal words. Return true if all cases are passed
	*/
	public boolean checkBoard()
	{
		//special case to beat the initial move of only 1 tile
		if(initialMove == true && recentlyPlayedCellStack.size() == 1)
		{
			return false;
		}
		
		//if player presses submit but hasn't laid at least 1 tile on board
		if(recentlyPlayedCellStack.size () == 0)
		{
			return false;
		}
		//If center cell is filled as rule intend
		if(board.cellMatrix[7][7].getTile() == null)
		{
			return false;
		}
		//If move is not connected in horizontal or vertical move if more than 1 tile is placed
		if(checkIfMoveIsConnectedHorizontally() == false && checkIfMoveIsConnectedVertically() == false)
		{
			return false;
		}
		//If move played is connected to previous moves
		if(checkIfMoveIsConnectedToPastMoves() == false)
		{
			return false;
		}
		//If we find any words on the board not found in the dictionary
		if(verifyHorizontalWords() == false || verifyVerticalWords() == false)
		{
			return false;
		}
		//If move is connected only horizontally, we score the horizontal word and connecting vertical words
		if(checkIfMoveIsConnectedHorizontally() == true && checkIfMoveIsConnectedVertically() == false)
		{
			Cell leftMost = getLeftMostOccupiedCellFrom(recentlyPlayedCellStack.peek());
			player.addScore(scoreHorizontalWord(leftMost) + scoreVerticalWordsConnectedToHorizontalMove());
		}
		//If move is connected only vertically, we score the vertical word and connecting horizontal words
		else if(checkIfMoveIsConnectedVertically() == true && checkIfMoveIsConnectedHorizontally() == false)
		{
			Cell topMost = getTopMostOccupiedCellFrom(recentlyPlayedCellStack.peek());
			player.addScore(scoreVerticalWord(topMost) + scoreHorizontalWordsConnectedToVerticalMove());
		}
		else if(checkIfMoveIsConnectedVertically() == true && checkIfMoveIsConnectedHorizontally() == true)
		{
			Cell leftMost = getLeftMostOccupiedCellFrom(recentlyPlayedCellStack.peek());
			Cell topMost = getTopMostOccupiedCellFrom(recentlyPlayedCellStack.peek());
			player.addScore(scoreHorizontalWord(leftMost) + scoreVerticalWord(topMost));
		}
		
		transferRecentlyPlayedCellStackToOccupiedCellsArrayList(); //prepare for next move
		clearStacks(); //from recently played tile and cell button stacks
		initialMove = false;
		return true;
	}
	
	//check only cell played of vertical move to check if any horizontal words are connected to it for scoring
	public int scoreHorizontalWordsConnectedToVerticalMove()
	{
		Cell[] cellArray = copyRecentlyPlayedCellStackToArray();
		int score = 0;
		
		for(int i = 0; i < cellArray.length; i++)
		{
			//Get left most occupied cell of potential horizontal word connect to vertical move
			Cell leftMost = getLeftMostOccupiedCellFrom(cellArray[i]);
			score = score + scoreHorizontalWord(leftMost);
		}
		return score;
	}
	
	//iterate through cells played of horizontal move to score connecting vertical words
	public int scoreVerticalWordsConnectedToHorizontalMove()
	{
		Cell[] cellArray = copyRecentlyPlayedCellStackToArray();
		int score = 0;
		
		for(int i = 0; i < cellArray.length; i++)
		{
			//Gets top most occupied cell of potential vertical words connected to played tiles of horizontal move
			Cell topMost = getTopMostOccupiedCellFrom(cellArray[i]);	
			score = score + scoreVerticalWord(topMost);
		}
		return score;
	}
	
	//traverses and scores from left most occupied cell of horizontal words to the right most
	public int scoreHorizontalWord(Cell leftMost)
	{
		Cell current = leftMost;
		int wordMultiply = 1;
		int wordScore = 0;
		boolean hasOneTile = true; //To not score non connected words. (When scanning for horizontally connected words of vertical words, there will be this case.)
		
		while(current.getTile() != null)
		{
			wordMultiply = wordMultiply + getWordBonus(current);
			wordScore = wordScore + (current.getTile().getPoints() * getLetterBonus(current));
		
			if(current.getRight() != null)
			{
				if(current.getRight().getTile() != null)
				{
					current = current.getRight();
					hasOneTile = false;
				}
				else
				{
					break;
				}
			}
			else
			{
				break;
			}
		}
		
		if(hasOneTile == true)
		{
			return 0;
		}
		
		return wordScore * wordMultiply;
	}
	
	//traverses and scores from top most occupied cell of vertical word to most bottom 
	public int scoreVerticalWord(Cell topMost)
	{
		Cell current = topMost;
		int wordMultiply = 1;
		int wordScore = 0;
		boolean hasOneTile = true;
		
		while(current.getTile() != null)
		{
			wordMultiply = wordMultiply + getWordBonus(current);
			wordScore = wordScore + (current.getTile().getPoints() * getLetterBonus(current));
		
			if(current.getBottom() != null)
			{
				if(current.getBottom().getTile() != null)
				{
					current = current.getBottom();
					hasOneTile = false;
				}
				else
				{
					break;
				}
			}
			else
			{
				break;
			}
		}
		
		if(hasOneTile == true)
		{
			return 0;
		}
		
		return wordScore * wordMultiply;
	}
	
	//In scoring method, we will update the total multiplication word bonus
	public int getWordBonus(Cell cell)
	{
		if(occupiedCells.contains(cell))
		{
			return 0;
		}
		else if(cell.getBonus() == "TW")
		{
			return 3;
		}
		else if(cell.getBonus() == "DW")
		{
			return 2;
		}
		
		return 0;
	}
	
	//In scoring method, we will update only letter on current cell with letter bonus
	public int getLetterBonus(Cell cell)
	{
		if(occupiedCells.contains(cell))
		{
			return 1;
		}
		else if(cell.getBonus() == "TL")
		{
			return 3;
		}
		else if(cell.getBonus() == "DL")
		{
			return 2;
		}
		
		return 1;
	}
	
	public void clearStacks()
	{
		recentlyPlayedTileStack.clear();
		recentlyPlayedCellButtonStack.clear();
	}
	
	public void transferRecentlyPlayedCellStackToOccupiedCellsArrayList()
	{
		while(!recentlyPlayedCellStack.isEmpty())
		{
			occupiedCells.add(recentlyPlayedCellStack.pop());
		}
	}
	
	public Cell[] copyRecentlyPlayedCellStackToArray()
	{
		Cell[] cellArray = new Cell[recentlyPlayedCellStack.size()]; 
		recentlyPlayedCellStack.toArray(cellArray);
		
		return cellArray;
	}
	
	public boolean checkIfMoveIsConnectedToPastMoves()
	{
		//No past moves to be connected to
		if(occupiedCells.size() == 0)
		{
			return true;
		}
		
		Cell[] recentlyPlayedCellArray = convertRecentlyPlayedCellStackToArray();
	
		for(Cell currentCell : recentlyPlayedCellArray)
		{
			//FREEZES HERE
			if(traverseLeftUntilPastMove(currentCell) == true || traverseRightUntilPastMove(currentCell) == true || traverseUpUntilPastMove(currentCell) == true || traverseDownUntilPastMove(currentCell) == true )
			{
				return true;
			}
		}
		
		return false;
	}
	
	//If we verified the move as horizontal, we can traverse left of recently played cell to look for left-most occupied cell in order to build the word played to score it
	public Cell getLeftMostOccupiedCellFrom(Cell cell)
	{
		Cell current = cell;
		
		while(current.getLeft() != null)
		{
			if(current.getLeft().getTile() != null)
			{
				current = current.getLeft();
			}
			else
			{
				break;
			}
		}
		
		return current;
	}
	
	public Cell getTopMostOccupiedCellFrom(Cell cell)
	{
		Cell current = cell;
		
		while(current.getTop() != null)
		{
			if(current.getTop().getTile() != null)
			{
				current = current.getTop();
			}
			else
			{
				break;
			}
		}
		
		return current;
	}
	
	//traversing left of cell to see if connected to past moves
	public boolean traverseLeftUntilPastMove(Cell cell)
	{
		Cell current = cell;
		
		while(current.getLeft() != null)
		{
			if(current.getLeft().getTile() != null)
			{
				current = current.getLeft();
				
				if(occupiedCells.contains(current))
				{
					return true;
				}
			}
			else
			{
				break;
			}
		}
		return false;
	}
	
	//traversing right of cell to see if connected to past moves
	public boolean traverseRightUntilPastMove(Cell cell)
	{
		Cell current = cell;
		
		while(current.getRight() != null)
		{
			if(current.getRight().getTile() != null)
			{
				current = current.getRight();
				
				if(occupiedCells.contains(current))
				{
					return true;
				}
			}
			else
			{
				break;
			}
		}
		return false;
	}
	
	//traversing up of cell to see if connected to past moves
	public boolean traverseUpUntilPastMove(Cell cell)
	{
		Cell current = cell;
		
		while(current.getTop() != null)
		{
			if(current.getTop().getTile() != null)
			{
				current = current.getTop();
				
				if(occupiedCells.contains(current))
				{
					return true;
				}
			}
			else
			{
				break;
			}
		}
		return false;
	}
	
	//traversing down of cell to see if connected to past moves
	public boolean traverseDownUntilPastMove(Cell cell)
	{
		Cell current = cell;
		
		while(current.getBottom() != null)
		{
			if(current.getBottom().getTile() != null)
			{
				current = current.getBottom();
				
				if(occupiedCells.contains(current))
				{
					return true;
				}
			}
			else
			{
				break;
			}
		}
		return false;
	}
	
	//Checks if move is only horizontally connected by traversing left/right while counting number of matched played cells
	public boolean checkIfMoveIsConnectedHorizontally()
	{
		Cell currentCell = recentlyPlayedCellStack.peek();
		
		while(currentCell.getLeft() != null)
		{
			if(currentCell.getLeft().getTile() != null)
			{
				currentCell = currentCell.getLeft();
			}
			else
			{
				break;
			}
		}
		
		Cell[] recentlyPlayedCellArray = convertRecentlyPlayedCellStackToArray();
		int count = 0;
		
		if(isCellRecentlyPlayed(currentCell, recentlyPlayedCellArray) == true)
		{
			count++;
		}
		
		while(currentCell.getRight() != null)
		{
			if(currentCell.getRight().getTile() != null)
			{
				currentCell = currentCell.getRight();
				
				if(isCellRecentlyPlayed(currentCell, recentlyPlayedCellArray) == true)
				{
					count++;
				}
			}
			else
			{
				break;
			}
		}
		
		if(count == recentlyPlayedCellArray.length)
		{
			return true;
		}
		
		return false;
	}
	
	//Checks if move is only vertically connected by traversing up/down while counting number of matched played cells
	public boolean checkIfMoveIsConnectedVertically()
	{
		Cell currentCell = recentlyPlayedCellStack.peek();
		
		while(currentCell.getTop() != null)
		{
			if(currentCell.getTop().getTile() != null)
			{
				currentCell = currentCell.getTop();
			}
			else
			{
				break;
			}
		}
		
		Cell[] recentlyPlayedCellArray = convertRecentlyPlayedCellStackToArray();
		int count = 0;
		
		if(isCellRecentlyPlayed(currentCell, recentlyPlayedCellArray) == true)
		{
			count++;
		}
		
		while(currentCell.getBottom() != null)
		{
			if(currentCell.getBottom().getTile() != null)
			{
				currentCell = currentCell.getBottom();
				
				if(isCellRecentlyPlayed(currentCell, recentlyPlayedCellArray) == true)
				{
					count++;
				}
			}
			else
			{
				break;
			}
		}
		
		if(count == recentlyPlayedCellArray.length)
		{
			return true;
		}
		
		return false;
	}
	
	//Only check horizontally words on board if legal, despite being legally connected or not
	public boolean verifyHorizontalWords()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				Cell currentCell = board.cellMatrix[i][j];
				
				if(currentCell.getTile() != null)
				{
					sb.append(currentCell.getTile().getLetter());
				}
				else if((currentCell.getTile() == null && sb.length() > 1) || (j == 14 && sb.length() > 1))
				{
					String word = sb.toString();
					
					if(dict.verifyWord(word) == false)
					{
						System.out.println("Failed: " + word);
						sb.setLength(0);
						return false;
					}
					else
					{
						System.out.println("Passed: " + word);
						sb.setLength(0);
					}
				}
				else if((currentCell.getTile() == null && sb.length() == 1) || (j == 14 && sb.length() == 1))
				{
					sb.setLength(0);
				}
			}
			
			sb.setLength(0);
		}
		return true;
	}
	
	//Only check vertical words on board if legal, despite being legally connected or not
	public boolean verifyVerticalWords()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				Cell currentCell = board.cellMatrix[j][i];
				
				if(currentCell.getTile()!= null)
				{
					sb.append(currentCell.getTile().getLetter());
				}
				else if((currentCell.getTile() == null && sb.length() > 1) || (j == 14 && sb.length() > 1))
				{
					String word = sb.toString();
					
					if(dict.verifyWord(word) == false)
					{
						System.out.println("Failed: " + word);
						sb.setLength(0);
						return false;
					}
					else
					{
						System.out.println("Passed: " + word);
						sb.setLength(0);
					}
				}
				else if((currentCell.getTile() == null && sb.length() == 1) || (j == 14 && sb.length() == 1))
				{
					sb.setLength(0);
				}
			}
			
			sb.setLength(0);
		}
		return true;
	}
	
	public Cell[] convertRecentlyPlayedCellStackToArray()
	{
		int size = recentlyPlayedCellStack.size();
		Cell[] cellStack = new Cell[size];
		recentlyPlayedCellStack.toArray(cellStack);
		return cellStack;
	}
	
	public boolean isCellRecentlyPlayed(Cell currentCell, Cell[] cellArray)
	{
		if(currentCell == null)
		{
			return false;
		}
		
		for(Cell i : cellArray)
		{
			if(i == currentCell)
			{
				return true;
			}
		}
		
		return false;
	}
}
	
	
