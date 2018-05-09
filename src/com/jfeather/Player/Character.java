package com.jfeather.Player;

import java.util.Random;

import javax.swing.ImageIcon;

import com.jfeather.Items.Armor;
import com.jfeather.Items.Helmet;
import com.jfeather.Items.Weapon;

public class Character {

	private int level, health, maxHealth, mana, maxMana, intelligence, strength, defense, agility, luck;
	private String name;
	private ImageIcon sprite;
	private boolean invulnerable;
	private Weapon activeWeapon;
	private Armor activeArmor;
	private Helmet activeHelmet;
	
	public Character(String charName) {
		name = charName;
		sprite = null;
		level = 1;
		maxHealth = 100;
		health = 100;
		maxMana = 100;
		mana = 100;
		intelligence = 1;
		strength = 1;
		defense = 0;
		agility = 1;
		luck = 1;
		invulnerable = false;
	}
	
	public void levelUp() {
		Random rand = new Random();
		level++;
		health += rand.nextInt(20) + 10;
		mana += rand.nextInt(10) + 5;
		defense += 2;
		int[] stats = {intelligence, strength, luck, agility};
		insertSort(stats);
		if (stats[0] == intelligence)
			intelligence += rand.nextInt((int) (level / 2)) + 1;
		if (stats[0] == strength)
			strength += rand.nextInt((int) (level / 2)) + 1;
		if (stats[0] == agility)
			agility += rand.nextInt((int) (level / 2)) + 1;
		if (stats[0] == luck)
			luck += rand.nextInt((int) (level / 2)) + 1;
		if (stats[1] == intelligence)
			intelligence += rand.nextInt((int) (level / 2.5)) + 1;
		if (stats[1] == strength)
			strength += rand.nextInt((int) (level / 2.5)) + 1;
		if (stats[1] == agility)
			agility += rand.nextInt((int) (level / 2.5)) + 1;
		if (stats[1] == luck)
			luck += rand.nextInt((int) (level / 2.5)) + 1;
		if (stats[2] == intelligence)
			intelligence += rand.nextInt((int) (level / 4)) + 1;
		if (stats[2] == strength)
			strength += rand.nextInt((int) (level / 4)) + 1;
		if (stats[2] == agility)
			agility += rand.nextInt((int) (level / 4)) + 1;
		if (stats[2] == luck)
			luck += rand.nextInt((int) (level / 4)) + 1;
		if (stats[3] == intelligence)
			intelligence += rand.nextInt((int) (level / 5)) + 1;
		if (stats[3] == strength)
			strength += rand.nextInt((int) (level / 5)) + 1;
		if (stats[3] == agility)
			agility += rand.nextInt((int) (level / 5)) + 1;
		if (stats[3] == luck)
			luck += rand.nextInt((int) (level / 5)) + 1;

	}
	
	public static void insertSort(int[] arr) {
		int n = arr.length;
		int tmp = 0;		 
		for (int i = 1; i < n; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j - 1] > arr[j]) {
					tmp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = tmp;
				}
			}
		}
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getMaxMana() {
		return maxMana;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMana() {
		return mana;
	}
	
	public int getIntelligence() {
		return intelligence;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public int getAgility() {
		return agility;
	}
	
	public int getLuck() {
		return luck;
	}
	
	public void setLevel(int newLevel) {
		if (newLevel > level) {
			while (level < newLevel) {
				levelUp();
			}
			
		} else {
			level = 1;
			health = 100;
			mana = 100;
			intelligence = 1;
			strength = 1;
			defense = 0;
			agility = 1;
			luck = 1;
			while (level < newLevel) {
				levelUp();
			}
		}
	}
	
	public void setMaxHealth(int newHealth) {
		maxHealth = newHealth;
	}
	
	public void setMaxMana(int newMana) {
		maxMana = newMana;
	}
	
	public void setIntelligence(int newIntelligence) {
		intelligence = newIntelligence;
	}
	
	public void setStrength(int newStrength) {
		strength = newStrength;
	}
	
	public void setDefense(int newDefense) {
		defense = newDefense;
	}
	
	public void setAgility(int newAgility) {
		agility = newAgility;
	}
	
	public void setLuck(int newLuck) {
		luck = newLuck;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public ImageIcon getSprite() {
		return sprite;
	}
	
	public void setSprite(ImageIcon newSprite) {
		sprite = newSprite;
	}
	
	public void setHealth(int newHealth) {
		health = newHealth;
	}
	
	public void setMana(int newMana) {
		mana = newMana;
	}
	
	public void addStrength(int increment) {
		strength += increment;
	}
	
	public void subtractHealth(int healthDecrement) {
		if (!invulnerable && healthDecrement > 0)
			health -= healthDecrement;
	}
	
	public void addHealth(int healthIncrement) {
		if (healthIncrement > 0)
			health += healthIncrement;
	}
	
	public void subtractMana(int manaDecrement) {
		mana -= manaDecrement;
	}
	
	public void addMana(int manaIncrement) {
		mana += manaIncrement;
	}
	
	public void setInvulnerable(boolean trueOrFalse) {
		invulnerable = trueOrFalse;
	}
	
	public boolean isInvulnerable() {
		return invulnerable;
	}
	
	public void setActiveWeapon(Weapon weapon) {
		activeWeapon = weapon;
	}
	
	public void setActiveArmor(Armor armor) {
		activeArmor = armor;
	}
	
	public void setActiveHelmet(Helmet helmet) {
		activeHelmet = helmet;
	}
	
	public Weapon getActiveWeapon() {
		return activeWeapon;
	}
	
	public Armor getActiveArmor() {
		return activeArmor;
	}
	
	public Helmet getActiveHelmet() {
		return activeHelmet;
	}

}
