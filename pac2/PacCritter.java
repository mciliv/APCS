package pacWorld;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class PacCritter extends Critter
{
	private boolean pelletEaten;
	private boolean ghostCritterOnGuard;
	private Color pelletColor;
	private Color pacColor;
	private int powerSteps;

	public PacCritter()
	{
		super();
		pelletEaten = false;
		ghostCritterOnGuard = false;
		pacColor = Color.yellow;
		powerSteps = 0;

	}

	public ArrayList<Actor> getActors()
	{
		ArrayList<Actor> theActors = new ArrayList<Actor>();
		Actor theActor = getGrid().get(getLocation().getAdjacentLocation(getDirection()));
		if (theActor instanceof Pellet)
		{
			Actor thePellet = getGrid().get(getLocation().getAdjacentLocation(getDirection()));
			theActors.add(theActor);
			pelletEaten = true;
			pelletColor = thePellet.getColor();
		} else
		{
			pelletEaten = false;
			if (theActor instanceof GhostCritter)
			{
				ghostCritterOnGuard = true;
			}
		}
		theActors.add(Runner.getGameKeeper());
		return theActors;
	}

	public void processActors(ArrayList<Actor> actors)
	{
		Grid<Actor> grid = getGrid();
		Location thisLoc = getLocation();
		int gameKeeperIndex = -1; //Make sure this is okay
		//There will always be a GameKeeper
		for (int i = 0; i<actors.size(); i++)
		{
			if (actors.get(i) instanceof GameKeeper)
			{
				gameKeeperIndex = i;
				GameKeeper gameKeeper = (GameKeeper) actors.get(i);

				if (pelletEaten)
				{
					if (pelletColor.equals(Color.red))
					{
						gameKeeper.setScore(gameKeeper.getScore() + 3);
						powerSteps = 40;
						setColor(Color.red);
					} else
					{
						gameKeeper.setScore(gameKeeper.getScore() + 1);
					}
				}
			}
		}

		GameKeeper gameKeeper = (GameKeeper) actors.get(gameKeeperIndex);

		if (pelletEaten)
		{
			if (pelletColor.equals(Color.red))
			{
				gameKeeper.setScore(gameKeeper.getScore() + 3);
				powerSteps = 40;
				setColor(Color.red);
			} else
			{
				gameKeeper.setScore(gameKeeper.getScore() + 1);
			}
		}

		if (powerSteps==0)
		{
			setColor(pacColor);
		}
		
		for (Actor a: actors)
		{
			if (a instanceof GhostCritter)
			{
				if (getColor().equals(Color.red))
				{
					a.removeSelfFromGrid();
				} else
				{
					gameKeeper.onPacJustEaten();
				}
			}
		}

	}

	public ArrayList<Location> getMoveLocations()
	{
		Grid<Actor> grid = getGrid();
		Location thisLoc = getLocation();
		ArrayList<Location> locs = grid.getValidAdjacentLocations(thisLoc);
		ArrayList<Location>	newLocs	= new ArrayList<Location>();

		for (Location loc: locs)
		{
			if (!(grid.get(loc) instanceof Rock))
			{
				newLocs.add(loc);	
			}				
		}	
		return newLocs;
	}

	public Location selectMoveLocation(ArrayList<Location> locs)
	{
		for (Location loc: locs)
		{
			if ((getLocation().getDirectionToward(loc)) == getDirection())
			{
				if (powerSteps>0)
					powerSteps--;

				return loc;
			}
		}
		return getLocation();
	}
}
