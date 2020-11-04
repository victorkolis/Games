package main;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	GameFrame() {
		
		this.add(new GamePanel()); 
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // Window cannot be resized
		this.pack();
		this.setVisible(true); // Makes the window visible
		this.setLocationRelativeTo(null); // Makes the window show up in the middle of the screen
		
	}

}
