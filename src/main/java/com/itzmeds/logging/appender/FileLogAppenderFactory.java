package com.itzmeds.logging.appender;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Appender;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import com.itzmeds.logging.FileAppenderConfig;
import com.itzmeds.logging.appender.exception.LoggerConfigException;

public class FileLogAppenderFactory implements LogAppenderFactory<FileAppenderConfig> {

	public static final Map<String, String> LOGGER_LAYOUT_PATTERN = new HashMap<String, String>();
	static {
		LOGGER_LAYOUT_PATTERN.put("DEFAULT_CONVERSION_PATTERN", "%m%n");
		LOGGER_LAYOUT_PATTERN.put("TTCC_CONVERSION_PATTERN", "%r [%t] %p %c %x - %m%n");
		LOGGER_LAYOUT_PATTERN.put("STANDARD_CONVERSION_PATTERN", "%p [%d] %r %m%n");
	}

	private static FileLogAppenderFactory fileLogAppenderFactory = null;

	private FileLogAppenderFactory() {
	}

	public static synchronized FileLogAppenderFactory getInstance() {
		if (fileLogAppenderFactory == null) {
			fileLogAppenderFactory = new FileLogAppenderFactory();
		}
		return fileLogAppenderFactory;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Cannot create clone of this factory class");
	}

	@Override
	public Appender createAppender(FileAppenderConfig logAppConfig) throws LoggerConfigException {
		RollingFileAppender fileLogAppender = null;

		if (LOGGER_LAYOUT_PATTERN.get(logAppConfig.getLoggerLayoutPattern()) == null) {
			throw new LoggerConfigException("Unsupported layout pattern, failed to initalize file log appender.");
		}

		try {
			PatternLayout layout = new PatternLayout(LOGGER_LAYOUT_PATTERN.get(logAppConfig.getLoggerLayoutPattern()));
			fileLogAppender = new RollingFileAppender(layout,
					logAppConfig.getLoggerFileBasePath()
							+ (logAppConfig.getLoggerFileBasePath().endsWith("/") ? "" : "/")
							+ logAppConfig.getLoggerFileName(),
					logAppConfig.shouldAppendLogs());
			fileLogAppender.setName(logAppConfig.getLoggerName());
			fileLogAppender.setMaxBackupIndex(logAppConfig.getMaxBackupFiles());
			fileLogAppender.setMaxFileSize(logAppConfig.getMaxFileSize());
			fileLogAppender.activateOptions();
		} catch (IOException e) {
			throw new LoggerConfigException(e);
		}
		return fileLogAppender;
	}
}
