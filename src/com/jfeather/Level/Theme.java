package com.jfeather.Level;

import java.awt.Color;
import java.util.HashMap;

import com.jfeather.Exceptions.UnsupportedThemeException;

public class Theme {

	public static String FOREST = "Forest";
	public static String OCEAN = "Ocean";
	public static String VOLCANO = "Volcano";
	public static String MOUNTAIN = "Mountain";
	public static String FIELD = "Field";
	public static String BEACH = "Beach";
	public static String DUNGEON = "Dungeon";
	public static String HALLS = "Halls";
	public static String CAVE = "Cave";
	public static String RAINBOW = "Rainbow";
	
	private HashMap<String, Color> BACKGROUND_COLORS;
	
	private static String[] themes = {FOREST, OCEAN, VOLCANO, MOUNTAIN, FIELD, BEACH, DUNGEON, HALLS, CAVE, RAINBOW};
	
	private String folderPath;
	private Color background;
	
	public Theme(String themeName) throws UnsupportedThemeException {
		initializeColors();
		
		if (!contains(themes, themeName))
			throw new UnsupportedThemeException();
		else {
			// Actual code goes here
			folderPath = "Sprites/Level/" + themeName + "/";
			background = BACKGROUND_COLORS.get(themeName);
		}
	}
	
	private void initializeColors() {
		BACKGROUND_COLORS = new HashMap<>();
		BACKGROUND_COLORS.put(FOREST, new Color(0, 0, 0));
		BACKGROUND_COLORS.put(OCEAN, new Color(0, 0, 0));
		BACKGROUND_COLORS.put(VOLCANO, new Color(0, 0, 0));
		BACKGROUND_COLORS.put(MOUNTAIN, new Color(0, 0, 0));
		BACKGROUND_COLORS.put(FIELD, new Color(0, 0, 0));
		BACKGROUND_COLORS.put(BEACH, new Color(0, 0, 0));
		BACKGROUND_COLORS.put(DUNGEON, new Color(0, 0, 0));
		BACKGROUND_COLORS.put(HALLS, new Color(0, 0, 0));
		BACKGROUND_COLORS.put(CAVE, new Color(0, 0, 0));
		BACKGROUND_COLORS.put(RAINBOW, new Color(0, 0, 0));
	}
	
	public String getFolder() {
		return folderPath;
	}
	
	public void setFolder(String path) {
		folderPath = path;
	}
	
	public Color getBackground() {
		return background;
	}
	
	private boolean contains(String[] arr, String str) {
		for (String s: arr) {
			if (s.equals(str))
				return true;
		}
		return false;
	}
}
