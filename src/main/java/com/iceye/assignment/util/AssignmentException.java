package com.iceye.assignment.util;

public class AssignmentException extends Exception{

	private static final long serialVersionUID = -909781563043677610L;
	
	public AssignmentException(String msg) {
		super(msg);
	}
	
	public AssignmentException(Throwable t) {
		super(t);
	}
	
	public AssignmentException(Throwable t, String msg) {
		super(msg, t);
	}

}
