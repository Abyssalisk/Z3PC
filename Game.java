package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Creates a runnable instance of the game that can be started or stopped.
 */
public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;

	public boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private BufferedImage level = null;

	/**
	 * Creates the game instance.
	 */
	public Game()
	{
		new Window(1920, 1080, "Z3PC", this);
		start();

		handler = new Handler();
		camera = new Camera(0, 0);

		this.addKeyListener(new KeyInput(handler));

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/test_map.png");

		loadLevel(level);
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
		handler.objects.parallelStream().forEach((object) ->
		{
			if (object.getId() == ID.Player)
			{
				camera.tick(object);
			}
		});

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
		Graphics2D g2d = (Graphics2D) g;

		// Background
		g.setColor(Color.black);
		g.fillRect(0, 0, 1920, 1080);
		g2d.translate(-camera.getX(), -camera.getY());

		// Game Objects
		handler.render(g);

		g.dispose();
		bs.show();
	}

	/**
	 * Loads an image from the resource folder and creates the level using the
	 * color values used on the images pixels.
	 * 
	 * @param image
	 */
	private void loadLevel(BufferedImage image)
	{
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++)
		{
			for (int yy = 0; yy < h; yy++)
			{
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255)
				{
					handler.addObject(new Wall(xx * 32, yy * 32, ID.Wall));
				}

				if (blue == 255)
				{
					handler.addObject(new Player(xx * 32, yy * 32, ID.Player, handler));
				}
			}
		}
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
