package com.jfeather.Generation;

import java.util.Random;

import com.jfeather.Items.*;
import com.jfeather.Player.Character;
public class Weapons {
	
	
	private String[] swordPreConstructions = {"The ", ""};
	private String[] swordBaseNames = {"Sword", "Scimitar", "Blade", "Katana", "Greatsword", "Saber", "Rapier", ""};
	private String[] swordRelations = {"of the", "of"};
	private String[] swordOfModifiers = {"Avarice", "Swiftness", ""};
	private String[] swordOfTheModifiers = {"Forgotten King", "King", "Jester", "Behemoth", "Giant", "Sentinel", "Beholder", "Gods", "Peasant", ""};

	public Weapon genSword(Character c) {
		Random rand = new Random();
		String name = "";
		name = name + swordPreConstructions[rand.nextInt(swordPreConstructions.length)] +swordBaseNames[rand.nextInt(swordBaseNames.length)];
		String relation = swordRelations[rand.nextInt(swordRelations.length)];
		if (relation.equals("of"))
			name = name + " " + relation +" "+ swordOfModifiers[rand.nextInt(swordOfModifiers.length)];
		else
			name = name + " "+relation +" "+ swordOfTheModifiers[rand.nextInt(swordOfTheModifiers.length)];
		int rar = rand.nextInt(1000);
		int rarity;
		if (rar > 500)
			rarity = 0;
		if (rar > 750)
			rarity = 1;
		if (rar > 850)
			rarity = 2;
		if (rar > 900)
			rarity = 3;
		if (rar > 940)
			rarity = 4;
		if (rar > 970)
			rarity = 5;
		if (rar > 990)
			rarity = 6;
		int type = rand.nextInt(5);
		int intelligence, strength, luck, agility;
		
		switch (type) {
			case 0:
				// Int
				intelligence = rand.nextInt(c.level * 2) + c.level;
			case 1:
				// Str
			case 2:
				// Luck
			case 3:
				// Agi
		}
	}
}
