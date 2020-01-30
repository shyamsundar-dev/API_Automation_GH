package com.github.tests;

import org.testng.annotations.Test;

import com.framework.core.Logs;
import com.github.core.Github;

public class TestCase {

	@Test
	public static void test() throws Exception {
		Github gh = new Github();
		gh.user();
		gh.org();
		gh.team();
		gh.addTeam();
		gh.team();
		gh.editTeam();
		gh.team();
		gh.deleteTeam();
		gh.team();
		gh.teamRepo();
		gh.addUpdateRepo();
		gh.resetRuntime();
		Logs.logger.info("============ Automation Tests Finished ================");
	}

}
