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
					if (handler.isDown())
					{
						handler.setUpPriority(true);
					}
				}

				if (key == KeyEvent.VK_S)
				{
					handler.setDown(true);
					if (handler.isUp())
					{
						handler.setDownPriority(true);
					}
				}

				if (key == KeyEvent.VK_A)
				{
					handler.setLeft(true);
					if (handler.isRight())
					{
						handler.setLeftPriority(true);
					}
				}

				if (key == KeyEvent.VK_D)
				{
					handler.setRight(true);
					if (handler.isLeft())
					{
						handler.setRightPriority(true);
					}
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
					handler.setUpPriority(false);
					handler.setDownPriority(false);
				}

				if (key == KeyEvent.VK_S)
				{
					handler.setDown(false);
					handler.setDownPriority(false);
					handler.setUpPriority(false);
				}

				if (key == KeyEvent.VK_A)
				{
					handler.setLeft(false);
					handler.setLeftPriority(false);
					handler.setRightPriority(false);
				}

				if (key == KeyEvent.VK_D)
				{
					handler.setRight(false);
					handler.setRightPriority(false);
					handler.setLeftPriority(false);
				}
			}
		});
	}
}
