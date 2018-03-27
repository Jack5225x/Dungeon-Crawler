package com.jfeather.Generation;

import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

import com.jfeather.Items.*;
import com.jfeather.Player.Character;
public class WeaponsGen {
	
	private String[] preConstructions = {"The ", ""};
	private String[] basePreAdjectives = {"Mighty", "Strong" ,"Steadfast", "Crystal", "Shine", "Angelic", "Demonic", "Adorned"};
	private String[] legendaryDivinePreAdjectives = {"Rainbow", "Godly", "Ancient", "Divine", "Legendary", "Infinity", "Quantum", "Tachyonic"};
	private String[] basePostAdjectives = {"Avarice", "Swiftness", "Might", "Destruction", "Darkness", "Light"};
	private String[] baseEntities = {"the Jester", "the Behemoth", "the Giant", "the Sentinel", "the Beholder", "the Peasant"};
	private String[] legendaryDivinePostAdjectives = {"Might", "Magic", "Infinity", "the Void", "Impossibility", "Prisms", "Illusions", "Aether"};
	private String[] legendaryDivineEntities = {"the Gods", "the King", "the Ancients", "the Morning Star", "a Meteor", "the McDonald's Breakfast Artisans"};

	// Weapon types and names
	private String[] swordBaseNames = {"Sword", "Scimitar", "Blade", "Katana", "Greatsword", "Saber", "Rapier", "Longsword"};
	private String[] daggerBaseNames = {"Blade", "Dagger", "Shortsword"};
	private String[] hammerBaseNames = {"Hammer", "Mallet", "Mace"};
	private String[] wandBaseNames = {"Wand", "Catalyst"};
	private String[] staffBaseNames = {"Staff"};
	private String[] bowBaseNames = {"Bow", "Longbow"};
	private String[] crossbowBaseNames = {"Crossbow"};
	
	// Weapon materials based on rarity
	private String[][] matsMelee = {
			{"pig iron", "aluminum", "stone", "tin", "lead", "copper"},
			{"steel", "iron", "bronze", "manganite"},
			{"cobalt", "black iron", "mithril"},
			{"damascus steel", "titanium", "dark steel"},
			{"iridium", "electrum", "promethium"},
			{"draconium", "dragonite", "metoerite"},
			{"adamantium", "vibranium", "ethereum", "neutronium"},
	};
	
	private String[] matsRanged = {"maple", "oak", "cherry"};
	
	// Description arrays
	private String[][] descrPhrasesEntity = {
			{"Forged for a ", "Crafted for a ", "Made by a "},
			{"very strong.", "very reliable.", "quite old", "seemingly new"}
	};
	
	private String[][] descrPhrasesAdjective = {
			{"is rivaled by no other.", "is unmatched by any other common weapon.", "holds up even to the most exquisite weapons."},
	};
	
	private String[][] descrPhrasesType = {
			{" forged from ", " made of ", " crafted from "},
			{". Seems pretty sturdy.", ". Looks strong enough.", ". Should hold up in battle."}
	};

	private String[][] descrLegendaryDivine  = {
			{"was recovered from an ancient tomb.", "was found alongside a forgotten king.", "defeated hordes of enemies in a lost past.", "was wielded by an old god.", "causes any who wield it to be controlled by a mystical energy.", "summons the might of ungodly forces.", "has proved to be too powerful for even the mightiest kings to wield"},
			{"Wielded by", "Forged by", "Summoned by"}
	};
	
	public Weapon genWeapon(Character c, String weaponType) {
		Random rand = new Random();
		
		String spritesFolder = weaponType.substring(0, 1).toUpperCase() + weaponType.substring(1, weaponType.length()).toLowerCase() + "s/";
		
		int rar = rand.nextInt(1000 + c.level * 5);
		String descr = "";
		int rarity = 0;
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
		
		String[] nameComponents = genName(weaponType, rarity);
		String name = nameComponents[0];
		descr = genDescr(nameComponents, weaponType, rarity);
		
		int type = rand.nextInt(4);
		int intelligence = 0, strength = 0, luck = 0, agility = 0, speed = 0;
		
		switch (type) {
			case 0:
				// Int
				intelligence = rand.nextInt(c.level * 2) + (rarity + 1) * 2;
				strength = 0;
				agility = rand.nextInt((int)(c.level / 10) + 1);
				luck = rand.nextInt((int)(c.level / 10) + 1);
				speed = rand.nextInt(10) + 1;
				break;
			case 1:
				// Str
				strength = rand.nextInt(c.level * 2) + (rarity + 1) * 2;
				intelligence = 0;
				luck = rand.nextInt(c.level / 5) + (rarity + 1) * 2;
				agility = rand.nextInt(c.level / 10) + (rarity + 1) * 2;
				speed = rand.nextInt(6) + 1;
				break;
			case 2:
				// Luck
				strength = rand.nextInt(c.level / 2 + 1);
				intelligence = rand.nextInt(c.level / 2 + 1);
				luck = rand.nextInt(c.level);
				agility = rand.nextInt(c.level / 5) + (rarity + 1) * 2;
				speed = rand.nextInt(12) + 1;
				break;
			case 3:
				// Agi
				strength = rand.nextInt(c.level / 2 + 1);
				intelligence = rand.nextInt(c.level / 2 + 1);
				agility = rand.nextInt(c.level);
				luck = rand.nextInt(c.level / 5) + (rarity + 1) * 2;
				speed = rand.nextInt(10) + 5;
				break;
		}
		
		int damage = rand.nextInt(c.level) + (c.level) + 2;
		
		Weapon sword =  new Weapon(name, descr, rarity, damage, speed, strength, intelligence, agility, luck, genSprite("Sprites/Items/Weapons/" + spritesFolder, rarity));	
		return sword;
	}
	
	public ImageIcon genSprite(String folderName, int rarity) {
		Random rng = new Random();
		File[] arr = null;
		if (rarity < 5) {
			File folder = new File(folderName);
			arr = folder.listFiles();
		}
		if (rarity == 5) {
			File folder = new File(folderName + "Legendary/");
			arr = folder.listFiles();
		}
		if (rarity == 6) {
			File folder = new File(folderName + "Divine/");
			arr = folder.listFiles();
		}
		if (arr.length == 0)
			return new ImageIcon("");
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
	
	public String[] genName(String weaponType, int rarity) {
		Random rng = new Random();
		String[] arr = {};
		String[] preAdjectives, postAdjectives, entities;
		switch (rarity) {
			case 5:
			case 6: 
				preAdjectives = legendaryDivinePreAdjectives;
				postAdjectives = legendaryDivinePostAdjectives;
				entities = legendaryDivineEntities;
				break;
			default:
				preAdjectives = basePreAdjectives;
				postAdjectives = basePostAdjectives;
				entities = baseEntities;
				break;
		}
		
		switch (weaponType.toLowerCase()) {
			case "sword":
				arr = swordBaseNames;
				break;
			case "dagger":
				arr = daggerBaseNames;
				break;
			case "hammer":
				arr = hammerBaseNames;
				break;
			case "wand":
				arr = wandBaseNames;
				break;
			case "staff":
				arr = staffBaseNames;
				break;
			case "bow":
				arr = bowBaseNames;
				break;
			case "crossbow":
				arr = crossbowBaseNames;
				break;
			default:
		}
		
		String[] name = new String[4];
		int path = rng.nextInt(4);
		
		// name[0] = the actual name
		// name[1] = the type of weapon
		// name[2] = entity
		// name[3] = adjective
		
		switch (path) {
			case 0:
				// No pre-adjective, with a post adjective
				name[0] = preConstructions[rng.nextInt(preConstructions.length)] + " " + (name[1] = arr[rng.nextInt(arr.length)]) + " of " + (name[3] = postAdjectives[rng.nextInt(postAdjectives.length)]);
				name[2] = "";
				break;
			case 1:
				// No pre-adjective, with a post entity
				name[0] = preConstructions[rng.nextInt(preConstructions.length)] + " " + (name[1] = arr[rng.nextInt(arr.length)]) + " of " + (name[2] = entities[rng.nextInt(entities.length)]);
				name[3] = "";
				break;
			case 2:
				// Pre-adjective
				name[0] = (name[3] = preAdjectives[rng.nextInt(preAdjectives.length)]) + " " + (name[1] = arr[rng.nextInt(arr.length)]);
				name[2] = "";
				break;
			case 3: 
				// Pre-entity
				int temp;
				name[0] = (name[2] = (entities[temp = rng.nextInt(entities.length)].substring(0, 1).toUpperCase()) + entities[temp].substring(1, entities[temp].length())) + "'s " + (name[1] = arr[rng.nextInt(arr.length)]);
				name[3] = "";
				break;
		}
		
		return name;
	}
	
	public String genDescr(String[] nameComponents, String weaponType, int rarity) {
		// name[0] = the actual name
		// name[1] = the type of weapon
		// name[2] = entity
		// name[3] = adjective
		Random rng = new Random();
		String material = "";
		String descr = "";
		switch (weaponType) {
			case "sword":
			case "dagger":
			case "hammer":
				material = matsMelee[rarity][rng.nextInt(matsMelee[rarity].length)];
				break;
			case "wand":
			case "staff":
			case "bow":
			case "crossbow":
				material = matsRanged[rng.nextInt(matsRanged.length)];
		}
		int path = rng.nextInt(2);
		if (rarity < 5) {
			if (path == 1) {
				descr = "A " + nameComponents[1].toLowerCase() + descrPhrasesType[0][rng.nextInt(descrPhrasesType[0].length)] + material + descrPhrasesType[1][rng.nextInt(descrPhrasesType[1].length)];
			} else {
				if (nameComponents[2].equals("")) {
					descr = "The " + nameComponents[3].toLowerCase() + " of this " + nameComponents[1].toLowerCase() + " " + descrPhrasesAdjective[0][rng.nextInt(descrPhrasesAdjective[0].length)];
				} else {
					descr = descrPhrasesEntity[0][rng.nextInt(descrPhrasesEntity[0].length)] + nameComponents[2] + ", this " + material + " " + nameComponents[1].toLowerCase() + " is " + descrPhrasesEntity[1][rng.nextInt(descrPhrasesEntity[1].length)];
				}
			}
		} else {
			if (nameComponents[2].equals("")) {
				descr = "This " + material + " " + nameComponents[1].toLowerCase() + " " + descrLegendaryDivine[0][rng.nextInt(descrLegendaryDivine[0].length)];
			} else {
				descr = descrLegendaryDivine[1][rng.nextInt(descrLegendaryDivine[1].length)] + " " + nameComponents[2] + ", this " + nameComponents[1].toLowerCase() + " " + descrLegendaryDivine[0][rng.nextInt(descrLegendaryDivine[0].length)];
			}
		}
		return descr;
	}
}
