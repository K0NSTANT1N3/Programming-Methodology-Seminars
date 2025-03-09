package section;
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	private JButton add = null;
	private JButton delete = null;
	private JButton lookup = null;
	private JButton changeStatus = null;
	private JButton changePicture = null;
	private JButton addFriend = null;
	private JLabel name = null;
	private JTextField nameField = null;
	private JTextField statusField = null;
	private JTextField friendField = null;
	private JTextField pictureField = null;
	private FacePamphletDatabase database = new FacePamphletDatabase();
	private String currentProfile = "";
	private FacePamphletCanvas canvas;

	/**
	 * This method has the responsibility for initializing the interactors in
	 * the application
	 */
	public void init() {
		canvas = new FacePamphletCanvas();
		add(canvas);
		createButtons();
		addActionListeners();
	}

	/**
	 * This method creates and adds buttons to the needed place and adds action
	 * listeners and action commands
	 */
	private void createButtons() {
		add = new JButton("Add");
		add.setActionCommand("add");

		delete = new JButton("Delete");
		delete.setActionCommand("delete");

		lookup = new JButton("Lookup");
		lookup.setActionCommand("lookup");

		changeStatus = new JButton("Change Status");
		changeStatus.setActionCommand("changeStatus");

		changePicture = new JButton("Change Picture");
		changePicture.setActionCommand("changePicture");

		addFriend = new JButton("Add Friend");
		addFriend.setActionCommand("addFriend");

		name = new JLabel("Name");

		nameField = new JTextField(TEXT_FIELD_SIZE);

		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.setActionCommand("statusField");
		statusField.addActionListener(this);

		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.setActionCommand("friendField");
		friendField.addActionListener(this);

		pictureField = new JTextField(TEXT_FIELD_SIZE);
		pictureField.setActionCommand("pictureField");
		pictureField.addActionListener(this);

		add(name, NORTH);
		add(nameField, NORTH);
		add(add, NORTH);
		add(delete, NORTH);
		add(lookup, NORTH);
		add(statusField, WEST);
		add(changeStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(pictureField, WEST);
		add(changePicture, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friendField, WEST);
		add(addFriend, WEST);
	}

	/**
	 * This method is responsible for detecting when the buttons are clicked or
	 * interactors are used
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("add") && !nameField.getText().equals("")) {
			addProfile(nameField.getText());
		}
		if (cmd.equals("delete") && !nameField.getText().equals("")) {
			deleteProfile(nameField.getText());
		}
		if (cmd.equals("lookup") && !nameField.getText().equals("")) {
			lookupProfile(nameField.getText());
		}
		if (cmd.equals("changeStatus") || cmd.equals("statusField")) {
			if (!statusField.getText().equals("")) {
				changeStatus(statusField.getText());
			}
		}
		if (cmd.equals("changePicture") || cmd.equals("pictureField")) {
			if (!pictureField.getText().equals("")) {
				changePicture(pictureField.getText());
			}
		}
		if (cmd.equals("addFriend") || cmd.equals("friendField")) {
			if (!friendField.getText().equals("")) {
				addFriend(friendField.getText());
			}
		}
	}

	/**
	 * If the button add was pressed if the profile already does not exist with
	 * entered name this method creates the profile with entered name
	 */
	private void addProfile(String name) {
		if (database.containsProfile(name)) {
			canvas.showMessage("A profile with the name " + name + " already exists");
		} else {
			currentProfile = name;
			database.addProfile(new FacePamphletProfile(name));
			canvas.displayProfile(database.getProfile(name));
			canvas.showMessage("New profile created");
			nameField.setText("");
		}
	}

	/**
	 * This method deletes the profile with the name entered if it exists in
	 * database if it does not message appears saying that
	 */
	private void deleteProfile(String name) {
		if (database.containsProfile(name)) {
			database.deleteProfile(name);
			canvas.removeAll();
			canvas.showMessage("Profile of " + name + " was deleted");
			currentProfile = "";
		} else {
			canvas.showMessage("Profile with name " + name + " does not exist");
		}
		nameField.setText("");
	}

	/**
	 * This method displays the profile with the name which was entered in the
	 * name field if it exists if not it writes a message saying that
	 */
	private void lookupProfile(String name) {
		if (database.containsProfile(name)) {
			currentProfile = name;
			canvas.displayProfile(database.getProfile(currentProfile));
			canvas.showMessage("Dislplaying " + name + "'s profile");
		} else {
			canvas.removeAll();
			canvas.showMessage("Profile with name " + name + " does not exist");
		}
		nameField.setText("");
	}

	/**
	 * This method changes status to the text that was entered in the status
	 * textField
	 */
	private void changeStatus(String text) {
		if (currentProfile == "") {
			canvas.showMessage("Please select a profile to change status");
		} else {
			database.getProfile(currentProfile).setStatus(text);
			canvas.displayProfile(database.getProfile(currentProfile));
			canvas.showMessage("Status updated to " + text);
		}
		statusField.setText("");
	}

	/**
	 * This method changes picture to the picture with the filename that was
	 * entered if it exists if not it writs message saying that
	 */
	private void changePicture(String filename) {
		GImage image = null;
		try {
			image = new GImage(filename);
			database.getProfile(currentProfile).setImage(image);
			canvas.displayProfile(database.getProfile(currentProfile));
			canvas.showMessage("Picture updated");
		} catch (ErrorException ex) {
			canvas.showMessage("Unable to open image file: " + filename);
		}
		pictureField.setText("");
	}

	/**
	 * This method adds friend to the profile's database if the friend has a
	 * profile him/herself if there's no profile with an entered name this
	 * method displays a message saying that if the friend is already in the
	 * friends database method displays the message saying that this method also
	 * adds a current profile as a friend in the profile with entered name
	 */
	private void addFriend(String name) {
		if (currentProfile == "") {
			canvas.showMessage("Please select a profile to add friend");
		} else if (checkIfFriend(name)) {
			canvas.showMessage(currentProfile + " already has " + name + " as a friend");
		} else {
			if (database.containsProfile(name) && !name.equals(currentProfile)) {
				database.getProfile(currentProfile).addFriend(name);
				database.getProfile(name).addFriend(currentProfile);
				canvas.displayProfile(database.getProfile(currentProfile));
				canvas.showMessage(name + " added as a friend");
			}
			if (!database.containsProfile(name))
				canvas.showMessage(name + " does not exist");
		}
		friendField.setText("");
	}

	/**
	 * This method returns true or false depending if the friend with entered
	 * name is in current profiles database
	 */
	private boolean checkIfFriend(String name) {
		Iterator<String> friends = database.getProfile(currentProfile).getFriends();
		while (true) {

			if (!friends.hasNext()) {
				break;
			}
			if (friends.next().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
