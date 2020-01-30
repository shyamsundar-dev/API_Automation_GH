package com.framework.core;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

import com.framework.util.Environment;

public class Setup extends Environment{
  

	public void executeTest(String[] type) throws Exception {
		envSelect();
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(Constants.TESTNG);
		testng.setTestSuites(suites);
		testng.run();
		
	}
}