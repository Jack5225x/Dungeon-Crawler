package com.jfeather.Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jfeather.Exceptions.*;
import com.jfeather.Game.GameInstance;
import com.jfeather.Generation.*;
import com.jfeather.Items.*;
import com.jfeather.Player.Character;
import com.jfeather.Player.SaveSelect;

public class GameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static GameWindow frame;
	public GameInstance instance;
	public volatile TitleScreen ts;
	
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
	
	public GameWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		setTitle("Game");
		setResizable(false);
		
		Character jack = new Character("Jack");
		jack.setAgility(200);
			
		GameInstance instance = new GameInstance(jack);
		add(instance);
		addKeyListener(instance.KL);
		addMouseListener(instance.ML);
		addMouseMotionListener(instance.MML);
		instance.setFPS(60);
		jack.setActiveWeapon(WeaponsGen.genWeapon(jack, Weapon.SWORD));
		/*
		Inventory inv = createInv(jack, 10);

		for (int i = 0; i < 9; i++) {
			Weapon testSword = WeaponsGen.genWeapon(jack, Weapon.SWORD);
			inv.addItem(testSword);
		}
		/*
		/*Thread title = new Thread() {
			@Override
			public void run() {
				ts = new TitleScreen(contentPane);
				pack();
			}
		};
		title.start();
		*/
		
		
		new Thread () {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//jack.subtractHealth(1);
					while (true) {
						
					}
				}
			}
		}.start();
		//inv.setUpdate(true);
		//inv.setUpdateInterval(1000);
		
	}
	
	public Inventory createInv(Character c, int capacity) {
		Inventory inv;
		try {
			inv = new Inventory(c, capacity);
			contentPane.add(inv.dialog, BorderLayout.SOUTH);
			// This has to be here to initialize the tooltips, because otherwise they are only done when you click
			for (int i = 0; i < capacity; i++) {
				inv.slots[i].setToolTipText("Empty Slot");
			}
			return inv;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
		
	public void close() {
		frame.dispose();
	}

}