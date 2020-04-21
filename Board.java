import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import static javax.swing.JOptionPane.showMessageDialog;

/** Represents the sections of the game screen
 * @author Trevin Mabarana
 * @version 5.4.3.5.3.5.6.4.3.4.2
 * @since 5.4.3.5.3.5.6.4.3.4.1
 */
public class Board extends JFrame{
	private JFrame frame;
	private JPanel MainPanel;
	private JPanel GamePanel;
	private JPanel LevelPanel;

	private int SquareType = 1;
	private int temp = 1;
	/** Represents the level the user is on
	*/
	public static int levelSelected = 1;
	/** Represents the possible coordinates of the frogs
	*/
	public static int[][] possibleLevels = {
			{0, 1, 2, 2, 3, 4},
			{4, 1, 2, 4, 1, 4},
			{2, 2, 2, 3, 2, 2}
	};
	/** Represents the possible coordinates of the frogs on the
	* on the second level
	*/
	public static int[][] levelTwo = {
			{0, 1, 2, 2, 3, 4},
			{2, 3, 4, 2, 1, 4},
			{2, 3, 2, 2, 2, -1}
	};
	/** Represents temporary array to store levels
	*/
	public static int[][] tempLevel ;

	
    private JButton lessButton = new JButton("<");
    private JButton moreButton = new JButton(">");
    
    public static int minLvl = 0, maxLvl = 5, prevInt = 0;


	/** Creates a GUI with the given requirments
	*/
    public Board(){
        frame = new JFrame();
        frame.setTitle("Frogger");
        frame.setSize(900,800);
        
        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());
        
        GamePanel = new JPanel();
        GamePanel.setLayout(new GridLayout(5,5));
        
        LevelPanel = new JPanel();
        LevelPanel.setLayout(new FlowLayout());
    	/** Creates two side buttons 
    	 * @param the name of the button
    	 * @param the gui which the button acts on
    	*/
        LevelCreator a = new LevelCreator("prev",this);
        MainPanel.add("West",LevelPanel.add(a.getButton()));
        
        LevelCreator b = new LevelCreator("next",this);
        MainPanel.add("East",LevelPanel.add(b.getButton()));
    	
        /** Implements the coordinates of the frogs to buttons
         * on the gui
    	*/
        gameSetter();
        	
        MainPanel.add("Center",GamePanel);	
        frame.setContentPane(MainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        }

    
    
    public void gameSetter() {
        for (int i=0; i<5; i++) {
        	for (int j=0; j<5; j++) {

        		temp = temp*-1;
        		SquareType = TypeFinder(j,i,minLvl,maxLvl,temp);
        		
        		Square d = new Square(j, i, SquareType, this);
        		GamePanel.add(d.getButton());

        	}
        }
        temp = 1;
    }
	/** Creates a square with coordinates and a type of frog
	 * @param x coordinate
	 * @param y coordinate 
	 * @param minimum level
	 * @param max level
	 * @param the original type of square it was before
	*/
   
	private int TypeFinder(int xdisp, int ydisp, int min, int max,int original) {
		for (int i=min; i< possibleLevels[0].length; i++) {
			if (possibleLevels[0][i] == xdisp && possibleLevels[1][i] == ydisp) {
				return possibleLevels[2][i];
				
			} 
		}
		return original;
	}

	
	
	public void levelChanger(int x){
		System.out.println(levelSelected);
		levelSelected +=  x;
		System.out.println(levelSelected);
		if (levelSelected == 1) {
			possibleLevels = tempLevel;
			gameSetter();
		} else if (levelSelected == 2) {
			tempLevel = possibleLevels;
			possibleLevels = levelTwo;
			gameSetter();
		} else {
			System.out.println(" more levels coming");
			levelSelected = 1;
			gameSetter();
		}
	}
	
	public void levelFrogSelector(int xpos, int ypos, int prevX, int blockType) {
		if (prevX != -1) {
			for (int i=0; i< possibleLevels[0].length; i++) {
                if (possibleLevels[0][i] == xpos && possibleLevels[1][i] == ypos && possibleLevels[2][i] == 2) {
               		if (possibleLevels[2][prevInt] == 5 ) {
               			possibleLevels[2][prevInt] = 2;
               		} else if ( possibleLevels[2][prevInt] == 4) {
               			possibleLevels[2][prevInt] = 3;
               		}
                	possibleLevels[2][i] = 5;
               		prevInt = i;

               		gameSetter();
               		
               	} else if (possibleLevels[0][i] == xpos && possibleLevels[1][i] == ypos && possibleLevels[2][i] == 3) {
               		if (possibleLevels[2][prevInt] == 5 ) {
               			possibleLevels[2][prevInt] = 2;
               		} else if ( possibleLevels[2][prevInt] == 4) {
               			possibleLevels[2][prevInt] = 3;
               		}
                	possibleLevels[2][i] = 4;
               		prevInt = i;
               		gameSetter();
               	} else if (blockType == 1 || blockType == -1 || blockType == 4 || blockType == 5) {
               		frogHandler(possibleLevels[0][prevInt],possibleLevels[1][prevInt], blockType, xpos, ypos, prevInt);

               		gameSetter();
               		return;
               	}
       	
			}  
	  } else {
		  for (int i=0; i < possibleLevels[0].length; i++) {
              if (possibleLevels[0][i] == xpos && possibleLevels[1][i] == ypos && possibleLevels[2][i] == 2) {
            	  possibleLevels[2][i] = 5;
            	  gameSetter();
            	  prevInt = i;
              } else if (blockType == 1 || blockType == -1 || blockType == 4 || blockType == 5) {

            	  gameSetter();
            	  return;
               }
		  }
		  
		 
	  }
		
    }
       
    
	
	public void frogHandler(int x, int y, int type, int newX, int newY, int arrayPos){
		int distX = newX - x;
		int distY = newY - y;
		System.out.println("x " + x + " y "+ y+ " type "+ type + " newX "+ newX+ " newY "+ newY + " distX " + distX + " distY " + distY + " arrayPos " + arrayPos);
		if (Math.abs(distX)== 2 && Math.abs(distY)== 2) {
			int killX = x + (distX/2);
			int killY = y + (distY/2);
			System.out.println("PossibleX  " + killX + " PossibleY "+ killY);
			for (int i=0; i< possibleLevels[0].length ; i++) {
				if (possibleLevels[0][i] == killX && possibleLevels[1][i] == killY && possibleLevels[2][i] == 2 && possibleLevels[2][i] != 3) {
					
					possibleLevels[0][i] = 6;
					possibleLevels[1][i] = 6;
					possibleLevels[2][i] = -1;
					if (possibleLevels[2][arrayPos] == 4) {
						possibleLevels[0][arrayPos] = newX;
						possibleLevels[1][arrayPos] = newY;
						possibleLevels[2][arrayPos] = 3;
					} else if (possibleLevels[2][arrayPos] == 5) {
						possibleLevels[0][arrayPos] = newX;
						possibleLevels[1][arrayPos] = newY;
						possibleLevels[2][arrayPos] = 2;
					}

					System.out.println(" ");
					int valueSum = 0;
					for (int j=0; j< possibleLevels[0].length ; j++) {
						valueSum = valueSum + possibleLevels[2][j];
						if (valueSum == -2) {
							showMessageDialog(null, "You WIN");
						}
	               		
					}

				}
			}
			
		}
		
	}
	
	
	

	public void digitHit(int xpos, int ypos) {
		// TODO Auto-generated method stub
		
		System.out.println(xpos + " " + ypos);	

	}
	

}
