package com.jpmorgan.salesprocessing.exception;

/**
 * This is an custom Exception class for Application
 * @author anshu.singh
 *
 */
public class ApplicationException extends Exception{

	private static final long serialVersionUID = -6548764900871971520L;

	public ApplicationException(String message,Throwable error) {
		super(message,error);
	}

}
