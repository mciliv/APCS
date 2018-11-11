import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.KeyStroke;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Main
{
    private static ActorWorld world;
    private static SnakeBug snake; 
    
    private static final int KEY_LEFT = 37;
    private static final int KEY_UP = 38;
    private static final int KEY_RIGHT = 39;
    private static final int KEY_DOWN = 40;
    
    private static KeyEventDispatcher dispatcher;
    
    public static void main(String[] args)
    {
        // Create GridWorld
        world = new ActorWorld(new BoundedGrid<Actor>(25,25));
        
        // Create Snake
        snake = new SnakeBug(); 
        
        // Add the walls 
    	addWalls(world.getGrid());
    	
    	// Add the characters
        Main.world.add(new Seed());
        Main.world.add(new Location(7, 9), snake);
        Main.snake.setDirection(Location.NORTH);
        
        // Hook up the keyboard handler to the snake
        Main.setKeyActor(snake);
        
        // GO!
        Main.world.show();
    }
    
    public static void setKeyActor(Actor actor) 
    {
    	if (dispatcher != null) 
    	{
    		KeyboardFocusManager.getCurrentKeyboardFocusManager()
    		.removeKeyEventDispatcher(dispatcher);    		
    	}
    	    	
    	dispatcher = new KeyEventDispatcher() 
    	{
    		public boolean dispatchKeyEvent (KeyEvent event) 
    		{
    			KeyStroke stroke = javax.swing.KeyStroke.getKeyStrokeForEvent(event);
    			int keyCode = stroke.getKeyCode();
    			
    			if (keyCode == KEY_LEFT) 
    			{
    		        snake.setDirection(270);
    			}
    			else if (keyCode == KEY_UP) 
    			{
    		        snake.setDirection(0);
    			}
    			else if (keyCode == KEY_RIGHT)
    			{
    		        snake.setDirection(90);
    			}
    			else if (keyCode == KEY_DOWN) 
    			{
    		        snake.setDirection(180);
    			}
    			
    			return true;
    		}
    	};
    	
    	KeyboardFocusManager.getCurrentKeyboardFocusManager()
    		.addKeyEventDispatcher(dispatcher);
    }
    
    public static void addWalls(Grid<Actor> gr) 
    {
		int x = 3;
		int y = gr.getNumCols() - 4;
		
		ArrayList<Location> locs = new ArrayList<Location>();
		for(int i = 0; i < 5; i++) 
		{
			if(i == 0) 
			{
				locs.add(new Location(x, y));
				locs.add(new Location(x, x));
				locs.add(new Location(y, x));
				locs.add(new Location(y, y));
			}
			locs.add(new Location(x, x + i));
			locs.add(new Location(x + i, x));
			locs.add(new Location(y, x + i));
			locs.add(new Location(x + i, y));
			locs.add(new Location(y - i, x));
			locs.add(new Location(y - i, y));
			locs.add(new Location(x, y - i));
			locs.add(new Location(y, y - i));
		}
		for(int z = 0; z < locs.size(); z++)
		{
			Location l = locs.get(z);
			if(gr.isValid(l)) 
			{
				Rock r = new Rock();
				r.putSelfInGrid(gr, l);
			}
		}
		
	}
}
