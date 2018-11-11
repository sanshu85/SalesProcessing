package com.jpmorgan.salesprocessing.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmorgan.salesprocessing.exception.ApplicationException;
import com.jpmorgan.salesprocessing.model.NotificationMessage;
import com.jpmorgan.salesprocessing.model.Report;
import com.jpmorgan.salesprocessing.model.SalesAdjustment;
import com.jpmorgan.salesprocessing.repository.AdjustmentRepository;
import com.jpmorgan.salesprocessing.repository.SalesRepository;
import static com.jpmorgan.salesprocessing.utils.ApplicationConstants.*;

/**
 * This class provides service implementation for Sales Processing Service
 * 
 * @author anshu.singh
 *
 */
@Component(value = "salesProcessingService")
public class SalesProcessingService {

	private Logger LOGGER = LoggerFactory.getLogger("SalesProcessingService");
	private static final int TEN = 10;
	private static final int FIFTY = 50;

	@Autowired
	private SalesRepository salesRepository;

	@Autowired
	private AdjustmentRepository adjustmentRepository;

	private static int count = 0;

	/**
	 * This method processes the sales notification messages
	 * 
	 * @param notifications
	 * @throws ApplicationException
	 */
	public void processNotification(LinkedList<NotificationMessage> notifications) throws ApplicationException {
		LOGGER.debug("processNotification  no of notifications" + notifications.size());
		try {

			ListIterator<NotificationMessage> iterator = notifications.listIterator();
			outerloop: while (iterator.hasNext()) {

				NotificationMessage nm = iterator.next();
				if (nm.getMessageType() != null) {
					switch (nm.getMessageType()) {
					case TYPE1:
						salesRepository.save(nm.getSale());
						break;
					case TYPE2:
						salesRepository.saveMultipleSales(nm.getOccurences(), nm.getSale());
						break;

					case TYPE3:
						salesRepository.adjustSales(nm.getOperation(), nm.getSale());
						adjustmentRepository.save(new SalesAdjustment(nm.getOperation(), nm.getSale().getProductType(),
								nm.getSale().getValue()));
						break;

					default:
						break;
					}
				}
				count++;
				if (count % TEN == 0) {
					generateReport();
				}

				if (count == FIFTY) {
					generateAdjustmentsReport();
					break outerloop;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error while processing sales notification message", e);
			throw new ApplicationException(SALES_PROCESSING_ERROR, e);
		}
		LOGGER.debug("Completed processNotification");
	}

	private void generateReport() {

		List<Report> reportList = new ArrayList<>();
		reportList = salesRepository.generateReport();
		LOGGER.debug("Generating Sales Report");

		System.out.println("**************************REPORT**********************************");
		System.out.println("NO OF SALES" + "\t" + "PRODUCT" + "\t" + "TOTAL VALUE");
		reportList.forEach(
				r -> System.out.println(r.getSalesCount() + "\t" + r.getProductType() + "\t" + r.getTotalValue()));

	}

	private void generateAdjustmentsReport() {

		LOGGER.info("THE APPLICATION IS PAUSING,NO MORE RECORDS WILL BE ACCEPTED");
		LOGGER.debug("Generating Sales Adjustment Report");
		System.out.println("************************ADJUSTMENT REPORT*************************");
		System.out.println("OPERATION" + "\t" + "PRODUCT" + "\t" + "VALUE");
		List<SalesAdjustment> adjustmentList = adjustmentRepository.findAll();
		adjustmentList.forEach(
				ad -> System.out.println(ad.getOperation() + "\t" + ad.getProductType() + "\t" + ad.getValue()));
	}

}
