package Character;

import java.util.Random;

public class GoodGuys extends Party
{
	public GoodGuys(Random r)
	{
		super();
		characters.add(new HeroGenerator(r));
		characters.add(new HeroGenerator(r));
		characters.add(new HeroGenerator(r));
	}
}
