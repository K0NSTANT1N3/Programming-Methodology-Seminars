package assignments.assignment2;/*
 * File: Pyramid.java
 * Name: Konstantine Endeladze
 * Section Leader: Archili
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It draws perfect pyramid of any sized and any numbered bricks.
 * Pyramid is exactly in the middle of the canvas board.
 * Board is flexible so size doesn't matter [ well it does for women ;) ]
 */

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	//distance from wall
	private static final int DISTANCE = 10;

	//modifying consol dimentions
	public void init(){
		setSize((BRICK_WIDTH + DISTANCE) * BRICKS_IN_BASE, (BRICK_HEIGHT + DISTANCE) * BRICKS_IN_BASE);
	}

	public void run() {
		pyramidBuilder();
	}

	/** builds entire pyramid */
	public void pyramidBuilder(){
		for (int i = 0; i < BRICKS_IN_BASE; i++){
			layerBuilder(i);
		}
	}

	/** takes: number of floor. creates: layer of bricks */
	private void layerBuilder(int floor){
		int brickNum = BRICKS_IN_BASE - floor;
		int startX = (getWidth() - brickNum * BRICK_WIDTH) / 2;
		int startY = getHeight() - (floor + 1) * BRICK_HEIGHT;
		layerBuilderHelper(brickNum, startX, startY);
	}

	/** takes: number of bricks, coordinates
	 * builds: layer of bricks*/
	private void layerBuilderHelper(int brickNum, double startX, double startY){
		for(int i = 0; i < brickNum; i ++){
			brickBuilder(startX, startY);
			startX += BRICK_WIDTH;
		}
	}

	/** creates individual brick on given coordinates */
	private void brickBuilder(double startX, double startY){
		GRect rect = new GRect(startX, startY, BRICK_WIDTH, BRICK_HEIGHT);
		add(rect);
	}

}

