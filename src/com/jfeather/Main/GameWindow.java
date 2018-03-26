package com.jfeather.Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import com.jfeather.Exceptions.*;
import com.jfeather.Generation.*;
import com.jfeather.Items.*;
import com.jfeather.Player.Character;

public class GameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static GameWindow frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GameWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public GameWindow() throws IOException, InventoryCapacityException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		setTitle("Game");
		setResizable(false);
		WeaponsGen rwg = new WeaponsGen();
		//Weapon starterSword = new Weapon("Starter Sword","A basic sword made of iron. Seems sturdy enough... for now. I should probably try to find another soon.", 1, 6, 1, 0, 0, 0, 0, new ImageIcon("Sprites/Items/Weapons/Swords/GreenSwordSmall.png"));
		Armor testArmor = new Armor("Basic Armor", "A basic set of armor; suited for a new recruit.", 0, 1, 0, 0, 0, 0, new ImageIcon("Sprites/Items/Weapons/Swords/GreenSwordSmall.png"));
		Helmet testHelmet = new Helmet("Basic Helmet", "A basic helmet; suited for a new recruit.", 1, 1, 0, 0, 0, 0, new ImageIcon("Sprites/Items/Weapons/Swords/GreenSwordSmall.png"));
		Character jack = new Character("Jack");
		Inventory inv = createInv(jack, 10);
		jack.level = 50;
		Weapon testRandom = rwg.genSword(jack);
		inv.addItem(testHelmet);
		inv.addItem(testArmor);
		inv.addItem(testRandom); 
		TitleScreen ts = new TitleScreen(contentPane);
		pack();
	}
	
	public Inventory createInv(Character c, int capacity) throws InventoryCapacityException {
		Inventory inv;
		inv = new Inventory(c, capacity);
		contentPane.add(inv.dialog, BorderLayout.SOUTH);
		for (int i = 0; i < capacity; i++) {
			inv.slots[i].setToolTipText("Empty Slot");
		}
		
		return inv;
	}
	
	public void close() {
		frame.dispose();
		
	}
}