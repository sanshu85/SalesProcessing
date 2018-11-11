package com.jpmorgan.salesprocessing.utils;

import java.math.BigDecimal;
import java.util.LinkedList;

import com.jpmorgan.salesprocessing.model.MessageType;
import com.jpmorgan.salesprocessing.model.NotificationMessage;
import com.jpmorgan.salesprocessing.model.Operation;
import com.jpmorgan.salesprocessing.model.Sale;

/**
 * This class is used to generate sample sale notification messages
 * 
 * @author anshu.singh
 *
 */
public class SampleDataGenerator {

	private static LinkedList<NotificationMessage> notifications = new LinkedList<>();

	public static LinkedList<NotificationMessage> getNotifications() {
		for (int i = 0; i < 10; i++) {
			notifications
					.add(new NotificationMessage(new Sale("Apple", new BigDecimal(10.50)), null, MessageType.TYPE1, 0));
			notifications.add(
					new NotificationMessage(new Sale("Banana", new BigDecimal(11.50)), null, MessageType.TYPE1, 0));
			notifications
					.add(new NotificationMessage(new Sale("Orage", new BigDecimal(14.50)), null, MessageType.TYPE1, 0));
		}

		for (int i = 0; i < 5; i++) {
			notifications
					.add(new NotificationMessage(new Sale("Apple", new BigDecimal(10.50)), null, MessageType.TYPE2, 5));
			notifications.add(
					new NotificationMessage(new Sale("Banana", new BigDecimal(11.50)), null, MessageType.TYPE2, 4));
			notifications
					.add(new NotificationMessage(new Sale("Orage", new BigDecimal(14.50)), null, MessageType.TYPE2, 3));
		}

		notifications.add(
				new NotificationMessage(new Sale("Apple", new BigDecimal(10.50)), Operation.ADD, MessageType.TYPE3, 0));
		notifications.add(new NotificationMessage(new Sale("Banana", new BigDecimal(11.50)), Operation.ADD,
				MessageType.TYPE3, 0));
		notifications.add(
				new NotificationMessage(new Sale("Orage", new BigDecimal(14.50)), Operation.ADD, MessageType.TYPE3, 0));
		notifications.add(new NotificationMessage(new Sale("Apple", new BigDecimal(10.50)), Operation.SUBTRACT,
				MessageType.TYPE3, 0));
		notifications.add(new NotificationMessage(new Sale("Banana", new BigDecimal(11.50)), Operation.MULTIPLY,
				MessageType.TYPE3, 0));

		return notifications;

	}

}
