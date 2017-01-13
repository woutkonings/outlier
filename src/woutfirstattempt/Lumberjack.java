package woutfirstattempt;
import battlecode.common.*;

public class Lumberjack extends Globals
{
	public static boolean hasChopped;
	/**
	 * The main loop to be executed.
	 */
	public static void loop()
	{
		System.out.println("I'm a Lumberjack!");

        // The code you want your robot to perform every round should be in this loop
        while (true) 
        {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try 
            {
            	update();
            	//Updates the number of lumberjacks.
        		int prev = Message.getNumberOfType("LUMBERJACK");
        		rc.broadcast(Message.LUMBERJACK_CHANNEL, prev+1);
            	
        		strikeNearby();
        		chopNearby();

        		//Checks for nearby enemy.
                if(Navigation.isEnemyDir())
                {
                	Navigation.tryMove(myDir);
                }
                if(!rc.hasMoved() && Navigation.isYieldingTreeDir() && !hasChopped)
                {
                	rc.move(myDir);
                }
                if(!rc.hasMoved() && !hasChopped)
                {
                    // Move Randomly
                    Navigation.tryMove(Navigation.randomDirection());
                }

                Clock.yield();

            } 
            catch (Exception e) 
            {
                System.out.println("Lumberjack Exception");
                e.printStackTrace();
            }
        }
	}
	
	//BELOW ALL OTHER METHODS
	//
	//
	//
	
	/**
	 * Senses if there are robots in strike radius, if so strikes all nearby robots.
	 */
	public static void strikeNearby()
	{
		// See if there are any enemy robots within striking range (distance 1 from lumberjack's radius)
        RobotInfo[] robots = rc.senseNearbyRobots(RobotType.LUMBERJACK.bodyRadius+GameConstants.LUMBERJACK_STRIKE_RADIUS, them);
        if(robots.length > 0 && !rc.hasAttacked()) 
        {
            // Use strike() to hit all nearby robots!
        	try
        	{
        		rc.strike();
        	}
            catch(GameActionException e)
        	{
            	e.printStackTrace();
        	}
        } 
	}
	
	/**
	 * Senses if there are tree in chop radius, if so, chops tree
	 */
	public static void chopNearby()
	{
		// See if there are any trees within striking range (distance 1 from lumberjack's radius)
        TreeInfo[] trees = rc.senseNearbyTrees(RobotType.LUMBERJACK.bodyRadius+GameConstants.LUMBERJACK_STRIKE_RADIUS);
        if(trees.length > 0) 
        {
            // Use chop
        	try
        	{
        		rc.chop(trees[0].getLocation());
        		hasChopped = true;
        	}
            catch(GameActionException e)
        	{
            	e.printStackTrace();
        	}
        }
        if(trees.length == 0)
        {
            hasChopped = false;
        }
	}
	
	
}
