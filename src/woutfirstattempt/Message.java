package woutfirstattempt;
import battlecode.common.*;

public strictfp class Message extends Globals 
{	
	//Keep track of channels here.
	public static int ARCHON_CHANNEL = 1;
	public static int GARDENER_CHANNEL = 2;
	public static int LUMBERJACK_CHANNEL = 3;
	public static int SCOUT_CHANNEL = 4;
	public static int SOLDIER_CHANNEL = 5;
	public static int TANK_CHANNEL = 6;
	
	
	/**
	 * This method will return the amount of robots of the given type currently on the team.
	 * @param s type of the robot in capital letters.
	 * @return int the number of robots of the given type.
	 */
	public static int getNumberOfType(String s)
	{
		try
		{
			if(s.equals("ARCHON"))
			{
				return rc.readBroadcast(ARCHON_CHANNEL);
			}
			if(s.equals("GARDENER"))
			{
				return rc.readBroadcast(GARDENER_CHANNEL);
			}
			if(s.equals("LUMBERJACK"))
			{
				return rc.readBroadcast(LUMBERJACK_CHANNEL);
			}
			if(s.equals("SCOUT"))
			{
				return rc.readBroadcast(SCOUT_CHANNEL);
			}
			if(s.equals("SOLDIER"))
			{
				return rc.readBroadcast(SOLDIER_CHANNEL);
			}
			if(s.equals("TANK"))
			{
				return rc.readBroadcast(TANK_CHANNEL);
			}
		}
		catch(GameActionException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
