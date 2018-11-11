import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class KingCrab extends CrabCritter
{

	public void processActors(ArrayList<Actor> actors)
	{
		for (Actor a: actors)
		{
			if (adjacentLoc(a)==null)
			{
				a.removeSelfFromGrid();
			}
			
		}
	}

	public Location adjacentLoc(Actor a)
	{
		Grid<Actor> grid = getGrid();
		
		ArrayList<Location> locations = grid.getEmptyAdjacentLocations(a.getLocation());
		ArrayList<Location> finallocations = new ArrayList<Location>();
		
		for (Location loc: locations)
		{
			if (((getLocation()).compareTo(loc)>1 || (getLocation()).compareTo(loc)<-1))
			{
				a.moveTo(loc);
				return loc;
			}
		}
		return null;

//		if(getLocation().getRow()==a.getLocation().getRow())
//		{
//			if (getLocation().getCol()<a.getLocation().getCol())
//			{
//				Location loc = new Location(a.getLocation().getRow(),a.getLocation().getCol()+1);
//				
//				return loc;
//			} else
//			{
//				Location loc = new Location(a.getLocation().getRow(),a.getLocation().getCol()-1);
//				return loc;
//			}
//		} else
//		{
//			if (getLocation().getRow()<a.getLocation().getCol())
//			{
//				Location loc = new Location(a.getLocation().getRow()+1,a.getLocation().getCol());
//				return loc;
//			} else
//			{
//				Location loc = new Location(a.getLocation().getRow()-1,a.getLocation().getCol());
//				return loc;
//			}
//		}
	
	}
}
