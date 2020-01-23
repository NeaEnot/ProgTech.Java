package Classes;

import java.awt.Color;
import java.awt.Graphics;

import Enums.Direction;
import Interfaces.IRollers;

public class Tractor extends Transport {

	protected final int etWidth = 90;
    protected final int etHeight = 50;
    
    protected IRollers trackRollers;
	
    public Tractor(int maxSpeed, float weight, Color mainColor)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        
        trackRollers = new TrackRollers((int)(Math.random() * 3) + 4);
    }
    
    public Tractor(String info)
    {
        String[] strs = info.split(";");
        if (strs.length == 3)
        {
            MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Float.parseFloat(strs[1]);
            MainColor = new Color(Integer.parseInt(strs[2]));
        }
        
        trackRollers = new TrackRollers((int)(Math.random() * 3) + 4);
    }
    
    public void setTrackRollers(IRollers rollers) {
    	trackRollers = rollers;
    }
    
	@Override
	public void MoveTransport(Direction direction) {
		float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            case Right:
                if (_startPosX + step < _pictureWidth - etWidth)
                {
                    _startPosX += step;
                }
                break;
            case Left:
                if (_startPosX - step > 5)
                {
                    _startPosX -= step;
                }
                break;
            case Up:
                if (_startPosY - step > 0)
                {
                    _startPosY -= step;
                }
                break;
            case Down:
                if (_startPosY + step < _pictureHeight - etHeight)
                {
                    _startPosY += step;
                }
                break;
        }
	}

	@Override
	public void Draw(Graphics g) {
        g.setColor(MainColor);
        g.fillRect(_startPosX + 3, _startPosY + 25, 50, 15);
        g.drawRect(_startPosX + 40, _startPosY + 7, 7, 25);
        g.setColor(Color.GRAY);
        g.fillOval(_startPosX, _startPosY + 35, 55, 15);
        
        trackRollers.Draw(g, Color.BLACK, _startPosX, _startPosY + 38);
	}
	
	@Override
	public String ToString()
    {
        return MaxSpeed + ";" + Weight + ";" + MainColor.getRGB();
    }

}
