package assignments.facePamphlet;/*
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.graphics.GImage;
import acm.program.Program;
import acm.util.ErrorException;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FacePamphlet extends Program
        implements FacePamphletConstants {

    /* J variables */
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

    /* objects */
    private FacePamphletProfile currentProfile;
    private FacePamphletDatabase database;
    private FacePamphletCanvas canvas;

    /**
     * This method has the responsibility for initializing the
     * interactors in the application, and taking care of any other
     * initialization that needs to be performed.
     */
    public void init() {
        canvas = new FacePamphletCanvas();
        database = new FacePamphletDatabase();
        currentProfile = null;
        add(canvas);

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
            handleDeleteButton();
        } else if (e.getSource().equals(lookUpButton)) {
            handleLookUpButton();
        } else if (e.getSource().equals(statusTextField) || e.getSource().equals(statusButton)) {
            handleStatusButton();
        } else if (e.getSource().equals(pictureTextField) || e.getSource().equals(pictureButton)) {
            handlePictureButton();
        } else if (e.getSource().equals(friendsTextField) || e.getSource().equals(friendsButton)) {
            handleFriendsButton();
        }
    }

    /**
     * private methods
     */

    private void handleFriendsButton() {
        if (currentProfile != null) {
            String newFriend = friendsTextField.getText();
            if (newFriend.equals(currentProfile.getName())) {
                canvas.showMessage("You cannot add yourself as a friend.");
                return;
            }
            if (database.containsProfile(newFriend)) {
                if (currentProfile.addFriend(newFriend)) {
                    database.getProfile(newFriend).addFriend(currentProfile.getName());
                    canvas.displayProfile(currentProfile);
                } else {
                    canvas.showMessage(currentProfile.getName() + " already has " + newFriend + " as a friend.");
                }
            } else {
                canvas.showMessage(newFriend + " does not exist.");
            }
        } else {
            canvas.showMessage("Please select a profile to add friend.");
        }

    }

    private void handlePictureButton() {
        if (currentProfile != null) {
            String imagePath = pictureTextField.getText(); // modify this line based on your image path
            try {
                GImage image = new GImage(imagePath, IMAGE_WIDTH, IMAGE_HEIGHT);
                image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
                currentProfile.setImage(image);
                canvas.displayProfile(currentProfile);
            } catch (ErrorException ex) {
                canvas.showMessage("Unable to open image file: " + imagePath);
                throw new ErrorException(ex);
            }
        } else {
            canvas.showMessage("Please select a profile to change picture.");
        }
    }

    private void handleStatusButton() {
        if (currentProfile != null) {
            currentProfile.setStatus(statusTextField.getText());
            canvas.displayProfile(currentProfile);
        } else {
            canvas.showMessage("Please select a profile to change status.");
        }
    }

    private void handleLookUpButton() {//search for profile given from name
        currentProfile = database.getProfile(nameTextField.getText());
        canvas.displayProfile(currentProfile);
        canvas.showMessage("Displaying " + currentProfile.getName());
    }

    private void handleDeleteButton() { // delete profile given from name
        database.deleteProfile(nameTextField.getText());
        currentProfile = null;
        canvas.removeAll();
        canvas.showMessage("Profile of " + nameTextField.getText() + " deleted");
    }

    private void handleAddButton() { // add new profile in database. show new profile
        String name = nameTextField.getText();

        if (database.containsProfile(name)) {
            currentProfile = database.getProfile(name);
        } else {
            FacePamphletProfile profile = new FacePamphletProfile(name);
            database.addProfile(profile);
            currentProfile = profile;
        }
        if (currentProfile != null) {
            canvas.displayProfile(currentProfile);
            canvas.showMessage("Displaying " + currentProfile.getName());
        } else {
            // Handle the case where the profile is null
            canvas.showMessage("Error: Unable to create or retrieve the profile.");
        }
    }

    private void jInitializer() {//initialize global variables
        nameLabel = new JLabel("Name");
        nameTextField = new JTextField(15);
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        lookUpButton = new JButton("Lookup");

        statusTextField = new JTextField(15);
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
