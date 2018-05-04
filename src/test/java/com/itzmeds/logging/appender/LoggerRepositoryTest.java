package com.itzmeds.logging.appender;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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

public class LoggerRepositoryTest {

	LoggerConfig loggerConfig = null;
	ObjectMapper mapper = null;

	@Before
	public void setup() throws JsonParseException, JsonMappingException, IOException {
		mapper = new ObjectMapper(new YAMLFactory());
		loggerConfig = mapper.readValue(Files.readAllBytes(Paths.get("logconfigtest.yml")), LoggerConfig.class);
	}

	@Test
	public void testLoggerConfig() {
		Assert.assertNotNull(loggerConfig.getFileAppenderConfig());
		Assert.assertEquals(2, loggerConfig.getFileAppenderConfig().length);
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

	/**
	 * Ignoring this test case since it manipulates file systems for testing and
	 * cannot be run in CI environments
	 */
	@Ignore
	public void testLogging() throws LoggerConfigException {
		new File("/logconftest1.log").delete();
		new File("/logconftest2.log").delete();
		LogConfigurator.init(loggerConfig);
		LogWriter LOGF = LoggerRepository.getLogger("LogConfigTest1", LoggerRepositoryTest.class);
		LogWriter LOGS = LoggerRepository.getLogger("LogConfigTest2", LoggerRepositoryTest.class);
		Assert.assertTrue(new File("/logconftest1.log").exists());
		Assert.assertTrue(new File("/logconftest2.log").exists());
		Assert.assertEquals(new File("/logconftest2.log").length(), 0);
		LOGF.info("Logg config test appender first");
		LOGS.info("Logg config test appender second");
		Assert.assertNotEquals(new File("/logconftest1.log").length(), 0);
		Assert.assertNotEquals(new File("/logconftest2.log").length(), 0);
	}

	/**
	 * Ignoring this test case since it manipulates file systems for testing and
	 * cannot be run in CI environments
	 */
	@Ignore
	public void testLogArchiveAndPurge() throws LoggerConfigException {
		final File folder = new File("d:/");
		final File[] files = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(final File dir, final String name) {
				return name.matches("logconftest1.log.*");
			}
		});
		for (final File file : files) {
			if (file.exists()) {
				file.delete();
			}
		}
		LogConfigurator.init(loggerConfig);
		LogWriter LOG = LoggerRepository.getLogger("LogConfigTest1", LoggerRepositoryTest.class);
		for (int i = 0; i < 1000; i++) {
			LOG.info("Logg config test appender first " + i);
		}
		int backupcnt = 0;
		for (final File file : files) {
			if (file.exists()) {
				backupcnt++;
			}
		}
		Assert.assertNotEquals(backupcnt, 1);
		Assert.assertEquals(backupcnt, 4);
	}

}
