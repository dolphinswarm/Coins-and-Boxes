import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Image;
import java.awt.Rectangle;
//import java.awt.Toolkit;
import java.util.Random;

public class Box extends Rectangle {

	// ========== Properties
	Color c;
	Random r = new Random();
	int fallSpeed = 4;
	//Image box = Toolkit.getDefaultToolkit().getImage();
	private static final long serialVersionUID = 1L;
	
	// ========== Constructors
	public Box(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	// ========== Methods
	public void draw (Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}
	
	public void move() {
		y += fallSpeed;
		if (this.intersects(Tester.character)) {
			Tester.character.health--;
		}
	}
	
}