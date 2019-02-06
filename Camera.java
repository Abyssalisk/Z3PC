package main;

public class Camera
{
	private float x, y;

	public Camera(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject object)
	{
		// Fixed Camera //
		x += (object.getX() - x) - 1920 / 2;
		y += (object.getY() - y) - 1080 / 2;

		// Fluid Camera //
		// x += ((object.getX() - x) - 1920 / 2) * 0.05f;
		// y += ((object.getY() - y) - 1080 / 2) * 0.05f;

		if (x <= 0)
		{
			x = 0;
		}

		if (x >= 1952) // TODO dynamically adjust to current map size
		{
			x = 1952;
		}

		if (y <= 0)
		{
			y = 0;
		}

		if (y >= 1096) // TODO dynamically adjust to current map size
		{
			y = 1096;
		}
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}
}
