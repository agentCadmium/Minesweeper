import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * @author Anastasia Saverchenko
 * 
 * Create the GUI version of Minesweeper
 * 
 * Requires these files:  
 *  1.png
 *  2.png
 *  3.png
 *  4.png
 *  5.png
 *  6.png
 *  7.png
 *  8.png
 *  mine.png
 *  redmine.png
 *  empty.png
 *  flag.png
 *  level.png
 *  restart.png
 *  default.png
 */

public class MinesweeperUI extends JPanel implements MouseListener 
{
	//instance variables 
	//access methods of MinesweeperGame class
	private MinesweeperGame game;
	//declare a bottom label 
	private JLabel bottomLabel;
	//instance variable of the timer class 
	private Timer ourTimer;
	// true when game ends
	private boolean gameOver;
    // true when the timer is started
    private boolean startedTimer;
	//declare a label with the timer
	public JLabel timerLabel;
	//declare a grid frame
	public static JFrame gridFrame;
	// an integer representing the size of a grid
	public static int gridSize = 8;
	// create array of JButtons 
	JButton[][] cellButton = new JButton[gridSize][gridSize];

	//Constructor
	public MinesweeperUI() 
	{			
		game = new MinesweeperGame(gridSize);		
		// set default settings of your game
		gameOver=false;
        startedTimer=false;
		// use BorderLayout
		setLayout(new BorderLayout() );
		
	    // create and add a top panel with a timer, reset button and settings buttons
		ourTimer=new Timer();
      	add(createTopPanel(), BorderLayout.NORTH);         
	    
	    // create and add a panel with cells in the center
	    add(createGridPanel(), BorderLayout.CENTER);
	    
	    // create and add panel to display messages "Welcome!", "You won!", "Game over" in the South
	    add(createBottomLabel(), BorderLayout.SOUTH );
	}
	
	/**
	 * Starts a new game	
	 */
	public static void startNewGame()
	{
		// create a new JFrame to hold the GUI
		gridFrame = new JFrame( "Minesweeper" );
		gridFrame.getContentPane().add( new MinesweeperUI() );	
		// cause this Window to be sized to fit the preferred size and layouts of its subcomponents
		//referenced Java API for following methods of JFrame
		gridFrame.pack();    
		// the window is placed in the center of the target screen
		gridFrame.setLocationRelativeTo(null);
		// this frame is't resizable
		gridFrame.setResizable(false);
		// ask it to exit the program when the window is closed
		gridFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		// show the window
		gridFrame.setVisible( true );
	}
	
	
	/**
	 * Starts a timer
	 */
	public void startOurTimer() 
	{
		//call an anonymous inner class 		
		ourTimer.schedule(new TimerTask()
		{			
				//an integer keeping track of how long it takes a user to clear a board 
				private int seconds=0;
				/**
				 * A task that repeats execution by a Timer. It's increase the timer and update the timer label
				 */
				@Override
				public void run() 
				{
					//increase the timer for a second
					seconds++;
					//update the label with the timer
					//following notation inspired by example:
					//http://stackoverflow.com/questions/23633260/setting-text-with-two-different-font-size-on-jbutton
					timerLabel.setText("<html><font size = +1>"+this.seconds+"</font>");		
				}			
		},1000,1000);
	}
	
	
	/**
	 * Stops our timer
	 */
	public void stopOurTimer() 
	{
		ourTimer.cancel();
	}
	
	/**
	 * Creates a bottom label to display our game messages 
	 * no parameters
	 * @return bottom label
	 */
	public JLabel createBottomLabel() 
	{	
	      // initialize instance welcomeLable
	      bottomLabel = new JLabel( "Welcome!", JLabel.CENTER );	
	      bottomLabel.setOpaque( true );
	      return bottomLabel;
	}
	
	
	/**
	 * Creates a top label with a timer, reset and settings buttons 
	 * no parameters
	 * @return top label
	 */
	public JPanel createTopPanel() 
	{
		JPanel TopPanel=new JPanel();
		// use BorderLayout
		TopPanel.setLayout(new BorderLayout());
		
        //Create a restart button
		//example found in:
		//https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
		JButton restartButton=new JButton("Restart", new ImageIcon("restart.png"));
		//set the focus state to be not painted
		//referenced API for this method 
		restartButton.setFocusPainted(false);
		//set the background color of the button  
		restartButton.setBackground(Color.ORANGE);
		//set space for margin between the button's border and the label
		//referenced API
		restartButton.setMargin(new Insets(0,0,0,5));
		//add an ActionListener to the button
		restartButton.addActionListener(
				new ActionListener() 
				{
		          public void actionPerformed( ActionEvent e )
		          {	
		        	  //release all of the native screen resources used by this window, its subcomponents, and all of its owned children
		        	  //referenced API for a method to clear game 
		        	  gridFrame.dispose();
		        	  //call the startNewGame method to start a new game
		        	  startNewGame();
		          }
		         }			
		);
		//add a restart button to the panel and position it on the left
		TopPanel.add(restartButton, BorderLayout.WEST);
		
		//create a label with a default value of the timer (zero)
		//inspired by example from:
		//http://stackoverflow.com/questions/13219158/jlabel-shifts-the-text-vertically-down-while-displaying-html
		timerLabel=new JLabel("<html><font size = +1>0</font>"); //increase font size
		//set the alignment of the label's contents along the X axis 
		timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//add a timer label to the panel
		TopPanel.add(timerLabel,BorderLayout.CENTER);		
		
		//create a button to setup a new game level 
		JButton levelButton=new JButton("Level", new ImageIcon("level.png"));
		//set the focus state to be not painted
		levelButton.setFocusPainted(false);
		//set the background color of the button
		levelButton.setBackground(Color.ORANGE);
		//set space for margin between the button's border and the label
		levelButton.setMargin(new Insets(0,0,0,5));
		//add an ActionListener to the button
		levelButton.addActionListener(
				new ActionListener() 
				{
		          public void actionPerformed( ActionEvent e )
		          {        	  
		        	  MinesweeperSettings settings=new MinesweeperSettings(); 
		        	  
		        	  settings.newGameButton.addActionListener(
		        			  new ActionListener() 
		        			  {
		        		          public void actionPerformed( ActionEvent e )
		        		          {    
		        		        	  String yourChoice=new String();
		        		        	  //check all buttons that are participating in the levelButtonGroup and
		        		        	  //find selected
		        		        	  for (Enumeration<AbstractButton> buttons = settings.levelButtonGroup.getElements(); buttons.hasMoreElements();) 
		        		        	  {
		        		        		  AbstractButton button = buttons.nextElement();
		        		        		  if (button.isSelected()) 
		        		        		  {
		        		        			  yourChoice=button.getText();
		        		        			  continue;
		        		        		  }
		        		        	  }
		        		        	  //set a new game settings and start new game
		        		        	  switch (yourChoice)
		        		        	  {
		        		        	  case "intermediate":
		        		        		  //set a new grid size
		        		        		  gridSize=12;
		        		        		  //release all of the native screen resources used by the window with the game board, its subcomponents, and all of its owned children
			        		        	  gridFrame.dispose();
			        		        	  //call the startNewGame method to start a new game
			        		        	  startNewGame();
			        		        	  //release all of the native screen resources used by this window with settings
			        		        	  settings.dispose();
		        		        		  break;
		        		        	  case "advanced":
		        		        		  gridSize=16;
			        		        	  gridFrame.dispose();
			        		        	  startNewGame();
			        		        	  settings.dispose();
		        		        		  break;
		        		        	  default:
		        		        		  gridSize=8;
			        		        	  gridFrame.dispose();
			        		        	  startNewGame();
			        		        	  settings.dispose();
		        		        		  break;		        		        			  
		        		        	  }        		        	 
		        		          }
		        			  }
		        	  );
		          }		          
		         }			
		);
		
		TopPanel.add(levelButton, BorderLayout.EAST);

		return TopPanel;
	}
	
	/**
	 * Displays a new message in the bottom of the game window: "Welcome!", "You won!", "Game over" in the South
	 * @param String as a new game message
	 * returns nothing
	 */
	public void showBottomLabel(String message) 
	{      
	      bottomLabel.setText(message);
	}
	
	
	/**
	 * Creates a panel with cells
	 * @return grid with cells
	 */
	public JPanel createGridPanel()	
	{
		JPanel gridPanel=new JPanel();
		//use GridLayout
		gridPanel.setLayout(new GridLayout(gridSize,gridSize));
		//add cells to the grid
 		for (int i =0; i<gridSize; i++)
 			for (int j =0; j<gridSize; j++)
 			{
 				//add JButtons to the array of JButtons 
 				cellButton[i][j] = new JButton("");
 				//these values were found by experimenting
 				cellButton[i][j].setPreferredSize(new Dimension(32,32));
 				//add a listener to every button
 				cellButton[i][j].addMouseListener(this);
 				//set a default icon (image that will be showing at start of game)
        		cellButton[i][j].setIcon(new ImageIcon ("default.png"));
        		//add these cells to the grid panel
        		gridPanel.add(cellButton[i][j]);
 			}
		//call the method to place mines and calculate all surrounding values
 		game.setMines(); 	 		
		return gridPanel;        
	}
		
		
    /**
    * Makes all mines visible when game is over
    * @param x X coordinate of the last clicked mine
    * @param y Y coordinate of the last clicked mine
    **/
    public void gameOver (int x, int y) 
    {
    	//timer is stopped
    	stopOurTimer();
    	//update display of bottom label 
    	showBottomLabel("Game over");
    	//invoke method gameOver
    	gameOver=true;
		for (int i = 0; i<gridSize; i++)
		{
 			for (int j = 0; j<gridSize; j++)
 			{
 				//update visibility of cells
 				if (game.getCells()[i][j].getValue()==-1 & (x!=i | y!=j)) makeCellVisible(i,j);
 			}
		}
    }
    
    
    /**
     *  Checks if all cells without mines were opened
     */
     public void checkYouWin () 
     {
     	int openedCells=0;
     	
         for (int i=0; i<gridSize; i++)
         	for (int j=0; j<gridSize; j++) 
                	if (game.getCells()[i][j].getVisibility()==true) 
                	{	//increment number of opened cells 
                		openedCells++;        	
                	}
         // end the game when the sum of opened cells and mines equals to
         // the total number of board cells  
         if (openedCells+game.getMinesNumber()==gridSize*gridSize) 
         {
         	//stop the timer
         	stopOurTimer();
         	//show win message
         	showBottomLabel("You won!");
         	//disable the listener of buttons
         	gameOver=true;
         }
     }
        
  
    /**
     * Makes a cell visible
     * Updates the icons of the buttons 
     * @param i - current cell coordinate
     * @param j - current cell coordinate
     **/
    public void makeCellVisible (int i, int j) 
    {
    	//make cells visible 
    	game.getCells()[i][j].setVisibility();
    	//update the icons of the buttons based on the corresponding values of the cells
		switch (game.getCells()[i][j].getValue()) {
		case -1:
			//when the cell contains value -1, display "mine.png"
			cellButton[i][j].setIcon(new ImageIcon("mine.png"));
			break;
		case 0:
			//when the cell contains value 0, display "empty.png"
			cellButton[i][j].setIcon(new ImageIcon("empty.png"));
			break;
		case 1:
			//when the cell contains value 1, display "1.png"
			cellButton[i][j].setIcon(new ImageIcon("1.png"));
			break;
		case 2:
			//when the cell contains value 2, display "2.png"
			cellButton[i][j].setIcon(new ImageIcon("2.png"));
			break;
		case 3:
			//when the cell contains value 3, display "3.png"
			cellButton[i][j].setIcon(new ImageIcon("3.png"));
			break;
		case 4:
			//when the cell contains value 4, display "4.png"
			cellButton[i][j].setIcon(new ImageIcon("4.png"));
			break;
		case 5:
			//when the cell contains value 5, display "5.png"
			cellButton[i][j].setIcon(new ImageIcon("5.png"));
			break;
		case 6:
			//when the cell contains value 6, display "6.png"
			cellButton[i][j].setIcon(new ImageIcon("6.png"));
			break;
		case 7:
			//when the cell contains value 7, display "7.png"
			cellButton[i][j].setIcon(new ImageIcon("7.png"));
			break;
		case 8:
			//when the cell contains value 8, display "8.png"
			cellButton[i][j].setIcon(new ImageIcon("8.png"));
			break;
		}	
	}
    
    /**
     * Reveal all adjacent 0 tiles from the selected tile outwards
     * @param x X coordinate of the parent cell
     * @param y Y coordinate of the parent cell
     */
    public void scanNeighbourCell(int x, int y) 
    {    	
    	for (int i=x-1; i<x+2; i++) 
    	{
    		for (int j=y-1; j<y+2; j++) 
    		{
    			//if not the parent cell and cell not out of bounds of the game board 
    			if ((i!=x | j!=y) & (i>-1 & i<gridSize) & (j>-1 & j<gridSize))
    			{    					
    				if (game.getCells()[i][j].getVisibility()==false)
    				{
    					makeCellVisible(i,j);
    					//Recursion
    					if (game.getCells()[i][j].getValue()==0) scanNeighbourCell(i,j);
    				}
    			
    			}
    		}
    	}

    }   

    
    
    
    /**
     * Mouse Listener. Sets a flag when the right mouse button is clicked, 
     * opens a cell when the left mouse button is clicked. 
     * Referenced API 
     */
    	@Override
    	public void mouseClicked(MouseEvent e) 
    	{
    		//if not the end of the game
    		if (gameOver==false) {
    			// start a timer  
    			if (startedTimer==false) {				
    				startOurTimer();
    				//indicate that timer has been started 
    				startedTimer=true;
    			}
    			
    			// Referenced Labeled Break:
    			// https://docs.oracle.com/javase/tutorial/java/nutsandbolts/branch.html
    			search:
    			//for every button in the array of JButtons
		 		for (int i =0; i<gridSize; i++)
		 			for (int j =0; j<gridSize; j++)
		 				if (e.getSource()==cellButton[i][j])
		 				{
		 					//set or remove a flag when right mouse button clicked
		 					if ( SwingUtilities.isRightMouseButton(e) ) 
		 					{
		 						//if this cells has not been accessed yet
    		    				if (game.getCells()[i][j].getVisibility()==false) 
    		    				{
    		    					//indicate that this cells now has a flag
    		    					game.getCells()[i][j].changeFlagValue(game.getCells()[i][j].getFlag());
    		    					//update icon of button to display a flag
    		    					if (game.getCells()[i][j].getFlag()==true)  cellButton[i][j].setIcon(new ImageIcon("flag.png"));
    		    					//if a flag is already present in the cell and button, update display to default
    		    					else cellButton[i][j].setIcon(new ImageIcon("default.png"));
    		    				}		 					
		 					}
		 					//make a cell visible when the left mouse button clicked  
		 					else {
    		 					//make cell visible and update display of button
    		    				makeCellVisible(i,j);
    		    				//check if this cell is zero and if yes, reveal all neighboring cells with zero
    		    				if (game.getCells()[i][j].getValue()==0) scanNeighbourCell(i,j);
    		    				//end the game when a mine was clicked		
    		    				if (game.getCells()[i][j].getValue()==-1) {
    		    					//update icon to a red mine
    		    					cellButton[i][j].setIcon(new ImageIcon("redmine.png"));
    		    					//invoke game over
    		    					gameOver(i,j);
    		    				}
    		    				//check if all cells without mines were opened
    		    				else checkYouWin();
		 					}
		 					//
		 					break search;
		 				}
    		}
     	}

    	
    	@Override
    	public void mouseEntered(MouseEvent arg0) 
    	{	
    	}


    	@Override
    	public void mouseExited(MouseEvent arg0) 
    	{		
    	}


    	@Override
    	public void mousePressed(MouseEvent arg0) 
    	{		
    	}


    	@Override
    	public void mouseReleased(MouseEvent arg0) 
    	{		
    	}    
    
    
   /** 
    * Main function
    */
	public static void main(String[] args) {
	// start a new game
	startNewGame();
	}

}
