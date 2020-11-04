package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {


	private static final long serialVersionUID = 1L;
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 20;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75;
	// Holds the coordinates for the body parts of the snake including the head
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX; 
	int appleY;
	char direction = 'D'; // The letters stand for the position the snake is going, R(right); L(left); U(up); D(down)
	boolean running = false;
	Timer timer;
	Random random;
	static boolean gameOn = false;

	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();

	}
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();

	}

	public void pause() {
		GamePanel.gameOn = true;
		timer.stop();
	}

	public void resume() {
		GamePanel.gameOn = false;
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);

	}
	public void draw(Graphics g) {

		if(running){

			//			// Grid
			//			g.setColor(Color.gray);
			//			for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
			//				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
			//				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
			//			}

			// Apple
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

			// Head
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(new Color(104, 160, 80));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					// Body	
				} else {
					g.setColor(new Color(150, 230, 110));
					g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}

			// Score text
			g.setColor(new Color(240, 130, 110));
			g.setFont(new Font("Georgia", Font.BOLD, 30));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score : " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score : " + applesEaten)) / 2, g.getFont().getSize());

		} else {
			gameOver(g);
		}


	}
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;


	}
	public void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}

		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;

		}

	}
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}

	}
	public void checkCollisions() {
		// Check if head collides with body
		for(int i = bodyParts; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])){
				running = false;
			}
		}
		// Check if head collides with left boarder
		if(x[0] < 0) {
			running = false;
		}

		// Check if head collides with right boarder
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		// Check if head collides with top boarder
		if(y[0] < 0) {
			running = false;
		}

		// Check if head collides with bottom boarder
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}

		if(!running) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {
		// Game over text
		g.setColor(new Color(240, 130, 110));
		g.setFont(new Font("Georgia", Font.BOLD, 75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) / 2, SCREEN_HEIGHT/2);

		// Score text
		g.setColor(new Color(240, 130, 110));
		g.setFont(new Font("Georgia", Font.BOLD, 30));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score : " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score : " + applesEaten)) / 2, g.getFont().getSize());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(running) {
			move();
			checkApple();
			checkCollisions();
		}

		repaint();

	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			case KeyEvent.VK_SPACE:
				if(GamePanel.gameOn) {
					resume();
				} else {
					pause();
				}
				break;

			}
		}

	}
}

