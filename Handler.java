package main;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Adds, removes, and renders game objects within the game instance.
 */
public class Handler
{
	LinkedList<GameObject> object = new LinkedList<GameObject>();

	/**
	 * Updates the game per frame by updating each game object.
	 */
	public void tick()
	{
		for (int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);

			tempObject.tick();
		}
	}

	/**
	 * Renders the graphics for each game object.
	 * 
	 * @param g
	 */
	public void render(Graphics g)
	{
		for (int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	/**
	 * Adds the passed game object into game handler.
	 * 
	 * @param tempObject
	 */
	public void addObject(GameObject tempObject)
	{
		object.add(tempObject);
	}

	/**
	 * Removes the game object from the game handler.
	 * 
	 * @param tempObject
	 */
	public void removeObject(GameObject tempObject)
	{
		object.remove(tempObject);
	}
}
