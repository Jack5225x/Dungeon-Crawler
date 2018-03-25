package com.jfeather.Player;

import java.util.Random;

import javax.swing.ImageIcon;

public class Character {

	public int level, health, mana, intelligence, strength, defense, agility, luck;
	public String name;
	public ImageIcon sprite;
		
	public Character(String charName) {
		name = charName;
		sprite = null;
		level = 1;
		health = 100;
		mana = 100;
		intelligence = 1;
		strength = 1;
		defense = 0;
		agility = 1;
		luck = 1;
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

}
