package exams.finaleExamProblem.final_mario;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Mario extends GraphicsProgram implements ComponentListener {
    private JButton left;
    private JButton right;
    private JButton up;
    private JButton down;
    private JLabel score;
    private int scoreNum;
    private GImage mario;
    private int mariox;
    private int marioy;
    private GImage mushroom;
    private int mushroomx;
    private int mushroomy;
    private GObject[][] greed;


    @Override
    public void init() {
        JInit();

    }

    @Override
    public void run() {

        try {
            txtToGreed("src/exams/finaleExamProblem/final_mario/board.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        drawGreed();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int newMarioX = mariox;
        int newMarioY = marioy;

        if (e.getSource().equals(up)) {
            newMarioY--;
        } else if (e.getSource().equals(down)) {
            newMarioY++;
        } else if (e.getSource().equals(left)) {
            newMarioX--;
        } else if (e.getSource().equals(right)) {
            newMarioX++;
        }

        if (isValidMove(newMarioX, newMarioY)) {
            handleMove(newMarioX, newMarioY);
        }

        drawGreed();
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < greed.length && y >= 0 && y < greed[0].length;
    }

    private void handleMove(int newX, int newY) {
        if (newX == mushroomx && newY == mushroomy) {
            greed[newX][newY] = mario;
            greed[mariox][marioy] = new GRect(1, 1);
            mariox = newX;
            marioy = newY;
            setMushroom();

            scoreNum += 100;
            score.setText("Points: " + scoreNum + ' ');
        } else {
            GRect rect = (GRect) greed[newX][newY];
            if (!rect.isFilled()) {
                greed[newX][newY] = mario;
                greed[mariox][marioy] = new GRect(1, 1);
                mariox = newX;
                marioy = newY;
            }
        }
    }


    private void setMushroom() {
        RandomGenerator rgen = RandomGenerator.getInstance();
        int x = rgen.nextInt(greed.length);
        int y = rgen.nextInt(greed[0].length);

        GRect rect = (GRect) greed[x][y];

        while ((x == mariox && y == marioy) || rect.isFilled()) {
            x = rgen.nextInt(greed.length);
            y = rgen.nextInt(greed[0].length);

            rect = (GRect) greed[x][y];
        }
        mushroomx = x;
        mushroomy = y;

        greed[x][y] = mushroom;
    }

    private void drawGreed() {
        removeAll();
        double squreSize = Math.min(getWidth() / greed.length, getHeight() / greed[0].length);

        for (int i = 0; i < greed.length; i++) {
            for (int j = 0; j < greed[0].length; j++) {
                GObject obj = greed[i][j];
                if (obj.equals(mario) || obj.equals(mushroom)) {
                    GImage img = (GImage) obj;
                    img.setSize(squreSize, squreSize);
                    img.setLocation(i * squreSize, j * squreSize);
                    add(img);
                } else {
                    GRect rect = (GRect) obj;
                    rect.setSize(squreSize, squreSize);
                    rect.setLocation(i * squreSize, j * squreSize);
                    add(rect);
                }
            }
        }
    }

    private void txtToGreed(String txt) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(txt));
            String line;
            StringTokenizer token;
            int x, y;

            line = reader.readLine();
            token = new StringTokenizer(line);
            y = Integer.parseInt(token.nextToken()); // y for rows
            x = Integer.parseInt(token.nextToken()); // x for columns

            this.greed = new GObject[y][x];
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    greed[i][j] = new GRect(1, 1);
                }
            }

            line = reader.readLine();
            token = new StringTokenizer(line);
            marioy = Integer.parseInt(token.nextToken()); // x for columns
            mariox = Integer.parseInt(token.nextToken()); // y for rows
            greed[marioy][mariox] = mario;

            line = reader.readLine();
            token = new StringTokenizer(line);
            mushroomx = Integer.parseInt(token.nextToken()); // x for columns
            mushroomy = Integer.parseInt(token.nextToken()); // y for rows
            greed[mushroomy][mushroomx] = mushroom;

            line = reader.readLine();
            while (line != null) {
                token = new StringTokenizer(line);
                y = Integer.parseInt(token.nextToken());
                x = Integer.parseInt(token.nextToken());
                if (!greed[y][x].equals(mario) && !greed[y][x].equals(mushroom)) {
                    ((GRect) greed[y][x]).setFilled(true);
                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            throw new IOException(e);
        }
    }


    private void JInit() {
        left = new JButton("Go Left");
        right = new JButton("Go Right");
        up = new JButton("Go Up");
        down = new JButton("Go Down");
        score = new JLabel("Points: 0 ");
        mario = new GImage("src/exams/finaleExamProblem/final_mario/Mario.png");
        mushroom = new GImage("src/exams/finaleExamProblem/final_mario/Mushroom.png");

        add(left, WEST);
        add(right, WEST);
        add(up, WEST);
        add(down, WEST);
        add(score, EAST);

        left.addActionListener(this);
        right.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
    }


    //junk
    @Override
    public void componentResized(ComponentEvent e) {
        drawGreed();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
