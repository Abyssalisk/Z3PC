package main;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Adds, removes, and renders game objects within the game instance.
 */
public class Handler
{
	LinkedList<GameObject> objects = new LinkedList<GameObject>();

	private boolean up = false, down = false, right = false, left = false;

	/**
	 * Updates the game per frame by updating each game object.
	 */
	public void tick()
	{
		objects.parallelStream().forEach((object) ->
		{
			object.tick();
		});
	}

	/**
	 * Renders the graphics for each game object.
	 * 
	 * @param g
	 */
	public void render(Graphics g)
	{
		objects.parallelStream().forEach((object) ->
		{
			object.render(g);
		});
	}

	/**
	 * Adds the passed game object into game handler.
	 * 
	 * @param tempObject
	 */
	public void addObject(GameObject tempObject)
	{
		objects.add(tempObject);
	}

	/**
	 * Removes the game object from the game handler.
	 * 
	 * @param tempObject
	 */
	public void removeObject(GameObject tempObject)
	{
		objects.remove(tempObject);
	}

	// Getters and Setters//
	public boolean isUp()
	{
		return up;
	}

	public void setUp(boolean up)
	{
		this.up = up;
	}

	public boolean isDown()
	{
		return down;
	}

	public void setDown(boolean down)
	{
		this.down = down;
	}

	public boolean isRight()
	{
		return right;
	}

	public void setRight(boolean right)
	{
		this.right = right;
	}

	public boolean isLeft()
	{
		return left;
	}

	public void setLeft(boolean left)
	{
		this.left = left;
	}
}
