package com.itzmeds.logging;

public class LoggerRepository {
	public static LogWriter getLogger(String name, Class loggingClass) {
		return new LogWriter(name, loggingClass);
	}
}
