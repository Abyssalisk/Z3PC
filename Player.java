package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject
{
	Handler handler;

	public Player(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
	}

	public void tick()
	{
		x += velX;
		y += velY;

		// KeyEvent Movement
		if (handler.isUp() && !handler.isDownPriority())
		{
			velY = -3;
		} else if (!handler.isDown())
		{
			velY = 0;
		}

		if (handler.isDown() && !handler.isUpPriority())
		{
			velY = 3;
		} else if (!handler.isUp())
		{
			velY = 0;
		}

		if (handler.isRight() && !handler.isLeftPriority())
		{
			velX = 3;
		} else if (!handler.isLeft())
		{
			velX = 0;
		}

		if (handler.isLeft() && !handler.isRightPriority())
		{
			velX = -3;
		} else if (!handler.isRight())
		{
			velX = 0;
		}
	}

	/**
	 * Renders the Player Sprite.
	 */
	public void render(Graphics g)
	{
		g.setColor(Color.green);
		g.fillRect(x, y, 50, 75);
	}

	/**
	 * Initializes the Player hit box.
	 */
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 50, 75);
	}
}
