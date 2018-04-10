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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import com.jfeather.Exceptions.*;
import com.jfeather.Game.GameInstance;
import com.jfeather.Generation.*;
import com.jfeather.Items.*;
import com.jfeather.Player.Character;

public class GameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static GameWindow frame;
	public GameInstance instance;
	public volatile TitleScreen ts;
	
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
	
	public GameWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		setTitle("Game");
		setResizable(false);
		
		Character jack = new Character("Jack");
		jack.setLevel(1);
		
		GameInstance instance = new GameInstance(jack);
		add(instance);
		addKeyListener(instance.KL);
		
		/*
		WeaponsGen rwg = new WeaponsGen();
		Inventory inv = createInv(jack, 10);
		
		for (int i = 0; i < 10; i++) {
			Weapon testSword = rwg.genWeapon(jack, "sword");
			inv.addItem(testSword);
		}
		/*
		Thread title = new Thread() {
			@Override
			public void run() {
				ts = new TitleScreen(contentPane);
				pack();
			}
		};
		title.start();
		
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
		//th.stop();
		//Character jack = new Character("Jack");
		//createInstance(jack);
		inv.setUpdate(true);
		//inv.setUpdateInterval(1000);
		*/
	}
	
	public Inventory createInv(Character c, int capacity) {
		Inventory inv;
		try {
			inv = new Inventory(c, capacity);
			contentPane.add(inv.dialog, BorderLayout.SOUTH);
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