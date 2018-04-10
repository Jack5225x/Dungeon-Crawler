package com.jfeather.Game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

public class Movement {

	/**
	 * 
	 */
	
	private boolean mUp, mDown, mLeft, mRight, rRight, rLeft;
	private int x, y, dx, dy;
	
	public Movement(boolean newUp, boolean newDown, boolean newRight, boolean newLeft, boolean newRRight, boolean newRLeft) {
		mUp = newUp;
		mDown = newDown;
		mLeft = newLeft;
		mRight = newRight;
		rRight = newRRight;
		rLeft = newRLeft;
	}
	
	public void moveUp() {
		System.out.println("Up");
	}
	
	public void moveDown() {
		System.out.println("Down");
	}
	
	public void moveRight() {
		System.out.println("Right");
	}
	
	public void moveLeft() {
		System.out.println("Left");
	}
	
	public void rotateRight() {
		System.out.println("Rotate right");
	}
	
	public void rotateLeft() {
		System.out.println("Rotate left");
	}

}
