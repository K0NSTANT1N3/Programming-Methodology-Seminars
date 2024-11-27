/*
 * File: Target.java
 * Name:
 * Section Leader:
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;

import acm.program.*;

import java.awt.*;

public class Target extends GraphicsProgram {



	private static final int L_RADI = 32;//radius of big circle.
	private static final double M_RADI = L_RADI*((1.65)/(2.54));//radius of medium circle.
	private static final double S_RADI = L_RADI*((0.76)/(2.54));//radius of small circle.





	public void run(){
		drawBigCircle();
		drawMediumCircle();
		drawSmallCircle();

	}




	private void drawSmallCircle() {
		GOval small = new GOval(S_RADI*2,S_RADI*2);
		small.setFilled(true);
		small.setFillColor(Color.RED);
		add(small,getWidth() / 2 - S_RADI,getHeight() / 2 - S_RADI);


	}




	private void drawMediumCircle() {
		GOval medium = new GOval(M_RADI*2,M_RADI*2);
		medium.setFilled(true);
		medium.setFillColor(Color.WHITE);
		add(medium,getWidth() / 2 - M_RADI,getHeight() / 2 - M_RADI);

	}




	private void drawBigCircle() {
		GOval large = new GOval(L_RADI*2,L_RADI*2);
		large.setFilled(true);
		large.setFillColor(Color.RED);
		add(large,getWidth() / 2 - L_RADI,getHeight() / 2 - L_RADI);

	}
}