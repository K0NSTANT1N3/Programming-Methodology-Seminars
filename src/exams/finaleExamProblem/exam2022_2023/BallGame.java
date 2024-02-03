package exams.finaleExamProblem.exam2022_2023;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class BallGame extends GraphicsProgram {
    private final static int BALL_SIZE = 100;

    private JTextField vx;
    private int vX;
    private JTextField vy;
    private int vY;
    private GOval ball;
    private int ballMustMove = 2;
    private int ballMustStop = 0;

    @Override
    public void init() {
        jinit();
        ball = new GOval(BALL_SIZE, BALL_SIZE);
        addActionListeners();
        addMouseListeners();
    }

    @Override
    public void run() {
        vX = RandomGenerator.getInstance().nextInt(6);
        vY = RandomGenerator.getInstance().nextInt(6);

        add(ball, getWidth() / 2.0 - BALL_SIZE /2.0, getHeight() / 2.0 - BALL_SIZE / 2.0);
        ball.setFilled(true);

        while(true){
            while(ballMustMove == 2){
                if((ball.getX() <= 0 && vX < 0) || (ball.getX() + BALL_SIZE >= getWidth() && vX > 0)){
                    vX *= -1;
                }if((ball.getY() + BALL_SIZE >= getHeight() && vY > 0) || (ball.getY() <= 0 && vY < 0)){
                    vY *= -1;
                }
                ball.move(vX, vY);
                pause(10);
            }

            pause(1);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(getElementAt(e.getX(), e.getY()).equals(ball)){
            if(ballMustMove == 2){
                ballMustStop++;
                if (ballMustStop >= 3){
                    ballMustMove = 0;
                }
            }else {
                ballMustMove++;
                if(ballMustMove == 2){
                    ballMustStop = 0;
                }
            }
        }else {
            if(ballMustMove == 2){
                ballMustStop = 0;
            }else {
                ballMustMove = 0;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(vx)){
            vX = Integer.parseInt(vx.getText());
        }else if(e.getSource().equals(vy)){
            vY = Integer.parseInt(vy.getText());
        }
    }

    private void jinit() {
        vx = new JTextField(3);
        vy = new JTextField(3);

        vx.addActionListener(this);
        vy.addActionListener(this);

        add(vx, WEST);
        add(vy, EAST);
    }

}
