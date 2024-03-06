package com.springboot.assignment.model;

public class User {
    private String userName;
    private String userEmail;

    // Constructor
    public User(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public User() {
		// TODO Auto-generated constructor stub
	}

	// Getter for userName
    public String getUserName() {
        return userName;
    }

    // Setter for userName
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter for userEmail
    public String getUserEmail() {
        return userEmail;
    }

    // Setter for userEmail
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

