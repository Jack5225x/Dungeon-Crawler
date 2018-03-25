package com.jfeather.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import com.jfeather.Exceptions.*;
import com.jfeather.Items.*;
import com.jfeather.Player.Character;

public class Inventory extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	public JPanel dialog = new JPanel();
	public JButton[] slots;
	public Item[] items;
	public JButton armorSlot, weaponSlot, helmetSlot, statsButton;
	public final int MAX_SLOTS = 10;
	public int weaponSlotIndex, armorSlotIndex, helmetSlotIndex, capacity;
	public int[][] slotLocations = {{10, 20}, {10, 80}, {65, 20}, {65, 80}, {120, 20}, {120, 80}, {175, 20}, {175, 80}, {230, 20}, {230, 80}, {540, 20} ,{540, 80}, {485, 50}};
	
	
	public Inventory(Character c, int inventoryCapacity) throws InventoryCapacityException {
		capacity = inventoryCapacity;
		if (inventoryCapacity > MAX_SLOTS)
			throw new InventoryCapacityException();
		else {
			dialog.setPreferredSize(new Dimension(640, 150));
			dialog.setLayout(new BorderLayout(0, 0));
			dialog.setBackground(new Color(94, 80, 30));
			dialog.addMouseListener(this);
			
			// Initialize the array the JButtons		
			slots = new JButton[MAX_SLOTS + 3];
			// Setup each JButton, whether it is used or not
			for (int i = 0; i < MAX_SLOTS + 3; i++) {
				slots[i] = new JButton();
				dialog.add(slots[i]);
				slots[i].setBounds(slotLocations[i][0], slotLocations[i][1], 50, 50);
				slots[i].setBackground(Color.LIGHT_GRAY);
				slots[i].setText((i+1)+"");
				slots[i].addMouseListener(this);
			}
			
			for (int i = MAX_SLOTS - 1; i > inventoryCapacity - 1; i--) {
				slots[i].setVisible(false);
			}
			
			// Create the armor and weapon active slots
			// Create the helmet slot
			slots[MAX_SLOTS].setText("H");
			slots[MAX_SLOTS].setToolTipText("Place your active helmet here");
			slots[MAX_SLOTS].setBackground(new Color(160, 160, 160));

			// Create the armor slot
			slots[MAX_SLOTS + 1].setText("A");
			slots[MAX_SLOTS + 1].setToolTipText("Place your active armor here");
			slots[MAX_SLOTS + 1].setBackground(new Color(160, 160, 160));
			
			// Create the weapon slot
			slots[MAX_SLOTS + 2].setText("W");
			slots[MAX_SLOTS + 2].setToolTipText("Place your active weapon here");
			slots[MAX_SLOTS + 2].setBackground(new Color(160, 160, 160));

			weaponSlot = slots[MAX_SLOTS + 2];
			armorSlot = slots[MAX_SLOTS + 1];
			helmetSlot = slots[MAX_SLOTS];
			
			// Create the stats button
			statsButton = new JButton("<html>S <br>T <br>A <br>T <br>S </html>");
			statsButton.setHorizontalTextPosition(JButton.CENTER);
			statsButton.setBounds(595, 20, 40, 110);
			statsButton.setBackground(new Color(130, 100, 130));
			statsButton.setToolTipText("See your character's stats");
			dialog.add(statsButton);
			
			// Create the item list
			items = new Item[MAX_SLOTS + 3];
			for (int i = 0; i < MAX_SLOTS + 3; i++) {
				items[i] = new Item();
			}
			
			// Add the decorative inventory borders (looks kinda wonky rn TODO: make it not wonky)
			JLabel topBorder = new JLabel(new ImageIcon("Sprites/Inventory/InventoryTopBorder.png"));
			dialog.add(topBorder, BorderLayout.NORTH);
			JLabel bottomBorder = new JLabel(new ImageIcon("Sprites/Inventory/InventoryBottomBorder.png"));
			dialog.add(bottomBorder, BorderLayout.SOUTH);
			JLabel rightBorder = new JLabel(new ImageIcon("Sprites/Inventory/InventoryBorderRight.png"));
			dialog.add(rightBorder, BorderLayout.EAST);
			rightBorder.setBounds(625, 15, 14, 120);
			JLabel leftBorder = new JLabel(new ImageIcon("Sprites/Inventory/InventoryBorderLeft.png"));
			leftBorder.setBounds(0, 15, 14, 120);
			dialog.add(leftBorder);

			
			// For some reason this is needed to fix the other buttons
			// Don't change below this line (unless you're better at coding than I am, which is probably not going to happen since I'm the only one working on this :( )
			JButton fix = new JButton();
			dialog.add(fix);
			fix.setVisible(false);
			fix.setEnabled(false);
			
		}
		
		statsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				int totalStrength = items[MAX_SLOTS].strength + items[MAX_SLOTS + 1].strength + items[MAX_SLOTS + 2].strength + c.strength;
				int totalInt = items[MAX_SLOTS].intelligence + items[MAX_SLOTS + 1].intelligence + items[MAX_SLOTS + 2].intelligence + c.intelligence;
				int totalDef = items[MAX_SLOTS].defense + items[MAX_SLOTS + 1].defense + items[MAX_SLOTS + 2].defense + c.defense;
				int totalAgility = items[MAX_SLOTS].agility + items[MAX_SLOTS + 1].agility + items[MAX_SLOTS + 2].agility + c.agility;
				int totalLuck = items[MAX_SLOTS].luck + items[MAX_SLOTS + 1].luck + items[MAX_SLOTS + 2].luck + c.luck;

				
				String stats = "<html><b>Character Name: </font><font color='red'>"+c.name+"</font>"
						+ "<br><b>Level: </font><font color='orange'>"+c.level+"</font>"
						+ "<br><b>Strength: </font><font color='yellow'>"+totalStrength+"     </font>"
						+ "<b><br>Intelligence: </font><font color='yellow'>"+totalInt+"</font>"
						+ "<br><b>Defense: </font><font color='green'>"+totalDef+"</font>"
						+ "<br><b>Health: </font><font color='blue'>"+c.health+"     </font>"
						+ "<b><br>Mana: </font><font color='purple'>"+c.mana+"</font>"
						+ "<br><b>Agility: </font><font color='red'>"+totalAgility+"</font>"
						+ "<br><b>Luck: </font><font color='orange'>"+totalLuck+"</font>";
				JOptionPane.showMessageDialog(null, stats, "Stats", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}
	
	// This set of methods adds a specific item to a specific spot in the inventory
	public void addItemToSlot(Weapon weapon, int slot) {
		slots[slot].setIcon(weapon.sprite);
		slots[slot].setToolTipText(weapon.toolTip);
		items[slot] = new Item(weapon);
	}
	
	public void addItemToSlot(StandardConsumable consumable, int slot) {
		slots[slot].setIcon(consumable.sprite);
		slots[slot].setToolTipText(consumable.toolTip);
		items[slot] = new Item(consumable);
	}
	
	public void addItemToSlot(UniqueConsumable consumable, int slot) {
		slots[slot].setIcon(consumable.sprite);
		slots[slot].setToolTipText(consumable.toolTip);
		items[slot] = new Item(consumable);
	}
	
	public void addItemToSlot(Armor armor, int slot) {
		slots[slot].setIcon(armor.sprite);
		slots[slot].setToolTipText(armor.toolTip);
		items[slot] = new Item(armor);
	}
	
	
	public void addItemToSlot(Helmet helmet, int slot) {
		slots[slot].setIcon(helmet.sprite);
		slots[slot].setToolTipText(helmet.toolTip);
		items[slot] = new Item(helmet);
	}
	
	// This set of methods add a specific item to the first available slot in the inventory
	public void addItem(Weapon weapon) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].name.equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(weapon.sprite);
			slots[index].setToolTipText(weapon.toolTip);
			items[index] = new Item(weapon);
		}
	}
	
	public void addItem(StandardConsumable consumable) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].name.equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(consumable.sprite);
			slots[index].setToolTipText(consumable.toolTip);
			items[index] = new Item(consumable);
		}
	}
	
	public void addItem(UniqueConsumable consumable) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].name.equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(consumable.sprite);
			slots[index].setToolTipText(consumable.toolTip);
			items[index] = new Item(consumable);
		}
	}
	
	public void addItem(Armor armor) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].name.equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(armor.sprite);
			slots[index].setToolTipText(armor.toolTip);
			items[index] = new Item(armor);
		}
	}

	public void addItem(Helmet helmet) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].name.equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(helmet.sprite);
			slots[index].setToolTipText(helmet.toolTip);
			items[index] = new Item(helmet);
		}
	}


	public void removeItem(int slot) {
		slots[slot].setIcon(null);
		slots[slot].setToolTipText("Empty Slot");
		items[slot] = new Item();
	}
	
	public void switchItems(int slot1, int slot2) {
		Icon tempIcon = slots[slot1].getIcon();
		String tempTip = slots[slot1].getToolTipText();
		Item tempItem = items[slot1];
		slots[slot1].setIcon(slots[slot2].getIcon());
		slots[slot1].setToolTipText(slots[slot2].getToolTipText());
		slots[slot2].setToolTipText(tempTip);
		slots[slot2].setIcon(tempIcon);
		items[slot1] = items[slot2];
		items[slot2] = tempItem;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	private int slot1, slot2;
	private boolean assigned1, assigned2;
	//{{10, 20}, {10, 80}, {65, 20}, {65, 80}, {120, 20}, {120, 80}, {175, 20}, {175, 80}, {230, 20}, {230, 80}, {0, 0}};

	@Override
	public void mousePressed(MouseEvent e) {
		int x = (int) (e.getLocationOnScreen().getX() - dialog.getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - dialog.getLocationOnScreen().getY());
		
		for (int i = 0; i < MAX_SLOTS + 3; i++) {
			if ((x > slotLocations[i][0] && x < slotLocations[i][0] + 50) && (y > slotLocations[i][1] && y < slotLocations[i][1] + 50)) {
				slot1 = i;
				assigned1 = true;
			}
		}
		// TODO: make the sprite follow the cursor while pressed down
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = (int) (e.getLocationOnScreen().getX() - dialog.getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - dialog.getLocationOnScreen().getY());
		for (int i = 0; i < MAX_SLOTS + 3; i++) {
			if ((x > slotLocations[i][0] && x < slotLocations[i][0] + 50) && (y > slotLocations[i][1] && y < slotLocations[i][1] + 50)) {
				slot2 = i;
				assigned2 = true;
			}
		}
		if ((slot2 == MAX_SLOTS && items[slot1].itemType == 4) || (slot2 == MAX_SLOTS + 1 && items[slot1].itemType == 3) || (slot2 == MAX_SLOTS + 2 && items[slot1].itemType == 0))
			switchItems(slot1, slot2);
		if (assigned1 && assigned2 && slots[slot1].isVisible() && slots[slot2].isVisible() && slot1 != slot2 && !(items[slot1].name.equals("null")) && slot2 < 10) {
			switchItems(slot1, slot2);
		}
		if (items[MAX_SLOTS + 2].name.equals("null"))
			slots[MAX_SLOTS + 2].setToolTipText("Place your active weapon here");
		if (items[MAX_SLOTS + 1].name.equals("null"))
			slots[MAX_SLOTS + 1].setToolTipText("Place your armor weapon here");
		if (items[MAX_SLOTS].name.equals("null"))
			slots[MAX_SLOTS].setToolTipText("Place your active helmet here");
		
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].name.equals("null"))
				slots[i].setToolTipText("Empty Slot");
		}

		
	}
}