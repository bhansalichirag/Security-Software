package main.java.business.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.dal.Appointments;
import main.java.dal.users.User;
import main.java.dal.users.customers.Customer;
import main.java.repositories.AppointmentRepository;

@Service
public class AppointmentServices {

	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	IUserServices userServices;

	public boolean isDateAvailable(Date date)
	{
		int count = 0;
		Iterable<Appointments> appointments = appointmentRepository.findAllByDate(date);
		for ( Appointments appointment: appointments)
		{
			count++;
			if(count>4)
				return false;
		}
		return true;

	}
	
	public List<Appointments> getAppointmentsForUser(String username)
	{
		Customer customer = (Customer)userServices.GetCustomerByUsername(username);
		List<Appointments> appointmentsList = new ArrayList<Appointments>();
		Iterable<Appointments> appointments = appointmentRepository.findAllByCustomer(customer);
		for ( Appointments appointment: appointments)
		{
			appointmentsList.add(appointment);
		}
		return appointmentsList;

	}
	
	public boolean isCustomerAlreadyBookedOnDate(Customer customer, Date date)
	{
		Iterable<Appointments> appointments = appointmentRepository.findAllByDate(date);
		for ( Appointments appointment: appointments)
		{
			if(appointment.getCustomer()!=null || customer.getUsername()!=null || appointment.getCustomer().getUsername().equals(customer.getUsername()))
				return false;
		}
		return true;
	}
	
	public boolean bookOnDate(Customer customer, Date date, String reason)
	{
		if(isDateAvailable(date) && isCustomerAlreadyBookedOnDate(customer, date))
		{
			Appointments appointments = new Appointments(customer, date, reason);
			appointmentRepository.save(appointments);
			return true;
		}
		return false;
	}
}
