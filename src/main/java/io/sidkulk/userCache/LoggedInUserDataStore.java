package io.sidkulk.userCache;

import io.sidkulk.model.CurrentUser;

public class LoggedInUserDataStore {
    private static CurrentUser loggedInUser;
    private static String privateKeyOfUser;

    public static void setCurrentlyLoggedInUserData(CurrentUser currentlyLoggedInUserData) {
        loggedInUser = currentlyLoggedInUserData;
    }

    public static void setCurrentlyLoggedInUserPrivateKey(String privateKey) {
        privateKeyOfUser = privateKey;
    }

    public static String getCurrentUsername() {
        if (loggedInUser == null) {
            return "null";
        }
        return loggedInUser.getUsername();
    }

    public static String getCurrentUserPrivateKey() {
        if(privateKeyOfUser == null) {
            return "null";
        }
        return privateKeyOfUser;
    }

    public static void clearCurrentlyLoggedInUserData() {
        loggedInUser = null;
        System.gc();
    }
}
