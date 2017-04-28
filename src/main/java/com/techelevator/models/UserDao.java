package com.techelevator.models;

public interface UserDao {
	
	//crud
	
	public User attemptUserLogIn(String userName, String password);

	public boolean createUser(User user, String password);

	public User getUserById(Long userId);
	
	public long getNextUserId();
	
	public User getUserByEmail(String email);
	
	public boolean updateUserStatusToEmployee(String email);
	
	public boolean updateUserStatusToRegular(String email);

}
