package com.techelevator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.models.JdbcPotholeDao;
import com.techelevator.models.JdbcUserDao;
import com.techelevator.models.Pothole;
import com.techelevator.models.PotholeDao;
import com.techelevator.models.User;
import com.techelevator.models.UserDao;
import com.techelevator.security.PasswordHasher;

public class JDBCPotholeIntegrationTest extends DAOIntegrationTest {

	private PotholeDao potholeDao;
	private UserDao userDAO;
	
	@Before
	public void setup() {
		potholeDao = new JdbcPotholeDao(getDataSource());
		userDAO = new JdbcUserDao(getDataSource(), new PasswordHasher());

}
	
	@Test
	public void test_adding_pothole_to_database() {
		
		User testUser = new User();
		testUser.setFirstName("Pat");
		testUser.setLastName("Smith");
		testUser.setEmailAddress("patSmith@test.com");
		testUser.setPhoneNumber("614-555-5555");
		
		userDAO.createUser(testUser, "Password1");
		
		User savedUser = userDAO.getUserById(userDAO.getNextUserId()-1);
		
		long userId = savedUser.getUserId();
		
		Pothole pothole = new Pothole();
		
		pothole.setComments("testing 123");
		pothole.setReported_by(userId);
		pothole.setLatitude(new BigDecimal(200));
		pothole.setLongitude(new BigDecimal(190));
		potholeDao.save(pothole);
		
		Pothole matchingPothole = potholeDao.getPotholeByLatitudeandLongitude(new BigDecimal(200), new BigDecimal(190));
		
		Assert.assertEquals(pothole.getComments(), matchingPothole.getComments());
		Assert.assertEquals(0, pothole.getLatitude().compareTo(matchingPothole.getLatitude()));
		Assert.assertEquals(0, pothole.getLongitude().compareTo(matchingPothole.getLongitude()));
		Assert.assertEquals(pothole.getReported_by(), matchingPothole.getReported_by());
		
		
	}
}
