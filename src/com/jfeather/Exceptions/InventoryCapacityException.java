package com.jfeather.Exceptions;

import javax.swing.JOptionPane;

public class InventoryCapacityException extends Exception {

	private static final long serialVersionUID = 1L;

	public InventoryCapacityException() {
		JOptionPane.showMessageDialog(null, "<html> Invalid inventory size, <br> Maximum is 10!", "Inventory Error", JOptionPane.ERROR_MESSAGE);
	}
}
