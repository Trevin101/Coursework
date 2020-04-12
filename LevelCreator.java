import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class LevelCreator implements ActionListener {
	
	private JButton button;
	private String direction;
	private Board gui;
	
	public LevelCreator(String x) {
		
		this.gui = gui;
		direction = x;
		button = new JButton(x);
		button.addActionListener(this);
			
		
	}
   
	public JButton getButton() {
    	return button;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gui.levelChanger(direction);
	}
	

}
