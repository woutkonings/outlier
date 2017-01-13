package woutfirstattempt;
import battlecode.common.*;

public class Soldier extends Globals
{
	public static void loop()
	{
		System.out.println("I'm an soldier!");
        Team enemy = rc.getTeam().opponent();

        // The code you want your robot to perform every round should be in this loop
        while (true) 
        {

            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try 
            {
            	update();
            	//Updates the number of soldiers.
        		int prev = Message.getNumberOfType("SOLDIER");
        		rc.broadcast(Message.SOLDIER_CHANNEL, prev+1);
        	
        		shoot();
        		
        		//The following lines make the soldier go to an enemy if it is not a lumberjack. If it's a lumberjack, it flees.
        		if(Navigation.isEnemyRangedDir())
        		{
        			Navigation.tryMove(myDir);
        		}
        		if(!rc.hasMoved())
        		{
        			Navigation.wander();
        		}

                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                Clock.yield();

            } 
            catch (Exception e) 
            {
                System.out.println("Soldier Exception");
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * Shoots at nearby enemy.
	 */
	public static void shoot()
	{
		try
		{
		RobotInfo[] robots = rc.senseNearbyRobots(-1, them);
		if (robots.length > 0) 
        {
            // And we have enough bullets, and haven't attacked yet this turn...
            if (rc.canFireSingleShot()) 
            {
                // ...Then fire a bullet in the direction of the enemy.
                rc.fireSingleShot(here.directionTo(robots[0].location));
            }
        }
		}
		catch(GameActionException e)
		{
			e.printStackTrace();
		}
	}
}
