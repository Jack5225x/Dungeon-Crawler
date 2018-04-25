package com.jfeather.Level;

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
	
	private static String[] themes = {FOREST, OCEAN, VOLCANO, MOUNTAIN, FIELD, BEACH, DUNGEON, HALLS, CAVE, RAINBOW};
	
	private String folderPath;
	
	public Theme(String themeName) throws UnsupportedThemeException {
		if (!contains(themes, themeName))
			throw new UnsupportedThemeException();
		else {
			// Actual code goes here
			folderPath = "Sprites/Level/" + themeName + "/";
			
		}
	}
	
	public String getFolder() {
		return folderPath;
	}
	
	public void setFolder(String path) {
		folderPath = path;
	}
	
	private boolean contains(String[] arr, String str) {
		for (String s: arr) {
			if (s.equals(str))
				return true;
		}
		return false;
	}
}
