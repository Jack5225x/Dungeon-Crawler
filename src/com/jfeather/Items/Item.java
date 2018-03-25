package com.jfeather.Items;

import javax.swing.ImageIcon;

public class Item {

	public int damage, speed, rarity, itemType, consumableType, potency, defense, effectType, strength, intelligence, luck, agility;
	public String name, descr, toolTip;
	public ImageIcon sprite;
	
	public Item(Weapon weapon) {
		itemType = 0;
		damage = weapon.damage;
		strength = weapon.strength;
		intelligence = weapon.intelligence;
		speed = weapon.speed;
		agility = weapon.agility;
		luck = weapon.luck;
		rarity = weapon.rarity;
		name = weapon.name;
		descr = weapon.descr;
		toolTip = weapon.toolTip;
		sprite = weapon.sprite;
	}
	
	public Item(StandardConsumable consumable) {
		itemType = 1;
		consumableType = consumable.consumableType;
		potency = consumable.potency;
		sprite = consumable.sprite;
		name = consumable.name;
		toolTip = consumable.toolTip;
	}
	
	public Item(UniqueConsumable consumable) {
		itemType = 2;
		effectType = consumable.effectType;
		potency = consumable.potency;
		name = consumable.name;
		toolTip = consumable.toolTip;
		sprite = consumable.sprite;
	}
	
	public Item(Armor armor) {
		itemType = 3;
		defense = armor.defense;
		rarity = armor.rarity;
		name = armor.name;
		toolTip = armor.toolTip;
		sprite = armor.sprite;
		intelligence = armor.intelligence;
		strength = armor.strength;
		agility = armor.agility;
		luck = armor.luck;
	}
	
	public Item(Helmet helmet) {
		itemType = 4;
		defense = helmet.defense;
		rarity = helmet.rarity;
		name = helmet.name;
		toolTip = helmet.toolTip;
		sprite = helmet.sprite;
		intelligence = helmet.intelligence;
		strength = helmet.strength;
		agility = helmet.agility;
		luck = helmet.luck;

	}
	
	public Item() {
		name = "null";
	}
}
