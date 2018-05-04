package com.itzmeds.logging.appender.exception;

@SuppressWarnings("serial")
public class LoggerConfigException extends Exception {

	public LoggerConfigException() {
	}

	public LoggerConfigException(String message) {
		super(message);
	}

	public LoggerConfigException(Throwable cause) {
		super(cause);
	}

	public LoggerConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoggerConfigException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
