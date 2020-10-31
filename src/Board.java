package scrabble;

//Create and connect cells to imitate a scrabble board
public class Board
{
    Cell[][] cellMatrix;
    
    public Board()
    {
        createBoard();
        connectCells();
        createBonus();
    }
    
    //creates board with 15x15 array of cells
    public void createBoard()
    {
        cellMatrix = new Cell[15][15];
        
        for(int i = 0; i < 15; i ++)
        {
            for(int j = 0; j < 15; j++)
            {
                
                cellMatrix[i][j] = new Cell();
            }
        }
    }
    
    public void connectCells()
    {
        for(int i = 0; i < 15; i ++)
        {
            for(int j = 0; j < 15; j++)
            {
                connectTop(i, j, cellMatrix);
                connectLeft(i, j, cellMatrix);
                connectRight(i, j, cellMatrix);
                connectBottom(i, j, cellMatrix);
                /*
                connectTopLeft(i, j, cellMatrix);
                connectTopRight(i, j, cellMatrix);
                connectBottomLeft(i, j, cellMatrix);
                connectBottomRight(i, j, cellMatrix);
                */
            }
        }
    }
   
    public void createBonus()
    {
    	//starting center
    	cellMatrix[7][7].setBonus("X");
    	//triple word
    	cellMatrix[0][0].setBonus("TW");
    	cellMatrix[7][0].setBonus("TW");
    	cellMatrix[14][0].setBonus("TW");
    	cellMatrix[0][7].setBonus("TW");
    	cellMatrix[0][14].setBonus("TW");
    	cellMatrix[7][14].setBonus("TW");
    	cellMatrix[14][7].setBonus("TW");
    	cellMatrix[14][14].setBonus("TW");
        //triple letter
    	cellMatrix[5][1].setBonus("TL");
    	cellMatrix[9][1].setBonus("TL");
    	cellMatrix[1][5].setBonus("TL");
    	cellMatrix[5][5].setBonus("TL");
    	cellMatrix[9][5].setBonus("TL");
    	cellMatrix[13][5].setBonus("TL");
    	cellMatrix[1][9].setBonus("TL");
    	cellMatrix[5][9].setBonus("TL");
    	cellMatrix[9][9].setBonus("TL");
    	cellMatrix[13][9].setBonus("TL");
    	cellMatrix[5][13].setBonus("TL");
    	cellMatrix[9][13].setBonus("TL");
        //double word
    	cellMatrix[1][1].setBonus("DW");
    	cellMatrix[2][2].setBonus("DW");
    	cellMatrix[3][3].setBonus("DW");
    	cellMatrix[4][4].setBonus("DW");
    	cellMatrix[4][10].setBonus("DW");
    	cellMatrix[3][11].setBonus("DW");
    	cellMatrix[2][12].setBonus("DW");
    	cellMatrix[1][13].setBonus("DW");
    	cellMatrix[13][1].setBonus("DW");
    	cellMatrix[12][2].setBonus("DW");
    	cellMatrix[11][3].setBonus("DW");
    	cellMatrix[10][4].setBonus("DW");
    	cellMatrix[10][10].setBonus("DW");
    	cellMatrix[11][11].setBonus("DW");
    	cellMatrix[12][12].setBonus("DW");
    	cellMatrix[13][13].setBonus("DW");
        //double letters
    	cellMatrix[3][0].setBonus("DL");
    	cellMatrix[11][0].setBonus("DL");
    	cellMatrix[0][3].setBonus("DL");
    	cellMatrix[6][2].setBonus("DL");
    	cellMatrix[7][3].setBonus("DL");
    	cellMatrix[8][2].setBonus("DL");
    	cellMatrix[14][3].setBonus("DL");
    	cellMatrix[2][6].setBonus("DL");
    	cellMatrix[6][6].setBonus("DL");
    	cellMatrix[8][6].setBonus("DL");
    	cellMatrix[12][6].setBonus("DL");
    	cellMatrix[3][7].setBonus("DL");
    	cellMatrix[11][7].setBonus("DL");
    	cellMatrix[2][8].setBonus("DL");
    	cellMatrix[6][8].setBonus("DL");
    	cellMatrix[8][8].setBonus("DL");
    	cellMatrix[12][8].setBonus("DL");
    	cellMatrix[0][11].setBonus("DL");
    	cellMatrix[7][11].setBonus("DL");
    	cellMatrix[14][11].setBonus("DL");
    	cellMatrix[6][12].setBonus("DL");
    	cellMatrix[8][12].setBonus("DL");
    	cellMatrix[3][14].setBonus("DL");
    	cellMatrix[6][12].setBonus("DL");
    	cellMatrix[11][14].setBonus("DL");
     
        
    }
    
    public void connectTop(int row, int col, Cell[][] cellMatrix)
    {
        if((row - 1) < 0)
        {
        	cellMatrix[row][col].setTop(null);
            return;
        }
        
        int newRow = row - 1;
        
        cellMatrix[row][col].setTop(cellMatrix[newRow][col]);
    }
    
    public void connectBottom(int row, int col, Cell[][] cellMatrix)
    {
        if((row + 1) > 14)
        {
        	cellMatrix[row][col].setBottom(null);
            return;
        }
        
        int newRow = row + 1;
        
        cellMatrix[row][col].setBottom(cellMatrix[newRow][col]);
    }
    
    public void connectLeft(int row, int col, Cell[][] cellMatrix)
    {
        if((col - 1) < 0)
        {
        	cellMatrix[row][col].setLeft(null);
            return;
        }
        
        int newCol = col - 1;
        cellMatrix[row][col].setLeft(cellMatrix[row][newCol]);
    }
    
    public void connectRight(int row, int col, Cell[][] cellMatrix)
    {
        if((col+ 1) > 14)
        {
        	cellMatrix[row][col].setRight(null);
            return;
        }
        
        int newCol = col + 1;
        cellMatrix[row][col].setRight(cellMatrix[row][newCol]);
    }
   
}
