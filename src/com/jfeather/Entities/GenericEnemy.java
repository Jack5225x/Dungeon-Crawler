package com.jfeather.Entities;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GenericEnemy {

	private int health, maxHealth;
	private Image sprite;
	private Image projectile;
	private int range, damage, speed;
	
	public GenericEnemy() {
		
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public Image getProjectile() {
		return projectile;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setHealth(int newHealth) {
		health = newHealth;
	}
	
	public void setMaxHealth(int newMaxHealth) {
		maxHealth = newMaxHealth;
	}
	
	public void setSprite(Image newSprite) {
		sprite = newSprite;
	}
	
	public void setSprite(ImageIcon newSprite) {
		sprite = newSprite.getImage();
	}
	
	public void setSprite(String imagePath) {
		sprite = new ImageIcon(imagePath).getImage();
	}
	
	public void setProjectile(Image newSprite) {
		projectile = newSprite;
	}
	
	public void setProjectile(ImageIcon newSprite) {
		projectile = newSprite.getImage();
	}
	
	public void setProjectile(String imagePath) {
		projectile = new ImageIcon(imagePath).getImage();
	}
	
	public void setRange(int newRange) {
		range = newRange;
	}
	
	public void setDamage(int newDamage) {
		damage = newDamage;
	}
	
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}

	
}
