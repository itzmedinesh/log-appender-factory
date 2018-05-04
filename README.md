# log-appender-factory
**************************
Library to produce log4j appenders and chain it with the loggers defined in the input configuration. Wrapping log4j for ease of use with yaml

# Sample YAML configuration
***********************************
```html
dailyFileAppenderConfig:
-
  loggerLayoutPattern: TTCC_CONVERSION_PATTERN
  loggerFileName: d:/logconftest.log
  loggerName: LogConfigTest
  loggerLevel: INFO
  rollingSchedule: MINUTELY_PATTERN
fileAppenderConfig:   
-   
  loggerLayoutPattern: TTCC_CONVERSION_PATTERN    
  loggerFileName: d:/logconftest1.log   
  loggerName: LogConfigTest1   
  loggerLevel: INFO   
  appendLogs: true   
  maxBackupFiles: 5   
  maxFileSize: 20KB   
-    
  loggerLayoutPattern: TTCC_CONVERSION_PATTERN   
  loggerFileName: d:/logconftest2.log   
  loggerName: LogConfigTest2   
  loggerLevel: INFO   
  appendLogs: true   
  maxBackupFiles: 5   
  maxFileSize: 20KB   
```
# Producing appenders and chaining it to named loggers
*********************************************************************
```html
LogConfigurator.init(getLoggerConfig());
```
# Logging
************
```html
private static final LogWriter MSGLOGGER = LoggerRepository.getLogger("LogConfigTest1");
MSGLOGGER.info ("Some message!");
```
# Note: 
If you are trying to get logger with the names not configured in the yml, log4j will throw
a warning that no appenders are found for the given logger name. Log statements will be rejected.
