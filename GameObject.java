package main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Creates and updates a game object.
 */
public abstract class GameObject
{
	protected int x, y;
	protected float velX = 0, velY = 0;
	protected ID id;

	/**
	 * Creates a game object.
	 * 
	 * @param x
	 * @param y
	 */
	public GameObject(int x, int y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	// Getters and Setters//
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public float getVelX()
	{
		return velX;
	}

	public void setVelX(float velX)
	{
		this.velX = velX;
	}

	public float getVelY()
	{
		return velY;
	}

	public void setVelY(float velY)
	{
		this.velY = velY;
	}

	public ID getId()
	{
		return id;
	}

	public void setId(ID id)
	{
		this.id = id;
	}
}
