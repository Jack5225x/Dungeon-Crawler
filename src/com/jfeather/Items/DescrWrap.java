package com.jfeather.Items;

public class DescrWrap {
	
	public static String descrWrap(String descr, String name) {
		String reformatted = "";
		int j = 0;
		for (int i = 0, k = 0; i < descr.length(); i++, k++) {
			if (descr.substring(i, i+1).equals(" ") && k > name.length() - 5) {
				reformatted = reformatted + "<br>" + descr.substring(j, i);
				j = i + 1;
				k = 0;
			}
		}
		reformatted = reformatted + "<br>" + descr.substring(j, descr.length());
		return reformatted;
	}
}
