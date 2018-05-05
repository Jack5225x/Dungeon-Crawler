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
	
	public Character character;
	private static final long serialVersionUID = 1L;
	public JButton[] slots;
	public Item[] items;
	public JLabel healthBar, manaBar;
	public JButton armorSlot, weaponSlot, helmetSlot, statsButton;
	public static final int MAX_SLOTS = 10;
	public int weaponSlotIndex, armorSlotIndex, helmetSlotIndex, capacity;
	public int[][] slotLocations = {{10, 20}, {10, 80}, {65, 20}, {65, 80}, {120, 20}, {120, 80}, {175, 20}, {175, 80}, {230, 20}, {230, 80}, {540, 20} ,{540, 80}, {485, 50}};
	private int totalStrength, totalDefense, totalAgility, totalLuck, totalIntelligence;
	private volatile boolean update = false;
	private int updateInterval = 50;
	public static final int WEAPON_SLOT = MAX_SLOTS + 2;
	public static final int ARMOR_SLOT = MAX_SLOTS + 1;
	public static final int HELMET_SLOT = MAX_SLOTS;
	private boolean showStats;
	private JLabel statsLine1, statsLine2, statsLine3;
	private TitleText statsName;
	
	public Inventory(Character c, int inventoryCapacity) throws InventoryCapacityException {
		character = c;
		capacity = inventoryCapacity;
		showStats = false;
		if (inventoryCapacity > MAX_SLOTS)
			throw new InventoryCapacityException();
		else {
			setPreferredSize(new Dimension(640, 150));
			setLayout(new BorderLayout(0, 0));
			setBackground(new Color(94, 80, 30));
			addMouseListener(this);
			
			// Initialize the array the JButtons		
			slots = new JButton[MAX_SLOTS + 3];
			// Setup each JButton, whether it is used or not
			for (int i = 0; i < MAX_SLOTS + 3; i++) {
				slots[i] = new JButton();
				add(slots[i]);
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
			add(statsButton);
			
			// Create the item list
			items = new Item[MAX_SLOTS + 3];
			for (int i = 0; i < MAX_SLOTS + 3; i++) {
				items[i] = new Item();
			}
			/*
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
			*/
			
			// Add the health and mana bars
			healthBar = new JLabel(new ImageIcon("Sprites/Inventory/HealthBar.png"));
			healthBar.setBounds(290, 75, 181, 25);
			add(healthBar);		
					
			manaBar = new JLabel(new ImageIcon("Sprites/Inventory/ManaBar.png"));
			manaBar.setBounds(290, 105, 181, 25);
			add(manaBar);
			
			new Thread() {
				@Override
				public void run() {
					while (true) {
						if (update) {
							try {
								Thread.sleep(updateInterval);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							updateStats();
						}
					}
				}
			}.start();
			
			// Create the stats state of the inventory panel
			statsName = new TitleText(this, character.getName(), 20, 25, 15, TitleText.BLUE);
			
			// Level, Health, Mana
			statsLine1 = new JLabel("<html> Level: <font color='green'>" + c.getLevel() + "</font> &emsp; &emsp; Health: <font color='red'>" + c.getMaxHealth() + "</font> &emsp; &emsp; Mana: <font color='blue'>" + c.getMaxMana());
			statsLine1.setBounds(20, 50, 350, 25);
			add(statsLine1);
			
			// Strength, Intelligence, Defense
			statsLine2 = new JLabel("<html> Strength: <font color='red'>" + totalStrength + "</font> &emsp; &emsp; Intelligence: <font color='red'>" + totalIntelligence + "</font> &emsp; &emsp; Defense: <font color='red'>" + totalDefense);
			statsLine2.setBounds(20, 80, 400, 25);
			add(statsLine2);
			
			// Luck and Agility
			statsLine3 = new JLabel("<html> Luck: <font color='red'>" + totalLuck + "</font> &emsp; &emsp; Agility: <font color='red'>" + totalAgility);
			statsLine3.setBounds(20, 110, 200, 25);
			add(statsLine3);
			
			statsName.setVisible(false);
			statsLine1.setVisible(false);
			statsLine2.setVisible(false);
			statsLine3.setVisible(false);
			
			// For some reason this is needed to fix the other buttons
			// Don't change below this line (unless you're better at coding than I am, which is probably not going to happen since I'm the only one working on this :( )
			add(new JLabel());

		}
		
		statsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				// TODO: make this toggle a different inventory screen that displays stats instead of a new window
				String stats = "<html><b>Character Name: </font><font color='blue'>"+c.getName()+"</font>"
						+ "<br><b>Level: </font><font color='green'>"+c.getLevel()+"</font>"
						+ "<br><b>Strength: </font><font color='red'>"+totalStrength+"     </font>"
						+ "<b><br>Intelligence: </font><font color='red'>"+totalIntelligence+"</font>"
						+ "<br><b>Defense: </font><font color='red'>"+totalDefense+"</font>"
						+ "<br><b>Health: </font><font color='red'>"+c.getHealth()+"</font>/<font color='red'>"+c.getMaxHealth() + "</font>"
						+ "<b><br>Mana: </font><font color='red'>"+c.getMana()+"</font>/<font color='red'>"+c.getMaxMana() + "</font>"
						+ "<br><b>Agility: </font><font color='red'>"+totalAgility+"</font>"
						+ "<br><b>Luck: </font><font color='red'>"+totalLuck+"</font>";
				//JOptionPane.showMessageDialog(null, stats, "Stats", JOptionPane.PLAIN_MESSAGE);
				
				showStats = !showStats; // Flip the state of the inventory
				
				if (showStats) {
					// First remove all of the other stuff and toggle the button text
					statsButton.setText("<html>I <br>T <br>E <br>M <br>S </html>");
					statsButton.setToolTipText("See your character's items");

					toggleSlots(false);
					healthBar.setVisible(false);
					manaBar.setVisible(false);
					
					statsName.setVisible(true);
					statsLine1.setVisible(true);
					statsLine2.setVisible(true);
					statsLine3.setVisible(true);
				} else {
					// Reset stuff
					statsButton.setText("<html>S <br>T <br>A <br>T <br>S </html>");
					statsButton.setToolTipText("See your character's stats");

					toggleSlots(true);
					healthBar.setVisible(true);
					manaBar.setVisible(true);
					
					statsName.setVisible(false);
					statsLine1.setVisible(false);
					statsLine2.setVisible(false);
					statsLine3.setVisible(false);

					
				}
			}
		});
	}
	
	// This set of methods adds a specific item to a specific spot in the inventory
	public void addItemToSlot(Weapon weapon, int slot) {
		slots[slot].setIcon(weapon.getSprite());
		slots[slot].setToolTipText(weapon.getToolTip());
		items[slot] = new Item(weapon);
	}
	
	public void addItemToSlot(StandardConsumable consumable, int slot) {
		slots[slot].setIcon(consumable.getSprite());
		slots[slot].setToolTipText(consumable.getToolTip());
		items[slot] = new Item(consumable);
	}
	
	public void addItemToSlot(UniqueConsumable consumable, int slot) {
		slots[slot].setIcon(consumable.getSprite());
		slots[slot].setToolTipText(consumable.getToolTip());
		items[slot] = new Item(consumable);
	}
	
	public void addItemToSlot(Armor armor, int slot) {
		slots[slot].setIcon(armor.getSprite());
		slots[slot].setToolTipText(armor.getToolTip());
		items[slot] = new Item(armor);
	}
	
	
	public void addItemToSlot(Helmet helmet, int slot) {
		slots[slot].setIcon(helmet.getSprite());
		slots[slot].setToolTipText(helmet.getToolTip());
		items[slot] = new Item(helmet);
	}
	
	// This set of methods add a specific item to the first available slot in the inventory
	public void addItem(Weapon weapon) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].getName().equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(weapon.getSprite());
			slots[index].setToolTipText(weapon.getToolTip());
			items[index] = new Item(weapon);
		}
	}
	
	public void addItem(StandardConsumable consumable) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].getName().equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(consumable.getSprite());
			slots[index].setToolTipText(consumable.getToolTip());
			items[index] = new Item(consumable);
		}
	}
	
	public void addItem(UniqueConsumable consumable) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].getName().equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(consumable.getSprite());
			slots[index].setToolTipText(consumable.getToolTip());
			items[index] = new Item(consumable);
		}
	}
	
	public void addItem(Armor armor) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].getName().equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(armor.getSprite());
			slots[index].setToolTipText(armor.getToolTip());
			items[index] = new Item(armor);
		}
	}

	public void addItem(Helmet helmet) {
		int index = -1;
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].getName().equals("null")) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			slots[index].setIcon(helmet.getSprite());
			slots[index].setToolTipText(helmet.getToolTip());
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
		int x = (int) (e.getLocationOnScreen().getX() - getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - getLocationOnScreen().getY());
		
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
		int x = (int) (e.getLocationOnScreen().getX() - getLocationOnScreen().getX());
		int y = (int) (e.getLocationOnScreen().getY() - getLocationOnScreen().getY());
		for (int i = 0; i < MAX_SLOTS + 3; i++) {
			if ((x > slotLocations[i][0] && x < slotLocations[i][0] + 50) && (y > slotLocations[i][1] && y < slotLocations[i][1] + 50)) {
				slot2 = i;
				assigned2 = true;
			}
		}
		if ((slot2 == MAX_SLOTS && items[slot1].getItemType() == 4) || (slot2 == MAX_SLOTS + 1 && items[slot1].getItemType() == 3) || (slot2 == MAX_SLOTS + 2 && items[slot1].getItemType() == 0))
			switchItems(slot1, slot2);
		if (assigned1 && assigned2 && slots[slot1].isVisible() && slots[slot2].isVisible() && slot1 != slot2 && !(items[slot1].getName().equals("null")) && slot2 < 10) {
			switchItems(slot1, slot2);
		}
		if (items[MAX_SLOTS + 2].getName().equals("null"))
			slots[MAX_SLOTS + 2].setToolTipText("Place your active weapon here");
		if (items[MAX_SLOTS + 1].getName().equals("null"))
			slots[MAX_SLOTS + 1].setToolTipText("Place your armor weapon here");
		if (items[MAX_SLOTS].getName().equals("null"))
			slots[MAX_SLOTS].setToolTipText("Place your active helmet here");
		
		for (int i = 0; i < MAX_SLOTS; i++) {
			if (items[i].getName().equals("null"))
				slots[i].setToolTipText("Empty Slot");
		}
		character.setActiveWeapon(items[MAX_SLOTS + 2].toWeapon());
		character.setActiveArmor(items[MAX_SLOTS + 1].toArmor());
		character.setActiveHelmet(items[MAX_SLOTS].toHelmet());
		
	}
	
	public void updateStats() {
		if (character.getHealth() != 0) {
			totalStrength = items[MAX_SLOTS].getStrength() + items[MAX_SLOTS + 1].getStrength() + items[MAX_SLOTS + 2].getStrength() + character.getStrength();
			totalIntelligence = items[MAX_SLOTS].getIntelligence() + items[MAX_SLOTS + 1].getIntelligence() + items[MAX_SLOTS + 2].getIntelligence() + character.getIntelligence();
			totalDefense = items[MAX_SLOTS].getDefense() + items[MAX_SLOTS + 1].getDefense() + items[MAX_SLOTS + 2].getDefense() + character.getDefense();
			totalAgility = items[MAX_SLOTS].getAgility() + items[MAX_SLOTS + 1].getAgility() + items[MAX_SLOTS + 2].getAgility() + character.getAgility();
			totalLuck = items[MAX_SLOTS].getLuck() + items[MAX_SLOTS + 1].getLuck() + items[MAX_SLOTS + 2].getLuck() + character.getLuck();
			healthBar.setBounds(290, 75, (int)(((double)character.getHealth() / (double)character.getMaxHealth()) * 181), 25);
			manaBar.setBounds(manaBar.getX(), manaBar.getY(), (int)(((double)character.getMana() / (double)character.getMaxMana()) * 181), 25);
			healthBar.setToolTipText("<html>Health: <font color='red'>"+character.getHealth() + "</font>/<font color='red'>" + character.getMaxHealth());
			manaBar.setToolTipText("<html>Mana: <font color='red'>"+character.getMana() + "</font>/<font color='red'>" + character.getMaxMana());
		
			// Update the stats panel
			statsLine1.setText("<html> Level: <font color='green'>" + character.getLevel() + "</font> &emsp; &emsp; Health: <font color='red'>" + character.getMaxHealth() + "</font> &emsp; &emsp; Mana: <font color='blue'>" + character.getMaxMana());
			statsLine2.setText("<html> Strength: <font color='red'>" + totalStrength + "</font> &emsp; &emsp; Intelligence: <font color='red'>" + totalIntelligence + "</font> &emsp; &emsp; Defense: <font color='red'>" + totalDefense);
			statsLine3.setText("<html> Luck: <font color='red'>" + totalLuck + "</font> &emsp; &emsp; Agility: <font color='red'>" + totalAgility);

		} else {
			// What happens if you die
			// TODO: ?
			//instance.dispose();
			System.out.println("Dead");
		}
	}
	
	public void setUpdate(boolean state) {
		update = state;
	}
	
	public void setUpdateInterval(int newIntervalMillis) {
		updateInterval = newIntervalMillis;
	}
	
	public int getTotalStrength() {
		return totalStrength;
	}
	
	public int getTotalIntelligence() {
		return totalIntelligence;
	}
	
	public int getTotalDefense() {
		return totalDefense;
	}
	
	public int getTotalLuck() {
		return totalLuck;
	}
	
	public int getTotalAgility() {
		return totalAgility;
	}
	
	// This method should be used in place of the method of the same name in Character
	public void setActiveWeapon(Weapon weapon) {
		character.setActiveWeapon(weapon);
		addItemToSlot(weapon, WEAPON_SLOT);
	}

	// This method should be used in place of the method of the same name in Character
	public void setActiveArmor(Armor armor) {
		character.setActiveArmor(armor);
		addItemToSlot(armor, ARMOR_SLOT);
	}
	
	// This method should be used in place of the method of the same name in Character
	public void setActiveHelmet(Helmet helmet) {
		character.setActiveHelmet(helmet);
		addItemToSlot(helmet, HELMET_SLOT);
	}
	
	public void setSlots(int newCapacity) {
		if (newCapacity > capacity) {
			for (int i = capacity; i < newCapacity; i++) {
				slots[i].setVisible(true);
				slots[i].addMouseListener(this);
			}
		} 
		if (newCapacity < capacity){
			for (int i = newCapacity; i < MAX_SLOTS; i++) {
				slots[i].setVisible(false);
				slots[i].removeMouseListener(this);
			}
		}
		capacity = newCapacity;
	}
	
	private void toggleSlots(boolean toggle) {
		for (int i = 0; i < capacity; i++) {
			slots[i].setVisible(toggle);
			if (toggle)
				slots[i].removeMouseListener(this);
			else
				slots[i].addMouseListener(this);
		}
	}
		
}