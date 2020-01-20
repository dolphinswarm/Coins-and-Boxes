import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Character extends Rectangle {

	// ========== Properties
	Color c;
	int health = 5;
	double xAccel = 0.0, yAccel = 0.0;
	private static final long serialVersionUID = 1L;
	
	// ========== Constructors
	public Character(int x, int y, int width, int height) {
		super(x, y, width, height);
		health = 5;
	}
	
	// ========== Methods
	public void draw (Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}
	
	public void move() {
		x += xAccel;
		if (x > Tester.canvas.getWidth()) x = 0;
		if (x < 0 - width) x = Tester.canvas.getWidth();
		y += yAccel;
		if (y > Tester.canvas.getHeight()) health = 0;
		yAccel += 0.1;
	}
	
}
