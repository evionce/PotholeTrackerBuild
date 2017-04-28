package com.techelevator.models;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import com.techelevator.security.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao{

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordhasher;

	@Autowired
	public JdbcUserDao(DataSource dataSource, PasswordHasher passwordhasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordhasher = passwordhasher;
	}
	

	@Override
	public boolean createUser(User user, String password) {
		String sqlAddUser = "INSERT INTO app_user (first_name, last_name, email, phone, password_hash, salt) values (?, ?, ?, ?, ?, ?)";
		byte[] salt = passwordhasher.generateRandomSalt();
		String hashedPassword = passwordhasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		try {
		jdbcTemplate.update(sqlAddUser, user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getPhoneNumber(), hashedPassword, saltString);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public User attemptUserLogIn(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM app_user "+
							      "WHERE UPPER(email) = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(results.next()) {
			String storedSalt = results.getString("salt");
			String storedPassword = results.getString("password_hash");
			String hashedPassword = passwordhasher.computeHash(password, Base64.decode(storedSalt));
			if(storedPassword.equals(hashedPassword)){
				User user = new User();
				user.setFirstName(results.getString("first_name"));
				user.setLastName(results.getString("last_name"));
				user.setUserType(results.getInt("user_type"));
				user.setPhoneNumber(results.getString("phone"));
				user.setUserId(results.getLong("app_user_id"));
				user.setEmailAddress(results.getString("email"));
				user.setNumVerifiedPotholes(results.getInt("num_verified_potholes"));
				return user;
			}
		}
		return null;
	}
	@Override  
	public User getUserById(Long userId) {
		
		String sqlGetUserById = "SELECT * FROM app_user WHERE app_user_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetUserById, userId);
		
		List<User> userList = mapRowSetToUser(results);
			if(userList.size() == 1) {
				return userList.get(0);
			} else {
				return null;
			}
	}
	
	@Override
	public User getUserByEmail(String email) {
		String sqlGetUserByEmail = "SELECT * FROM app_user WHERE email = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetUserByEmail, email);
		
		List<User> userList = mapRowSetToUser(results);
		if(userList.size() == 1) {
			return userList.get(0);
		} else {
			return null;
		}
		
	}
	
	@Override
	public boolean updateUserStatusToEmployee(String email) {
		if(getUserByEmail(email) == null){
			return false;
		}
		else{
			String sqlUpdateUserStatusToEmployee = "UPDATE app_user SET user_type = 2 WHERE UPPER(email) = ?";
			jdbcTemplate.update(sqlUpdateUserStatusToEmployee, email.toUpperCase());
			return true;
		}
	}
	
	@Override 
	public boolean updateUserStatusToRegular(String email) {
		if(getUserByEmail(email) == null){
			return false;
		}
		else{
			String sqlUpdateUserStatusToEmployee = "UPDATE app_user SET user_type = 1 WHERE UPPER(email) = ?";
			jdbcTemplate.update(sqlUpdateUserStatusToEmployee, email.toUpperCase());
			return true;
		}
	}

	private List<User> mapRowSetToUser(SqlRowSet results) {
		ArrayList<User> users = new ArrayList<>();
		while(results.next()) {
			User user = new User();
			user.setEmailAddress(results.getString("email"));
			user.setFirstName(results.getString("first_name"));
			user.setLastName(results.getString("last_name"));
			user.setPhoneNumber(results.getString("phone"));
			user.setNumVerifiedPotholes(results.getInt("num_verified_potholes"));
			user.setUserId(results.getLong("app_user_id"));
			user.setUserType(results.getInt("user_type"));
			users.add(user);
			
		}
		return users;
	}
	
	
	@Override
	public long getNextUserId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('app_user_id_seq')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new user id");
		}
	}


}
