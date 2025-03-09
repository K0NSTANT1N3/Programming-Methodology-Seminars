package section;/*
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		designWindow();
		addActionListeners();
		
    }

	


	private void designWindow() {
		north();
		west();
		
	}




	private void west() {
		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.addActionListener(this);
		
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		pictureField.addActionListener(this);
		
		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.addActionListener(this);
		
		status = new JButton("Change Status");
		picture = new JButton("Change Picture");
		friend = new JButton("Add Friend");
		add(statusField,WEST);
		add(status,WEST);
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
		add(pictureField,WEST);
		add(picture,WEST);
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
		add(friendField,WEST);
		add(friend,WEST);
		
	}




	private void north() {
		name = new JLabel("Name");
		nameField = new JTextField(TEXT_FIELD_SIZE);
		nameField.addActionListener(this);
		add = new JButton("Add");
		delete = new JButton("Delete");
		lookup = new JButton("Lookup");
		add(name,NORTH);
		add(nameField,NORTH);
		add(add,NORTH);
		add(delete,NORTH);
		add(lookup,NORTH);
		
	}




	/**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Add")){
		
			FacePamphletProfile user = new FacePamphletProfile(nameField.getText());
			curProfile = database.getProfile(nameField.getText());
			if(database.containsProfile(nameField.getText())){
				database.addProfile(user);
				message = "A profile Already exists for " + curProfile.getName();
				
			}else{
				database.addProfile(user);
				message = "";
				
			}
		}
		
		
		if(e.getActionCommand().equals("Delete")){
			
			curProfile = null;
			if(database.containsProfile(nameField.getText())){
				database.deleteProfile(nameField.getText());
				message = "Profile for " + nameField.getText() + " was deleted.";
			}
		}
		
		
		if(e.getActionCommand().equals("Lookup")){
			
			if(database.containsProfile(nameField.getText())){
				
				curProfile = database.getProfile(nameField.getText());
				message = "";
			}else {
				
				curProfile = null;
				message = "The profile for " + nameField.getText() + " does not exist.";
			}
		}
		
		
		if(e.getActionCommand().equals("Change Status") 
				|| e.getSource() == statusField){
			
			curProfile.setStatus(statusField.getText());
			message = "";
		}
		
		
		if(e.getActionCommand().equals("Change Picture") || e.getSource() == pictureField){
			
			GImage image = null;
			if(curProfile != null){
				try{
					image = new GImage(pictureField.getText());
					curProfile.setImage(image);	
					message = "";
				}catch(ErrorException ex){
					image = null;
					message = "Sorry, we cannot find and image with this name";
				}
			}
		}
		
		
		if(e.getActionCommand().equals("Add Friend") || e.getSource() == friendField){
			
			if(curProfile.addFriend(friendField.getText()) == true){
				database.getProfile(friendField.getText()).addFriend(curProfile.getName());	
				message = "";
			}else message = "A profile does not yet exist for " + friendField.getText();
		}
		
		canvas.displayProfile(curProfile);
		canvas.showMessage(message);
				
	}




private JLabel name;
private JTextField nameField;
private JButton add;
private JButton delete;
private JButton lookup;
private JTextField statusField;
private JButton status;
private JTextField pictureField;
private JButton picture;
private JTextField friendField;
private JButton friend;
private String message = "";

private FacePamphletDatabase database = new FacePamphletDatabase();
private FacePamphletProfile curProfile;
private FacePamphletCanvas canvas= new FacePamphletCanvas();
}
