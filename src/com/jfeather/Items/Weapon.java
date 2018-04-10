package com.jfeather.Items;

import javax.swing.ImageIcon;

import com.jfeather.Items.DescrWrap;
public class Weapon {
	
	public int damage, strength, intelligence, speed, rarity, itemType, agility, luck;
	public ImageIcon sprite;
	public String name, toolTip, descr;
	
	public Weapon(String itemName, String itemDescr, int itemRarity, int itemDamage, int itemSpeed, int itemStrength, int itemIntelligence, int itemAgility, int itemLuck, ImageIcon itemSprite) {
		strength = itemStrength;
		intelligence = itemIntelligence;
		damage = itemDamage;
		speed = itemSpeed;
		agility = itemAgility;
		luck = itemLuck;
		rarity = itemRarity;
		sprite = itemSprite;
		itemType = 0;
		name = itemName;
		descr = itemDescr;
		String rarityColor = "";
		switch (itemRarity) {
			case 0: rarityColor = "black"; break;
			case 1: rarityColor = "white"; break;
			case 2: rarityColor = "green"; break;
			case 3: rarityColor = "blue"; break;
			case 4: rarityColor = "purple"; break;
			case 5: rarityColor = "yellow"; break;
			case 6: rarityColor = "orange"; break;
			default: System.out.println("Invalid item rarity! ("+name+")"); rarityColor = "white"; break;
		}
		String stats = "";
		if (strength != 0)  
			stats = stats + "Strength +<font color='red'>"+strength+"</font><br>";
		if (intelligence != 0)
			stats = stats + "Intelligence +<font color='red'>"+intelligence+"</font><br>";
		if (agility != 0)
			stats = stats + "Agility +<font color='red'>"+agility+"</font><br>";
		if (luck != 0)
			stats = stats + "Luck +<font color='red'>"+luck+"</font><br>";
		
		toolTip = "<html> <b><font color='"+rarityColor+"'>"+name+"</font><br>Damage: <font color='red'>"+damage+"</font><br>Speed: <font color='red'>"+speed+"</font><br>"+stats+"<i>"+DescrWrap.descrWrap(descr, name)+"</html>";
	}
	
	public void debugWeapon(Weapon sword) {
		System.out.println("Name: "+sword.name);
		System.out.println("Damage: "+sword.damage);
		System.out.println("Speed: "+sword.speed);
		System.out.println("Rarity ID: "+sword.rarity);
		System.out.println("Description (Formatted): "+DescrWrap.descrWrap(sword.descr, name));
	}
	
}
