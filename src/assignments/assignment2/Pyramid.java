/*
 * File: Pyramid.java
 * Name:
 * Section Leader:
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 27;
	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 8;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 21;

	public void run() {

		for(int i = BRICKS_IN_BASE; i > 0;i-- ){
			int x = ((getWidth() - BRICK_WIDTH*i)/2);//starting x coordinate on the first floor.
			int y = (getHeight() - BRICK_HEIGHT*(BRICKS_IN_BASE+1-i));//starting y coordinate on the first floor.
			for(int j=0; j < i; j++){
				GRect rect = new GRect(BRICK_WIDTH,BRICK_HEIGHT);
				add(rect,x + j*BRICK_WIDTH,y); //draws rectangles in the first row.

			}
		}

	}

}





