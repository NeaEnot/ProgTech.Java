package Interfaces;

import java.awt.Graphics;

import Enums.Direction;

public interface ITransport {
	void SetPosition(int x, int y, int width, int height);
    void MoveTransport(Direction direction);
    void Draw(Graphics g);
	String ToString();
}
