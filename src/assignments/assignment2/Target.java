/*
 * File: Target.java
 * Name: Konstantine Endeladze
 * Section Leader: 
 * -----------------
 * This file draws target.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

	//2.54 cm radius
	private static final double RADIUS1 = 96;
	// 1.65 cm radius
	private static final double RADIUS2 = 62.3622047;
	// 0.76 cm radius
	private static final double RADIUS3 = 28.724409449;

	//draw three circle idividually
	public void run() {
		drawCircle(RADIUS1, 1);
		drawCircle(RADIUS2, 0);
		drawCircle(RADIUS3, 1);
	}

	//takes radius and color and draws circle in center
	private void drawCircle(double radius, int color) {
		GOval target = new GOval(2 * radius, 2 * radius);
		target.setFilled(true);
		target.setColor(color == 1? Color.RED: Color.white);
		add(target, getWidth() / 2 - radius, getHeight() / 2 - radius);
	}
}
