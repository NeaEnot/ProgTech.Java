package Classes;

import java.awt.Color;
import java.awt.Graphics;

import Enums.NumberOfRollers;
import Interfaces.IRollers;

public class TrackRollers implements IRollers {
	private NumberOfRollers numberOfRollers;
	
	public TrackRollers(int n) {
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
		g.setColor(color);
		
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
			g.fillOval(startPosX + r * i, startPosY, 10, 10);
		}
	}
}
