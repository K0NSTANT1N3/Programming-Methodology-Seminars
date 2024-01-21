package assignments.facePamphlet;/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FacePamphletCanvas extends GCanvas
        implements FacePamphletConstants {
    GLabel message;
    GLabel name;
    GLabel imageLabel;
    GLabel status;
    // List to store friend labels
    private List<GLabel> friendLabels;
    GObject imageBox;
    GRect imageRect;

    /**
     * Constructor
     * This method takes care of any initialization needed for
     * the display
     */
    public FacePamphletCanvas() {
        message = new GLabel("");
        name = new GLabel("");
        imageLabel = new GLabel("");
        status = new GLabel("");
        friendLabels = new ArrayList<>();
        imageBox = null;
        imageRect = null;
    }


    /**
     * This method displays a message string near the bottom of the
     * canvas.  Every time this method is called, the previously
     * displayed message (if any) is replaced by the new message text
     * passed in.
     */
    public void showMessage(String msg) {
        message.setLabel(msg);
        message.setFont(MESSAGE_FONT);
        add(message, getWidth() / 2.0 - message.getWidth() / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
    }


    /**
     * This method displays the given profile on the canvas.  The
     * canvas is first cleared of all existing items (including
     * messages displayed near the bottom of the screen) and then the
     * given profile is displayed.  The profile display includes the
     * name of the user from the profile, the corresponding imageLabel
     * (or an indication that an imageLabel does not exist), the status of
     * the user, and a list of the user's friends in the social network.
     */
    public void displayProfile(FacePamphletProfile profile) {
        removeAll();

        removeAllFriendLabels();
        addName(profile.getName());
        addPicture(profile);
        addStatus(profile);
        addFriends(profile);
    }

    /* add name on canvas*/
    private void addName(String name) {
        this.name.setLabel(name);
        this.name.setFont(PROFILE_NAME_FONT);
        add(this.name, LEFT_MARGIN, TOP_MARGIN + this.name.getHeight());
    }

    /* add imageLabel on canvas*/
    private void addPicture(FacePamphletProfile profile) {
        imageRect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
        imageRect.setLocation(LEFT_MARGIN, name.getY() + name.getHeight() + IMAGE_MARGIN);
        add(imageRect);

        if (profile.getImage() != null) {
            imageBox = profile.getImage();
            imageBox.setLocation(imageRect.getX(), imageRect.getY());
            add(imageBox);
        } else {
            imageLabel.setLabel("No Image");
            imageLabel.setFont(PROFILE_IMAGE_FONT);
            add(imageLabel, LEFT_MARGIN + IMAGE_WIDTH / 2 - imageLabel.getWidth() / 2, name.getY() + name.getHeight() + IMAGE_MARGIN + imageRect.getHeight() / 2.0 + imageLabel.getHeight() / 2.0);
        }
    }

    /* add status on canvas*/
    private void addStatus(FacePamphletProfile profile) {
        if (profile.getStatus() != null) {
            status.setLabel(profile.getName() + " is " + profile.getStatus());
        } else {
            status.setLabel("No current status");
        }
        status.setFont(PROFILE_STATUS_FONT);
        add(status, LEFT_MARGIN, imageRect.getY() + imageRect.getHeight() + STATUS_MARGIN);
    }

    /* add friends on canvas*/
    private void addFriends(FacePamphletProfile profile) {
        GLabel friendsLabel = new GLabel("Friends:");
        friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
        add(friendsLabel, getWidth() / 2.0, imageRect.getY());

        // Clear the list of friend labels
        friendLabels.clear();

        double currentY = friendsLabel.getY() + friendsLabel.getHeight() / 2;

// Iterate through friends and add labels
        Iterator<String> friendsIterator = profile.getFriends();
        while (friendsIterator.hasNext()) {
            String friendName = friendsIterator.next();
            GLabel friendLabel = new GLabel(friendName);
            friendLabel.setFont(PROFILE_FRIEND_FONT);

            // Increment the vertical position
            currentY += friendLabel.getHeight() ;

            // Add the friend label to the canvas
            add(friendLabel, getWidth() / 2.0, currentY);

            // Add the friend label to the list
            friendLabels.add(friendLabel);
        }
    }

    // Method to remove all friend labels
    public void removeAllFriendLabels() {
        for (GLabel friendLabel : friendLabels) {
            remove(friendLabel);
        }
        friendLabels.clear();
    }

}
