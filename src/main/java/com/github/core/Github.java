package com.github.core;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;

public class Github extends Common {

	String baseUrl = getProp("baseUrl", "config");

//     ######### Get User ID #########     //
	public void user() throws IOException {
		System.out.println(" ");
		System.out.println("######### Get User ID #########");
		String url = baseUrl + "/users/" + getProp("user", "runtime");
		String auth = null;
		Response response = getRequest(url, auth);
		assertSuccessStatus(response, 200);
		JSONObject data = new JSONObject(response.body().asString());
		String id = String.valueOf(data.get("id"));
		Assert.assertTrue(!id.isEmpty(), "User id not found");
		writeProp("userId", id);
		System.out.println("User ID : " + id + " || " + "User Name : " + getProp("user", "runtime"));
	}

//     ######### Get Organization ID #########     //
	public void org() throws IOException {
		System.out.println(" ");
		System.out.println("######### Get Organization ID #########");
		String url = baseUrl + "/orgs/" + getProp("org", "runtime");
		String auth = null;
		Response response = getRequest(url, auth);
		assertSuccessStatus(response, 200);
		JSONObject data = new JSONObject(response.body().asString());
		String id = String.valueOf(data.get("id"));
		Assert.assertTrue(!id.isEmpty(), "Organization id not found");
		writeProp("orgId", id);
		System.out.println("Organization ID : " + id + " || " + "Organization Name : " + getProp("org", "runtime"));
	}

//     ######### Get List of Teams #########     //
	public void team() throws IOException {
		System.out.println(" ");
		System.out.println("######### Get List of Teams #########");
		String url = baseUrl + "/orgs/" + getProp("org", "runtime") + "/teams";
		String auth = getProp("auth", "runtime");
		Response response = getRequest(url, auth);
		assertSuccessStatus(response, 200);
		JSONArray data = new JSONArray(response.asString());
		for (int i = 0; i <= data.length() - 1; i++) {
			JSONObject obj = data.getJSONObject(i);
			String id = String.valueOf(obj.get("id"));
			String name = String.valueOf(obj.get("name"));
			String slug = String.valueOf(obj.get("slug"));
			if (getProp("pTeamId", "runtime") == null && i == 0) {
				writeProp("pTeamId", id);
			}
			System.out.println("Team Name : " + id + " || " + name + " || " + slug);
		}
	}

//     ######### Add a New Team #########     //
	public void addTeam() throws IOException {
		System.out.println(" ");
		System.out.println("######### Add a New Team #########");
		String url = baseUrl + "/orgs/" + getProp("org", "runtime") + "/teams";
		String auth = getProp("auth", "runtime");
		JSONObject jobj = addTeamJson();
		Response response = postRequestWithJsonData(url, auth, jobj);
		assertSuccessStatus(response, 201);
		JSONObject data = new JSONObject(response.body().asString());
		String id = String.valueOf(data.get("id"));
		String name = String.valueOf(data.get("name"));
		System.out.println("Team Name : " + id + " || " + name);
		writeProp("teamId", id);
	}

//     ######### Edit a Team #########     //
	public void editTeam() throws IOException {
		System.out.println(" ");
		System.out.println("######### Edit a Team #########");
		String url = baseUrl + "/organizations/" + getProp("orgId", "runtime") + "/team/"
				+ getProp("teamId", "runtime");
		String auth = getProp("auth", "runtime");
		JSONObject jobj = editTeamJson();
		Response response = patchRequestWithJsonData(url, auth, jobj);
		assertSuccessStatus(response, 200);
		JSONObject data = new JSONObject(response.body().asString());
		String id = String.valueOf(data.get("id"));
		String name = String.valueOf(data.get("name"));
		System.out.println("Team Name : " + id + " || " + name);
	}

//  ######### Delete a Team #########     //
	public void deleteTeam() throws IOException {
		System.out.println(" ");
		System.out.println("######### Delete a Team #########");
		String team = getProp("teamId", "runtime");
		String url = baseUrl + "/organizations/" + getProp("orgId", "runtime") + "/team/" + team;
		String auth = getProp("auth", "runtime");
		Response response = deleteRequest(url, auth);
		assertSuccessStatus(response, 204);
		writeProp("teamId", "");
		System.out.println("Team " + team + " Deleted");
	}

//  ######### List Team Repository #########     //
	public void teamRepo() throws IOException {
		System.out.println(" ");
		System.out.println("######### List Team Repository #########");
		String url = baseUrl + "/organizations/" + getProp("orgId", "runtime") + "/team/"
				+ getProp("pTeamId", "runtime") + "/repos";
		String auth = getProp("auth", "runtime");
		Response response = getRequest(url, auth);
		assertSuccessStatus(response, 200);
		JSONArray data = new JSONArray(response.asString());
		for (int i = 0; i <= data.length() - 1; i++) {
			JSONObject obj = data.getJSONObject(i);
			String repo = String.valueOf(obj.get("name"));
			JSONObject ownerObj = obj.getJSONObject("owner");
			String name = String.valueOf(ownerObj.get("login"));
			System.out.println("Repository Name : " + repo);
			if (getProp("repoName", "runtime") == null && i == 0) {
				writeProp("repoName", repo);
				writeProp("repoOwner", name);
			}
		}
	}

//  ######### Add or Update Repository #########     //
	public void addUpdateRepo() throws Exception {
		System.out.println(" ");
		System.out.println("######### Add or Update Repository #########");
		String url = baseUrl + "/organizations/" + getProp("orgId", "runtime") + "/team/"
				+ getProp("pTeamId", "runtime") + "/repos/" + getProp("repoOwner", "runtime") + "/"
				+ getProp("repoName", "runtime");
		String auth = getProp("auth", "runtime");
		JSONObject jobj = repoJson();
		Response response = putRequestWithJsonData(url, auth, jobj);
		assertSuccessStatus(response, 204);
	}

	public void resetRuntime() throws Exception {
		resetProp();
		System.out.println(" ");
	}

// =================================================================================================================//	

//     ######### Add new team json #########     //
	public static org.json.JSONObject addTeamJson() {
		JSONObject jObject = new JSONObject();
		jObject.accumulate("name", "API Team");
		jObject.accumulate("description", "New Team");
		jObject.accumulate("permission", "admin");
		jObject.accumulate("privacy", "closed");
		return jObject;
	}

//  ######### Edit a team json #########     //
	public static org.json.JSONObject editTeamJson() {
		JSONObject jObject = new JSONObject();
		jObject.accumulate("name", "API Team Edited");
		jObject.accumulate("description", "Edited Team");
		jObject.accumulate("permission", "admin");
		jObject.accumulate("privacy", "closed");
		return jObject;
	}

//  ######### Add or update repo json #########     //
	public static org.json.JSONObject repoJson() {
		JSONObject jObject = new JSONObject();
		jObject.accumulate("permission", "admin");
		return jObject;
	}

}
