package com.framework.core;

public class StartApi {

	public static void main(String[] args) {
		
		try {

			new Logs();
			Logs.logger.info("============ Starting Automation Tests ================");
			Setup st = new Setup();
			st.executeTest(args);
		} catch (Exception e) {
			Logs.logger.info("Exception during executions.Error: " + e.getMessage());
		} 

	}

}