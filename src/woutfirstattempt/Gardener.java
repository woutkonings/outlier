package woutfirstattempt;
import battlecode.common.*;

public class Gardener extends Globals
{
	public static void loop()
	{
		System.out.println("I'm a gardener!");

        // The code you want your robot to perform every round should be in this loop
        while (true) {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {

	                /** Listen for home archon's location.
	                 * MOET DUS NOG GEFIKST WORDEN.
	                int xPos = rc.readBroadcast(0);
	                int yPos = rc.readBroadcast(1);
	                MapLocation archonLoc = new MapLocation(xPos,yPos);
	                */
            	
            		update();
            		//Updates the number of gardeners.
            		int prev = Message.getNumberOfType("GARDENER");
            		rc.broadcast(Message.GARDENER_CHANNEL, prev+1);
	
            		if(Navigation.flee())
            		{
            			Navigation.tryMove(myDir);
            		}
            		if(Navigation.isWoundedTreeDir() && !rc.hasMoved())
            		{
            			Navigation.tryMove(myDir);
            		}
	                if(!rc.hasMoved())
	                {
	                	Navigation.wander();
	                }
	
	                randomBuild();
	
	                Clock.yield();
            } 
            catch (Exception e) 
            {
                System.out.println("Gardener Exception");
                e.printStackTrace();
            }
        }
	}
	
		
	/**
	 * randomly builds a soldier or a gardener.
	 */
	public static void randomBuild()
	{
		try
		{
			Direction dir = Navigation.randomDirection();
	        // Randomly attempt to build a soldier or lumberjack in this direction
	        if (rc.canBuildRobot(RobotType.SOLDIER, dir) && Math.random() < .01) 
	        {
	            rc.buildRobot(RobotType.SOLDIER, dir);
	        } 
	        else if (rc.canBuildRobot(RobotType.LUMBERJACK, dir) && Math.random() < .01 && rc.isBuildReady()) 
	        {
	            rc.buildRobot(RobotType.LUMBERJACK, dir);
	        }
		}
		catch(GameActionException e)
		{
			e.printStackTrace();
		}
	}
}
