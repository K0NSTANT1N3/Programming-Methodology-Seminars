package exams.finaleExamProblem.singularProblems;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class userSide extends GraphicsProgram {
    private JTextField txt;
    private JButton btn;
    DataBase data;
    String filename = "file";

    @Override
    public void init() {
        jInit();
        data = new DataBase(filename);
    }

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(btn) || e.getSource().equals(txt)){
            String word;
            if((word = txt.getText()) != null){
                List<Site> lst = data.getSiteByKeyWord(word);
                showSites(lst);
            }
        }
    }

    private void showSites(List<Site> lst){
        removeAll();

        int i = 0;
        for(Site site: lst){
            i++;
            GLabel label = new GLabel(site.getContent());
            add(label, 10, label.getHeight() * i * 2);
        }
    }

    private void jInit() {
        txt = new JTextField(10);
        btn = new JButton("Search");

        txt.addActionListener(this);
        btn.addActionListener(this);

        add(txt, SOUTH);
        add(btn, SOUTH);
    }
}
