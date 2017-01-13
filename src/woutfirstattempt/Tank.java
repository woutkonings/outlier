package woutfirstattempt;
import battlecode.common.*;

public class Tank extends Globals
{
	public static void loop()
	{

		System.out.println("Do you even lift bro?");
		
		while(true)
		{
			try
			{
				update();
				//Updates the number of tanks.
        		int prev = Message.getNumberOfType("TANK");
        		rc.broadcast(Message.TANK_CHANNEL, prev+1);
			}
			catch(Exception e)
			{
				System.out.println("Tank Exception: guns are too big");
				e.printStackTrace();
			}
		}
	}
}
