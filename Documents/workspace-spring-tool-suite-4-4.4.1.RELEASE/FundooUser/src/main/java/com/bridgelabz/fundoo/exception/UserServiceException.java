package com.bridgelabz.fundoo.exception;

public class UserServiceException extends Exception{
	private static final long serialVersionUID = -470180507998010368L;
	
	public UserServiceException(int i, String string, String string2)
	{
		super();
	}
	
	public UserServiceException(String msg, String string)
	{
		super(msg);
	}

	public UserServiceException(String property, long noteId) {
		super(property);
	}

	public UserServiceException(String string) {
		super(string);
	}

	public UserServiceException() {
		super();
	}

	public UserServiceException(int i, String string) {
	super(string);
	}
}
