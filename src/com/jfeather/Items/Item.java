package com.jfeather.Items;

import javax.swing.ImageIcon;

public class Item {

	private int damage, speed, rarity, itemType, consumableType, potency, defense, effectType, strength, intelligence, luck, agility;
	private double range;
	private String name, descr, toolTip;
	private ImageIcon sprite, projectile;
	
	public Item(Weapon weapon) {
		itemType = 0;
		damage = weapon.getDamage();
		strength = weapon.getStrength();
		intelligence = weapon.getIntelligence();
		speed = weapon.getSpeed();
		agility = weapon.getAgility();
		luck = weapon.getLuck();
		rarity = weapon.getRarity();
		name = weapon.getName();
		descr = weapon.getDescr();
		toolTip = weapon.getToolTip();
		sprite = weapon.getSprite();
		projectile = weapon.getProjectile();
	}
	
	public Item(StandardConsumable consumable) {
		itemType = 1;
		consumableType = consumable.getConsumableType();
		potency = consumable.getPotency();
		sprite = consumable.getSprite();
		name = consumable.getName();
		toolTip = consumable.getToolTip();
	}
	
	public Item(UniqueConsumable consumable) {
		itemType = 2;
		effectType = consumable.getEffectType();
		potency = consumable.getPotency();
		name = consumable.getName();
		toolTip = consumable.getToolTip();
		sprite = consumable.getSprite();
	}
	
	public Item(Armor armor) {
		itemType = 3;
		defense = armor.getDefense();
		rarity = armor.getRarity();
		name = armor.getName();
		toolTip = armor.getToolTip();
		sprite = armor.getSprite();
		intelligence = armor.getIntelligence();
		strength = armor.getStrength();
		agility = armor.getAgility();
		luck = armor.getLuck();
	}
	
	public Item(Helmet helmet) {
		itemType = 4;
		defense = helmet.getDefense();
		rarity = helmet.getRarity();
		name = helmet.getName();
		toolTip = helmet.getToolTip();
		sprite = helmet.getSprite();
		intelligence = helmet.getIntelligence();
		strength = helmet.getStrength();
		agility = helmet.getAgility();
		luck = helmet.getLuck();

	}
	
	public Item() {
		name = "null";
	}
	
	public Weapon toWeapon() {
		if (!name.equals("null"))
			return new Weapon(name, descr, rarity, damage, speed, range, strength, intelligence, agility, luck, sprite, projectile);
		return null;
	}
	
	public Armor toArmor() {
		if (!name.equals("null"))
			return new Armor(name, descr, rarity, defense, strength, intelligence, agility, luck, sprite);
		return null;
	}
	
	public Helmet toHelmet() {
		if (!name.equals("null"))
			return new Helmet(name, descr, rarity, defense, strength, intelligence, agility, luck, sprite);
		return null;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getIntelligence() {
		return intelligence;
	}
	
	public int getAgility() {
		return agility;
	}
	
	public int getLuck() {
		return luck;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public double getRange() {
		return range;
	}
	
	public int getRarity() {
		return rarity;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescr() {
		return descr;
	}
	
	public String getToolTip() {
		return toolTip;
	}
	
	public ImageIcon getSprite() {
		return sprite;
	}

	public int getItemType() {
		return itemType;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public int getEffectType() {
		return effectType;
	}
	
	public int getConsumableType() {
		return consumableType;
	}
	
	public int getPotency() {
		return potency;
	}

}
