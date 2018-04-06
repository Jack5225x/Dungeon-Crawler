package com.jfeather.Generation;

import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

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
	
	public ArmorGen(Character c) {
		character = c;
		int rarity = genRarity();
	}
	
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


}
