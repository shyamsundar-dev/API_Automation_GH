package com.framework.core;

public class Constants {

	public static String TESTNG= OsSelector("src\\main\\resources\\xml\\testng.xml");
	public static String LOG4J = OsSelector("src\\main\\resources\\configuration\\log4j.properties");
	public static String CONFIG = OsSelector("src\\main\\resources\\configuration\\config.properties");
	public static String RUNTIME = OsSelector("src\\main\\resources\\configuration\\runtime.properties");
	
	public static String OsSelector(String str) {
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.contains("mac")){
			str = str.replaceAll("\\\\", "/");
		}
		return str;	
	}

}
