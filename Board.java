import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class Board implements ActionListener{
	private JFrame frame;
	private JPanel Panel;

    public Board(){
        frame = new JFrame();
        frame.setTitle("Frogger");
        frame.setSize(200,200);
        
        Panel = new JPanel();
        Panel.setLayout(new BorderLayout());
        
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
