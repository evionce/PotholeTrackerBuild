package com.techelevator.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Pothole {
	private BigDecimal longitude;
	private BigDecimal latitude;
	private long id;
	private int status;
	private long reported_by;
	private Timestamp date_reported;
	private int severity;
	private String comments;
	private Date date_scheduled_for_repair;
	
	public Date getDate_scheduled_for_repair() {
		return date_scheduled_for_repair;
	}
	public void setDate_scheduled_for_repair(Date date_scheduled_for_repair) {
		this.date_scheduled_for_repair = date_scheduled_for_repair;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getReported_by() {
		return reported_by;
	}
	public void setReported_by(long reported_by) {
		this.reported_by = reported_by;
	}
	public Timestamp getDate_reported() {
		return date_reported;
	}
	public void setDate_reported(Timestamp timestamp) {
		this.date_reported = timestamp;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
