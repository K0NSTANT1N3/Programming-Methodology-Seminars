package section;
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

	private GLabel message = new GLabel(EMPTY_LABEL_TEXT);
	private GLabel name = new GLabel(EMPTY_LABEL_TEXT);
	private GImage image = new GImage(EMPTY_LABEL_TEXT);
	private GRect noImage = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
	private GLabel noImageText = new GLabel("No image");
	private GLabel status = new GLabel("No current status");
	private GLabel friendsLabel = new GLabel("Friends:");
	private GLabel friends;

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	/**
	 * This method displays a message string near the bottom of the canvas.
	 * Every time this method is called, the previously displayed message (if
	 * any) is replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		message.setLabel(msg);
		message.setFont(MESSAGE_FONT);
		message.setLocation(getWidth() / 2 - message.getWidth() / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		add(message);
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the
	 * bottom of the screen) and then the given profile is displayed. The
	 * profile display includes the name of the user from the profile, the
	 * corresponding image (or an indication that an image does not exist), the
	 * status of the user, and a list of the user's friends in the social
	 * network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		displayName(profile);
		displayPhoto(profile);
		displayStatus(profile);
		displayFriends(profile);
	}

	/**
	 * This method displays friends of a current profile
	 */
	private void displayFriends(FacePamphletProfile profile) {
		friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		friendsLabel.setLocation(getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN + name.getAscent());
		add(friendsLabel);
		Iterator<String> friend = profile.getFriends();
		int n = 0;
		while (true) {
			if (!friend.hasNext()) {
				break;
			}
			n++;
			friends = new GLabel(friend.next());
			double i = n * friends.getHeight();
			friends.setFont(PROFILE_FRIEND_FONT);
			friends.setLocation(getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN + name.getAscent() + i);
			add(friends);
		}
	}

	/**
	 * This method displays status of a current profile
	 */
	private void displayStatus(FacePamphletProfile profile) {
		if (profile.getStatus() != null) {
			status.setLabel(profile.getName() + " is " + profile.getStatus());
		} else {
			status.setLabel("No current status");
		}
		status.setFont(PROFILE_STATUS_FONT);
		status.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + name.getAscent());
		add(status);
	}

	/**
	 * This method displays picture of a current profile
	 */
	private void displayPhoto(FacePamphletProfile profile) {
		image = profile.getImage();
		if (image != null) {
			image.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + name.getAscent());
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
		} else {
			noImage.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + name.getAscent());
			noImageText.setFont(PROFILE_IMAGE_FONT);
			noImageText.setLocation(LEFT_MARGIN + IMAGE_WIDTH / 2 - noImageText.getWidth() / 2,
					TOP_MARGIN + IMAGE_MARGIN + name.getAscent() + IMAGE_HEIGHT / 2);
			add(noImage);
			add(noImageText);
		}
	}

	/**
	 * This method displays name of a current profile
	 */
	private void displayName(FacePamphletProfile profile) {
		name.setLabel(profile.getName());
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		name.setLocation(LEFT_MARGIN, TOP_MARGIN + name.getAscent());
		add(name);
	}

}
