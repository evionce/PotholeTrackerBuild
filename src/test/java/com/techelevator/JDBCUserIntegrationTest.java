package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.models.JdbcUserDao;
import com.techelevator.models.User;
import com.techelevator.models.UserDao;
import com.techelevator.security.PasswordHasher;



public class JDBCUserIntegrationTest extends DAOIntegrationTest {
	private UserDao userDAO;
	
	@Before
	public void setup() {
		userDAO = new JdbcUserDao(getDataSource(), new PasswordHasher());
	}
	
	@Test
	public void test_save_user_to_database() {
		User testUser = new User();
		testUser.setFirstName("Pat");
		testUser.setLastName("Smith");
		testUser.setEmailAddress("patSmith@test.com");
		testUser.setPhoneNumber("614-555-5555");
		
		Assert.assertTrue(userDAO.createUser(testUser, "Password1"));
		
		User savedUser = userDAO.getUserById(userDAO.getNextUserId()-1);

		Assert.assertNotNull(savedUser.getUserId());
		
		User queriedUser = userDAO.getUserById(savedUser.getUserId());
		Assert.assertEquals(savedUser.getFirstName(), queriedUser.getFirstName());
		Assert.assertEquals("patSmith@test.com", savedUser.getEmailAddress());
		Assert.assertEquals(savedUser.getEmailAddress(), queriedUser.getEmailAddress());
		Assert.assertEquals(savedUser.getPhoneNumber(), queriedUser.getPhoneNumber());
		
		
	}
	
	
	@Test
	public void test_get_user_by_email() {
		User testUser = new User();
		testUser.setFirstName("Pat");
		testUser.setLastName("Smith");
		testUser.setEmailAddress("patSmith@test.com");
		testUser.setPhoneNumber("614-555-5555");
		
		Assert.assertTrue(userDAO.createUser(testUser, "Password1"));
		
		User savedUser = userDAO.getUserByEmail("patSmith@test.com");

		Assert.assertNotNull(savedUser.getUserId());
		
		User queriedUser = userDAO.getUserById(savedUser.getUserId());
		Assert.assertEquals(savedUser.getFirstName(), queriedUser.getFirstName());
		Assert.assertEquals("patSmith@test.com", savedUser.getEmailAddress());
		Assert.assertEquals(savedUser.getEmailAddress(), queriedUser.getEmailAddress());
		Assert.assertEquals(savedUser.getPhoneNumber(), queriedUser.getPhoneNumber());
		
	}
	
	@Test
	public void test_changing_user_to_employee() {
		User testUser = new User();
		testUser.setFirstName("Pat");
		testUser.setLastName("Smith");
		testUser.setEmailAddress("patSmith@test.com");
		testUser.setPhoneNumber("614-555-5555");

		userDAO.createUser(testUser, "Password1");
		Assert.assertTrue(userDAO.updateUserStatusToEmployee("patSmith@test.com"));
		
	}
	
	
	
	
}
