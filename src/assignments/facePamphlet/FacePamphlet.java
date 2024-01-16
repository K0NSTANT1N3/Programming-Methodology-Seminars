package assignments.facePamphlet;/*
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.ConsoleProgram;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FacePamphlet extends ConsoleProgram
        implements FacePamphletConstants {


    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton lookUpButton;

    private JTextField statusTextField;
    private JButton statusButton;
    private JTextField pictureTextField;
    private JButton pictureButton;
    private JTextField friendsTextField;
    private JButton friendsButton;

    /**
     * This method has the responsibility for initializing the
     * interactors in the application, and taking care of any other
     * initialization that needs to be performed.
     */
    public void init() {
        jInitializer();
        jAdder();
        jAddActionListener();
    }


    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addButton)) {
            handleAddButton();
        } else if (e.getSource().equals(deleteButton)) {
            handleDelxeteButton();
        } else if (e.getSource().equals(lookUpButton)) {
            handleLookUpButton();
        } else if (e.getSource().equals(statusTextField) || e.getSource().equals(statusButton)) {
            handleStatusButton();
        } else if (e.getSource().equals(pictureTextField) || e.getSource().equals(pictureButton)) {
            handlePictureButton();
        }else if (e.getSource().equals(friendsTextField) || e.getSource().equals(friendsButton)) {
            handleFriendsButton();
        }
    }

    /**
     * private methods
     */

    private void handleFriendsButton() {
    }

    private void handlePictureButton() {
    }

    private void handleStatusButton() {

    }

    private void handleLookUpButton() {
    }

    private void handleDelxeteButton() {
    }

    private void handleAddButton() {
    }

    private void jInitializer() {//initialize global variables
        nameLabel = new JLabel("Name");
        nameTextField = new JTextField(15);
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        lookUpButton = new JButton("Lookup");

        statusTextField = new JTextField("15");
        statusButton = new JButton("Change Status");
        pictureTextField = new JTextField(15);
        pictureButton = new JButton("Change Picture");
        friendsTextField = new JTextField(15);
        friendsButton = new JButton("Add Friend");
    }

    private void jAdder() {//add global variables on canvas
        add(nameLabel, NORTH);
        add(nameTextField, NORTH);
        add(addButton, NORTH);
        add(deleteButton, NORTH);
        add(lookUpButton, NORTH);

        add(statusTextField, WEST);
        add(statusButton, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(pictureTextField, WEST);
        add(pictureButton, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(friendsTextField, WEST);
        add(friendsButton, WEST);
    }

    private void jAddActionListener() {//add action listener on global variables
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        lookUpButton.addActionListener(this);

        statusTextField.addActionListener(this);
        statusButton.addActionListener(this);
        pictureTextField.addActionListener(this);
        pictureButton.addActionListener(this);
        friendsTextField.addActionListener(this);
        friendsButton.addActionListener(this);
    }
}
