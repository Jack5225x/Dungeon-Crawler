package com.jfeather.Items;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jfeather.Game.Line;
import com.jfeather.Items.DescrWrap;
import com.jfeather.Player.PlayerInstance;
public class Weapon {
	
	public static String SWORD = "sword";
	public static String DAGGER = "dagger";
	public static String WAND = "wand";
	public static String STAFF = "staff";
	public static String BOW = "bow";
	public static String HAMMER = "hammer";
	public static String CROSSBOW = "crossbow";
	
	private int damage, strength, intelligence, speed, rarity, itemType, agility, luck;
	private double range;
	private ImageIcon sprite, projectile;
	private String name, toolTip, descr;
	private boolean shooting;
	private int characterX, characterY;
	private int mouseX, mouseY;
	private JPanel dialog;
	
	public Weapon(String itemName, String itemDescr, int itemRarity, int itemDamage, int itemSpeed, double itemRange, int itemStrength, int itemIntelligence, int itemAgility, int itemLuck, ImageIcon itemSprite, ImageIcon itemProjectile) {
		strength = itemStrength;
		intelligence = itemIntelligence;
		damage = itemDamage;
		speed = itemSpeed;
		range = itemRange;
		agility = itemAgility;
		luck = itemLuck;
		rarity = itemRarity;
		sprite = itemSprite;
		projectile = itemProjectile;
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
	
	public ImageIcon getProjectile() {
		return projectile;
	}
	
	public void getPlayerCoords(PlayerInstance player) {
		characterX = player.getX();
		characterY = player.getY();
	}
	
	public void shoot(int xo, int yo, int xf, int yf, JPanel dialog) {
		if (name != null) {
			Line path = new Line(xo, yo, xf, yf);
			//path.printMatrix(path.genPoints(range));
			int[][] arr = path.genPoints(range);
			JLabel[] labels = new JLabel[arr.length];
			new Thread() {
				public void run() {
					for (int i = 0; i < arr.length; i++) {
						labels[i] = new JLabel(projectile);
						dialog.add(labels[i]);
						System.out.println(arr[i][0] + " " + arr[i][1]);
						if (arr[i][0] > 0 && arr[i][1] > 0)
							labels[i].setBounds(arr[i][0], arr[i][1], projectile.getIconWidth(), projectile.getIconHeight());
						labels[i].setVisible(true);
						try {
							Thread.sleep((int) (speed * 2.5));
							//labels[i].setVisible(false);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
	}
	
	public void updateShoot() {
		// A method that will be run continuously that will reference shoot at certain times
		if (shooting) {
			shoot(characterX, characterY, mouseX, mouseY, dialog);
		}
	}
	
	public void mousePressed(MouseEvent e, int mouseLocX, int mouseLocY, JPanel dialog) {
		shooting = true;
		mouseX = mouseLocX;
		mouseY = mouseLocY;
		this.dialog = dialog;
	}
	
	public void mouseReleased() {
		shooting = false;
	}
}
