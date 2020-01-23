package Classes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import Interfaces.IRollers;
import Interfaces.ITransport;

public class Parking<T extends ITransport, R extends IRollers> {
	private HashMap<Integer, T> _places;

    private int _maxCount;
    
    private int PictureWidth;
    private int PictureHeight;

    private final int _placeSizeWidth = 210;
    private final int _placeSizeHeight = 80;

    public Parking(int sizes, int pictureWidth, int pictureHeight)
    {
        _maxCount = sizes;
    	_places = new HashMap<Integer, T>();
        PictureWidth = pictureWidth;
        PictureHeight = pictureHeight;
    }
    
    public int getPictureWidth() {
    	return PictureWidth;
    }
    
    public int getPictureHeight() {
    	return PictureHeight;
    }
    
    public void setPictureWidth(int value) {
    	PictureWidth = value;
    }
    
    public void setPictureHeight(int value) {
    	PictureHeight = value;
    }

    public int ADD(T transport)
    {
    	if (_places.size() == _maxCount)
        {
            return -1;
        }

        for (int i = 0; i < _maxCount; i++)
        {
            if (CheckFreePlace(i))
            {
                _places.put(i, transport);
                _places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 15, PictureWidth, PictureHeight);
                return i;
            }
        }

        return -1;
    }
    
    public T SUBTRACT(int index)
    {
    	if (!CheckFreePlace(index))
        {
            T car = _places.get(index);
            _places.remove(index);
            return car;
        }
        return null;
    }
    
    public boolean MORE(T transport) {
    	T t = null;
    	for (int i = 0; i < _places.size(); i++) {
    		if (_places.get(i) != null) {
    			t = _places.get(i);
    		}
    	}
    	
    	if (t == null || t.hashCode() < transport.hashCode()) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean LESS(T transport) {
    	T t = null;
    	for (int i = 0; i < _places.size(); i++) {
    		if (_places.get(i) != null) {
    			t = _places.get(i);
    		}
    	}
    	
    	if (t != null && t.hashCode() > transport.hashCode()) {
    		return true;
    	} else {
    		return false;
    	}
    }

    private boolean CheckFreePlace(int index)
    {
        return _places.get(index) == null;
    }

    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < _maxCount; i++) {
            if (!CheckFreePlace(i)) {
                _places.get(i).Draw(g);
            }
        }
    }

    private void DrawMarking(Graphics g)
    {
    	g.setColor(Color.black);
        g.drawRect(0, 0, (_maxCount / 5) * _placeSizeWidth, 480);
        for (int i = 0; i < _maxCount / 5; i++) {
            for (int j = 0; j < 6; j++) {
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight,i * _placeSizeWidth + 110, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
        }
    }
    
    public void setTractor(int index, T tractor) {
    	_places.put(index, tractor);
    	_places.get(index).SetPosition(5 + index / 5 * _placeSizeWidth + 5, index % 5 * _placeSizeHeight + 15, PictureWidth, PictureHeight);
    }
    
    public ITransport get(int index) {
    	if (!CheckFreePlace(index))
        {
            return _places.get(index);
        }
    	return null;
    }
}
