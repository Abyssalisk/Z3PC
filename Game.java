package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * Creates a runnable instance of the game that can be started or stopped.
 */
public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = 1L;

	public boolean isRunning = false;
	private Thread thread;
	private Handler handler;

	/**
	 * Creates the game instance.
	 */
	public Game()
	{
		new Window(1920, 1080, "Z3PC", this);
		start();

		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));

		handler.addObject(new Player(100, 100, ID.Player, handler));

	}

	/**
	 * Starts the game instance.
	 */
	private void start()
	{
		isRunning = true;
		thread = new Thread(this);
		thread.start();

	}

	/**
	 * Stops the game instance.
	 */
	private void stop()
	{
		isRunning = false;
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Runs the 60FPS method developed by Notch.
	 */
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.nanoTime();
		int frames = 0;
		while (isRunning)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1)
			{
				tick();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	/**
	 * Simulates a frame advance to update the game.
	 */
	public void tick()
	{
		handler.tick();
	}

	/**
	 * Renders the 2D graphics to the Window.
	 */
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Background
		g.setColor(Color.white);
		g.fillRect(0, 0, 1920, 1080);

		// Game Objects
		handler.render(g);

		g.dispose();
		bs.show();
	}

	/**
	 * Launches a new game instance through main.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Game();
	}
}
