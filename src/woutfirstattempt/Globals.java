package woutfirstattempt;
import battlecode.common.*;

public class Globals 
{
	public static RobotController rc;
	public static MapLocation here;
	public static Team us;
	public static Team them;
	public static int myID;
	public static RobotType myType;
	
	public static void initialise(RobotController theRC) 
	{
		rc = theRC;
		us = rc.getTeam();
		them = us.opponent();
		myID = rc.getID();
		myType = rc.getType();
	}
	
	public static void update() 
	{
		here = rc.getLocation();
	}
}
