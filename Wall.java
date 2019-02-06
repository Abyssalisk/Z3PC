package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject
{
	public Wall(int x, int y, ID id)
	{
		super(x, y, id);
	}

	public void tick()
	{
	}

	public void render(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 32, 32);
	}
}
