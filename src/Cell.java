package scrabble;

/*
Any code commented out is due to the fact that Scrabble does not allow diagonal plays
*/
public class Cell
{
    private Tile tile;
    private Cell top, left, right, bottom; //adjacent cells
    //private Cell topLeft, topRight, bottomLeft, bottomRight;
    /*
    Some cells have bonus points when tiles are placed on them
    (e.g.: DL = double letter points, TL = triple letter points, 
    DW = double word points, TW = triple word points
    */
    private String bonus; 
    
    public Cell()
    {
        tile = null;
        top = left = right = bottom = null;
        //topLeft = topRight = bottomLeft = bottomRight = null;
    }
    
   public void setTile(Tile tile)
   {
       this.tile = tile;
   }
    
   public Tile getTile()
   {
       return this.tile;
   }
   
   public void setBonus(String bonus)
   {
       this.bonus = bonus;
   }
   
   public String getBonus()
   {
       return this.bonus;
   }
    
   public void setTop(Cell cell)
   {
       this.top = cell;
   }
   
   public void setLeft(Cell cell)
   {
       this.left = cell;
   }
   
   public void setRight(Cell cell)
   {
       this.right = cell;
   }
   
   public void setBottom(Cell cell)
   {
       this.bottom = cell;
   }
   
   /*
   public void setTopLeft(Cell cell)
   {
       this.topLeft = cell;
   }
   
   public void setTopRight(Cell cell)
   {
       this.topRight = cell;
   }
   
   public void setBottomLeft(Cell cell)
   {
       this.bottomLeft = cell;
   }
   
   public void setBottomRight(Cell cell)
   {
       this.bottomRight = cell;
   }
   */
   
   public Cell getTop()
   {
       return this.top;
   }
   
   public Cell getLeft()
   {
       return this.left;
   }
   
   public Cell getRight()
   {
       return this.right;
   }
    
   public Cell getBottom()
   {
       return this.bottom;
   }
   
   /*
   public Cell getTopLeft()
   {
       return this.topLeft;
   }
   
   public Cell getTopRight()
   {
       return this.topRight;
   }
   
   public Cell getBottomLeft()
   {
       return this.bottomLeft;
   }
   
   public Cell getBottomRight()
   {
       return this.bottomRight;
   }
   */
}
