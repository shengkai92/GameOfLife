import java.util.HashMap;
import java.util.Map;


public class Env {

	private Map<Point,Bio> map = new HashMap<Point,Bio>();
	private Map<Point,Bio> nextGenMap;
	private int RANGE;
	private boolean onNextGen = false;
	
	public Env(int RANGE)
	{
		this.RANGE = RANGE;
	}
	
	public void addBio(Point loc, Bio bio)
	{
		Map<Point,Bio> tempmap = onNextGen ? nextGenMap : map;
		
		tempmap.put(loc, bio);

	}
	
	public Bio findBio(int x, int y)
	{
		for(Point loc: map.keySet())
		{
			if(loc.x == x && loc.y == y)
				return map.get(loc);
		}
		
		return null;
	}
	
	public HashMap<Point,Bio> getBios()
	{
		return new HashMap<Point,Bio>(map);
	}
	
	
	public void nextGen()
	{
		nextGenMap = new HashMap<Point,Bio>();
		
		onNextGen = true;
		
		for(Point loc: map.keySet())
		{
			Bio bio = map.get(loc);
			bio.AliveOrDie(RANGE);
			if(bio.isAlive())
				nextGenMap.put(loc, bio);
		}
		map = nextGenMap;
		
		onNextGen = false;
		
	}
	
	static class Point
	{
		public int x;
		public int y;
		
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object that) {
			if(that instanceof Point) {
				Point p = (Point) that;
				return this.x == p.x && this.y == p.y;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return 41 * (41 + x) + y;
		}
	}
}
