package exams.finaleExamProblem.exam2017;

import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class movingBall extends GraphicsProgram {


    private GOval ball;
    private JButton left;
    private JButton right;
    private JButton up;
    private JButton down;

    private final static int width = 500;
    private final static int height = 500;

    private final static int curX = 4;
    private final static int curY = 3;



    @Override
    public void init() {
        setSize(width, height);



        for(int i = 0; i <= 8; i++) {
            int xc = i * getWidth() / 8;
            GLine line = new GLine(xc, 0, xc, getHeight());
            add(line);
        }

        for(int i = 0; i <= 8; i++) {
            int yc = i* getHeight() / 8;
            GLine line = new GLine(0, yc, getWidth(), yc);
            add(line);
        }
        JInit();
        int xcur = curX * getWidth() / 8;
        int ycur = (8 - curY + 1) * getHeight() / 8;
        ball = new GOval( xcur, ycur, getWidth() / 8.0, getHeight() / 8.0);
        add(ball);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
    }

    private void JInit() {
        left = new JButton("left");
        right = new JButton("right");
        up = new JButton("up");
        down = new JButton("down");

        add(left, SOUTH);
        add(right, SOUTH);
        add(up, SOUTH);
        add(down, SOUTH);

        left.addActionListener(this);
        right.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
    }

    @Override
    public void run() {
        addActionListeners();

    }

    @Override
    public void actionPerformed(ActionEvent e){
        double xcur = ball.getX();
        double ycur = ball.getY();

        if(e.getSource().equals(up)){
            if(ycur > 0){
                ycur -= (getHeight() / 8.0);
            }
        }else if(e.getSource().equals(down)){
            if(ycur < getHeight() * 7 / 8.0){
                ycur += (getHeight() / 8.0);
            }
        }else if(e.getSource().equals(left)){
            if(xcur > 0){
                xcur -= (getWidth() / 8.0);
            }
        }
        else if(e.getSource().equals(right)){
            if(xcur < getWidth() - ball.getWidth()){
                xcur += ball.getWidth();
            }
        }
        ball.setLocation(xcur, ycur);
    }

}


