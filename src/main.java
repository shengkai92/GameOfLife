
public class main {

	public static void main(String[] args) 
	{
		final int RANGE = 5;
		final int INIT_NUM_BIOS = 10;
		final int NUM_GENERATION = 10;
		//final int PULSE_TIME = 3000;
		
		int CurrentBiosNum = 0;
		
		Env env = new Env(RANGE);
		
		while(CurrentBiosNum < INIT_NUM_BIOS && CurrentBiosNum < RANGE*RANGE)
		{
			Env.Point loc = new Env.Point((int) (Math.random()*RANGE), (int) (Math.random()*RANGE));
			Bio bio = new Bio(env, loc);
			env.addBio(loc, bio);
			CurrentBiosNum = env.getBios().size();
		}
		
		for(int n = 0; n <= NUM_GENERATION; n++)
		{
			System.out.println("No." + n + "  Bios Number:" + env.getBios().size());
			
			for(int x = 0; x < RANGE; x++)
			{
				for(int y = 0; y < RANGE; y++)
				{
					Env.Point loc = new Env.Point(x,y);
					System.out.print((env.getBios().containsKey(loc)) ? "O" : "_");
					
				}
				System.out.println();
			
			}
			
			env.nextGen();
		}
		
	}
}
