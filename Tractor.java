import java.awt.Color;
import java.awt.Graphics;

public class Tractor extends Transport {

	protected final int etWidth = 90;
    protected final int etHeight = 50;
    
    protected IRollers trackRollers;
	
    public Tractor(int maxSpeed, float weight, Color mainColor)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        
        switch ((int)(Math.random() * 3)) {
        case 0:
        	trackRollers = new TrackRollers((int)(Math.random() * 3) + 4);
        	break;
        case 1:
        	trackRollers = new TrackRollersCircle((int)(Math.random() * 3) + 4);
        	break;
        case 2:
        	trackRollers = new TrackRollersSpokes((int)(Math.random() * 3) + 4);
        	break;
        }
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

}
