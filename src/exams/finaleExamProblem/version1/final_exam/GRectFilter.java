package exams.finaleExamProblem.version1.final_exam;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashSet;
import java.util.Set;
import java.util.random.RandomGenerator;

public class GRectFilter extends GraphicsProgram implements ComponentListener {
    private int canvWidth;
    private int canvHeight;
    private JLabel height;

    private JTextField widthText;
    private JTextField heightText;

    private JButton addBtn;
    private JButton filter;
    Set<GRect> rectSet;

    RandomGenerator rand = RandomGenerator.getDefault();


    @Override
    public void init() {
        rectSet = new HashSet<>();
        jInit();
        canvHeight = getHeight();
        canvWidth = getWidth();
    }

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (widthText.getText() != null && heightText.getText() != null) {
            int wdt = Integer.parseInt(widthText.getText());
            int high = Integer.parseInt(heightText.getText());

            if (e.getSource().equals(addBtn)) {
                int x = rand.nextInt(0, getWidth() - wdt);
                int y = rand.nextInt(0, getHeight() - high);
                GRect rct = new GRect(x, y, wdt, high);
                rectSet.add(rct);
                add(rct);
            } else if (e.getSource().equals(filter)) {
                Set<GRect> tmp = new HashSet<>();
                for (GRect rct : rectSet) {
                    if (rct.getHeight() < high && rct.getWidth() < wdt) {
                        tmp.add(rct);
                    }
                }
                rectSet = tmp;
                draw(1, 1);
            }
        }
    }

    private void draw(double diffx, double diffy) {
        removeAll();

        for (GRect rct : rectSet) {
            double x = rct.getWidth() * diffx;
            double y = rct.getHeight() * diffy;
            rct.setSize(x, y);
            add(rct);
        }
    }

    private void jInit() {
        JLabel width = new JLabel("width:");
        height = new JLabel("height:");
        widthText = new JTextField(10);
        heightText = new JTextField(10);
        addBtn = new JButton("Add");
        filter = new JButton("Filter");

        addBtn.addActionListener(this);
        filter.addActionListener(this);

        add(width, SOUTH);
        add(widthText, SOUTH);
        add(height, SOUTH);
        add(heightText, SOUTH);
        add(addBtn, SOUTH);
        add(filter, SOUTH);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println("TTTTTdfal;sdjk;fasjd;fa");
        int diffx = getWidth() / canvWidth;
        canvWidth = getWidth();

        int diffy = getHeight() / canvHeight;
        canvHeight = getHeight();

        System.out.println(diffx);
        draw(diffx, diffy);
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
