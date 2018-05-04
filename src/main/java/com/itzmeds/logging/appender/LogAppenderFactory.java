package com.itzmeds.logging.appender;

import org.apache.log4j.Appender;

import com.itzmeds.logging.appender.exception.LoggerConfigException;

public interface LogAppenderFactory<T> {

	public Appender createAppender(T logAppConfig) throws LoggerConfigException;

}
