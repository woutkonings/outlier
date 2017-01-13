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
            		Navigation.flee();
            		
            	    //Gets the number of current gardeners and resets it so the gardeners can update
            		int prevNumGard = Message.getNumberOfType("GARDENER");
            		System.out.println(prevNumGard);
	               
            		// Generate a random direction for hiring purposes.
	                Direction dir = Navigation.randomDirection();
	                
	                //Hires a gardener if the number of gardeners is less than twice the number of archons.
	                if (rc.canHireGardener(dir) && (prevNumGard < GARDENER_MAX)) 
	                {
	                    rc.hireGardener(dir);
	                }
	
	                // Move to myDir
	                Navigation.tryMove(myDir);
	
	                /** Broadcast archon's location for other robots on the team to know
	                 * DIT MOET NOG EVEN GEFIKST WORDEN OP EEN ANDER CHANNEL WANT CHANNEL 0 en 1 ZIJN NU
	                 * AL VOOR ANDERE DINGEN IN GEBRUIK. ZIE MESSAGE.
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
