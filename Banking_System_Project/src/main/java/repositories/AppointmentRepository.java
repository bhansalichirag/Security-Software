package main.java.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import main.java.dal.Appointments;

public interface AppointmentRepository extends CrudRepository<Appointments, Integer> {

	public Iterable<Appointments> findAllByDate(Date date);
	
}
