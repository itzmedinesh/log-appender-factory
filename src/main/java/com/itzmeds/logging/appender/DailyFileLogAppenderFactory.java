package com.itzmeds.logging.appender;

import com.itzmeds.logging.DailyFileAppenderConfig;
import com.itzmeds.logging.appender.exception.LoggerConfigException;

import org.apache.log4j.Appender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.PatternLayout;

import static com.itzmeds.logging.appender.LoggerConstants.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DailyFileLogAppenderFactory implements LogAppenderFactory<DailyFileAppenderConfig> {

	public static final Map<String, String> LOGGER_LAYOUT_PATTERN = new HashMap<String, String>();

	static {
		LOGGER_LAYOUT_PATTERN.put("DEFAULT_CONVERSION_PATTERN", "%m%n");
		LOGGER_LAYOUT_PATTERN.put("TTCC_CONVERSION_PATTERN", "%r [%t] %p %c %x - %m%n");
		LOGGER_LAYOUT_PATTERN.put("STANDARD_CONVERSION_PATTERN", "%p [%d] %r %m%n");
	}

	private static DailyFileLogAppenderFactory dailyFileLogAppenderFactory = null;

	private DailyFileLogAppenderFactory() {
	}

	public static synchronized DailyFileLogAppenderFactory getInstance() {
		if (dailyFileLogAppenderFactory == null) {
			dailyFileLogAppenderFactory = new DailyFileLogAppenderFactory();
		}
		return dailyFileLogAppenderFactory;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Cannot create clone of this factory class");
	}

	@Override
	public Appender createAppender(DailyFileAppenderConfig logAppConfig) throws LoggerConfigException {
		DailyRollingFileAppender dailyfileLogAppender = null;

		if (LOGGER_LAYOUT_PATTERN.get(logAppConfig.getLoggerLayoutPattern()) == null) {
			throw new LoggerConfigException("Unsupported layout pattern, failed to initalize file log appender.");
		}

		try {
			PatternLayout layout = new PatternLayout(LOGGER_LAYOUT_PATTERN.get(logAppConfig.getLoggerLayoutPattern()));

			switch (logAppConfig.getrollingSchedule()) {
			case "MINUTELY_PATTERN": {
				dailyfileLogAppender = new DailyRollingFileAppender(layout,
						logAppConfig.getLoggerFileBasePath()
								+ (logAppConfig.getLoggerFileBasePath().endsWith("/") ? "" : "/")
								+ logAppConfig.getLoggerFileName(),
						MINUTELY_PATTERN);
				break;
			}
			case "HOURLY_PATTERN": {
				dailyfileLogAppender = new DailyRollingFileAppender(layout,
						logAppConfig.getLoggerFileBasePath()
								+ (logAppConfig.getLoggerFileBasePath().endsWith("/") ? "" : "/")
								+ logAppConfig.getLoggerFileName(),
						HOURLY_PATTERN);
				break;
			}
			case "HALF_DAILY_PATTERN": {
				dailyfileLogAppender = new DailyRollingFileAppender(layout,
						logAppConfig.getLoggerFileBasePath()
								+ (logAppConfig.getLoggerFileBasePath().endsWith("/") ? "" : "/")
								+ logAppConfig.getLoggerFileName(),
						HALF_DAILY_PATTERN);

				break;
			}
			case "DAILY_PATTERN": {
				dailyfileLogAppender = new DailyRollingFileAppender(layout,
						logAppConfig.getLoggerFileBasePath()
								+ (logAppConfig.getLoggerFileBasePath().endsWith("/") ? "" : "/")
								+ logAppConfig.getLoggerFileName(),
						DAILY_PATTERN);

				break;
			}
			case "WEEKLY_PATTERN": {
				dailyfileLogAppender = new DailyRollingFileAppender(layout,
						logAppConfig.getLoggerFileBasePath()
								+ (logAppConfig.getLoggerFileBasePath().endsWith("/") ? "" : "/")
								+ logAppConfig.getLoggerFileName(),
						WEEKLY_PATTERN);

				break;
			}
			case "MONTHLY_PATTERN": {
				dailyfileLogAppender = new DailyRollingFileAppender(layout,
						logAppConfig.getLoggerFileBasePath()
								+ (logAppConfig.getLoggerFileBasePath().endsWith("/") ? "" : "/")
								+ logAppConfig.getLoggerFileName(),
						MONTHLY_PATTERN);
				break;
			}
			default: {
				throw new LoggerConfigException("Invalid log rotation schedule");
			}
			}

			dailyfileLogAppender.setName(logAppConfig.getLoggerName());
			dailyfileLogAppender.activateOptions();
		} catch (IOException e) {
			throw new LoggerConfigException(e);
		}
		return dailyfileLogAppender;

	}
}
