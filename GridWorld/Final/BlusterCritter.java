import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class BlusterCritter extends Critter
{

	private int c;
	public BlusterCritter(int c)
	{
		super();
		this.c = c;
		
	}
	
	public ArrayList<Actor> getActors()
	{
		return twoMove(getLocation());
	}
	
	public void processActors(ArrayList<Actor> actors)
	{
		int crittercounter = 0;
		for (int i = 0; i<actors.size(); i++)
		{
			if (actors.get(i) instanceof Critter)
			{
				crittercounter++;
			}
		}
		
		
		
		
		
		if (crittercounter>=c)
		{
			Color color = this.getColor();
			setColor(color.darker());
		} else
		{
			Color color = this.getColor();
			setColor(color.brighter());
		}
		
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

				if (grid.isValid(loc2) && grid.get(loc2) instanceof Actor && grid.get(loc2) != null && grid.get(loc2) != this)
				{
					Actor theActor = grid.get(loc2);
					critters.add(theActor);
				}
			}	
		}
		return critters;
	}
	

}
