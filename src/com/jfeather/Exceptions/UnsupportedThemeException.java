package com.jfeather.Exceptions;

import javax.swing.JOptionPane;

public class UnsupportedThemeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsupportedThemeException() {
		JOptionPane.showMessageDialog(null, "Invalid theme!", "Theme Error", JOptionPane.ERROR_MESSAGE);
	}
}
