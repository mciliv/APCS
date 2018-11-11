import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
	private int sidelength;
	private int steps;
	private int check;

	public ZBug(int sidelength)
	{
		super();
		this.sidelength = sidelength;
		steps = 0;
		check = 0;
		turn();
		turn();
	}

	public void act()
	{
		int place = 0;


		if (check!=2)
		{
			if (steps < sidelength && canMove())
			{	
				move();
				steps++;
				if (check == 1 && steps == sidelength)
				{
					check = 2;
				}

			}
			else
			{

				if (getDirection() == 90)
				{
					turn();
					turn();	
					turn();
				} else
				{
					turn();
					turn();
					turn();
					turn();
					turn();
					check = 1;
				}
				steps = 0;
				if (canMove()==false)
				{
					check=2;
				}

			}
		}


//		if (canMove() && steps < sidelength)
//		{
//			for (int i = 0; i <= sidelength; i++)
//			{
//				int count = 3;
//				if (place % 2 == 0)
//				{
//					count = 5;
//				}
//				for (int j = 0; i < count; i++)
//				{
//
//				}
//				if(i == sidelength)
//				{
//					turn();
//					turn();
//					turn();
//				}
//				move();
//
//			}
//		}
	}
}
