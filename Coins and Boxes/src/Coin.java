import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Coin extends Rectangle {

	// ========== Properties
	Color c;
	Random r = new Random();
	int fallSpeed = 3 + r.nextInt(3);
	private static final long serialVersionUID = 1L;
	
	// ========== Constructors
	public Coin(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	// ========== Methods
	public void draw (Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, width, height);
		g.setColor(Color.black);
		g.drawOval(x, y, width, height);
	}
	
	public void move() {
		y += fallSpeed;
		if (this.intersects(Tester.character)) {
			Tester.score++;
		}
	}
	
}
