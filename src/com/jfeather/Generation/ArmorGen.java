package com.jfeather.Generation;

import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

import com.jfeather.Items.Armor;
import com.jfeather.Player.Character;

public class ArmorGen {

	private String[] preConstructions = {"The ", ""};
	private String[] basePreAdjectives = {"Mighty", "Strong" ,"Steadfast", "Crystal", "Shiny", "Angelic", "Demonic", "Adorned", "Void"};
	private String[] legendaryDivinePreAdjectives = {"Rainbow", "Godly", "Ancient", "Divine", "Legendary", "Infinity", "Quantum", "Tachyonic"};
	private String[] basePostAdjectives = {"Avarice", "Swiftness", "Strength", "Destruction", "Darkness", "Brightness"};
	private String[] baseEntities = {"Jester", "Behemoth", "Giant", "Sentinel", "Beholder", "Peasant"};
	private String[] legendaryDivinePostAdjectives = {"Might", "Magic", "Infinity", "the Void", "Impossibility", "Prisms", "Illusions", "Aether"};
	private String[] legendaryDivineEntities = {"Gods", "King", "Ancients", "Morning Star", "Meteor", "McDonald's Breakfast Artisans", "Mr. Gudbrandsen's Ex-wife", "Mr. Gudbrandsen"};
	private Character character;
	// Weapon types and names
	private String[] armorNames = {"Chainmail", "Armor", "Chestplate", ""};
	
	// Weapon materials based on rarity
	private String[][] mats = {
			{"pig iron", "aluminum", "stone", "tin", "lead", "copper"},
			{"steel", "iron", "bronze", "manganite"},
			{"cobalt", "black iron", "mithril"},
			{"damascus steel", "titanium", "dark steel"},
			{"iridium", "electrum", "promethium"},
			{"draconium", "dragonite", "metoerite"},
			{"adamantium", "vibranium", "ethereum", "neutronium", "electronium"},
	};
		
	// Description arrays
	private String[][] descrPhrasesEntity = {
			{"Forged for ", "Crafted for ", "Made by "},
			{"very strong.", "very reliable.", "quite old", "seemingly new", "quite solid"}
	};
	
	private String[][] descrPhrasesAdjective = {
			{"is rivaled by no other.", "is unmatched by any other common piece.", "holds up even to the most damaging attacks."},
	};
	
	private String[][] descrPhrasesType = {
			{" forged from ", " made of ", " crafted from "},
			{". Seems pretty sturdy.", ". Looks strong enough.", ". Should hold up in battle."}
	};

	private String[][] descrLegendaryDivine  = {
			{"was recovered from an ancient tomb.", "was found alongside a forgotten king.", "protected its wearer from hordes of enemies in a lost past.", "was worn by an old god.", "causes any who wear it to be controlled by a mystical energy.", "summons the might of ungodly forces.", "has proved to be too powerful for even the mightiest kings to control."},
			{"Worn by the", "Forged by the", "Summoned by the"}
	};
	/*
	public Armor genArmor(Character c) {
		character = c;
		int rarity = genRarity();
		String[] nameComponents = genName(rarity);
		String name = nameComponents[0];
		String descr = genDescr(nameComponents, rarity);
		String spritesFolder = "Sprites/Items/Armor/";
		ImageIcon sprite = genSprite(spritesFolder, rarity);
	}
	*/
	public int genRarity() {
		Random rng = new Random();
		int rarity = 0;
		int rar = rng.nextInt(1000 + character.getLevel() * 5);
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
	
	public ImageIcon genSprite(String folderName, int rarity) {
		Random rng = new Random();
		File[] arr = null;
		int index;
		while (true) {
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
			if (arr.length != 0) {
				index = rng.nextInt(arr.length);
				if (!(arr[index].getName().equals("Legendary")) && !(arr[index].getName().equals("Divine")) && !(arr[index].getName().equals(".directory")))
					break;
			}
		}
		return new ImageIcon(arr[index].getPath());
	}


	public String[] genName(int rarity) {
		Random rng = new Random();
		String[] arr = armorNames;
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
		
		String[] name = new String[5];
		int path = rng.nextInt(4);
		for (int i = 0; i < name.length; i++) {
			name[i] = "";
		}
		// name[0] = the actual name
		// name[1] = the type of weapon
		// name[2] = post entity
		// name[3] = post adjective
		// name[4] = pre adjective
		
		switch (path) {
			case 0:
				// No pre-adjective, with a post adjective
				name[0] = preConstructions[rng.nextInt(preConstructions.length)] + (name[1] = arr[rng.nextInt(arr.length)]) + " of " + (name[3] = postAdjectives[rng.nextInt(postAdjectives.length)]);
				name[2] = "";
				name[4] = "";
				break;
			case 1:
				// No pre-adjective, with a post entity
				name[0] = preConstructions[rng.nextInt(preConstructions.length)] + (name[1] = arr[rng.nextInt(arr.length)]) + " of the " + (name[2] = entities[rng.nextInt(entities.length)]);
				name[3] = "";
				name[4] = "";
				break;
			case 2:
				// Pre-adjective
				name[0] = (name[4] = preAdjectives[rng.nextInt(preAdjectives.length)]) + " " + (name[1] = arr[rng.nextInt(arr.length)]);
				name[2] = "";
				name[3] = "";
				break;
			case 3: 
				// Pre-entity
				int temp;
				name[2] = (entities[temp = rng.nextInt(entities.length)].substring(0, 1).toUpperCase()) + entities[temp].substring(1, entities[temp].length());
				if (name[2].substring(name[2].length() - 1, name[2].length()).equals("s"))
					name[0] = name[2] + "'" +  " " + (name[1] = arr[rng.nextInt(arr.length)]);
				else
					name[0] = name[2] + "'s " + (name[1] = arr[rng.nextInt(arr.length)]);
				name[3] = "";
				break;
		}
		
		return name;
	}
	
	public String genDescr(String[] nameComponents, int rarity) {
		// name[0] = the actual name
		// name[1] = the type of weapon
		// name[2] = entity
		// name[3] = adjective
		Random rng = new Random();
		String descr = "";
		String material = mats[rarity][rng.nextInt(mats[rarity].length)];
		int path = rng.nextInt(2);
		if (rarity < 5) {
			if (path == 1) {
				descr = nameComponents[1].toLowerCase() + descrPhrasesType[0][rng.nextInt(descrPhrasesType[0].length)] + material + descrPhrasesType[1][rng.nextInt(descrPhrasesType[1].length)];
			} else {
				if (nameComponents[2].equals("") && nameComponents[4].equals("")) {
					descr = "The " + nameComponents[3].toLowerCase() + " of this " + nameComponents[1].toLowerCase() + " " + descrPhrasesAdjective[0][rng.nextInt(descrPhrasesAdjective[0].length)];
				} else {
					if (nameComponents[2].equals(""))
						descr = "This " + material + " " + nameComponents[1].toLowerCase() + " " + descrPhrasesAdjective[0][rng.nextInt(descrPhrasesAdjective[0].length)];
					else
						descr = descrPhrasesEntity[0][rng.nextInt(descrPhrasesEntity[0].length)] + nameComponents[2] + ", this " + material + " " + nameComponents[1].toLowerCase() + " is " + descrPhrasesEntity[1][rng.nextInt(descrPhrasesEntity[1].length)];
				}
			}
		} else {
			if (nameComponents[2].equals("") && nameComponents[4].equals("")) {
				descr = "This " + material + " " + nameComponents[1].toLowerCase() + " " + descrLegendaryDivine[0][rng.nextInt(descrLegendaryDivine[0].length)];
			} else {
				if (nameComponents[2].equals(""))
					descr = "This " + material + " " + nameComponents[4].toLowerCase() + " " + nameComponents[1].toLowerCase() + " " + descrLegendaryDivine[0][rng.nextInt(descrLegendaryDivine[0].length)];
				else
					descr = descrLegendaryDivine[1][rng.nextInt(descrLegendaryDivine[1].length)] + " " + nameComponents[2] + ", this " + nameComponents[1].toLowerCase() + " " + descrLegendaryDivine[0][rng.nextInt(descrLegendaryDivine[0].length)];
			}
		}
		return descr;
	}


}
