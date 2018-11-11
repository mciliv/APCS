package pacWorld;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Critter;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class GhostCritter extends Critter
{
    public GhostCritter(Color colorParam, double movementAccuracyParam, int stepsPerMoveParam)
    {
    	super();
    	setColor(colorParam);
    }
    
//    public ArrayList<Actor> getActors()
//    {
//    	
//    }
    
    public ArrayList<Location> getMoveLocations()
    {
    	Grid<Actor> grid = getGrid();
    	Location thisLoc = getLocation();
    	ArrayList<Location> locs = grid.getValidAdjacentLocations(thisLoc);
    	ArrayList<Location> newLocs = new ArrayList<Location>();
    	for (Location loc: locs)
    	{
    		int compDirection = thisLoc.getDirectionToward(loc);
    		if (!(grid.get(loc) instanceof Rock) && 0 == compDirection % 90)
    		{
    			newLocs.add(loc);
    		}
    	}
    	return newLocs;
    }
    
    

    public void setActorImOnTopOf(Actor a, Location loc)
    {
    }
    
    public void replaceActorIWasOnTopOf(Grid<Actor> grid)
    {
    }
    
    public void reset()
    {
    }
}
