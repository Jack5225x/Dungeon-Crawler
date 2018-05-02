package com.jfeather.Game;

import java.text.DecimalFormat;

public class Line {

	// Some random number that will represent a vertical line's slope
	public static final double VERTICAL = 1.41248971234;
	private final int RANGE_TO_PIXEL = 48;
	private final int POINTS = 50;
	private double xo, yo, xf, yf;
	private double slope;
	private double b;
	private int quadrant;
	private int angle;
		
	public Line(double newXO, double newYO,  double newXF, double newYF) {
		xo = newXO;
		yo = newYO;
		xf = newXF;
		yf = newYF;
		slope = getSlope();
		b = getIntercept();
		angle = getAngleFromX();
	}
	
	public double getSlope() {
		if (xo != xf)
			return ((yf - yo) / (xf - xo));
		return VERTICAL;
	}
	
	public double getIntercept() {
		return (yo - slope * xo);
	}
	
	public double getXInitial() {
		return xo;
	}
	
	public double getXFinal() {
		return xf;
	}
	
	public double getYInitial() {
		return yo;
	}
	
	public double getYFinal() {
		return yf;
	}
	
	public double getXLength() {
		return Math.abs(xf - xo);
	}
	
	public double getYLength() {
		return Math.abs(yf - yo);
	}
	
	public double getHypotenuse() {
		return Math.sqrt(getXLength() * getXLength() + getYLength() * getYLength());
	}
	
	public int getAngleFromX() {
		int angle = 0;
		if (getSlope() == VERTICAL) {
			if (yf > yo)
				angle = 90;
			if (yo < yf)
				angle = 270;
			quadrant = -1;
			return angle;
		}
		if (getSlope() > 0 && xf > xo) {
			angle = (int) Math.toDegrees(Math.atan(getYLength() / getXLength()));
			quadrant = 4;
		}
		if (getSlope() > 0 && xo > xf) {
			angle = 270 - (int) Math.toDegrees(Math.atan(getXLength() / getYLength()));
			quadrant = 2;
		}
		if (getSlope() < 0 && xf > xo) {
			angle = 270 + (int) Math.toDegrees(Math.atan(getXLength() / getYLength()));
			quadrant = 1;
		}
		if (getSlope() < 0 && xo > xf) {
			angle = 180 - (int) Math.toDegrees(Math.atan(getYLength() / getXLength()));
			quadrant = 3;
		}
		if (getSlope() == 0) {
			if (xf > xo)
				angle = 0;
			if (xo > xf)
				angle = 180;
			quadrant = 0;
			return angle;
		}
		return 360 - angle;
	}
	
	public void setXInitial(double newX) {
		xo = newX;
	}
	
	public void setYInitial(double newY) {
		yo = newY;
	}
	
	public void setXFinal(double newX) {
		xf = newX;
	}
	
	public void setYFinal(double newY) {
		yf = newY;
	}
	
	public double y(double x) {
		return getSlope() * x + getIntercept();
	}
	
	public int getQuadrant() {
		return quadrant;
	}
	
	public int[][] genPoints(double range) {
		int[][] arr = new int[POINTS][2];
		double dx, dy, i = 0;
		switch (quadrant) {
			case -1:
				// Vertical line
				dy = (range * RANGE_TO_PIXEL) / POINTS;
				if (yf > yo)
					dy *= -1;
				for (int k = 0; k < arr.length; k++, i += dy) {
					arr[k][0] = (int) xo;
					arr[k][1] = (int) (yo + i);
				}
				break;
			case 0:
				// Horizontal line
				dx = (range * RANGE_TO_PIXEL) / POINTS;
				if (getAngleFromX() == 180)
					dx *= -1;
				for (int k = 0; k < arr.length; k++, i += dx) {
					arr[k][0] = (int) (xo + i);
					arr[k][1] = (int) yo;
				}
				break;
			case 1:
				dx = (range * RANGE_TO_PIXEL * Math.cos(Math.toRadians(getAngleFromX())) / POINTS);
				//System.out.println(dx);
				//System.out.println(range * RANGE_TO_PIXEL * Math.cos(Math.toRadians(getAngleFromX())));
				for (int k = 0; k < arr.length; k++, i += dx) {
					arr[k][0] = (int) (xo + i);
					arr[k][1] = (int) y(arr[k][0]);
				}
				break;
			case 2:
				dx = (range * RANGE_TO_PIXEL * Math.sin(Math.toRadians(90 - getAngleFromX())) / POINTS);
				for (int k = 0; k < arr.length; k++, i -= dx) {
					arr[k][0] = (int) (xo - i);
					arr[k][1] = (int) (y(arr[k][0]));
				}
				break;
			case 3:
				dx = (range * RANGE_TO_PIXEL * Math.cos(Math.toRadians(180 - getAngleFromX())) / POINTS);
				for (int k = 0; k < arr.length; k++, i += dx) {
					arr[k][0] = (int) (xo - i);
					arr[k][1] = (int) (y(arr[k][0]));
				}
				break;
			case 4:
				dx = (range * RANGE_TO_PIXEL * Math.sin(Math.toRadians(270 - getAngleFromX())) / POINTS);
				for (int k = 0; k < arr.length; k++, i += dx) {
					arr[k][0] = (int) (xo - i);
					arr[k][1] = (int) (y(arr[k][0]));
				}
				break;
		}
		return arr;
	}
	
	public void printMatrix(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("[");
			for (int j = 0; j < arr[i].length - 1; j++) {
				System.out.printf("%2d, ", arr[i][j]);
			}
			System.out.printf("%2d]", arr[i][arr[i].length - 1]);
			System.out.println();
		}
	}

	
	public void debug() {
		DecimalFormat df = new DecimalFormat(".##");
		System.out.println("Line with slope of " + df.format(getSlope()) + " and intercept " + df.format(getIntercept()) + ", through the points (" + df.format(xo) + "," + df.format(yo) + ") and (" + df.format(xf) + "," + df.format(yf) + ").");
		System.out.println("X Length: " + getXLength() + "\nY Length: " + getYLength() + "\nH: " + df.format(getHypotenuse()));
		System.out.println("Line is " + getAngleFromX() + " degrees from the x-axis");
	}
}
