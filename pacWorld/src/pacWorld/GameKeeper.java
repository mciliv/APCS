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
    		 if (grid.get(loc) instanceof Flower)
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
        // Add the walls and pellets
        addWalls();
        addPellets();
        
        // Reset locations of PacCritter and GhostCritters
        resetBoard();
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
    		Location loc1 = new Location(1, i);
    		Location loc2 = new Location(grid.getNumRows()-1, i);
    		Rock Rock1 = new Rock(Color.cyan);
    		Rock Rock2 = new Rock(Color.GREEN);
    		Rock1.putSelfInGrid(grid, loc1);
    		Rock2.putSelfInGrid(grid, loc2);
    	}
    	for (int i = 1; i<grid.getNumRows(); i++)//Edits the vertical lines
    	{
    		Location loc1 = new Location(i, 0);
    		Location loc2 = new Location(i, grid.getNumCols()-1);
    		Rock Rock1 = new Rock(Color.blue);
    		Rock Rock2 = new Rock(Color.magenta);
    		Rock1.putSelfInGrid(grid, loc1);
    		Rock2.putSelfInGrid(grid, loc2);
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
