package Panels;

import java.awt.Graphics;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JPanel;

import Classes.MultiLevelParking;
import Interfaces.ITransport;

public class PanelParking extends JPanel {
	MultiLevelParking parking = new MultiLevelParking(5, 1000, 700);
    
    Queue<ITransport> queue = new LinkedList<ITransport>();

    private final int countLevel = 5;
    private int currentLevel = 0;
    
    public PanelParking()
    {
    	super();
        parking = new MultiLevelParking(countLevel, 1000, 700);
    }
	
	public void paint(Graphics g) {
		super.paint(g);
		parking.level(currentLevel).Draw(g);
	}
	
	public void setLevel(int index) {
		if (index >= 0 && index < countLevel) {
			currentLevel = index;
		}
	}
	
	public int ADD(ITransport transport) {
		return parking.level(currentLevel).ADD(transport);
	}
	
	public ITransport SUBTRACT(int index) {
		ITransport transport = parking.get(currentLevel, index);
		if (transport != null) {
			queue.add(transport);
		}
		
		return parking.level(currentLevel).SUBTRACT(index);
	}
	
	public boolean MORE(ITransport transport) {
		return parking.level(currentLevel).MORE(transport);
	}
	
	public boolean LESS(ITransport transport) {
		return parking.level(currentLevel).LESS(transport);
	}
	
	public void SaveData(String filename) {
		try {
			parking.SaveData(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void LoadData(String filename) {
		try {
			parking.LoadData(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
