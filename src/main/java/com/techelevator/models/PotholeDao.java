package com.techelevator.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface PotholeDao {
	public List<Pothole> getAllPotholes();
	
	public void save(Pothole pothole);
	
	public void deletePothole(BigDecimal latitude, BigDecimal longitude);
	
	public Pothole getPotholeByLatitudeandLongitude(BigDecimal latitude, BigDecimal longitude);
	
	public void updatePotholeStatus(BigDecimal latitude, BigDecimal longitude, int status);
	
	public void updatePotholeSeverity(BigDecimal latitude, BigDecimal longitude, int severity);
	
	public List<Pothole> getPotholesBySeverity(int severity);
	
	public List<Pothole> getPotholesByStatus(int status);
	
	public void deletePotholeById(long pothole_id);
	
	public Pothole getPotholeById(long pothole_id);

	public void updatePotholeStatusAndSeverity(long pothole_id, int status, int severity);

	public void updateScheduledDate(long pothole_id, LocalDate sqlDate);
}
