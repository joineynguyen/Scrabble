package scrabble;

public class Tile
{
    private String letter;
    private int points;
    
    public Tile(String letter, int points)
    {
        this.letter = letter;
        this.points = points;
    }
    
    public String getLetter()
    {
        return letter;
    }
    
    public int getPoints()
    {
        return points;
    }
}