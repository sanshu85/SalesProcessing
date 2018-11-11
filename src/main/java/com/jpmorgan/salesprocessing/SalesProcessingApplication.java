package com.jpmorgan.salesprocessing;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpmorgan.salesprocessing.model.NotificationMessage;
import com.jpmorgan.salesprocessing.service.SalesProcessingService;
import com.jpmorgan.salesprocessing.utils.SampleDataGenerator;

@SpringBootApplication
public class SalesProcessingApplication implements CommandLineRunner{

	private Logger LOGGER = LoggerFactory.getLogger("SalesProcessingApplication");
	
	@Autowired
	private SalesProcessingService salesProcessingService;
	public static void main(String[] args) {
		SpringApplication.run(SalesProcessingApplication.class, args);

	}
	
	 @Override
	  public void run(String... args)throws Exception {
		 
	     LOGGER.debug("Creating sample data for Sales Processing Applications"); 
	     LinkedList<NotificationMessage>  notifications =  SampleDataGenerator.getNotifications(); 
	     
	     LOGGER.debug("Starting processing of Sales Data");

	     salesProcessingService.processNotification(notifications);
	     LOGGER.debug("Completed processing");
	  }

}
