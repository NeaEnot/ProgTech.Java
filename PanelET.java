import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelET extends JPanel {
	
	ITransport transport;
	
	public void setTransport(ITransport t) {
		transport = t;
		transport.SetPosition(10, 10, 90, 50);
	}
	
	public void updateTransport(int width, int height, boolean isExcavatorTractor) {
		if (isExcavatorTractor) {
			transport = new ExcavatorTractor((int)(Math.random() * 200) + 100, (int)(Math.random() * 1000) + 1000, 
											Color.BLUE, Color.BLACK, Color.RED, true, true, true);
		} else {
			transport = new Tractor((int)(Math.random() * 200) + 100, (int)(Math.random() * 1000) + 1000, Color.BLUE);
		}
		transport.SetPosition((int)(Math.random() * 200) + 100, (int)(Math.random() * 100) + 50, width, height);
	}
	
	public void MoveTransport(Direction direction) {
		switch (direction) {
		case Right:
			transport.MoveTransport(Direction.Right);
			break;
		case Left:
			transport.MoveTransport(Direction.Left);
			break;
		case Up:
			transport.MoveTransport(Direction.Up);
			break;
		case Down:
			transport.MoveTransport(Direction.Down);
			break;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (transport != null) {
			transport.Draw(g);
		}
	}
}
