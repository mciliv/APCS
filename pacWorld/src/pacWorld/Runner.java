package pacWorld;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;


public class Runner
{
    private static final int KEY_LEFT = 37;
    private static final int KEY_UP = 38;
    private static final int KEY_RIGHT = 39;
    private static final int KEY_DOWN = 40;

    private static KeyEventDispatcher dispatcher;
    private static ActorWorld world;

    private static PacCritter pac;
    private static GameKeeper gameKeeper;
    
    public static void main(String[] args)
    {
        // Create the world
        world = new ActorWorld(new BoundedGrid<Actor>(20, 15));
        
        // Create PacCritter, connect to keyboard input
        pac = new PacCritter();
        pac.setColor(Color.YELLOW);
        setKeyActor(pac);
        
        // Add the ghosts
        GhostCritter[] ghosts = new GhostCritter[4];
        ghosts[0] = new GhostCritter(Color.ORANGE, 1, 2);
        ghosts[1] = new GhostCritter(Color.MAGENTA, 0.85, 2);
        ghosts[2] = new GhostCritter(Color.PINK, 0.55, 1);
        ghosts[3] = new GhostCritter(Color.CYAN, 0.65, 1);

        // Set up the number locations
        Location[] scoreLocations = new Location[4];
        scoreLocations[0] = new Location(0, 14);
        scoreLocations[1] = new Location(0, 13);
        scoreLocations[2] = new Location(0, 12);
        scoreLocations[3] = new Location(0, 11);
        Location livesLocation = new Location(0, 0);
        
        // Create the GameKeeper, which connects the PacCritter, GhostCritters,
        // and numbers for score and lives
        gameKeeper = new GameKeeper(scoreLocations, livesLocation, pac, ghosts);
        world.add(new Location(0, 10), gameKeeper);
        gameKeeper.setColor(Color.WHITE);
        gameKeeper.initializeBoard();
        
        // Go!
        world.show();
    }
    
    public static PacCritter getPacCritter()
    {
        return pac;
    }
    
    public static GameKeeper getGameKeeper()
    {
        return gameKeeper;
    }
    
    public static void setKeyActor(final PacCritter actor)
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
                    actor.setDirection(Location.WEST);
                }
                else if (keyCode == KEY_UP) 
                {
                    actor.setDirection(Location.NORTH);
                }
                else if (keyCode == KEY_RIGHT)
                {
                    actor.setDirection(Location.EAST);
                }
                else if (keyCode == KEY_DOWN)
                {
                    actor.setDirection(Location.SOUTH);
                }
                
                return true;
            }
        };
        
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addKeyEventDispatcher(dispatcher);
    }    

}
