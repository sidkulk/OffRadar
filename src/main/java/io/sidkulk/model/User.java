package io.sidkulk.model;

public class User {
    private String username;
    private String email;
    private String password;
    private String nickname;
    private String childhoodSchoolName;
    private final String privateKey;

    public User(String username, String email, String password, String childhoodFriendName, String childhoodSchoolName, String privateKey) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = childhoodFriendName;
        this.childhoodSchoolName = childhoodSchoolName;
        this.privateKey = privateKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getChildhoodSchoolName() {
        return childhoodSchoolName;
    }

    public void setChildhoodSchoolName(String childhoodSchoolName) {
        this.childhoodSchoolName = childhoodSchoolName;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }
}
