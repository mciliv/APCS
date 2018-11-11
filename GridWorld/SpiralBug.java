import info.gridworld.actor.Bug;

public class SpiralBug extends Bug
{
	private int sidelength;
	private int steps;
	
	public SpiralBug()
	{
		super();
		sidelength = 1;
		steps = 0;
	}

	public void act()
	{
		if (steps < sidelength && canMove())
		{
			move();
			steps++;
		}
		else
		{
			turn();
			turn();
			steps = 0;
			sidelength++;
		}
	}

}
