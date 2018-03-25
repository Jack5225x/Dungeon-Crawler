package com.jfeather.Items;

import javax.swing.ImageIcon;

public class UniqueConsumable {

	public int potency, effectType;
	public ImageIcon sprite;
	public String name, toolTip;
	
	public UniqueConsumable(String itemName, int itemPotency, int itemEffectType, ImageIcon itemSprite, String itemDescr) {
		name = itemName;
		potency = itemPotency;
		sprite = itemSprite;
	}
}
