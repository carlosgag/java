package com.crossmarx.rest.api;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossmarx.rest.api.entities.AccountResponse;
import com.crossmarx.rest.api.entities.Login;
import com.crossmarx.rest.api.entities.LoginResponse;
import com.crossmarx.rest.api.entities.StatusMessage;
import com.crossmarx.rest.api.exceptions.DeserializingException;
import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;
import com.crossmarx.rest.api.exceptions.SerializingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class TestLogin {
	
	private final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private final String UTC_DATE_REGEX = "^\\d{4}-[0-1][0-3]-[0-3]\\d{1}T[0-2]\\d{1}:[0-5]\\d{1}:[0-5]\\d{1}Z$";
	private final String UTC_DATE_REGEX_SHORT = "*";
	private LoginResponse loginResponse;

	@Before
	public void setUp() {
		try {
			loginResponse = getLoginResponse();
		} catch (JsonProcessingException | SerializingException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testLoginOutput() {
			System.out.println("Output from Server...");
			System.out.println(loginResponse);
			Assert.assertTrue(true);
	}

	@Test
	public void testLoginInputOK() {
		StatusMessage statusMessage = loginResponse.getStatusMessage();
		Assert.assertTrue(statusMessage.getErrcode().equals(ErrorCode.OK.getCode()) && 
				statusMessage.getMessage().equals("Found") &&
				(loginResponse.getAuthHash() != null));

	}

	@Test
	public void testLoginInputError() {
		String operation = "login";
		try {
			String bodyRequest = getLoginRequest("carloss", "oceanHouse1");
			Response response = Utils.getClient().doPost(bodyRequest, operation);
			LoginResponse loginResponse = response.readEntity(LoginResponse.class);
			StatusMessage statusMessage = loginResponse.getStatusMessage();
			Assert.assertTrue(statusMessage.getErrcode().equals(ErrorCode.LOGON_FAILED)
					&& statusMessage.getMessage().equals("logon not succeeded"));
		} catch (SerializingException | SecurityConfigurationException | JsonProcessingException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testLoginAuthHashError() {
		String operation = "login";
		try {
			String bodyRequest = getLoginRequest("carloss", "oceanHouse1");
			Response response = Utils.getClient().doPost(bodyRequest, operation);
			LoginResponse output = response.readEntity(LoginResponse.class);
			Assert.assertTrue(output.getAuthHash() == null);
		} catch (SerializingException | SecurityConfigurationException | JsonProcessingException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Use the authorization code to retrieve a record
	 * 
	 * @throws SerializingException
	 */
	@Test
	public void testLoginAuthHashValid() {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/account/1?authhash=" + loginResponse.getAuthHash();
		try {
			Response response = Utils.getClient().doGet(operation);
			//String result = response.readEntity(String.class);
			AccountResponse result = response.readEntity(AccountResponse.class);
			System.out.println(result);
//			JsonNode rootNode = Utils.getMapper().readTree(result);
//			System.out.println(rootNode);
//			JsonNode recordNode = rootNode.path("record");
//			String className = recordNode.path("class").textValue();
//			JsonNode dataNode = recordNode.findValue("data");
//			Integer id = dataNode.get("Id").asInt();
//			Assert.assertTrue(id == 1 && className.equals("account"));
		} catch (SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testTimeStamp() {
		Date dateTime = null;
		try {
			dateTime = deserializeDate(loginResponse.getTimestamp());
			Assert.assertTrue(dateTime != null);
		} catch (DeserializingException | ParseException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * yyyy-MM-dd'T'HH:mm:ssZ??
	 * @param dateString
	 * @return
	 * @throws DeserializingException
	 * @throws ParseException
	 */
	private Date deserializeDate(String dateString) throws DeserializingException, ParseException {
		if (dateString.matches(UTC_DATE_REGEX) || dateString.matches(UTC_DATE_REGEX_SHORT)) {
			dateString = dateString.replace("T", " ");
			dateString = dateString.replace("Z", "");
			SimpleDateFormat formatUTC = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
			formatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date localTime = formatUTC.parse(dateString);
			return localTime;
		} else {
			throw new DeserializingException("Couldn't deserialize timestamp");
		}
	}

	/**
	 * 
	 * @return
	 * @throws SerializingException
	 * @throws SecurityConfigurationException
	 * @throws JsonProcessingException
	 */
	private LoginResponse getLoginResponse()
			throws SerializingException, SecurityConfigurationException, JsonProcessingException {
		String operation = "login";
		String bodyRequest = getLoginRequest("carlos", "oceanHouse1");
		Response response = Utils.getClient().doPost(bodyRequest, operation);
		return response.readEntity(LoginResponse.class);
	}

	/**
	 * 
	 * @param loginname
	 * @param password
	 * @return
	 * @throws SerializingException
	 * @throws JsonProcessingException
	 */
	private String getLoginRequest(String loginname, String password)
			throws SerializingException, JsonProcessingException {
		Login login = new Login(loginname, password);
		return Utils.getMapper().writeValueAsString(login);
	}

}
