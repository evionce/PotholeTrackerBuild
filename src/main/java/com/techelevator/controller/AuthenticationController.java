package com.techelevator.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.models.User;
import com.techelevator.models.UserDao;

@Controller
@SessionAttributes("currentUser")
public class AuthenticationController {

	private UserDao userDAO;

	@Autowired
	public AuthenticationController(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String displayLoginForm() {
		return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request,
						Map<String, Object> model, 
						@RequestParam String userName, 
						@RequestParam String password,
						@RequestParam(required=false) String destination,
						HttpSession session) {
		User currUser = userDAO.attemptUserLogIn(userName, password);
		if(currUser!=null) {
			session.invalidate();
			model.put("currentUser", currUser);
			if(isValidRedirect(destination)) {
				return "redirect:"+destination;
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/login?loginError=true";
		}
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public String logoutUserAndGoToHomePage(Map<String, Object> model, HttpSession session) {
		model.remove("currentUser");
		session.removeAttribute("currentUser");
		return "redirect:/";
	}
	

	private boolean isValidRedirect(String destination) {
		return destination != null && destination.startsWith("http://localhost");
	}

	@RequestMapping(path="/logout")
	public String logout(Map<String, Object> model, HttpSession session) {
		model.remove("currentUser");
		session.removeAttribute("currentUser");
		session.invalidate();
		return "redirect:/";
	}
}