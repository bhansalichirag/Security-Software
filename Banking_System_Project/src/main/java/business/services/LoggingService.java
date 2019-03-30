package main.java.business.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

	Logger logger = LoggerFactory.getLogger(LoggingService.class);
	
	
	public void log(String message)
	{
		logger.trace(message);
	}
}