import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class SnakeBug extends Bug  
{

	public SnakeBug()
	{

	}

	public void act()
	{
		if (canMove())
		{

			
			move();

		} else
		{
			if (!getGrid().isValid(getFrontLocation())
					|| (getGrid()).get(getFrontLocation()) instanceof Flower
					|| (getGrid()).get(getFrontLocation()) instanceof Badger)
			{
				removeSelfFromGrid();
				Rock leRock = new Rock();
				leRock.putSelfInGrid(getGrid(), getLocation());


			}
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
