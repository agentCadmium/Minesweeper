
/**
 * @author Anastasia Saverchenko
 * Creates one cell that represents a component of the game field 
 */
public class MinesweeperCell
{
	//instance properties  
	//holds value of a cell:
	//-1 -mine
	// 0 -zero
	// 1..8 : number of adjacent squares that contain mines
	private int value;
	//stores cell visibility:
	// true - visible
	// false - hidden
	private boolean visibility;
	//stores a flagged tile where the user suspects a mine
	private boolean flag;

	/**
	 * The constructor sets preferred size of a cell, sets a value of the cell to zero,   
	 * hides value of a cell by default, saves coordinates of the cell  
	 * @param x X-coordinate of a cell on the board
	 * @param y Y-coordinate of a cell on the board
	 */
	public MinesweeperCell(int x, int y) 
	{	//initialize instance properties 
		//set default value to 0
		value = 0;
		//set initial visibility of sell to false 
		visibility = false;
		//cell does not contain a flag
		flag = false;	
	}
    
	/**
	 * Returns value of visibility
	 * @return value of visibility
	 * true - visible, false - hidden
	 * 
	 */
	public boolean getVisibility() 
	{      
      return visibility;
    }
	
	/**
	 * Makes a cell visible 
	 */
	public void setVisibility() 
	{
	      visibility = true;	      
	}
	
	/**
	 * Returns whether the cell is marked with a cell
	 * @return if cell is flagged
	 */
	public boolean getFlag() 
	{
  	      return flag;
	}
	
	/**
	 * Changes value of a marked cell
	 * @param current value of the flag parameter 
	 */
	public void changeFlagValue (boolean flagValue) 
	{	       
	      flag =!flagValue;
    }	
	
	/**
	 * Sets value of a cell:
	 * @param current cell value (-1 -mine, 0 -zero, 1..8 -number of adjacent squares that contain mines)
	 */
	public void setValue(int cellValue) 
	{
      //set value of cell 
      value = cellValue;      
    }
	
	/**
	 * Increases value of a cell by 1  
	 */
	public void incrementValue() 
	{
      //increment value of cell 
      value++;
    }
	
	/**
	 * Returns current value of a cell
	 * (-1 -mine, 0 -zero, 1..8 -number of adjacent squares that contain mines)
	 * @return current value of cell
	 */
	public int getValue() 
	{
      //returns value of cell
      return value;
    }	

}
