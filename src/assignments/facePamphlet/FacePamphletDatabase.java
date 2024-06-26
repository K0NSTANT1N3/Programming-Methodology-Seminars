package assignments.facePamphlet;/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.ArrayList;

public class FacePamphletDatabase implements FacePamphletConstants {

    private ArrayList<FacePamphletProfile> profiles;

    /**
     * Constructor
     * This method takes care of any initialization needed for
     * the database.
     */
    public FacePamphletDatabase() {
        profiles = new ArrayList<>();
    }


    /**
     * This method adds the given profile to the database.  If the
     * name associated with the profile is the same as an existing
     * name in the database, the existing profile is replaced by
     * the new profile passed in.
     */
    public void addProfile(FacePamphletProfile profile) {
        if (profile == null)return;

        FacePamphletProfile existingProfile = getProfile(profile.getName());
        if (existingProfile != null) {
            safeDeleteProfile(existingProfile.getName());
        }

        profiles.add(profile);
    }


    /**
     * This method returns the profile associated with the given name
     * in the database.  If there is no profile in the database with
     * the given name, the method returns null.
     */
    public FacePamphletProfile getProfile(String name) {
        for (FacePamphletProfile profile : profiles) {
            if (profile.getName().equals(name)) {
                return profile;
            }
        }

        return null;
    }


    /**
     * This method removes the profile associated with the given name
     * from the database.  It also updates the list of friends of all
     * other profiles in the database to make sure that this name is
     * removed from the list of friends of any other profile.
     * <p>
     * If there is no profile in the database with the given name, then
     * the database is unchanged after calling this method.
     */
    public void deleteProfile(String name) {
        safeDeleteProfile(name);
        clearFromFriendsList(name);
    }


    /**
     * This method returns true if there is a profile in the database
     * that has the given name.  It returns false otherwise.
     */
    public boolean containsProfile(String name) {
        for (FacePamphletProfile profile : profiles) {
            if (profile.getName().equals(name)) return true;
        }
        return false;
    }

    /* private methods */
    private void safeDeleteProfile(String name){ //removes profile from profiles list
        if (name == null)return;
        for (FacePamphletProfile profile : profiles) {
            if (profile.getName().equals(name)) {
                profiles.remove(profile);
                return;
            }
        }
    }

    private void clearFromFriendsList(String name) {// profile removed from every friends' list
        for (FacePamphletProfile profile : profiles) {
            profile.removeFriend(name);
        }
    }

}
