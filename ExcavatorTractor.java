import java.awt.Color;
import java.awt.Graphics;

public class ExcavatorTractor extends Tractor {
    public Color DopColor;
    public Color FlagColor;
    public boolean Flag;
    public boolean FrontTube;
    public boolean BackTube;
    
    public ExcavatorTractor(int maxSpeed, int weight, Color mainColor, 
        Color dopColor, Color flagColor, boolean flag, boolean frontTube, boolean backTube)
    {
    	super(maxSpeed, weight, mainColor);
        DopColor = dopColor;
        FlagColor = flagColor;
        Flag = flag;
        FrontTube = frontTube;
        BackTube = backTube;
    }

    @Override
    public void Draw(Graphics g)
    {
    	super.Draw(g);
    	
        trackRollers.Draw(g, DopColor, _startPosX, _startPosY + 38);
        
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
