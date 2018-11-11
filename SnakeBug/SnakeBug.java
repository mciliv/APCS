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

		} else
		{

		}


	}

	public boolean canMove()
	{
		 if (super.canMove())
		 {


		if (getGrid().isValid(getFrontLocation()))
		{

		}
		if((getGrid()).get(getFrontLocation()) instanceof Flower)
		{

		}
		 }
	}

	public Location getFrontLocation()
	{
		return (getLocation()).getAdjacentLocation(getDirection());
	}
}
