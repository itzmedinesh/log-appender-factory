package com.itzmeds.logging;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DailyFileAppenderConfig {

	@JsonProperty("loggerLayoutPattern")
	private String loggerLayoutPattern;

	@JsonProperty("loggerFileBasePath")
	private String loggerFileBasePath;

	@JsonProperty("loggerFileName")
	private String loggerFileName;

	@JsonProperty("loggerName")
	private String loggerName;

	@JsonProperty("loggerLevel")
	private String loggerLevel;

	@JsonProperty("rollingSchedule")
	private String rollingSchedule;

	public String getLoggerLayoutPattern() {
		return this.loggerLayoutPattern;
	}

	public String getLoggerFileBasePath() {
		return loggerFileBasePath;
	}

	public void setLoggerFileBasePath(String loggerFileBasePath) {
		this.loggerFileBasePath = loggerFileBasePath;
	}

	public String getLoggerFileName() {
		return this.loggerFileName;
	}

	public String getLoggerName() {
		return this.loggerName;
	}

	public String getLoggerLevel() {
		return this.loggerLevel;
	}

	public String getrollingSchedule() {
		return this.rollingSchedule;
	}

}
