package Character;

import java.util.Random;

public class BadGuys extends MonsterParty
{
	public BadGuys(Random r)
	{
		super();
		//possibly randomly create the monsters, do so later
		//monsters.add(new MonsterGenerator("Diablo"));
		//monsters.add(new MonsterGenerator("El Pablo"));
		//monsters.add(new MonsterGenerator("Geezer"));
		monsters.add(new MonsterGenerator(r));
		monsters.add(new MonsterGenerator(r));
		monsters.add(new MonsterGenerator(r));
	}
}
