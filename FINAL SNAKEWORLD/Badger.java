import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Badger extends Actor
{
	private int reproduce;
	
	public Badger()
	{
		setColor(Color.red);
		reproduce = 0;
	}
	
	public void act()
	{
		Grid<Actor> grid = getGrid();
		
		ArrayList<Location> theArray = grid.getEmptyAdjacentLocations(getLocation());
		
		if (theArray.size()>0)
		{
		int i = (int) (Math.random()*(theArray.size()));
		
		moveTo(theArray.get(i));
		}
		
		
		
		
		reproduce++;
		if (reproduce % 40==0)
		{
			Badger badger = new Badger();
			badger.putSelfInGrid(getGrid(), Main.randomly(grid.getOccupiedLocations(),grid));
		}
	}
}