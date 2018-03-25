package com.jfeather.Items;

public class DescrWrap {
	
	public static void main(String[] args) {
		System.out.println(descrWrap("My name is Jack Featherstone and I live in Old Saybrook"));
	}
	
	public static String descrWrap(String descr) {
		String reformatted = "";
		int j = 0;
		for (int i = 0, k = 0; i < descr.length(); i++, k++) {
			if (descr.substring(i, i+1).equals(" ") && k > 16) {
				reformatted = reformatted + "<br>" + descr.substring(j, i);
				j = i + 1;
				k = 0;
			}
		}
		reformatted = reformatted + "<br>" + descr.substring(j, descr.length());
		return reformatted;
	}
}
