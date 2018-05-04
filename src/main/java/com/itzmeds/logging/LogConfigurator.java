package com.itzmeds.logging;

import static com.itzmeds.logging.appender.LoggerConstants.DATE_PATTERN;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.itzmeds.logging.appender.DailyFileLogAppenderFactory;
import com.itzmeds.logging.appender.FileLogAppenderFactory;
import com.itzmeds.logging.appender.exception.LoggerConfigException;

public class LogConfigurator {


    public static void init(LoggerConfig logConfig) throws LoggerConfigException {
        BasicConfigurator.configure();
        Logger.getRootLogger().getLoggerRepository().resetConfiguration();

        FileLogAppenderFactory fileAppenderFactory = FileLogAppenderFactory.getInstance();
        DailyFileLogAppenderFactory dailyFileLogAppenderFactory = DailyFileLogAppenderFactory.getInstance();


           if (logConfig.getFileAppenderConfig() != null) {
               for (FileAppenderConfig appenderConfig : logConfig.getFileAppenderConfig()) {
                   Logger.getLogger(appenderConfig.getLoggerName())
                           .addAppender(fileAppenderFactory.createAppender(appenderConfig));

               }
           }

        if (logConfig.getDailyFileAppenderConfig() != null)
        {
            for (DailyFileAppenderConfig appenderConfig : logConfig.getDailyFileAppenderConfig()) {
                if (!DATE_PATTERN.contains(appenderConfig.getrollingSchedule())) {
                    throw new LoggerConfigException("Invalid logger schedule configuration");
                } else {
                    Logger.getLogger(appenderConfig.getLoggerName())
                            .addAppender(dailyFileLogAppenderFactory.createAppender(appenderConfig));
                }
            }

        }
    }
}
