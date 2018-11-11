package com.jpmorgan.salesprocessing.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.LinkedList;

import com.jpmorgan.salesprocessing.exception.ApplicationException;
import com.jpmorgan.salesprocessing.model.MessageType;
import com.jpmorgan.salesprocessing.model.NotificationMessage;
import com.jpmorgan.salesprocessing.model.Operation;
import com.jpmorgan.salesprocessing.model.Sale;
import com.jpmorgan.salesprocessing.repository.AdjustmentRepository;
import com.jpmorgan.salesprocessing.repository.SalesRepository;

/**
 * Test class for SalesProcessingService
 * @author anshu.singh
 *
 */
public class SalesProcessingServiceTest {

	@InjectMocks
	private SalesProcessingService salesProcessingService = new SalesProcessingService();

	@Mock
	private SalesRepository salesRepository;

	@Mock
	private AdjustmentRepository adjustmentRepository;

	private LinkedList<NotificationMessage> notifications = new LinkedList<>();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		notifications.add(new NotificationMessage(new Sale("Apple", new BigDecimal(15.5)), null, MessageType.TYPE1, 0));
		notifications
				.add(new NotificationMessage(new Sale("Orange", new BigDecimal(10.5)), null, MessageType.TYPE1, 0));
	}

	@Test
	public void testProcessNotificationType1Message() throws ApplicationException {
		salesProcessingService.processNotification(notifications);
		Mockito.verify(salesRepository, times(2)).save(any(Sale.class));
	}

	@Test
	public void testProcessNotificationType2Message() throws ApplicationException {
		notifications
				.add(new NotificationMessage(new Sale("Orange", new BigDecimal(10.5)), null, MessageType.TYPE2, 2));
		salesProcessingService.processNotification(notifications);
		Mockito.verify(salesRepository, times(1)).saveMultipleSales(anyInt(), any(Sale.class));
	}

	@Test
	public void testProcessNotificationType3Message() throws ApplicationException {
		notifications.add(
				new NotificationMessage(new Sale("Orange", new BigDecimal(10.5)), Operation.ADD, MessageType.TYPE3, 0));
		salesProcessingService.processNotification(notifications);
		Mockito.verify(salesRepository, times(1)).adjustSales(any(Operation.class), any(Sale.class));
	}

	@Test(expected = ApplicationException.class)
	public void testProcessNotificationException() throws ApplicationException {
		Mockito.when(salesRepository.save(any(Sale.class))).thenThrow(new RuntimeException());
		salesProcessingService.processNotification(notifications);
	}

	@Test
	public void testGenerateReport() throws ApplicationException {
		for (int i = 0; i < 10; i++) {
			notifications
					.add(new NotificationMessage(new Sale("Banana", new BigDecimal(10.5)), null, MessageType.TYPE1, 0));
		}
		salesProcessingService.processNotification(notifications);
		Mockito.verify(salesRepository, times(1)).generateReport();
	}

	@Test
	public void testGenerateAdjustmentsReport() throws ApplicationException {
		for (int i = 0; i < 50; i++) {
			notifications
					.add(new NotificationMessage(new Sale("Banana", new BigDecimal(10.5)), null, MessageType.TYPE1, 0));
		}
		salesProcessingService.processNotification(notifications);
		Mockito.verify(adjustmentRepository, times(1)).findAll();
	}

}
