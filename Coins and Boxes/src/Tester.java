import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tester extends JPanel {
	// ======================= Configure game objects and variables
	public static JPanel canvas;
	JFrame frame = new JFrame("Coins and Boxes");
	public Timer tmr;
	public Random rnd = new Random();
	static Character character = null;
	private static final long serialVersionUID = 1L;
	ArrayList<Coin> coins = new ArrayList<Coin>();
	ArrayList<Box> boxes = new ArrayList<Box>();
	static int score;
	Color color;
	int coinTimer = 0;
	int boxTimer = 0;
	
	// ======================= Game
	public Tester() {
		// ========== Window Properites
		canvas = this;
		frame.setBounds(0, 0, 
				Toolkit.getDefaultToolkit().getScreenSize().width, 
				Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.setVisible(true);
		
		character = new Character(frame.getWidth()/2, frame.getHeight()/2, 30, 30);
		
		// ========== Key/Mouse events
		frame.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					character.yAccel = -5.0;
				}
				
				if (e.getKeyCode() == KeyEvent.VK_A) {
					character.xAccel = -3;

				}
				
				if (e.getKeyCode() == KeyEvent.VK_D) {
					character.xAccel = 3;
				}
			}
			
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					character.xAccel = 0;

				}
				
				if (e.getKeyCode() == KeyEvent.VK_D) {
					character.xAccel = 0;
				}
			}	

		});
		
		// ========== Timer Tick (frame)
		tmr = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// CoinTimer
				if (coinTimer <= 0) {
					coins.add(new Coin(rnd.nextInt(canvas.getWidth()), -10, 10, 10));
					coinTimer = 5;
				}
				coinTimer--;
				
				// Moves each coin
				for (Coin c : coins) {
					c.move();
					if (c.y > canvas.getHeight() || c.intersects(character)) {
						coins.remove(c);
						return;
					}
				}
				
				// BoxTimer
				if (boxTimer <= 0) {
					boxes.add(new Box(rnd.nextInt(canvas.getWidth()), -10, 15, 15));
					boxTimer = 10;
				}
				boxTimer--;
				
				// Moves each coin
				for (Box b : boxes) {
					b.move();
					if (b.y > canvas.getHeight() || b.intersects(character)) {
						boxes.remove(b);
						return;
					}
				}
				
				// Moves character
				character.move();
				if (character.health <= 0) {
					JOptionPane.showMessageDialog(frame, "You lost! Your score is: " + score);
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
				
				// Reset frame
				repaint();
			}
		});
		
		tmr.start(); // Start game
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		character.draw(g);
		for (Coin c : coins) c.draw(g);
		for (Box b : boxes) b.draw(g);
		g.setColor(Color.white);
		g.drawString("Score: " + score, character.x - 7, character.y - 5);
		g.drawString("Health: " + character.health, character.x - 8, character.y - 20);
		setBackground(new Color(60, 60, 60));
	}
	
	public static void main(String[] args) {
		new Tester();
	}
}

