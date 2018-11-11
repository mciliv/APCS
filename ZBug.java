import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
	private int sidelength;
	private int steps;
	
	public ZBug(int sidelength)
	{
		super();
		this.sidelength = sidelength;
		steps = 0;
	}
	
	public void act()
	{
		int place = 0;
		
		if (canMove() && steps < sidelength)
		{
			for (int i = 0; i <= sidelength; i++)
			{
				int count = 3;
				if (place % 2 == 0)
				{
					count = 5;
				}
				for (int j = 0; i < count; i++)
				{
					
				}
				if(i == sidelength)
				{
					turn();
					turn();
					turn();
				}
				move();
				
			}
		}
	}
}
