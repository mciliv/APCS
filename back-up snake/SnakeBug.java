import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class SnakeBug extends Bug  
{
	private int tailLength;
	private ArrayList<Location> locations;

	public SnakeBug()
	{
		tailLength = 6;
		locations = new ArrayList<Location>();
	}

	public void act()
	{
		if (canMove())
		{
			Grid<Actor> grid = getGrid();
			Location loc = getLocation();

			locations.add(getLocation());
			if(locations.size()>=tailLength)
			{
				(grid.get(locations.get(0))).removeSelfFromGrid();
				locations.remove(0);
			}

			if (grid.get(getFrontLocation()) instanceof Seed)
			{
				tailLength += 2;

				Seed theSeed = new Seed();
				move();
				

				ArrayList<Location> occupied = grid.getOccupiedLocations();
				

				int checker = 1;
				while (checker ==1)
				{
					checker = 2;
					
					int r = (int) (Math.random()*grid.getNumRows());
					int c = (int) (Math.random()*grid.getNumCols());
					Location newLoc = new Location(r,c);
					

					for (int i = 0; i<occupied.size(); i++)
					{
						if ((occupied.get(i)).equals(newLoc))
						{
							checker=1;
						}
					}
					if (checker == 2)
					{
						theSeed.putSelfInGrid(grid, newLoc);
						break;
					}
					
				} 
				

				
			}
			else
			{
				move();
			}

			
			

		} else
		{
			Grid<Actor> grid = getGrid();
			Location loc = getLocation();
			removeSelfFromGrid();
			Rock leRock = new Rock();
			leRock.putSelfInGrid(grid, loc);
		}
	}

	public boolean canMove()
	{
		if (!getGrid().isValid(getFrontLocation())
				|| (getGrid()).get(getFrontLocation()) instanceof Flower
				|| (getGrid()).get(getFrontLocation()) instanceof Badger)
		{
			return false;
		}
		if (getGrid().get(getFrontLocation()) instanceof Seed)
		{
			return true;
		}
		return super.canMove();
	}

	public Location getFrontLocation()
	{
		return (getLocation()).getAdjacentLocation(getDirection());
	}
}
