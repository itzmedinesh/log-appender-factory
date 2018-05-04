package com.itzmeds.logging.appender;

import java.util.Arrays;
import java.util.List;

public interface LoggerConstants {

	String MINUTELY_PATTERN = "yyyy-MM-dd-HH-mm";
	String HOURLY_PATTERN = "yyyy-MM-dd-HH";
	String HALF_DAILY_PATTERN = "yyyy-MM-dd-a";
	String DAILY_PATTERN = "yyyy-MM-dd";
	String WEEKLY_PATTERN = "yyyy-ww";
	String MONTHLY_PATTERN = "yyyy-MM";

	public List<String> DATE_PATTERN = Arrays.asList("MINUTELY_PATTERN", "HOURLY_PATTERN", "HALF_DAILY_PATTERN",
			"DAILY_PATTERN", "WEEKLY_PATTERN", "MONTHLY_PATTERN");

}
