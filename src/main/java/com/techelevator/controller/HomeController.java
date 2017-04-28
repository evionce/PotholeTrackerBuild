package com.techelevator.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.techelevator.models.Pothole;
import com.techelevator.models.PotholeDao;
import com.techelevator.models.User;

@Controller
public class HomeController {
	
	@Autowired
	private PotholeDao potholeDao;
	
	@RequestMapping(path="/")
	public String displayHomePage(HttpServletRequest request) {
		List<Pothole> potholes=  potholeDao.getAllPotholes();
		String potholesJson = new Gson().toJson(potholes);
		request.setAttribute("potholes", potholesJson);
		return "home";
	}
	
	@RequestMapping(path="/user/report", method=RequestMethod.GET)
	public String displayReportPage() {
		return "user/report";
	}
	
	@RequestMapping(path="/user/report", method=RequestMethod.POST)
	public String addPotholeToDatabaseAndReturnHome(@RequestParam BigDecimal latitude, @RequestParam BigDecimal longitude, @RequestParam String comments, HttpSession session) {
		Pothole pothole = new Pothole();
		pothole.setLatitude(latitude);
		pothole.setLongitude(longitude);
		pothole.setComments(comments);
		pothole.setReported_by(((User)session.getAttribute("currentUser")).getUserId());
//		pothole.setReported_by(1);
		potholeDao.save(pothole);
		return "redirect:/";
	}
	
	@RequestMapping(path="/employee/potholeManagement", method=RequestMethod.GET)
	public String displyPotholeManagementPage(HttpServletRequest request) {
		List<Pothole> potholes=  potholeDao.getAllPotholes();
		String potholesJson = new Gson().toJson(potholes);
		request.setAttribute("potholes", potholesJson);
		return "employee/potholeManagement";
	}
	
	@RequestMapping(path="/employee/potholeDetail", method=RequestMethod.GET) 
	public String displayPotholeDetailPage(HttpServletRequest request, @RequestParam long pothole_id) {
		Pothole pothole = potholeDao.getPotholeById(pothole_id);
		request.setAttribute("pothole", pothole);
		String potholeAsJson = new Gson().toJson(pothole);
		request.setAttribute("potholeAsJson", potholeAsJson);
		return "employee/potholeDetailPage";
	}
	
	@RequestMapping(path="/employee/potholeDetail", method=RequestMethod.POST) 
	public String savePotholeChangesAndRedirectToPotholeManagementPage(@RequestParam long pothole_id, @RequestParam int status, @RequestParam int severity, @DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam(required=false) LocalDate scheduledDate) {
		potholeDao.updatePotholeStatusAndSeverity(pothole_id, status, severity);
		if(scheduledDate != null){
			potholeDao.updateScheduledDate(pothole_id, scheduledDate);
		}
		return "redirect:/employee/potholeManagement";
	}
	
	@RequestMapping(path="/admin/admin", method=RequestMethod.GET)
	public String displayAdminPage() {
		return "admin/admin";
	}
	
}