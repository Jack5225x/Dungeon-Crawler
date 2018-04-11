package com.jfeather.Level;

import com.jfeather.Player.Character;

public class LevelInstance {

	private int floor;
	private Character character;
	
	public LevelInstance(int floorNumber, Character c) {
		floor = floorNumber;
		character = c;
	}
}
