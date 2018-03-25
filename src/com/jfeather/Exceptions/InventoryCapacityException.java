package com.jfeather.Exceptions;

public class InventoryCapacityException extends Exception {

	private static final long serialVersionUID = 1L;

	public InventoryCapacityException() {
		System.out.println("Invalid inventory capacity!");
		System.out.println("Maximum inventory capacity is 10");
	}
}
