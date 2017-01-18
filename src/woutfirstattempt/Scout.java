package woutfirstattempt;
import battlecode.common.*;

public class Scout extends Globals
{
	static float offset = 0;
	public static void loop()
	{
		System.out.println("I am veeery fast!");
		
		while(true)
		{
			try
			{
				update();
				//Updates the number of scouts.
        		int prev = Message.getNumberOfType("SCOUT");
        		rc.broadcast(Message.SCOUT_CHANNEL, prev+1);
        		
        		Navigation.flee();
        		scouting(offset);
        		
			}
			catch(Exception e)
			{
				System.out.println("Scout Exception");
				e.printStackTrace();
			}
		}
	}
	/**
	 * Keep moving in a direction until it is no longer possible, then move to a different direction
	 * @param o : the angle in which the scout moves
	 */
	public static void scouting(float o)
	{
		try
		{
		
			Direction dir = new Direction(0);
			if (!Navigation.tryMove(dir, o, 2)){
				offset = (float) Math.random() * 360;
			}
			
		}
		catch(GameActionException e)
		{
			e.printStackTrace();
		}
	}
}
