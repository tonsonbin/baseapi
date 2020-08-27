package com.tyfo.app.common.exception;

/**
 * jsp报错
 */
public class MyJspException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyJspException() {
    }

    public MyJspException(String message) {
        super(message);
    }

    public MyJspException(String message, Throwable cause) {
        super(message, cause);
    }
}
