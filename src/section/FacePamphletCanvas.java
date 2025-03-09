package section;/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		if(message != null){
			remove(message);
		}else{
			message = new GLabel(msg);
			message.setFont(MESSAGE_FONT);
			add(message,(APPLICATION_WIDTH - message.getWidth())/2,
					APPLICATION_HEIGHT-BOTTOM_MESSAGE_MARGIN);
		}
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		if(profile != null){
			addName(profile.getName());
			addStatus(profile.getStatus());
			addFriend(profile.getFriends());
			if(profile.getImage() != null){
				addImage(profile.getImage());
			}else addRect();
		}
	}

	
	
	/*adds empty rectangle*/
	private void addRect() {
		GRect rect = new GRect(IMAGE_WIDTH,IMAGE_HEIGHT);
		add(rect,LEFT_MARGIN,TOP_MARGIN + IMAGE_MARGIN);
		GLabel label = new GLabel("No Image");
		label.setColor(Color.black);
		label.setFont(PROFILE_IMAGE_FONT);
		label.setLocation(LEFT_MARGIN + (IMAGE_WIDTH/2) - label.getWidth()/2,TOP_MARGIN + (IMAGE_HEIGHT/2) + label.getHeight()/2);
		add(label);
	}

	
	
	/*adds picture in the place of empty rectangle*/
	private void addImage(GImage image) {
		image.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN);
		image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
		add(image);
	}

	
	
	/*displays friendlist*/
	private void addFriend(Iterator<String> friends) {
		GLabel label = new GLabel("Friends:");
		label.setColor(Color.black);
		label.setFont(PROFILE_FRIEND_LABEL_FONT);
		label.setLocation(APPLICATION_WIDTH/2,TOP_MARGIN);
		add(label);
		double friendCount = 1.0;
		while(friends.hasNext() == true){
			GLabel friend = new GLabel(friends.next());
			friend.setFont(PROFILE_FRIEND_FONT);
			add(friend,APPLICATION_WIDTH/2,TOP_MARGIN + (friendCount*friend.getHeight()));
			friendCount += 1.0;
		}
	}


	
	/*adds status*/
	private void addStatus(String status) {
		String newStatus = "No current status";
		if(!status.isEmpty() == true){
			newStatus = status;
		}else{
			GLabel statusLabel = new GLabel(newStatus);
			statusLabel.setFont(PROFILE_STATUS_FONT);
			statusLabel.setColor(Color.black);
			add(statusLabel,LEFT_MARGIN,TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN);
		}
		
	}


	
	/*adds name on top of picture*/
	private void addName(String name) {
		GLabel newName = new GLabel(name);
		newName.setFont(PROFILE_NAME_FONT);
		newName.setColor(Color.blue);
		add(newName,LEFT_MARGIN,TOP_MARGIN);
	}

	
	
	
private GLabel message;
}
