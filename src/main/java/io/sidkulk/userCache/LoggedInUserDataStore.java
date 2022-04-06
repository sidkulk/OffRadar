package io.sidkulk.userCache;

import io.sidkulk.model.CurrentUser;

public class LoggedInUserDataStore {
    private static CurrentUser loggedInUser;

    public static void setCurrentlyLoggedInUserData(CurrentUser currentlyLoggedInUserData) {
        loggedInUser = currentlyLoggedInUserData;
    }

    public static String getCurrentUsername() {
        if (loggedInUser == null) {
            return "null";
        }
        return loggedInUser.getUsername();
    }

    public static UserRecoveryClass getUserRecoveryData() {
        return new UserRecoveryClass(loggedInUser.getNickname(), loggedInUser.getChildhoodSchoolName());
    }

    public static void clearCurrentlyLoggedInUserData() {
        loggedInUser = null;
        System.gc();
    }

    private static class UserRecoveryClass {
        private String userNickName;
        private String userChildhoodSchool;

        UserRecoveryClass(String userChildhoodName, String userChildhoodSchool) {
            this.userNickName = loggedInUser.getNickname();
            this.userChildhoodSchool = loggedInUser.getChildhoodSchoolName();
        }

        private String getUserChildhoodSchool() {
            return this.userChildhoodSchool;
        }

        private String getUserNickName() {
            return this.userNickName;
        }
    }
}
