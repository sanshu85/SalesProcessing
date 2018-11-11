package com.jpmorgan.salesprocessing.model;

/**
 * Model class for NotificationMessage
 * @author anshu.singh
 *
 */
public class NotificationMessage {
	
	private Sale sale;
	private Operation operation;
	private MessageType messageType;
	private int occurences;
	
	
	public NotificationMessage(Sale sale, Operation operation, MessageType messageType, int occurences) {
		super();
		this.sale = sale;
		this.operation = operation;
		this.messageType = messageType;
		this.occurences = occurences;
	}
	
	public Sale getSale() {
		return sale;
	}
	/**
	 * @return the operation
	 */
	public Operation getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	/**
	 * @return the messageType
	 */
	public MessageType getMessageType() {
		return messageType;
	}
	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	/**
	 * @return the occurences
	 */
	public int getOccurences() {
		return occurences;
	}
	/**
	 * @param occurences the occurences to set
	 */
	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}
	/**
	 * @param sale the sale to set
	 */
	public void setSale(Sale sale) {
		this.sale = sale;
	}


	
	
	

}
