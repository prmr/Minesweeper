package samples;

public class Rectangle
{
	private int aWidth = 0;
	private int aHeight = 0;
	
	public Rectangle(int pWidth, int pHeight)
	{
		aWidth = pWidth;
		aHeight = pHeight;
	}
	
	public int getWidth()
	{
		return aWidth;
	}
	
	public int getHeight()
	{
		return aWidth;
	}
	
	public void setWidth( int pWidth )
	{
		aWidth = pWidth;
	}
	
	public void setHeight( int pHeight )
	{
		aHeight = pHeight;
	}
	
	public int getArea()
	{
		return aHeight * aWidth;
	}
	
	public static int getArea(Rectangle pRectangle)
	{
		return pRectangle.aHeight * pRectangle.aWidth;
	}
}
