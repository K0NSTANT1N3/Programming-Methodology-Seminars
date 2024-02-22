package exams.finaleExamProblem.domino;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SlideGallery extends GraphicsProgram {
    public static final int MIN_DRAG = 50;
    private static final int SMALL_IMG_H = 200;
    private static final int BIG_IMG_H = 500;
    private static final int IMG_DST = 20;

    private ArrayList<String> images;
    private JButton left;
    private JButton right;
    private int index;

    @Override
    public void init() {
        setSize(SMALL_IMG_H * 4 + IMG_DST * 6 + BIG_IMG_H, BIG_IMG_H * 2);
        images = new ArrayList<>();
        jInit();
        imgInit();
        index = 0;
    }

    @Override
    public void run() {
        displayImg();

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(left)){
            if (index > 0){
                index--;
                displayImg();
            }
        }else if (e.getSource().equals(right)){
            if (index + 1 < images.size()){
                index++;
                displayImg();
            }
        }
    }

    private int initialX;
    @Override
    public void mousePressed(MouseEvent e){
        initialX = e.getX();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX() - initialX;
        if (x >= MIN_DRAG){
            if (index + 1 < images.size()){
                index++;
                displayImg();
            }
        }if(x <= MIN_DRAG * (-1)){
            if (index > 0){
                index--;
                displayImg();
            }
        }
    }

    private void displayImg() {
        if (index >= images.size() || index < 0) return;

        if (index >= 2) {
            GImage img = new GImage(images.get(index - 2));
            img.setSize(SMALL_IMG_H, SMALL_IMG_H);
            img.setLocation(IMG_DST, getHeight() / 2.0 - img.getHeight() / 2.0);
            add(img);
        }
        if (index >= 1) {
            GImage img = new GImage(images.get(index - 1));
            img.setSize(SMALL_IMG_H, SMALL_IMG_H);
            img.setLocation(IMG_DST * 2 + SMALL_IMG_H, getHeight() / 2.0 - img.getHeight() / 2.0);
            add(img);
        }
        if (index >= 0) {
            GImage img = new GImage(images.get(index));
            img.setSize(BIG_IMG_H, BIG_IMG_H);
            img.setLocation(IMG_DST * 3 + SMALL_IMG_H * 2, getHeight() / 2.0 - img.getHeight() / 2.0);
            add(img);
        }
        if (index + 1 < images.size()) {
            GImage img = new GImage(images.get(index + 1));
            img.setSize(SMALL_IMG_H, SMALL_IMG_H);
            img.setLocation(IMG_DST * 4 + SMALL_IMG_H * 2 + BIG_IMG_H, getHeight() / 2.0 - img.getHeight() / 2.0);
            add(img);
        }
        if (index + 2 < images.size()) {
            GImage img = new GImage(images.get(index + 2));
            img.setSize(SMALL_IMG_H, SMALL_IMG_H);
            img.setLocation(IMG_DST * 5 + SMALL_IMG_H * 3 + BIG_IMG_H, getHeight() / 2.0 - img.getHeight() / 2.0);
            add(img);
        }
    }

    private void imgInit() {
        for (int i = readInt(); i > 0; i--) {
            String s = readLine();

            images.add(s);
        }
    }

    private void jInit() {
        left = new JButton("Left");
        right = new JButton("Right");

        left.addActionListener(this);
        right.addActionListener(this);

        add(left);
        add(right);
    }
}
