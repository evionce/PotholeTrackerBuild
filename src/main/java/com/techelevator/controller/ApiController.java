package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.models.Pothole;
import com.techelevator.models.PotholeDao;
import com.techelevator.models.User;
import com.techelevator.models.UserDao;

@RestController
public class ApiController {

	private UserDao userDAO;
	private PotholeDao potholeDAO;

	@Autowired
	public ApiController(UserDao userDAO, PotholeDao potholeDAO) {
		this.userDAO = userDAO;
		this.potholeDAO = potholeDAO;
	}
	/**
	 * ApiController allows us to separate our
	 * controllers that handle view logic from controllers that provide output to clients making
	 * asynchronous HTTP requests.
	 */
	@RequestMapping(path="/admin/api/getUser", method=RequestMethod.GET)
    public User getUser(@RequestParam String email) {
		User user = userDAO.getUserByEmail(email);
		return user;
    }
	
	@RequestMapping(path="/admin/api/toggleUserType", method=RequestMethod.POST)
	public User toggleUserType(@RequestParam String email) {
		User user = userDAO.getUserByEmail(email);
		if (user!=null){
			if(user.getUserType()==1){
				userDAO.updateUserStatusToEmployee(email);
			} else if (user.getUserType()==2){
				userDAO.updateUserStatusToRegular(email);
			}
		}
		User returnedUser = userDAO.getUserByEmail(email);
		return returnedUser;
	}
	
	@RequestMapping(path="/employee/api/deletePothole", method=RequestMethod.POST)
	public List<Pothole> deletePotholeAndReturnUpdatedList(@RequestParam long id) {
		potholeDAO.deletePotholeById(id);
		return potholeDAO.getAllPotholes();
	}
	
//	@RequestMapping(path="api/editPothole", method=RequestMethod.POST)
//	public void editPotholeAndReturnToPotholeManagementPage(@RequestParam long pothole_id, @RequestParam int status, @RequestParam int severity, @RequestParam(value="scheduledDate", required=false) String scheduledDate){
//		potholeDAO.updatePotholeSeverityAndStatus(pothole_id, status, severity);
//		if(scheduledDate != null){
//			potholeDAO.updatePotholeRepairDate(pothole_id, scheduledDate);
//		}
//		return
//	}
}
