package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	Handler handler;

	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}

	/**
	 * Determines if keyboard input is present and executes the desired action.
	 */
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();

		handler.objects.parallelStream().forEach((object) ->
		{
			if (object.getId() == ID.Player)
			{
				if (key == KeyEvent.VK_W)
				{
					handler.setUp(true);
				}

				if (key == KeyEvent.VK_S)
				{
					handler.setDown(true);
				}

				if (key == KeyEvent.VK_A)
				{
					handler.setLeft(true);
				}

				if (key == KeyEvent.VK_D)
				{
					handler.setRight(true);
				}

			}
		});
	}

	/**
	 * Determines if keyboard input is released and stops the current action.
	 */
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();

		handler.objects.parallelStream().forEach((object) ->
		{
			if (object.getId() == ID.Player)
			{
				if (key == KeyEvent.VK_W)
				{
					handler.setUp(false);
				}

				if (key == KeyEvent.VK_S)
				{
					handler.setDown(false);
				}

				if (key == KeyEvent.VK_A)
				{
					handler.setLeft(false);
				}

				if (key == KeyEvent.VK_D)
				{
					handler.setRight(false);
				}
			}
		});
	}
}
