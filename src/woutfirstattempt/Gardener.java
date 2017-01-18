package woutfirstattempt;
import battlecode.common.*;

public class Gardener extends Globals
{
	public static void loop()
	{
		System.out.println("I'm a gardener!");
		int countdown = 50;
		float treeDir = 0;
		int treesPlanted = 0;
		MapLocation treeCenter = new MapLocation(0,0);
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
	                if(!rc.hasMoved() && countdown > 0)
	                {
	                	Navigation.wander();
	                }
	                
	                // Wander for a while and then start the planting
	                
	                //set the center of the tree circle
	                if (countdown == 0){
	                	treeCenter = here;
	                	countdown = -1;
	                }
	                else if (countdown > 0){
	                	countdown--;
	                }
	                //plant the 8 trees
	                if (countdown == -1 && treesPlanted < 8){
	                	//die shit van planten in cirkel
	                	rc.plantTree(here.directionTo(treeCenter.add(treeDir, 2)));
	                	treeDir += .25 * Math.PI;
	                	treesPlanted++;
	                }
	                
	                //ENTER SPAWN LOCATION HERE
	                randomBuild();
	                //ONLY BUILD UNITS THERE
	                
	                
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
	        if (rc.canBuildRobot(RobotType.SOLDIER, dir) && Math.random() < .01 && rc.isBuildReady()) 
	        {
	            rc.buildRobot(RobotType.SOLDIER, dir);
	        } 
	        else if (rc.canBuildRobot(RobotType.LUMBERJACK, dir) && Math.random() < .10 && rc.isBuildReady()) 
	        {
	            rc.buildRobot(RobotType.LUMBERJACK, dir);
	        }
	        else if (rc.canPlantTree(dir) && Math.random() < .5 && rc.isBuildReady())
	        {
	        	rc.plantTree(dir);
	        }
	        else if (rc.canBuildRobot(RobotType.SCOUT, myDir) && Math.random() < .5 && rc.isBuildReady())
	        {
	        	rc.buildRobot(RobotType.SCOUT, myDir);
	        }
		}
		catch(GameActionException e)
		{
			e.printStackTrace();
		}
	}
}
