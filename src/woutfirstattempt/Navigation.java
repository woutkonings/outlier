package woutfirstattempt;
import battlecode.common.*;

public class Navigation extends Globals
{
	public static Direction randomDirection() 
    {
        return new Direction((float)Math.random() * 2 * (float)Math.PI);
    }
	
	public static boolean tryMove(Direction dir) throws GameActionException 
    {
        return tryMove(dir,20,3);
    }
	
	/**
     * Attempts to move in a given direction, while avoiding small obstacles direction in the path.
     *
     * @param dir The intended direction of movement
     * @param degreeOffset Spacing between checked directions (degrees)
     * @param checksPerSide Number of extra directions checked on each side, if intended direction was unavailable
     * @return true if a move was performed
     * @throws GameActionException
     */
    public static boolean tryMove(Direction dir, float degreeOffset, int checksPerSide) throws GameActionException 
    {

        // First, try intended direction
        if (rc.canMove(dir)) 
        {
            rc.move(dir);
            return true;
        }

        // Now try a bunch of similar angles
        boolean moved = false;
        int currentCheck = 1;

        while(currentCheck<=checksPerSide) 
        {
            // Try the offset of the left side
            if(rc.canMove(dir.rotateLeftDegrees(degreeOffset*currentCheck))) 
            {
                rc.move(dir.rotateLeftDegrees(degreeOffset*currentCheck));
                return true;
            }
            // Try the offset on the right side
            if(rc.canMove(dir.rotateRightDegrees(degreeOffset*currentCheck))) 
            {
                rc.move(dir.rotateRightDegrees(degreeOffset*currentCheck));
                return true;
            }
            // No move performed, try slightly further
            currentCheck++;
        }

        // A move never happened, so return false.
        return false;
    }
    
    public static boolean flee()
    {
    	//Following lines check for enemies and get the direction to first enemy in the array.
		RobotInfo[] enemies = rc.senseNearbyRobots(-1, them);
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
			return true;
		}
		return false;
    }
    
    /**
	 * If there is an enemy to move to within sight radius. It sets myDir to that enemy and returns true.
	 * @return Direction the direction to a nearby enemy.
	 */
	public static boolean isEnemyDir()
	{
		// No close robots, so search for robots within sight radius
        RobotInfo[] robots = rc.senseNearbyRobots(-1,them);

        // If there is a robot, move towards it
        if(robots.length > 0) 
        {
            MapLocation enemyLocation = robots[0].getLocation();
            myDir = here.directionTo(enemyLocation);
            return true;
        }
        return false;
	}
	
	/**
	 * If there is a tree with low health to move to within sight radius. It sets myDir to that enemy and returns true.
	 * @return true is myDir is set to nearby tree.
	 */
	public static boolean isWoundedTreeDir()
	{
		// No close robots, so search for robots within sight radius
        TreeInfo[] trees = rc.senseNearbyTrees();

        // If there is a robot, move towards it
        if(trees.length > 0) 
        {
        	for(int i = 0; i < 0;i++)
        	{
        		if(trees[i].getHealth() < trees[i].getMaxHealth() - 5)
        		{
        			MapLocation treeLoc = trees[i].getLocation();
        			myDir = here.directionTo(treeLoc);
        			return true;
        		}
        	}
        }
        return false;
	}
	
	/**
	 * If there is a tree that yields to move to within sight radius. It sets myDir to that enemy and returns true.
	 * @return true is myDir is set to nearby tree.
	 */
	public static boolean isYieldingTreeDir()
	{
		// No close robots, so search for robots within sight radius
        TreeInfo[] trees = rc.senseNearbyTrees();

        // If there is a robot, move towards it
        if(trees.length > 0) 
        {
        	for(int i = 0; i < 0;i++)
        	{
        		if((trees[i].getContainedBullets() > 0) || !(trees[i].getContainedRobot().equals(null)))
        		{
        			 	MapLocation treeLoc = trees[0].getLocation();
        	            myDir = here.directionTo(treeLoc);
        	            return true;
        		}
        	}
        }
        return false;
	}
	
	
	/**
	 * Wanders in random direction.
	 * @throws GameActionException
	 */
	public static void wander() throws GameActionException {
        try {
            Direction dir = randomDirection();
            if (rc.canMove(dir)) {
                rc.move(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
