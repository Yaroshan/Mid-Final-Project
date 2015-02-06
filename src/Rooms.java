import java.util.ArrayList;
import java.util.Vector;
import java.util.Scanner;

public class Rooms extends Introduction
	{
	static Vector<Location> map;
	private static Hero hero;
	static Location currentLocation;

	public static void main(String[] args)
		{
		createMap();
		populateMonsters();
		hero = Player.generateHero();
		

		while (true)
			{
			System.out.println("You're in " + currentLocation.getTitle());
			System.out.println(currentLocation.getDescription());
			System.out.println();

			if (currentLocation.getLair() != null)
				{
				System.out.println("You also see a "
						+ currentLocation.getLair().getNameOfMonster());
				resolveCombat(hero, currentLocation.getLair());

				}
			}
		}

	public static void populateMonsters()
		{
		for (Location location : map)
			{

			int randomNumber = 1;
			switch ((int) ((randomNumber * 10) + 1))
				{
				case 1:
					location.lair = new Ganondorf();
					break;
//				case 2:
//					location.lair = new SpecificMonster2();
//					break;
				}
			}

		}

	public static void createMap()
		{

		map = new Vector<Location>(4);

		Location location1 = new Location("your house.",
				"You see doors to the north and east.");
		Location location2 = new Location("Hyrulian Fields.",
				"You see doors to the north and west.");
		Location location3 = new Location("Faron Woods",
				"You see doors to the south and east.");
		Location location4 = new Location("Temple of Time",
				"You see doors to the south and west.");
		Location location5 = new Location(".",
				"You see doors to the north and west.");

		map.addElement(location1);
		map.addElement(location2);
		map.addElement(location3);
		map.addElement(location4);
		map.addElement(location5);

		// This section defines the exits found in each location and the
		// locations to which they lead.
		location1.addExit(new Exit(Exit.north, location3));
		location1.addExit(new Exit(Exit.east, location2));
		location2.addExit(new Exit(Exit.north, location4));
		location2.addExit(new Exit(Exit.west, location1));
		location3.addExit(new Exit(Exit.south, location1));
		location3.addExit(new Exit(Exit.east, location4));
		location4.addExit(new Exit(Exit.west, location3));
		location4.addExit(new Exit(Exit.south, location2));
		location5.addExit(new Exit(Exit.west, location4));
		location5.addExit(new Exit(Exit.north, location3));

		currentLocation = location1;

			{

			while (true)
				{
				System.out.println("You're in " + currentLocation.getTitle());
				System.out.println(currentLocation.getDescription());
				System.out.println();

				System.out.println();
				System.out.println("Which direction would you like to go?");

				if (currentLocation == location2)
					{
					System.out.println();
					}

				for (Exit exit : currentLocation.getExits())
					{
					System.out.println(exit.getDirection());
					}

				Scanner scanner = new Scanner(System.in);
				String choice = scanner.nextLine();

				for (Exit exit : currentLocation.getExits())
					{
					if (exit.getDirection().equals(choice))
						{
						currentLocation = exit.getLeadsTo();
						}
					}
				}
			}
		}

	public static void resolveCombat(Hero heroCombatant,
			Monster monsterCombatant)
		{
		while (monsterCombatant.getHitPointsOfMonster() > 0)
			{
			System.out.println("The monster's HP = "
					+ monsterCombatant.getHitPointsOfMonster());
			monsterCombatant.setHitPointsOfMonster(monsterCombatant
					.getHitPointsOfMonster() - heroCombatant.performAttack());
			heroCombatant.setHitPointsOfHero(heroCombatant.getHitPointsOfHero()
					- monsterCombatant.performAttack());
			System.out.println("The monster now has "
					+ monsterCombatant.getHitPointsOfMonster() + " HP.");
			System.out.println("You now have "
					+ heroCombatant.getHitPointsOfHero() + " HP.");

			if (monsterCombatant.getHitPointsOfMonster() <= 0)
				{
				System.out.println("The monster dies.");
				}

			if (heroCombatant.getHitPointsOfHero() <= 0)
				{
				System.out.println("You die a failure.");
				System.exit(0);
				}

			}
		}
	}
