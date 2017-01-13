package woutfirstattempt;
import battlecode.common.*;

public strictfp class Archon extends Globals
{
	public static void loop()
	{
		System.out.println("I'm an archon!");
        while (true) 
        {
            try {
            		update();
            		myDir = here.directionTo(InitialLoc);
            		
            		//Following lines check for enemies and get the direction to first enemy in the array.
            		RobotInfo[] enemies = rc.senseNearbyRobots(5, them);
            		int arrayPos = 0;
            		boolean isEnemy = false;
            		for(int i = 0; i < enemies.length; i++)
            		{
            			if(!enemies[i].equals(null))
            			{
            				isEnemy = true;
            				arrayPos = i;
            				break;
            			}
            		}
            		if(isEnemy)
            		{
            			myDir = here.directionTo(enemies[arrayPos].getLocation()).opposite();
            		}
            		
            		
            	
            		//Gets the number of current gardeners and resets it so the gardeners can update
            		int prevNumGard = Message.getNumberOfType("GARDENER");
            		rc.broadcast(Message.GARDENER_CHANNEL, 0);
	               
            		// Generate a random direction for hiring purposes.
	                Direction dir = Navigation.randomDirection();
	                
	                //Hires a gardener if the number of gardeners is less than twice the number of archons.
	                if (rc.canHireGardener(dir) && prevNumGard < GameConstants.NUMBER_OF_ARCHONS_MAX*2) 
	                {
	                    rc.hireGardener(dir);
	                }
	
	                // Move to myDir
	                Navigation.tryMove(myDir);
	
	                /** Broadcast archon's location for other robots on the team to know
	                MapLocation myLocation = rc.getLocation();
	                rc.broadcast(0,(int)myLocation.x);
	                rc.broadcast(1,(int)myLocation.y);
					 */
	                
	                Clock.yield();
            	} 
            catch (Exception e) 
            	{
	                System.out.println("Archon Exception");
	                e.printStackTrace();
            	}
        }
	}
}
