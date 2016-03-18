package com.crossmarx.rest.api;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossmarx.rest.api.entities.Login;
import com.crossmarx.rest.api.entities.LoginResponse;
import com.crossmarx.rest.api.entities.StatusMessage;
import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;
import com.crossmarx.rest.api.exceptions.SerializingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.jersey.api.client.ClientResponse;

public class TestAccount {

	private LoginResponse loginResponse;

	@Before
	public void setUp() throws Exception {
		loginResponse = getLoginResponse();
	}

	@Test
	public void testGetAccountOK() {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/account/1?authhash=" + loginResponse.getAuthHash();
		try {
			ClientResponse response = Utils.getClient().doGet(operation);
			String result = response.getEntity(String.class);
			JsonNode rootNode = Utils.getMapper().readTree(result);
			JsonNode recordNode = rootNode.path("record");
			JsonNode dataNode = recordNode.findValue("data");
			Integer id = dataNode.get("Id").asInt();
			Assert.assertTrue(id == 1);
			String loginName = dataNode.get("LoginName").asText();
			Assert.assertTrue(loginName.equals("carlos"));
			Integer usergroup = dataNode.get("Usergroup").asInt();
			Assert.assertTrue(usergroup == 3);
			String firstname = dataNode.get("Firstname").asText();
			Assert.assertTrue(firstname.equals("Carlos"));
			String lastname = dataNode.get("Lastname").asText();
			Assert.assertTrue(lastname.equals("Gagliardi"));
			String email = dataNode.get("Email").asText();
			Assert.assertTrue(email.equals("carlosgag@gmail.com"));
		} catch (IOException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetAccountError() {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/account/2?authhash=" + loginResponse.getAuthHash();
		try {
			ClientResponse response = Utils.getClient().doGet(operation);
			String result = response.getEntity(String.class);
			JsonNode rootNode = Utils.getMapper().readTree(result);
			String status = rootNode.path("statusMessage").toString();
			StatusMessage statusMessage = Utils.getMapper().readValue(status, StatusMessage.class);
			Assert.assertTrue(statusMessage.getMessage().equals("Record not found"));
		} catch (IOException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	private LoginResponse getLoginResponse()
			throws SerializingException, SecurityConfigurationException, JsonProcessingException {
		String bodyRequest = getLoginRequest("carlos", "oceanHouse1");
		String operation = "login";
		ClientResponse response = Utils.getClient().doPost(bodyRequest, operation);
		return response.getEntity(LoginResponse.class);
	}

	private String getLoginRequest(String loginname, String password)
			throws SerializingException, JsonProcessingException {
		Login login = new Login(loginname, password);
		return Utils.getMapper().writeValueAsString(login);
	}

}
