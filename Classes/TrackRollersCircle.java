package Classes;

import java.awt.Color;
import java.awt.Graphics;

import Enums.NumberOfRollers;
import Interfaces.IRollers;

public class TrackRollersCircle implements IRollers {

	private NumberOfRollers numberOfRollers;
	
	public TrackRollersCircle(int n) {
		switch (n) {
		case 4:
			numberOfRollers = NumberOfRollers.Four;
			break;
		case 5:
			numberOfRollers = NumberOfRollers.Five;
			break;
		case 6:
			numberOfRollers = NumberOfRollers.Six;
			break;
		default:
			numberOfRollers = NumberOfRollers.Four;
			break;
		}
	}
	
	@Override
	public void Draw(Graphics g, Color color, int startPosX, int startPosY) {
		int num, r;
		
		switch (numberOfRollers) {
		case Four:
			num = 4;
			r = 15;
			break;
		case Five:
			num = 5;
			r = 11;
			break;
		case Six:
			num = 6;
			r = 9;
			break;
		default:
			num = 4;
			r = 14;
			break;
		}
		
		for (int i = 0; i < num; i++) {
			g.setColor(color);
			g.fillOval(startPosX + r * i, startPosY, 10, 10);
			
			Color alter = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
			g.setColor(alter);
			
			g.fillOval(startPosX + r * i, startPosY + 3, 3, 3);
			g.fillOval(startPosX + r * i + 6, startPosY + 3, 3, 3);
			g.fillOval(startPosX + r * i + 3, startPosY, 3, 3);
			g.fillOval(startPosX + r * i + 3, startPosY + 6, 3, 3);
		}
	}
}
