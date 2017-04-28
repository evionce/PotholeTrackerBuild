package com.techelevator.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcPotholeDao implements PotholeDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcPotholeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Pothole> getAllPotholes() {
		String sqlGetAllPotholes = "SELECT * FROM potholes ORDER BY status, pothole_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllPotholes);
		List<Pothole> potholes = mapRowSetToPotholes(results);
		return potholes;
	}
	
	private List<Pothole> mapRowSetToPotholes(SqlRowSet results) {
		ArrayList<Pothole> potholes = new ArrayList<>();
		while(results.next()) {
			Pothole pothole = new Pothole();
			pothole.setId(results.getLong("pothole_id"));
			pothole.setLatitude(results.getBigDecimal("latitude"));
			pothole.setLongitude(results.getBigDecimal("longitude"));
			pothole.setReported_by(results.getLong("reported_by"));
			pothole.setSeverity(results.getInt("severity"));
			pothole.setStatus(results.getInt("status"));
			pothole.setDate_reported(results.getTimestamp("date_reported"));
			pothole.setComments(results.getString("comments"));
			pothole.setDate_scheduled_for_repair(results.getDate("date_scheduled_for_repair"));
			potholes.add(pothole);
		}
		return potholes;
	}

	@Override
	public void save(Pothole pothole) {
		String insertPothole = "INSERT INTO potholes (reported_by, latitude, longitude, comments) VALUES (?,?,?,?)";
		jdbcTemplate.update(insertPothole, pothole.getReported_by(), pothole.getLatitude(), pothole.getLongitude(), pothole.getComments());
	}

	@Override
	public Pothole getPotholeByLatitudeandLongitude(BigDecimal latitude, BigDecimal longitude) {
		String sqlGetPotholeByLatLong = "SELECT * FROM potholes WHERE " +
										"latitude = ? AND longitude = ? ";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPotholeByLatLong, latitude, longitude);
		List<Pothole> resultList = mapRowSetToPotholes(results);
		if(!resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public void updatePotholeStatus(BigDecimal latitude, BigDecimal longitude, int status) {
		String sqlUpdateStatus = "UPDATE potholes SET status = ? WHERE latitude = ? AND longitude = ? ";
		jdbcTemplate.update(sqlUpdateStatus, status, latitude, longitude);
	}

	@Override
	public void updatePotholeSeverity(BigDecimal latitude, BigDecimal longitude, int severity) {
		String sqlUpdateSeverity = "UPDATE potholes SET severity = ? WHERE latitude = ? AND longitude = ? ";
		jdbcTemplate.update(sqlUpdateSeverity, severity, latitude, longitude);
	}
	
	@Override
	public void deletePothole(BigDecimal latitude, BigDecimal longitude) {
		String sqlDeletePotholeByCoord = "DELETE FROM potholes WHERE latitude = ? AND longitude = ? ";
		jdbcTemplate.update(sqlDeletePotholeByCoord, latitude, longitude);
	}
	
	@Override
	public void deletePotholeById(long potholeId) {
		String sqlDeletePotholebyId = "DELETE FROM potholes WHERE pothole_id = ? ";
		jdbcTemplate.update(sqlDeletePotholebyId, potholeId);
		
	}

	@Override
	public List<Pothole> getPotholesBySeverity(int severity) {
		String sqlGetPotholesBySeverity = "SELECT * FROM potholes WHERE severity = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPotholesBySeverity, severity);
		List<Pothole> potholes= mapRowSetToPotholes(results);
		return potholes;
	}

	@Override
	public List<Pothole> getPotholesByStatus(int status) {
		String sqlGetPotholesByStatus = "SELECT * FROM potholes WHERE status = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPotholesByStatus, status);
		List<Pothole> potholes = mapRowSetToPotholes(results);
		return potholes;
	}

	@Override
	public Pothole getPotholeById(long pothole_id) {
		String sqlGetPotholeById = "SELECT * FROM potholes WHERE pothole_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPotholeById, pothole_id);
		List<Pothole> resultList = mapRowSetToPotholes(results);
		if(!resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public void updatePotholeStatusAndSeverity(long pothole_id, int status, int severity) {
		String sqlUpdatePotholeStatusAndSeverity = "UPDATE potholes SET status = ?, severity = ? WHERE pothole_id = ?";
		jdbcTemplate.update(sqlUpdatePotholeStatusAndSeverity, status, severity, pothole_id);
	}

	@Override
	public void updateScheduledDate(long pothole_id, LocalDate sqlDate) {
		String sqlUpdatePotholeScheduledDate = "UPDATE potholes SET date_scheduled_for_repair = ? WHERE pothole_id = ?";
		jdbcTemplate.update(sqlUpdatePotholeScheduledDate, sqlDate, pothole_id);
	}
	
}
