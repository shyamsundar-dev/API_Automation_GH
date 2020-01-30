package com.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.framework.core.Constants;

public class Utilities {

//	### 	Properties file reader 		###
	public String getProp(String propName, String propFile) {
		InputStream inputStream;
		String propStr = null;
		try {
			String path = null;
			if (propFile.contentEquals("config")) {
				path = (Constants.CONFIG);
			} else if (propFile.contentEquals("runtime")) {
				path = (Constants.RUNTIME);
			} else {
				System.out.println("Wrong properties file name");
			}
			Properties prop = new Properties();
			File configFile = new File(path);
			inputStream = new FileInputStream(configFile);
			prop.load(inputStream);
			propStr = prop.getProperty(propName);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}
		return propStr;
	}

//	### 	Properties file writer 		###
	public void writeProp(String key, String value) throws IOException {
		Properties prop = new Properties();
		InputStream fis = new FileInputStream(Constants.RUNTIME);
		prop.load(fis);
		prop.setProperty(key, value);
		prop.store(new FileOutputStream(Constants.RUNTIME), null);
	}

//	### 	Reset Properties 		###
	public void resetProp() throws IOException {
		Properties prop = new Properties();
		InputStream fis = new FileInputStream(Constants.RUNTIME);
		prop.load(fis);
		prop.clear();
		prop.store(new FileOutputStream(Constants.RUNTIME), null);
	}

}
