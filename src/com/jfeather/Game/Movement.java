package com.jfeather.Game;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JLabel;

public class Movement extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean mUp, mDown, mLeft, mRight, rRight, rLeft;
	private JLabel label;
	
	public Movement(JLabel newLabel, boolean newUp, boolean newDown, boolean newRight, boolean newLeft, boolean newRRight, boolean newRLeft) {
		label = newLabel;
		mUp = newUp;
		mDown = newDown;
		mLeft = newLeft;
		mRight = newRight;
		rRight = newRRight;
		rLeft = newRLeft;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (mUp)
			moveUp();
		if (mDown)
			moveDown();
		if (mRight)
			moveRight();
		if (mLeft)
			moveLeft();
		if (rRight)
			rotateRight();
		if (rLeft)
			rotateLeft();
	}
	
	public void moveUp() {
		System.out.println("Up");
		label.setBounds(label.getX(), label.getY() + 3, label.getWidth(), label.getHeight());
	}
	
	public void moveDown() {
		System.out.println("Down");
		label.setBounds(label.getX(), label.getY() - 3, label.getWidth(), label.getHeight());
	}
	
	public void moveRight() {
		System.out.println("Right");
		label.setBounds(label.getX() + 3, label.getY(), label.getWidth(), label.getHeight());
	}
	
	public void moveLeft() {
		System.out.println("Left");
		label.setBounds(label.getX() - 3, label.getY(), label.getWidth(), label.getHeight());
	}
	
	public void rotateRight() {
		System.out.println("Rotate right");
	}
	
	public void rotateLeft() {
		System.out.println("Rotate left");
	}

}
