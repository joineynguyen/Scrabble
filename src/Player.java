package scrabble;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Player has rack array for tiles to be played and score integer
public class Player
{
    private Tile[] rack; //tiles they have that are ready to be played
    private int score;
    
    public Player()
    {
    	rack = new Tile[7];
    	score = 0;
    }
    
    public String getScore()
    {
    	return String.valueOf(score);
    }
    
    public void setScore(int num)
    {
    	score = num;
    }
    
    public void addScore(int num)
    {
    	score = score + num;
    }
    
    public void shuffleRack()
    {
    	List<Tile> list = Arrays.asList(rack);
    	Collections.shuffle(list);
    	list.toArray(rack);
    }
    
    public void organizeRack() //pushes tiles in rack to left side, leaving no spaces in between tiles. Helps rack GUI look cleaner
    {
    	for(int i = 0; i < 6; i ++)
    	{
    		if(rack[i] == null)
    		{
    			for(int j = i + 1; j < 7; j++)
    			{
    				if(rack[j] != null)
    				{
    					rack[i] = rack[j];
    					rack[j] = null;
    					break;
    				}
    			}
    		}
    	}
    }
    
    public Tile getFromRackAt(int index)
    {
    	return rack[index];
    }
    
    public Tile getAndRemoveFromRackAt(int index)
    {
    	Tile temp = rack[index];
    	rack[index] = null;
    	return temp;
    }
    
    public int addTileToRack(Tile tile) //adds tile to next free index in rack array then returns which index, returns -1 if did not finx a free index to insert tile
    {
    	for(int i = 0; i < rack.length; i++)
    	{
    		if(rack[i] == null)
    		{
    			rack[i] = tile;
    			return i;
    		}
    	}
    	
    	return -1;
    }
    
    //helper function for undo button
    public void addTileToRackAt(Tile tile, int index)
    {
    	if(rack[index] == null)
    	{
    		rack[index] = tile;
    
    	}
    }
 
    public boolean rackIsFull()
    {
    	int count = 0;
    	
    	for(int i = 0; i < rack.length; i++)
    	{
    		if(rack[i] != null)
    		{
    			count++;
    		}
    	}
    	
    	return count == 7;
    }
    
    public int getRackSize()
    {
    	int count = 0;
    	
    	for(int i = 0; i < rack.length; i++)
    	{
    		if(rack[i] != null)
    		{
    			count++;
    		}
    	}
    	
    	return count;
    }

    public Tile[] getRack()
    {
    	return this.rack;
    }
}
