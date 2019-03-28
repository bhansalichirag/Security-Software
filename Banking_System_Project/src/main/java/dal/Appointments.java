package main.java.dal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import main.java.dal.users.customers.Customer;

@Entity(name = "Appointments")
public class Appointments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentID;
	@OneToOne(fetch = FetchType.EAGER)
	private Customer customer;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String reason;
	
	public Appointments()
	{
		
	}
	
	public Appointments(Customer customer, Date date, String reason)
	{
		this.customer = customer;
		this.date = date;
		this.reason = reason;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getAppointmentID() {
		return appointmentID;
	}
	
}
