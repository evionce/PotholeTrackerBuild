package com.techelevator.models;

public class User {
	
	private Long userId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private int userType;
	private int numVerifiedPotholes;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getNumVerifiedPotholes() {
		return numVerifiedPotholes;
	}
	public void setNumVerifiedPotholes(int numVerifiedPotholes) {
		this.numVerifiedPotholes = numVerifiedPotholes;
	}
	
	
	
	
	

}
