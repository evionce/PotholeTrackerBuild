package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.models.User;
import com.techelevator.models.UserDao;

@Controller
public class UserController {

	private UserDao userDAO;

	@Autowired
	public UserController(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/register", method=RequestMethod.GET)
	public String displayNewUserForm() {
		return "register";
	}
	
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public String createUser(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String email, @RequestParam String phoneNumber, @RequestParam String password, @RequestParam String passwordMatch) {
		if(!password.equals(passwordMatch)){
			return "redirect:/register";
		}
		User user = new User();
		user.setFirstName(first_name);
		user.setLastName(last_name);
		user.setEmailAddress(email);
		user.setPhoneNumber(phoneNumber);
		if(userDAO.createUser(user, password)){
			return "redirect:/login";
		}
		else {
			return "redirect:/register?registerError=true";
		}
	}

//	@RequestMapping(path="/users/{userName}", method=RequestMethod.GET)
//	public String displayDashboard(Map<String, Object> model, @PathVariable String userName) {
//		model.put("conversations", messageDAO.getConversationsForUser(userName));
//		return "userDashboard";
//	}
//	
//	@RequestMapping(path="/users/{forUserName}/conversations/{withUserName}", method=RequestMethod.GET)
//	public String displayConversation(Map<String, Object> model, @PathVariable String forUserName, @PathVariable String withUserName) {
//		model.put("conversation", messageDAO.getConversation(forUserName, withUserName));
//		return "conversation";
//	}
//	
//	@RequestMapping(path="/users/{userName}/messages", method=RequestMethod.GET)
//	public String displaySentMessages(Map<String, Object> model, @PathVariable String userName) {
//		model.put("messages", messageDAO.getMessagesSentByUser(userName));		
//		return "sentMessages";
//	}
//	
//	@RequestMapping(path="/users/{userName}/changePassword", method=RequestMethod.GET)
//	public String displayChangePasswordForm(Map<String, Object> model, @PathVariable String userName) {
//		model.put("userName", userName);
//		return "changePassword";
//	}
//	
//	@RequestMapping(path="/users/{userName}/changePassword", method=RequestMethod.POST)
//	public String changePassword(@PathVariable String userName, @RequestParam String password) {
//		userDAO.updatePassword(userName, password);
//		return "userDashboard";
//	}
}