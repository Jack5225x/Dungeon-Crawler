package com.jfeather.Generation;

import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

import com.jfeather.Items.*;
import com.jfeather.Player.Character;
public class WeaponsGen {
	
	
	private String[] preConstructions = {"The ", ""};
	private String[] relations = {"of the", "of"};
	private String[] baseAdjectives = {"Avarice", "Swiftness"};
	private String[] baseEntities = {"King", "Jester", "Behemoth", "Giant", "Sentinel", "Beholder", "Gods", "Peasant"};
	
	private String[] swordBaseNames = {"Sword", "Scimitar", "Blade", "Katana", "Greatsword", "Saber", "Rapier"};
	private String[] swordLegendayAdjectives;
	private String[] swordDivineAdjectives;
			
	public Weapon genSword(Character c) {
		Random rand = new Random();
		
		String name = "", entity = "", adjective = "";
		name = name + preConstructions[rand.nextInt(preConstructions.length)] + swordBaseNames[rand.nextInt(swordBaseNames.length)];
		String relation = relations[rand.nextInt(relations.length)];
		if (relation.equals("of"))
			name = name + " " + relation +" "+ (adjective = baseAdjectives[rand.nextInt(baseAdjectives.length)]);
		else
			name = name + " "+relation +" "+ (entity = baseEntities[rand.nextInt(baseEntities.length)]);
		
		int rar = rand.nextInt(1000);
		String descr = "";
		int rarity = 0;
		descr = "A common sword crafted from iron. Seems pretty sturdy for now.";
		
		if (rar > 750) {
			rarity = 1;
			descr = "A well forged sword made from steel. Sharpended to a deadly point.";
		}
		if (rar > 850) {
			rarity = 2;
			descr = "Titanium";
		}
		if (rar > 900) {
			rarity = 3;
			descr = "Smelted from the Mithril hide of a griffon, nearly indestructable.";
		}
		if (rar > 940) {
			rarity = 4;
			descr = "Birthed from dragons, this blade was crafted from the lost metal draconium.";
		}
		if (rar > 970) {
			rarity = 5;
			descr = "Forged from solid adamantium, this sword has felled even the mightiest foes.";
		}
		if (rar > 990) {
			rarity = 6;
			descr = "A legendary blade crafted by the gods for ancient heros.";
		}
		int type = rand.nextInt(4);
		int intelligence = 0, strength = 0, luck = 0, agility = 0, speed = 0;
		
		switch (type) {
			case 0:
				// Int
				intelligence = rand.nextInt(c.level * 2) + (rarity + 1) * 2;
				strength = 0;
				luck = rand.nextInt(2);
				agility = rand.nextInt(2);
				speed = rand.nextInt(10) + 1;
				break;
			case 1:
				// Str
				strength = rand.nextInt(c.level * 2) + (rarity + 1) * 2;
				intelligence = 0;
				luck = rand.nextInt(2);
				agility = rand.nextInt(2);
				speed = rand.nextInt(8) + 1;
				break;
			case 2:
				// Luck
				strength = rand.nextInt(c.level / 2 + 1);
				intelligence = rand.nextInt(c.level / 2 + 1);
				luck = rand.nextInt(c.level);
				agility = rand.nextInt(1);
				speed = rand.nextInt(12) + 1;
				break;
			case 3:
				// Agi
				strength = rand.nextInt(c.level / 2 + 1);
				intelligence = rand.nextInt(c.level / 2 + 1);
				agility = rand.nextInt(c.level);
				luck = rand.nextInt(1);
				speed = rand.nextInt(15);
				break;
		}
		
		int damage = rand.nextInt(c.level) + (c.level) + 2;

		Weapon sword =  new Weapon(name, descr, rarity, damage, speed, strength, intelligence, agility, luck, genSprite("Sprites/Items/Weapons/Swords/"));	
		return sword;
	}
	
	public ImageIcon genSprite(String folderName) {
		Random rng = new Random();
		File folder = new File(folderName);
		File[] arr = folder.listFiles();
		return new ImageIcon(arr[rng.nextInt(arr.length)].getPath());
	}
	
	public int genRarity() {
		Random rng = new Random();
		int rarity = 0;
		int rar = rng.nextInt(1000);
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
		return rarity;
	}
	
	public String genName(String weaponType) {
		switch (weaponType.toLowerCase()) {
		case "sword":
		case "":
		case "":
		case "":
		case "":
		}
	}
}
