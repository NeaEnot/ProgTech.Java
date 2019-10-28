import java.awt.Color;
import java.awt.Graphics;

public abstract class Transport implements ITransport {

	protected int _startPosX;
    protected int _startPosY;
    protected int _pictureWidth;
    protected int _pictureHeight;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
	
	@Override
	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
	}

	abstract public void MoveTransport(Direction direction);
	abstract public void Draw(Graphics g);

}
