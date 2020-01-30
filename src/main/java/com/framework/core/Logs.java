package com.framework.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Logs {

	public static Logger logger;

	public Logs() throws FileNotFoundException, IOException {
		logger = Logger.getLogger("devpinoyLogger");
		String log4JPropertyFile = Constants.LOG4J;
		Properties p = new Properties();
		p.load(new FileInputStream(log4JPropertyFile));
		p.setProperty("log4j.appender.file.File", "Logs/" + getLogFileName());
		PropertyConfigurator.configure(p);
	}

	private String getLogFileName() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		return "Logs_" + dateFormat.format(date) + ".txt";
	}
}
