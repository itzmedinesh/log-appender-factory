package com.itzmeds.logging;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileAppenderConfig {

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

	@JsonProperty("appendLogs")
	private boolean appendLogs;

	@JsonProperty("maxBackupFiles")
	private int maxBackupFiles;

	@JsonProperty("maxFileSize")
	private String maxFileSize;

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

	public boolean shouldAppendLogs() {
		return this.appendLogs;
	}

	public int getMaxBackupFiles() {
		return this.maxBackupFiles;
	}

	public String getMaxFileSize() {
		return this.maxFileSize;
	}
}
