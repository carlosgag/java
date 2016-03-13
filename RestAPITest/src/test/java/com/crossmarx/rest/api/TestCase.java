package com.crossmarx.rest.api;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossmarx.rest.api.RESTClient;
import com.crossmarx.rest.api.entities.Login;
import com.crossmarx.rest.api.entities.LoginResponse;
import com.crossmarx.rest.api.entities.Query;
import com.crossmarx.rest.api.entities.Spec;
import com.crossmarx.rest.api.entities.StatusMessage;
import com.crossmarx.rest.api.exceptions.DeserializingException;
import com.crossmarx.rest.api.exceptions.SerializingException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

public class TestCase {

	private ObjectMapper mapper = new ObjectMapper();

	private RESTClient restClient;

	@Before
	public void setUp() throws Exception {
		restClient = new RESTClient();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	@Test
	public void testLoginOutput() throws SerializingException {
		try {
			LoginResponse output = getLoginResponse();
			System.out.println("Output from Server .... \n");
			System.out.println(output);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testInputLoginOK() throws SerializingException {
		String bodyRequest = getLoginRequest("carlos", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		try {
			LoginResponse output = response.getEntity(LoginResponse.class);
			Assert.assertTrue(output.getStatusMessage().getErrcode().equals("0") &&
					output.getStatusMessage().getErrcode().equals("Found"));
			System.out.println("Output from Server .... \n");
			System.out.println(output);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testInputLoginError() throws SerializingException {
		String bodyRequest = getLoginRequest("carloss", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		try {
			LoginResponse output = response.getEntity(LoginResponse.class);
			Assert.assertTrue(output.getStatusMessage().getErrcode().equals("20"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testStatusMsgOK() throws SerializingException {
		String bodyRequest = getLoginRequest("carlos", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		try {
			LoginResponse output = response.getEntity(LoginResponse.class);
			Assert.assertTrue(output.getStatusMessage().getMessage().equals("Found"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testStatusMsgError() throws SerializingException {
		String bodyRequest = getLoginRequest("carloss", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		try {
			LoginResponse output = response.getEntity(LoginResponse.class);
			Assert.assertTrue(output.getStatusMessage().getMessage().equals("logon not succeeded"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testTimeStamp() throws SerializingException{
		String bodyRequest = getLoginRequest("carlos", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		try {
			LoginResponse output = response.getEntity(LoginResponse.class);
			Date timestamp = deserializeDate(output.getTimestamp());
			System.out.println(timestamp);
		} catch (DeserializingException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testAuthHashOK() throws SerializingException{
		String bodyRequest = getLoginRequest("carlos", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		try {
			LoginResponse output = response.getEntity(LoginResponse.class);
			Assert.assertTrue(!output.getAuthHash().equals("null"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testAuthHashError() throws SerializingException{
		String bodyRequest = getLoginRequest("carloss", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		try {
			LoginResponse output = response.getEntity(LoginResponse.class);
			Assert.assertTrue(output.getAuthHash().equals("null"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testAuthHashValid() throws SerializingException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		LoginResponse output = getLoginResponse();
		String operation = "record/account/1?authhash=" + output.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		System.out.println(result);
		try {
			JsonNode rootNode = mapper.readTree(result);
			JsonNode recordNode = rootNode.path("record");
			JsonNode dataNode = recordNode.findValue("data");
			Integer id = dataNode.get("Id").asInt();
			Assert.assertTrue(id == 1);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetAccountOK() throws SerializingException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		LoginResponse output = getLoginResponse();
		String operation = "record/account/1?authhash=" + output.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		try {
			JsonNode rootNode = mapper.readTree(result);
			JsonNode recordNode = rootNode.path("record");
			JsonNode dataNode = recordNode.findValue("data");
			Integer id = dataNode.get("Id").asInt();
			Assert.assertTrue(id == 1);
			String loginName = dataNode.get("LoginName").asText();
			Assert.assertTrue(loginName.equals("carlos"));
//			String password = dataNode.get("Password").asText();
//			System.out.println(password);
//			Assert.assertTrue(password.equals("nl.crossmarx.common.util.CXPassword@5aae2bd0"));
			Integer usergroup = dataNode.get("Usergroup").asInt();
			Assert.assertTrue(usergroup == 3);
			String firstname = dataNode.get("Firstname").asText();
			Assert.assertTrue(firstname.equals("Carlos"));
			String lastname = dataNode.get("Lastname").asText();
			Assert.assertTrue(lastname.equals("Gagliardi"));
			String email = dataNode.get("Email").asText();
			Assert.assertTrue(email.equals("carlosgag@gmail.com"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetAccountError() throws SerializingException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		LoginResponse output = getLoginResponse();
		String operation = "record/account/2?authhash=" + output.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		try {
			JsonNode rootNode = mapper.readTree(result);
			String status = rootNode.path("statusMessage").path("message").asText();
			Assert.assertTrue(status.equals("Record not found"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	private Query makeTestStockQuery(){
		Query queryRequest = new Query();
		queryRequest.setClassName("");
		
		List<Spec> sortSpecs = new ArrayList<>();
		Spec spec = new Spec();
		spec.setField("name");
		spec.setDirection("desc");
		sortSpecs.add(spec);
		Spec spec2 = new Spec();
		spec2.setField("name");
		spec2.setDirection("desc");
		sortSpecs.add(spec2);
		queryRequest.setSortSpecs(sortSpecs);
		
		return queryRequest;
	}
	
	@Test
	public void testGetStockItemsQueryOK() throws SerializingException{
		// Operation:/query/<version>/record/<class>/<id>?authhash=<hash>
		// /engine/api/VERSION/query?querydef={}
		LoginResponse output = getLoginResponse();
		Query query = makeTestStockQuery();
		//String query ="{}";
		String operation = "query?querydef="+query+"&authhash=" + output.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		System.out.println(result);
		try {
			JsonNode rootNode = mapper.readTree(result);
			String status = rootNode.path("statusMessage").path("message").asText();
			Assert.assertTrue(status.equals("Record not found"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	private LoginResponse getLoginResponse() throws SerializingException{
		String bodyRequest = getLoginRequest("carlos", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		return response.getEntity(LoginResponse.class);
	}
	
	private String getLoginRequest(String loginname, String password) throws SerializingException {
		Login login = new Login(loginname, password);
		try {
			return mapper.writeValueAsString(login);
		} catch (IOException e) {
			throw new SerializingException();
		}
	}
	
	private Date deserializeDate(String date) throws DeserializingException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssSSSZ");
        try {
        	//yyyy-MM-dd'T'HH:mm:ssZ??
            return format.parse(date);
        } catch (ParseException e) {
            throw new DeserializingException(e.getMessage(), e.getCause());
        }
	}

}
