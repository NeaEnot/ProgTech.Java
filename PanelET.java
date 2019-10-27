import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelET extends JPanel {
	
	ExcavatorTractor excavatorTractor; 
	
	public void updateET(int width, int height) {
		excavatorTractor = new ExcavatorTractor((int)(Math.random() * 200) + 100, (int)(Math.random() * 1000) + 1000, 
                Color.BLUE, Color.BLACK, Color.RED, true, true, true);
		excavatorTractor.SetPosition((int)(Math.random() * 200) + 100, (int)(Math.random() * 100) + 50, width, height);
	}
	
	public void MoveTransport(Direction direction) {
		switch (direction) {
		case Right:
			excavatorTractor.MoveTransport(Direction.Right);
			break;
		case Left:
			excavatorTractor.MoveTransport(Direction.Left);
			break;
		case Up:
			excavatorTractor.MoveTransport(Direction.Up);
			break;
		case Down:
			excavatorTractor.MoveTransport(Direction.Down);
			break;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (excavatorTractor != null) {
			excavatorTractor.DrawET(g);
		}
	}
}
