import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class PigCritter extends Critter
{
private int eaten;

public PigCritter()
{
	super();
	eaten = 0;
}
	public ArrayList<Actor> getActors()
	{
		return twoMove(getLocation());
		
	}
	
	public void processActors(ArrayList<Actor> actors)
	{
		int j = 1;
		for (Actor a: actors)
		{
			if (a instanceof Critter)
			{
				a.removeSelfFromGrid();
				eaten = 0;
				j=0;
				
			}
		}
		eaten += j;
		
		if(eaten>=5)
		{
			removeSelfFromGrid();
		}
	}
	
	public Location selectMoveLocation(ArrayList<Location> locs)
	{
		Location max = locs.get(0);
		int maxNum = 0;
		for (Location loc: locs)
		{
			if (twoMove(loc).size() > maxNum)
			{
				max = loc;
			}
		}
		
		return max;
		
	}
	
	
	public ArrayList<Actor> twoMove(Location loc)
	{
		Grid<Actor> grid = getGrid();
		
		ArrayList<Actor> critters = new ArrayList<Actor>();
		
		for (int i =  - 2; i <= 2; i++)
		{
			for (int j = 2; j <=  2; j++)
			{

				Location loc2 = new Location(loc.getRow() + i, loc.getCol() +j);

				if (grid.isValid(loc2) && grid.get(loc2) instanceof Critter && grid.get(loc2) != null && grid.get(loc2) != this)
				{
					Actor theActor = grid.get(loc2);
					critters.add(theActor);
				}
			}	
		}
		return critters;
	}
	
}
