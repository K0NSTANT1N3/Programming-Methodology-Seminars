package assignments.nameSurfer;/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.Program;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NameSurfer extends Program implements NameSurferConstants {

    private JLabel nameLabel;
    private JTextField nameField;
    private JButton graphButton;
    private JButton clearButton;
    private JButton deleteButton;

    private NameSurferGraph graph;
    private NameSurferDataBase dataBase;


    public NameSurfer() {
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        graph = new NameSurferGraph();

        add(graph);

        // Add mouse listener to the graph
        graph.addMouseListener(new MyMouseListener());
    }

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the bottom of the window.
     */
    public void init() {
        nameLabel = new JLabel("Name");
        nameField = new JTextField(20);
        graphButton = new JButton("Graph");
        clearButton = new JButton("Clear");
        deleteButton = new JButton("Delete");

        add(nameLabel, SOUTH);
        add(nameField, SOUTH);
        add(graphButton, SOUTH);
        add(clearButton, SOUTH);
        add(deleteButton, SOUTH);

        graphButton.addActionListener(this);
        clearButton.addActionListener(this);
        nameField.addActionListener(this);
        deleteButton.addActionListener(this);
    }


    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(graphButton) || e.getSource().equals(nameField)) {
            graphButtonAction();
        }
        if (e.getSource().equals(clearButton)) {
            clearButtonAction();
        }
        if (e.getSource().equals(deleteButton)){
            deleteButtonAction();
        }
    }

    /* private methods */
    private void graphButtonAction() {
        String enteredText = nameField.getText();
        if (!enteredText.isEmpty()) {
            NameSurferEntry entry = dataBase.findEntry(enteredText.substring(0, 1).toUpperCase() + enteredText.substring(1).toLowerCase());
            nameField.setText("");

            if (entry != null) {
                graph.addEntry(entry);
                graph.update();
            }
        }
    }

    private void clearButtonAction() {//clear every name at once
        graph.clear();
        graph.update();
    }

    private void deleteButtonAction() {//delete individual name
        NameSurferEntry entry = graph.getCurrentEntry();
        if (entry != null) {
            graph.deleteEntry(entry);
            graph.update();
        }
    }

    private class MyMouseListener extends MouseAdapter { //inner class to listen mouse
        public void mouseClicked(MouseEvent e) {

            // Handle mouse click logic here
            GPoint point = new GPoint(e.getX(), e.getY());

            GObject obj = graph.getElementAt(point);
            if (obj instanceof GCompound) {
                /* take name of person from GCompound*/
                String name = ((GLabel) ((GCompound) obj).getElement(0)).getLabel().replaceAll("[^a-zA-Z]", "");
                NameSurferEntry entry = dataBase.findEntry(name);

                if (entry == null) return;

                graph.setCurrentEntry(entry);
                graph.update();
            }
        }
    }
}

