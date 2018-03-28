package com.jfeather.Items;

public class DescrWrap {
	
	public static String descrWrap(String descr, String name) {
		String reformatted = "";
		int j = 0;
		int br = 15;
		if (name.length() > 15)
			br = name.length();
		for (int i = 0, k = 0; i < descr.length(); i++, k++) {
			if (descr.substring(i, i+1).equals(" ") && k > br) {
				reformatted = reformatted + "<br>" + descr.substring(j, i);
				j = i + 1;
				k = 0;
			}
		}
		reformatted = reformatted + "<br>" + descr.substring(j, descr.length());
		return reformatted;
	}
}
