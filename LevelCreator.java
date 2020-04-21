import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class LevelCreator implements ActionListener {
	
	private JButton button;
	private String direction;
	private Board gui;
	
	public LevelCreator(String x, Board gui) {
		
		this.gui = gui;
		direction = x;
		button = new JButton(x);
		button.addActionListener(this);
			
		
	}
   
	public JButton getButton() {
    	return button;
    }
	private void processClick(int x){
		gui.levelChanger(x);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (direction == "next") {
			Square.boardClear();
			processClick(1);
				System.out.println(direction);
	
		} else {
			System.out.println(direction);
			Square.boardClear();
			processClick(-1);
		}

	}
	

}
