package main;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{


	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	boolean player1_turn;
	String xSignature = "X";
	String oSignature = "O";

	public TicTacToe() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.getContentPane().setBackground(new Color(100,100,100));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		textfield.setBackground(new Color(23,23,23));
		textfield.setForeground(new Color(255, 255, 0));
		textfield.setFont(new Font("Campus Personal Use", Font.BOLD, 75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-tac-toe");
		textfield.setOpaque(true);

		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0, 700, 30);

		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("Universidad", Font.BOLD, 110));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}

		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(10,10,250));

		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);

		firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for(int i = 0; i < 9; i++) {
			if(e.getSource() ==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(Color.BLACK);
						buttons[i].setText(this.xSignature);
						player1_turn = false;
						textfield.setText(this.oSignature);
					}
				} else {
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(Color.RED);
						buttons[i].setText(this.oSignature);
						player1_turn = true;
						textfield.setText(this.xSignature);
					}
				}
			}
		}

	}

	public void firstTurn() {

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		if(random.nextInt(2) == 0) {
			player1_turn = true;
			textfield.setText(this.xSignature);
		} else {
			player1_turn = false;
			textfield.setText(this.oSignature);
		}

	}

	public void winnerChecker() {

	}

	public void xWins(int a, int b, int c) {

	}

	public void oWins(int a, int b, int c) {

	}
}
