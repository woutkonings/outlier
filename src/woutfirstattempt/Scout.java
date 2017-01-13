package woutfirstattempt;
import battlecode.common.*;

public class Scout extends Globals
{
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
			}
			catch(Exception e)
			{
				System.out.println("Scout Exception");
				e.printStackTrace();
			}
		}
	}
}
