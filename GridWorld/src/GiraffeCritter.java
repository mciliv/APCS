import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class GiraffeCritter extends Critter
{
	private int stomachSize;
	private int neckSize;
	public GiraffeCritter(int stomachSize, int neckSize)
	{
		this.stomachSize = stomachSize;
		this.neckSize = neckSize;
	}

	public ArrayList<Actor> getActors()
	{
		Location loc = getLocation();
		Grid grid = getGrid();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		for (int i =  - neckSize; i <= neckSize; i++)
		{
			for (int j = neckSize; j <=  neckSize; j++)
			{

				Location loc2 = new Location(loc.getRow() + i, loc.getCol() +j);

				if (grid.isValid(loc2) && grid.get(loc2) instanceof Actor)
				{
					actors.add((Actor) grid.get(loc2));
				}
			}
		}

		return actors;
	}

	public void processActors(ArrayList<Actor> actors)
	{
		int t = 0;
		for (Actor a: actors)
		{
			if (a instanceof Flower && t<stomachSize)
			{
					a.removeSelfFromGrid();
					t++;
				
			}
			if (t==stomachSize)
			{
				
			}
		}
	}
	
	public Location selectMoveLocation(ArrayList<Location> locs)
	{
		
	}
	
	public void makeMove(Location loc)
	{
		
	}
}
