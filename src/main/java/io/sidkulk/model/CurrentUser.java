package io.sidkulk.model;

public class CurrentUser {
	private String username;
	private String email;
	private String nickname;
	private String childhoodSchoolName;

	public CurrentUser(String username, String email, String nickname, String childhoodSchoolName) {
		this.username = username;
		this.email = email;
		this.nickname = nickname;
		this.childhoodSchoolName = childhoodSchoolName;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getChildhoodSchoolName() {
		return childhoodSchoolName;
	}

	@Override
	public String toString() {
		System.out.println("--------------------");
		return "Username: " + this.username + "  \nEmail: " + this.email + "  \nNickname: " + this.nickname
				+ "  \nChildhood-School-Name: " + this.childhoodSchoolName + "\n------------------";
	}
}
