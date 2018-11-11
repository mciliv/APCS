import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class SnakeBug extends Bug  
{
private int seedsEaten;
	
	public SnakeBug()
	{
		seedsEaten = 5;
	}

	public void act()
	{
		if (canMove())
		{
			move();

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
		return super.canMove();
	}

	public Location getFrontLocation()
	{
		return (getLocation()).getAdjacentLocation(getDirection());
	}
}
