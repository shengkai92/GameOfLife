import java.util.ArrayList;
import java.util.List;




public class Bio {
	
	private static final int NUM_MATES_ALIVE = 2; // 周圍Bio小於等於1則存活
	private static final int NUM_MATES_REPRODUCE = 1; //周圍Bio等於1時繁殖
	private static final int SEARCH_RADIUS = 1;
	
	private boolean alive = true;
	
	private Env env;
	private Env.Point loc;


	public Bio(Env env, Env.Point loc)
	{
		this.env = env;
		this.loc = loc;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public Env.Point getLoc() {
		return loc;
	}
	
	public void AliveOrDie(int RANGE)
	{
		List<Bio> mates = findMates();
		
		if(mates.size() > NUM_MATES_ALIVE)
		{
			alive = false;
		}
		else if(mates.size() == NUM_MATES_REPRODUCE && mates.size() < getSearchRange())
		{
			boolean findBornPlace = false;
			Env.Point BornLoc;
			while(!(findBornPlace))
			{
				boolean a = true;
				do{
					//BornLoc = findNearPlace(loc);
					do{
					BornLoc = NearPlace(loc);
					}while(BornLoc.x < 0 || BornLoc.y < 0 || BornLoc.x >= RANGE || BornLoc.y >= RANGE);
					
					for(Bio mate  : mates) 
					{
						if(mate.getLoc().equals(BornLoc) || (BornLoc.x == loc.x && BornLoc.y == loc.y) )
						{
							a = true;
						}else
							a = false;
					}
					
				}while(a);
				
				System.out.print("("+loc.x+","+loc.y+")   ");
				System.out.println("("+BornLoc.x+","+BornLoc.y+")");
				env.addBio(BornLoc, new Bio(env, BornLoc));
				findBornPlace = true;
			}
			
		}
	}
	
	private int getSearchRange()
	{
		return (SEARCH_RADIUS * 2 + 1) * (SEARCH_RADIUS * 2 + 1);
	}
	/*
	private Env.Point findNearPlace(Env.Point loc)
	{
		return new Env.Point( loc.x + (int) ((Math.random()) * 2 - 1) , loc.y + (int) (Math.random() * 2 - 1));
	}*/
	
	private Env.Point NearPlace(Env.Point loc)
	{
		int a = (int) (Math.random()*7);
		Env.Point Nearloc = new Env.Point(loc.x, loc.y);
		switch(a)
		{
			case 0:
				Nearloc.x = loc.x-1;
				Nearloc.y = loc.y-1;
				break;
			case 1:
				Nearloc.x = loc.x-1;
				Nearloc.y = loc.y;
				break;
			case 2:
				Nearloc.x = loc.x-1;
				Nearloc.y = loc.y+1;
				break;
			case 3:
				Nearloc.x = loc.x;
				Nearloc.y = loc.y-1;
				break;
			case 4:
				Nearloc.x = loc.x;
				Nearloc.y = loc.y+1;
				break;
			case 5:
				Nearloc.x = loc.x+1;
				Nearloc.y = loc.y-1;
				break;
			case 6:
				Nearloc.x = loc.x+1;
				Nearloc.y = loc.y;
				break;
			case 7:
				Nearloc.x = loc.x+1;
				Nearloc.y = loc.y+1;
				break;
			default:
				break;
		}
		return Nearloc;
	}
	
	private List<Bio> findMates()
	{
		List<Bio> mates = new ArrayList<Bio>();
		
		for(int x = loc.x - SEARCH_RADIUS; x <= loc.x + SEARCH_RADIUS; x++)
		{
			for(int y = loc.y - SEARCH_RADIUS; y <= loc.y + SEARCH_RADIUS; y++)
			{
				Bio mate = env.findBio(x,y);
				
				if( mate != null && mate != this)
					mates.add(mate);
			}
		}
		
		return mates;
	}

}
