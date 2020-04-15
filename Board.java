import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class Board extends JFrame{
	private JFrame frame;
	private JPanel MainPanel;
	private JPanel GamePanel;
	private JPanel LevelPanel;

	private int SquareType = 1;
	private int temp;
	
	private int levelSelected = 1;
	private int[][] possibleLevels = {
			{0, 1, 2, 2, 3, 4},
			{4, 1, 2, 4, 1, 4},
			{2, 2, 2, 3, 2, 2}
	};
	
    private JButton lessButton = new JButton("<");
    private JButton moreButton = new JButton(">");
    




    public Board(){
        frame = new JFrame();
        frame.setTitle("Frogger");
        frame.setSize(800,800);
        
        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());
        
        GamePanel = new JPanel();
        GamePanel.setLayout(new GridLayout(5,5));
        
        LevelPanel = new JPanel();
        LevelPanel.setLayout(new FlowLayout());
        
        LevelCreator a = new LevelCreator("next");
        MainPanel.add("West",LevelPanel.add(a.getButton()));
        
        LevelCreator b = new LevelCreator("prev");
        MainPanel.add("East",LevelPanel.add(b.getButton()));
        
        gameSetter();
        	
        MainPanel.add("Center",GamePanel);	
        frame.setContentPane(MainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        }

    
    
    public void gameSetter() {
        for (int i=0; i<5; i++) {
        	for (int j=0; j<5; j++) {
        		SquareType = SquareType*-1;
        		temp = SquareType;
        		SquareType = TypeFinder(j,i,0,5,temp);
        		
        		Square d = new Square(j, i, SquareType, this);
        		GamePanel.add(d.getButton());
        		SquareType = temp;
        	}
        }
    }

	private int TypeFinder(int xdisp, int ydisp, int min, int max,int original) {
		// TODO Auto-generated method stub
		for (int i=min; i<=max; i++) {
			if (possibleLevels[0][i] == xdisp && possibleLevels[1][i] == ydisp) {
				return possibleLevels[2][i];
			}
		}
		return original;
	}

	public void digitHit(int xpos, int ypos) {
		// TODO Auto-generated method stub
		
		System.out.println(xpos + " " + ypos);	

	}
	
	public void levelChanger(String x) {
		if (x == "");
	}

}
