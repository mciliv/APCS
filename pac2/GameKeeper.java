package pacWorld;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class GameKeeper extends Actor
{
	private int score = 0;
	private int lives = 3;

	private Location[] scoreLocations;
	private Location livesLocation;
	private boolean pacJustEaten = false;
	private PacCritter pac;
	private GhostCritter[] ghosts;

	public GameKeeper(Location[] scoreLocationsParam, Location livesLocationParam, PacCritter pacParam, GhostCritter[] ghostsParam)
	{
		super();
		scoreLocations = scoreLocationsParam;
		livesLocation = livesLocationParam;
		pac = pacParam;
		ghosts = ghostsParam;
	}

	public void act()
	{
		if (!anyPelletsRemaining())
			initializeBoard();

		if (pacJustEaten)
		{
			// On the last step, someone told us that the PacCritter was
			// eaten.  So take care of resetting the board or ending the
			// entire game now
			lives--;
			if (lives == 0)
			{
				gameOver();
			}
			else
			{
				resetBoard();
			}
			pacJustEaten = false;
		}

		displayScore();
		displayLives();
	}

	public void onPacJustEaten()
	{
		pacJustEaten = true;
	}

	private boolean anyPelletsRemaining()
	{
		// TODO: Implement for Part 1
		Grid<Actor> grid = getGrid();
		ArrayList<Location> locs = grid.getOccupiedLocations();
		for (Location loc: locs)
		{
			if (grid.get(loc) instanceof Pellet)
			{
				return true;
			}
		}
		return false;
	}

	private void resetBoard()
	{
		Grid<Actor> grid = getGrid();

		// Reset PacCritter's position by removing it from the grid, and then putting
		// it at its home location.
		if (pac.getGrid() != null)
			pac.removeSelfFromGrid();
		pac.putSelfInGrid(grid, new Location(13, 7));
	}

	private void gameOver()
	{
		if (pac.getLocation() != null)
			pac.removeSelfFromGrid();
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int scoreParam)
	{
		score = scoreParam;
	}

	private void displayScore()
	{
		// TODO: Implement for Part 1
		Grid<Actor> grid = getGrid();
		int num = getScore();
		int ones = num % 10;
		num -= ones;
		(getNumberActorFromInteger(ones)).putSelfInGrid(grid, scoreLocations[0]);

		int tens = num % 100;
		num -= tens;
		tens /= 10;
		(getNumberActorFromInteger(tens)).putSelfInGrid(grid, scoreLocations[1]);

		int hundreds = num % 1000;
		num -= hundreds;
		hundreds /= 100;
		(getNumberActorFromInteger(hundreds)).putSelfInGrid(grid, scoreLocations[2]);

		int thousands = num /1000;
		(getNumberActorFromInteger(thousands)).putSelfInGrid(grid, scoreLocations[3]);

		//    	for (int i = 0; i<4; i++)
		//    	{
		//    		if (i<3)
		//    		int change = num % (int) Math.pow(10, (i+1));
		//    		else
		//    			int change = num;
		//    			
		//    	(getNumberActorFromInteger(change)).putSelfInGrid(grid, scoreLocations[i]);
		//    	num
		//    	}

	}

	private void displayLives()
	{
		Actor numActor = getNumberActorFromInteger(lives);
		getGrid().put(livesLocation, numActor);
	}

	private Actor getNumberActorFromInteger(int num)
	{
		if (num <= 0)
			return new NumberZero();

		if (num == 1)
			return new NumberOne();

		if (num == 2)
			return new NumberTwo();

		if (num == 3)
			return new NumberThree();

		if (num == 4)
			return new NumberFour();

		if (num == 5)
			return new NumberFive();

		if (num == 6)
			return new NumberSix();

		if (num == 7)
			return new NumberSeven();

		if (num == 8)
			return new NumberEight();

		if (num == 9)
			return new NumberNine();

		return null;
	}

	public void initializeBoard()
	{
		//Problem fixed

		// Reset locations of PacCritter and GhostCritters
		resetBoard();

		// Add the walls and pellets and the GhostCritters
		addWalls();
		addPellets();
		addGhostCritters();
	}

	private void addGhostCritters()
	{
		Grid<Actor> grid = getGrid();
		
		ghosts[0].putSelfInGrid(grid, new Location(2,1));
		ghosts[1].putSelfInGrid(grid, new Location(grid.getNumRows()-2, 1));
		ghosts[2].putSelfInGrid(grid, new Location(grid.getNumRows()-2,grid.getNumCols()-2));
		ghosts[3].putSelfInGrid(grid, new Location(2,grid.getNumCols()-2));
	}

	private void addWalls()
	{
		addFrame();

		addBox(3, 4, 2, 6);
		addBox(3, 4, 8, 12);

		addTWall(6, 8, 5, 9);
		addSideTWall(6, 10, 3, 5);
		addSideTWall(6, 10, 11, 9);

		addTWall(10, 12, 5, 9);

		addTWall(14, 16, 5, 9);
		addTWall(16, 14, 2, 4);
		addTWall(16, 14, 10, 12);

		addBox(18, 18, 5, 9);

		addBox(12, 12, 1, 3);
		addBox(12, 12, 11, 13);
	}

	// Adds rocks around the perimeter of the world, except
	// it leaves row 0 empty, so it can be used to display
	// score and lives.
	private void addFrame()
	{
		// TODO: Implement for Part 1
		Grid<Actor> grid = getGrid();

		for (int i = 1; i< grid.getNumCols()-1; i++)//Edits the horizontal lines
		{
			addRock(1,i);
			addRock(grid.getNumRows()-1, i);
		}
		for (int i = 1; i<grid.getNumRows(); i++)//Edits the vertical lines
		{ 		
			addRock(i,0);
			addRock(i, grid.getNumCols()-1);
		}
	}

	private void addBox(int rowTop, int rowBottom, int colLeft, int colRight)
	{
		for (int row = rowTop; row <= rowBottom; row++)
		{
			for (int col = colLeft; col <= colRight; col++)
			{
				addRock(row, col);
			}
		}
	}

	private void addTWall(int rowTop, int rowBottom, int colTopL, int colTopR)
	{
		// Upper part of T
		for (int col=colTopL; col <= colTopR; col++)
		{
			addRock(rowTop, col);
		}

		// Middle perpendicular
		for (int row=Math.min(rowTop, rowBottom); row <= Math.max(rowTop, rowBottom); row++)
		{
			addRock(row, (colTopL + colTopR) / 2);
		}
	}

	private void addSideTWall(int rowTop, int rowBottom, int colL, int colR)
	{
		// Side part of T
		for (int row=rowTop; row <= rowBottom; row++)
		{
			addRock(row, colL);
		}

		// Middle perpendicular
		for (int col=Math.min(colL, colR); col <= Math.max(colL, colR); col++)
		{
			addRock((rowTop + rowBottom) / 2, col);
		}
	}

	private void addRock(int row, int col)
	{
		Rock rock = new Rock();
		rock.setColor(Color.BLUE);
		rock.putSelfInGrid(getGrid(), new Location(row, col));
	}

	// Adds pellets in all empty spaces (except row 0, reserved
	// for score and lives).
	private void addPellets()
	{
		Grid<Actor> grid = getGrid(); 
		int numRows = grid.getNumRows();
		int numCols = grid.getNumCols();

		// Add regular pellets in all empty spaces
		for (int row = 1; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
			{
				Location loc = new Location(row, col);
				if (grid.get(loc) == null)
				{
					addPellet(loc, false);
				}
			}
		}

		// Replace regular pellets with power pellets in a
		// few specific locations
		addPellet(new Location(7, 1), true);
		addPellet(new Location(7, 13), true);
		addPellet(new Location(16, 1), true);
		addPellet(new Location(16, 13), true);
	}

	private void addPellet(Location loc, boolean isPowerPellet)
	{
		Pellet pellet = new Pellet();
		if (isPowerPellet)
		{
			pellet.setColor(Color.RED);
		}
		else
		{
			pellet.setColor(Color.YELLOW);
		}
		pellet.putSelfInGrid(getGrid(), loc);
	}
}
