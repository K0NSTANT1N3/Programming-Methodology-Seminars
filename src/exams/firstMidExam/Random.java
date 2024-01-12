package exams.firstMidExam;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.event.MouseEvent;

public class Random extends GraphicsProgram {
	
	private static final int BALL_COUNT = 20;
	
	private static final int MIN_RADIUS = 2;
	
	private static final int MAX_RADIUS = 10;
	
	private static final int V_Y = 3;


	
	private RandomGenerator rand = RandomGenerator.getInstance();
	private GOval lastBall;
	private boolean falling = false;
	
	@Override
	public void init(){
		ballDrawing();
	}

	@Override
	public void run() {
		addMouseListeners();

		while(true){
			while(falling && lastBall.getY() + 2 * lastBall.getHeight() < getHeight()){
				lastBall.move(0, V_Y);
				
				pause(10);
			}
			pause(1);
		}
	}


	@Override
	public void mouseClicked(MouseEvent e){
		double x = e.getX();
		double y = e.getY();
		 
		if(getElementAt(x, y) != null){
		
			GOval curBall = (GOval) getElementAt(x, y);
			if(curBall != lastBall){
				falling = false;
				curBall.setColor(rand.nextColor());
				lastBall = curBall;
			}
		}
		else{
			if(lastBall != null){
				falling = true;
			}
		}
	}

	private void ballDrawing(){
		for(int i = 0; i < BALL_COUNT; i++){
			int radius = rand.nextInt(MIN_RADIUS, MAX_RADIUS);
			int x = rand.nextInt(0, getWidth() - 2 * radius);
			int y = rand.nextInt(0, getHeight() - 2 * radius);
			GOval ball = new GOval(2 * radius, 2 * radius);
			ball.setFilled(true);
			add(ball, x, y);
		}
	}
	
	
}
