

/**
 * Creates an array with cells, sets mines and count values of cells
 * @author Anastasia Saverchenko
 */
public class MinesweeperGame 
{

	// an integer representing the size of a game board
	private int gridSize;
	// declares an array to contain all board cells
    private MinesweeperCell[][] cells;
    // holds number of mines
    private int minesNumber;

    
    /**
     * Constructor
     * @param gridSize size of a game board
     */
	public MinesweeperGame (int gridSize)	
	{
		//instance properties 
		this.gridSize = gridSize;
        minesNumber = 0;
        //initialize the array to contain MinesweeperCell elements
        cells = new MinesweeperCell[gridSize][gridSize];
		// initialize the contents of the array
        for (int i = 0; i < gridSize; i++)
        	for (int j = 0; j < gridSize; j++)
        	{ 
        		{
        			cells[i][j] = new MinesweeperCell(i,j);      		
        		}
        	}
	}
	
	/**
	 * Returns cells of the boards
     * @return all cells
	 */
	public MinesweeperCell[][] getCells() 
	{
		return cells;
	}
	
	
	/**
	 * Returns number of mines
	 * @return number of mines
	 */
	public int getMinesNumber()
	{
		return minesNumber;
	}
	
		
	/**
	 * Set mines and count values of all cells
	 * (value for a mine: -1)
	 **/
    public void setMines () 
    {    
    	//holds number of mines no more than 12.5% of our cells
    	minesNumber=(int) ((gridSize*gridSize*12.5)/100);
    	
    	//places random mines
    	for (int i=0; i<minesNumber; i++)
    	{
    		int iNumber = (int) Math.floor (Math.random()*gridSize);
    		int jNumber = (int) Math.floor (Math.random()*gridSize);
    		
    		//if cell already contains a mine adds an additional loop
    		if (cells[iNumber][jNumber].getValue()==-1)	i--;
    		else cells[iNumber][jNumber].setValue(-1);
    		
    	}
    	    	
    	//counts number of mines around a cell 
        for (int x=0; x<gridSize; x++)
        {
        	for (int y=0; y<gridSize; y++)	
        	{         	
        		if (cells[x][y].getValue()!=-1)	
        		{    
        			//algorithm for checking 8 neighbor cells 
        			for (int i=x-1; i<x+2; i++) 
        			{
        				for (int j=y-1; j<y+2; j++) 
        				{
        					//if not the current cell and if the cell is within the bounds of the game board
        					if ((i!=x | j!=y) & (i>-1 & i<gridSize) & (j>-1 & j<gridSize)) 
        					{
        						if (cells[i][j].getValue()==-1) 
        						{
        							cells[x][y].incrementValue();
        						}
        					}
        				}
        			}
        		}
        	}
        }       
    }
    

}
