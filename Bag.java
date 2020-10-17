package scrabble;

import java.util.ArrayList;
import java.util.Collections;

//Bag object to create and hold specific number of alphabet and blank tiles.
public class Bag
{
    private ArrayList<Tile> tiles;
    
    public Bag()
    {
        tiles = new ArrayList<>();
    	populateBag();
        shuffleBag();
    }
    
    public Tile getNextTile()
    {
    	return tiles.remove(0);
    }
    
    public boolean bagIsEmpty()
    {
    	return tiles.size() < 1;
    }
    /*
    0 points: Blank x2
    1 point: E ×12, A ×9, I ×9, O ×8, N ×6, R ×6, T ×6, L ×4, S ×4, U ×4
    2 points: D ×4, G ×3
    3 points: B ×2, C ×2, M ×2, P ×2
    4 points: F ×2, H ×2, V ×2, W ×2, Y ×2
    5 points: K ×1
    8 points: J ×1, X ×1
    10 points: Q ×1, Z ×1
    */
    public void populateBag()
    {
        createTile("-", 0, 2);
        createTile("E", 1, 12);
        createTile("A", 1, 9);
        createTile("I", 1, 9);
        createTile("O", 1, 8);
        createTile("N", 1, 6);
        createTile("R", 1, 6);
        createTile("T", 1, 6);
        createTile("L", 1, 4);
        createTile("S", 1, 4);
        createTile("U", 1, 4);
        createTile("D", 2, 4);
        createTile("G", 2, 3);
        createTile("B", 3, 2);
        createTile("C", 3, 2);
        createTile("M", 3, 2);
        createTile("P", 3, 2);
        createTile("F", 4, 2);
        createTile("H", 4, 2);
        createTile("V", 4, 2);
        createTile("W", 4, 2);
        createTile("Y", 4, 2);
        createTile("K", 5, 1);
        createTile("J", 8, 1);
        createTile("X", 8, 1);
        createTile("Q", 10, 1);
        createTile("Z", 10, 1);
    }
    
    public void createTile(String letter, int points, int count)
    {
        for(int i = 0; i < count; i ++)
        {
            tiles.add(new Tile(letter, points));
        }
    }
    
    public void shuffleBag()
    {
        Collections.shuffle(tiles);
    }
    
    
}
