package org.rupesh.demo.log4j;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class HelloExample {

	final static Logger overalLogger = Logger.getLogger(HelloExample.class);
	final static Logger scenarioLogger = Logger.getLogger(HelloExample.class);

	public HelloExample() {
		
		init();
	}

	public void init() {
		
		String log4jConfPath = "./log4j.properties";
		System.setProperty("overallLogFileName",
				"Overall" + new SimpleDateFormat("yyyy_mm_dd_hh_mm_ss").format(new Date()) + ".log");
		System.setProperty("scenarioLogFileName",
				"Scenario" + new SimpleDateFormat("yyyy_mm_dd_hh_mm_ss").format(new Date()) + ".log");
		PropertyConfigurator.configure(log4jConfPath);
		overalLogger.info("Overall Log Started.");
		overalLogger.info("HelloExample class instantiated with default constructor!");
		overalLogger.info("Loaded up Log4j properties!");
	}

	public static void main(String[] args) {
		overalLogger.info("Main Execution starts from here!");
		overalLogger.debug("Instantiating Hello Example class.");
		HelloExample obj = new HelloExample();
		overalLogger.debug("Started Running scenario.");
		obj.runScenario("Rupesh Kumar Somala");
		overalLogger.debug("Ended Running scenario.");
		overalLogger.info("Overall Log Ended.");

	}

	private void runScenario(String parameter) {
		scenarioLogger.info("Scenario Log Started.");
		if (scenarioLogger.isDebugEnabled() && overalLogger.isDebugEnabled()) {
			scenarioLogger.debug("This is debug : " + parameter);
			overalLogger.debug("This is debug : " + parameter);
		}

		if (scenarioLogger.isInfoEnabled() && overalLogger.isInfoEnabled()) {
			scenarioLogger.info("This is info : " + parameter);
			overalLogger.debug("This is debug : " + parameter);
		}

		scenarioLogger.warn("This is warn : " + parameter);
		scenarioLogger.error("This is error : " + parameter);
		scenarioLogger.fatal("This is fatal : " + parameter);
		overalLogger.warn("This is warn : " + parameter);
		overalLogger.error("This is error : " + parameter);
		overalLogger.fatal("This is fatal : " + parameter);
		scenarioLogger.info("Scenario Log Ended.");
	}

}
