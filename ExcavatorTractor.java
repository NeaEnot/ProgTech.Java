import java.awt.Color;
import java.awt.Graphics;

public class ExcavatorTractor {
	private int _startPosX;
    private int _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    final private int etWidth = 90;
    final private int etHeight = 50;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color DopColor;
    public Color FlagColor;
    public boolean Flag;
    public boolean FrontTube;
    public boolean BackTube;
    
    private TrackRollers trackRollers;
    
    public ExcavatorTractor(int d, int e, Color mainColor, 
        Color dopColor, Color flagColor, boolean flag, boolean frontTube, boolean backTube)
    {
        MaxSpeed = d;
        Weight = e;
        MainColor = mainColor;
        DopColor = dopColor;
        FlagColor = flagColor;
        Flag = flag;
        FrontTube = frontTube;
        BackTube = backTube;
        trackRollers = new TrackRollers((int)(Math.random() * 3) + 4);
    }

    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public void MoveTransport(Direction direction)
    {
        float step = MaxSpeed * 100 / Weight;
        switch (direction) {
            case Right:
                if (_startPosX + step < _pictureWidth - etWidth) {
                    _startPosX += step;
                    _startPosY += step;
                }
                break;
            case Left:
                if (_startPosX - step > 5) {
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

    public void DrawET(Graphics g)
    {
        g.setColor(MainColor);
        g.fillRect(_startPosX + 3, _startPosY + 25, 50, 15);
        g.drawRect(_startPosX + 40, _startPosY + 7, 7, 25);
        
        g.setColor(Color.GRAY);
        g.fillOval(_startPosX, _startPosY + 35, 55, 15);
        trackRollers.DrawTrackRollers(g, DopColor, _startPosX, _startPosY + 38);
        
        g.setColor(DopColor);
        g.drawLine(_startPosX + 3, _startPosY + 30, _startPosX - 2, _startPosY + 30);
        g.drawLine(_startPosX + 3, _startPosY + 35, _startPosX - 2, _startPosY + 35);
        g.drawLine(_startPosX - 2, _startPosY + 20, _startPosX - 2, _startPosY + 45);
        g.drawLine(_startPosX - 2, _startPosY + 20, _startPosX - 5, _startPosY + 15);
        g.drawLine(_startPosX - 2, _startPosY + 45, _startPosX - 5, _startPosY + 50);
 
        g.drawLine(_startPosX + 50, _startPosY + 25, _startPosX + 60, _startPosY + 10);
        g.drawLine(_startPosX + 60, _startPosY + 10, _startPosX + 80, _startPosY + 25);
        g.drawLine(_startPosX + 80, _startPosY + 25, _startPosX + 80, _startPosY + 45);
        g.drawLine(_startPosX + 80, _startPosY + 30, _startPosX + 70, _startPosY + 30);
        g.drawLine(_startPosX + 80, _startPosY + 45, _startPosX + 65, _startPosY + 50);
        
        if (Flag) {
            g.drawLine(_startPosX + 47, _startPosY + 10, _startPosX + 47, _startPosY);
            g.setColor(FlagColor);
            g.fillRect(_startPosX + 47, _startPosY, 20, 7);
        }

        g.setColor(MainColor);
        if (FrontTube) {
            g.fillRect(_startPosX + 15, _startPosY + 10, 5, 15);
        }

        if (BackTube) {
            g.fillRect(_startPosX + 23, _startPosY + 10, 5, 15);
        }
    }
}
