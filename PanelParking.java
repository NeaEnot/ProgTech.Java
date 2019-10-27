import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelParking extends JPanel {
	Parking parking = new Parking<ITransport, IRollers>(20, 1000, 700);
	
	public void paint(Graphics g) {
		super.paint(g);
		parking.Draw(g);
	}
	
	public int ADD(ITransport transport) {
		return parking.ADD(transport);
	}
	
	public ITransport SUBTRACT(int index) {
		return parking.SUBTRACT(index);
	}
	
	public boolean MORE(ITransport transport) {
		return parking.MORE(transport);
	}
	
	public boolean LESS(ITransport transport) {
		return parking.LESS(transport);
	}
}
