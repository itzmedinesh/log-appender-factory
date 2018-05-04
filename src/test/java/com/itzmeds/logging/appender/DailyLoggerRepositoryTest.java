package com.itzmeds.logging.appender;

import static java.lang.Thread.sleep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.itzmeds.logging.LogConfigurator;
import com.itzmeds.logging.LogWriter;
import com.itzmeds.logging.LoggerConfig;
import com.itzmeds.logging.LoggerRepository;
import com.itzmeds.logging.appender.exception.LoggerConfigException;

public class DailyLoggerRepositoryTest {

	LoggerConfig loggerConfig = null;
	ObjectMapper mapper = null;

	@Before
	public void setup() throws JsonParseException, JsonMappingException, IOException {
		mapper = new ObjectMapper(new YAMLFactory());
		loggerConfig = mapper.readValue(Files.readAllBytes(Paths.get("logconfigtest.yml")), LoggerConfig.class);
	}

	@Test
	public void testLoggerConfig() {
		Assert.assertNotNull(loggerConfig.getDailyFileAppenderConfig());
		Assert.assertEquals(1, loggerConfig.getDailyFileAppenderConfig().length);

	}

	@Test
	public void testLoggerConfigError() throws JsonParseException, JsonMappingException, IOException {
		loggerConfig = mapper.readValue(Files.readAllBytes(Paths.get("logconfigerror.yml")), LoggerConfig.class);
		try {
			LogConfigurator.init(loggerConfig);
			Assert.fail();
		} catch (LoggerConfigException lce) {
			Assert.assertEquals(lce.getMessage(), "Unsupported layout pattern, failed to initalize file log appender.");
		}
	}

	@Test
	public void testLogging() throws LoggerConfigException {

		new File("/logconftest.log");
		LogConfigurator.init(loggerConfig);
		LogWriter LOGF = LoggerRepository.getLogger("LogConfigTest1",DailyLoggerRepositoryTest.class);
		Assert.assertTrue(new File("/logconftest.log").exists());
		LOGF.info("Logg config test appender first");
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGF.info("Logg config test appender second");
	}

}