package com.jfeather.Items;

import javax.swing.ImageIcon;

public class StandardConsumable {
	
	private int consumableType, potency;
	private ImageIcon sprite;
	private String toolTip, name;
	
	public StandardConsumable(int itemConsumableType, int itemPotency) {
		consumableType = itemConsumableType;
		potency = itemPotency;
		String typeName = "", descr = "", color = "";
		switch (consumableType) {
			case 0:	
				typeName = "Health Potion";
				color = "red";
				sprite = new ImageIcon("Sprites/Consumables/HealthPotion.png");
				descr = DescrWrap.descrWrap("A potion that restores "+((potency+1) * 50)+" points of health. Can also be applied to a weapon to deal "+(potency+1)+" damage per second to undead creatures.", name);
				break;
			case 1:
				typeName = "Mana Potion";
				color = "purple";
				sprite = new ImageIcon("Sprites/Consumables/ManaPotion.png");
				descr = DescrWrap.descrWrap("A potion that restores "+((potency+1) * 50)+" points of mana", name);
				break;
			case 2:
				typeName = "Poison";
				color = "green";
				sprite = new ImageIcon("Sprites/Consumables/PoisonPotion.png");
				descr = DescrWrap.descrWrap("A potion that decreases one's health by "+((potency+1) * 10)+" points. Can also be applied to a weapon to deal "+(potency+1)+" damage per second to living creatures.", name);
				break;
			case 3:
				typeName = "Shield";
				color = "blue";
				sprite = new ImageIcon("Sprites/Consumables/ShieldPotion.png");
				descr = DescrWrap.descrWrap("Gives the player "+((potency + 1) * 40)+" shield points. These are depleted before health under normal circumstances.", name);
				break;
			case 4:
				typeName = "Food";
				color = "brown";
				sprite = new ImageIcon("Sprites/Consumables/FoodItem.png");
				descr = DescrWrap.descrWrap("Some scraps of food that restore "+((potency + 1)* 20)+" points of health. Probably not that great for you, but hey, who am I to judge?", name);
				break;
			case 5:
				typeName = "Health Potion";
				sprite = new ImageIcon("Sprites/Consumables/HealthPotion.png");
				break;
		}
		name = typeName;
		toolTip = "<html> <b><font color='"+color+"'>"+typeName+"</font><br>Potency: <font color='red'>"+potency+"</font><i>"+descr;
	}
	
	public int getConsumableType() {
		return consumableType;
	}
	
	public int getPotency() {
		return potency;
	}
		
	public ImageIcon getSprite() {
		return sprite;
	}
	
	public String getName() {
		return name;
	}
	
	public String getToolTip() {
		return toolTip;
	}

}
