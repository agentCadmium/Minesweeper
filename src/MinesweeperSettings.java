import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * @author Anastasia Saverchenko
 * Creates a window with settings of our game 
 * Used Java API for guidance on some of the above classes
 *
 */

public class MinesweeperSettings extends JFrame
{
	// declare a button for restarting the game
	public JButton newGameButton;
	// declare a set of radio buttons to set a new level of the game  
	public ButtonGroup levelButtonGroup;
	// declare a box with all components  
	private Box mainBox;

	/** 
	 * The constructor adds a title, sets location of the frame and calls
	 * a method to add components       
	 */
	public MinesweeperSettings() 
	{	
		super("Set new game level");
		//Automatically hide and dispose the frame after invoking any registered WindowListener objects
  	  	setDefaultCloseOperation(DISPOSE_ON_CLOSE);	  	  	  
  		// The window is placed in the center of the target screen
  	  	setLocationRelativeTo(null);
  	  	// Call a method to add buttons
  	  	addMainBox();  	  	  	  	
  	  	setContentPane(mainBox);
  	  	//Cause this window to be sized to fit the preferred size and layouts of its subcomponents
  	  	pack();
  	  	//Show the window
  	  	setVisible(true);
	}

	/**
	 * Creates boxes, a group with radio buttons and a button for restarting the game 
	 * // Reference used for BoxLayout: 
	 * https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html  
	 * 
	 * Referenced JRadioButton:
	 * https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
	 * 
	 */
	public void addMainBox()
	{
		// Creates boxes that displays its components from top to bottom. 
  	  	mainBox = Box.createVerticalBox();
  	  	Box box1 = Box.createVerticalBox();  	  	
  	  	// create three buttons to set a level of the game
  	  	JRadioButton rButton1 = new JRadioButton("beginner", true);
  	  	JRadioButton rButton2 = new JRadioButton("intermediate");
  	  	JRadioButton rButton3 = new JRadioButton("advanced");
  	  	// stack all radio buttons in one group
  	  	levelButtonGroup = new ButtonGroup();
  	  	levelButtonGroup.add(rButton1);
  	  	levelButtonGroup.add(rButton2);
  	  	levelButtonGroup.add(rButton3);
  	  	// stack all radio buttons in one box
  	  	box1.add(rButton1);
  	  	box1.add(rButton2);
  	  	box1.add(rButton3);
  	  	box1.setBorder(new TitledBorder("Level"));
  	  	box1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
  	  	mainBox.add(box1);
  	  	// create a button witch starts a new game
  	  	newGameButton=new JButton("New game");
  	  	newGameButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
  	  	mainBox.add(newGameButton);
	}
}
