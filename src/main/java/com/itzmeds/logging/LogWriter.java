package com.itzmeds.logging;

import org.apache.log4j.Logger;

public class LogWriter extends Logger {

	Logger logger = null;

	Class loggingClass;

	public LogWriter(String name, Class clazz) {
		super(name);
		this.logger = getLogger(name);
		this.loggingClass = clazz;
	}

	@Override
	public void debug(Object message) {
		if (logger.isDebugEnabled()) {
			logger.debug(loggingClass.getCanonicalName() + " - " + message);
		}
	}

	@Override
	public void trace(Object message) {
		if (logger.isTraceEnabled()) {
			logger.trace(loggingClass.getCanonicalName() + " - " + message);
		}
	}

	@Override
	public void info(Object message) {
		if (logger.isInfoEnabled()) {
			logger.info(loggingClass.getCanonicalName() + " - " + message);
		}
	}

	@Override
	public void error(Object message) {
		logger.error(loggingClass.getCanonicalName() + " - " + message);
	}
}
