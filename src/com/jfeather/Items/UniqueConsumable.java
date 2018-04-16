package com.jfeather.Items;

import javax.swing.ImageIcon;

public class UniqueConsumable {

	private int potency, effectType;
	private ImageIcon sprite;
	private String name, toolTip;
	
	public UniqueConsumable(String itemName, int itemPotency, int itemEffectType, ImageIcon itemSprite, String itemDescr) {
		name = itemName;
		potency = itemPotency;
		sprite = itemSprite;
	}
	
	public int getPotency() {
		return potency;
	}
	
	public int getEffectType() {
		return effectType;
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
