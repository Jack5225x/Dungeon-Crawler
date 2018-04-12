package com.jfeather.Level;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.jfeather.Player.Character;

public class LevelInstance {

	private int floor;
	private Character character;
	private Image sprite;
	
	public LevelInstance(int floorNumber, Character c) {
		floor = floorNumber;
		character = c;
		sprite = (new ImageIcon("Sprites/Level/TestLevel.png")).getImage();
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int newFloor) {
		floor = newFloor;
	}
	
}
