/**
 * @author victordematos
 * created on November 4th, 2020
 * Game name: Snake
 * Version: 1.0.0
 * */

package main;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	GameFrame() {
		
		this.add(new GamePanel()); 
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); // Window cannot be resized
		this.pack();
		this.setVisible(true); // Makes the window visible
		this.setLocationRelativeTo(null); // Makes the window show up in the middle of the screen
		
	}
	
	public String toString() {
		return "Game started!";
	}
}
