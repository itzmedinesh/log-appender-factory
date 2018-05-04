package com.itzmeds.logging;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoggerConfig {
	@JsonProperty("fileAppenderConfig")
	private FileAppenderConfig[] fileAppenderConfig;

	@JsonProperty("dailyFileAppenderConfig")
	private DailyFileAppenderConfig[] dailyFileAppenderConfig;

	public FileAppenderConfig[] getFileAppenderConfig() {
		return fileAppenderConfig;
	}

	public DailyFileAppenderConfig[] getDailyFileAppenderConfig() { return dailyFileAppenderConfig;}
}
