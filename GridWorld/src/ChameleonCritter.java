import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;



public class ChameleonCritter extends Critter
{
	public void processActors(ArrayList<Actor> actors)
	{
		int randomcritter = (int) Math.random() * actors.size();
		if (actors.size()!=0)
		{
		setColor((actors.get(randomcritter)).getColor());
		}
	}
	
	public void makeMove(Location loc)
	{
		int toTurn = (getLocation()).getDirectionToward(loc);
		
		if (loc == null)
		{
			removeSelfFromGrid();
		} else
		{
			setDirection(toTurn);
			moveTo(loc);
			
		}
	}
}
