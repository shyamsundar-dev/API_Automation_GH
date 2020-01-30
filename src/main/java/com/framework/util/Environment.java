package com.framework.util;

import com.github.core.Common;

public class Environment extends Common{
	
	public void envSelect() throws Exception {
		String env = getProp("env", "config");
		if (env.contains("SHYAM")) {
			writeProp("auth", getProp("s-auth", "config"));
			writeProp("user", getProp("s-user", "config"));
			writeProp("org", getProp("s-org", "config"));
		}else if(env.contains("RAHUL")) {
			writeProp("auth", getProp("r-auth", "config"));
			writeProp("user", getProp("r-user", "config"));
			writeProp("org", getProp("r-org", "config"));	
		}else {
				System.out.println("Wrong Environment");
				System.out.println("Execution terminated");
				System. exit(0);
			}
	}
}
	