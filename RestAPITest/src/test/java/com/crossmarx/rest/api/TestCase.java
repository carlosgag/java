package com.crossmarx.rest.api;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossmarx.rest.api.RESTClient;
import com.crossmarx.rest.api.entities.Filter;
import com.crossmarx.rest.api.entities.FilterLeaf;
import com.crossmarx.rest.api.entities.FilterNode;
import com.crossmarx.rest.api.entities.Login;
import com.crossmarx.rest.api.entities.LoginResponse;
import com.crossmarx.rest.api.entities.Query;
import com.crossmarx.rest.api.entities.Spec;
import com.crossmarx.rest.api.entities.StatusMessage;
import com.crossmarx.rest.api.entities.StockItem;
import com.crossmarx.rest.api.exceptions.DeserializingException;
import com.crossmarx.rest.api.exceptions.SerializingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

public class TestCase {

	private ObjectMapper mapper = new ObjectMapper();
	private StockItem stockItem;
	private LoginResponse loginResponse;
	private RESTClient restClient;

	private StockItem createStockItemForTest(){
		StockItem stockItem = new StockItem();
		stockItem.setName("stock item test 1");
		stockItem.setDate_in_stock("2016-03-07T23:00:00Z");
		stockItem.setAccount(1);
		stockItem.setCosts(1.0);
		stockItem.setNumber_in_stock(2);
		stockItem.setWeight(3.0);
		return stockItem;
	}
	
	private LoginResponse getLoginResponse() throws SerializingException{
		String bodyRequest = getLoginRequest("carlos", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		return response.getEntity(LoginResponse.class);
	}
	
	@Before
	public void setUp() throws Exception {
		restClient = new RESTClient();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		loginResponse = getLoginResponse();
		stockItem = createStockItemForTest();
	}
	
	@Test
	public void testLoginOutput() throws SerializingException {
		try {
			System.out.println("Output from Server .... \n");
			System.out.println(loginResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testLoginInputOK() throws SerializingException {
		try {
			StatusMessage statusMessage = loginResponse.getStatusMessage();
			Assert.assertTrue(statusMessage.getErrcode().equals("0") &&
					statusMessage.getMessage().equals("Found"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testLoginInputError() throws SerializingException {
		String bodyRequest = getLoginRequest("carloss", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		try {
			LoginResponse loginResponse = response.getEntity(LoginResponse.class);
			StatusMessage statusMessage = loginResponse.getStatusMessage();
			Assert.assertTrue(statusMessage.getErrcode().equals("20") &&
					statusMessage.getMessage().equals("logon not succeeded"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testTimeStamp() {
		try {
			System.out.println(loginResponse.getTimestamp());
			DateTime dateTime = deserializeDate(loginResponse.getTimestamp());
		} catch (Exception e) {
			//fail(e.getMessage());
		}
	}
	
	@Test
	public void testLoginAuthHashOK() throws SerializingException{
		try {
			Assert.assertTrue(loginResponse.getAuthHash() != null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testLoginAuthHashError() throws SerializingException{
		String bodyRequest = getLoginRequest("carloss", "oceanHouse1");
		String operation = "login";
		ClientResponse response = restClient.doPost(bodyRequest, operation);
		try {
			LoginResponse output = response.getEntity(LoginResponse.class);
			Assert.assertTrue(output.getAuthHash() == null);
		} catch (Exception e) {
			fail(e.getMessage()); 
		}
	}
	
	/**
	 * Use the authorization code to retrieve a record
	 * @throws SerializingException
	 */
	@Test
	public void testLoginAuthHashValid() throws SerializingException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/account/1?authhash=" + loginResponse.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		try {
			JsonNode rootNode = mapper.readTree(result);
			JsonNode recordNode = rootNode.path("record");
			String className = recordNode.path("class").textValue();
			JsonNode dataNode = recordNode.findValue("data");
			Integer id = dataNode.get("Id").asInt();
			Assert.assertTrue(id == 1 && className.equals("account"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetAccountOK() throws SerializingException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/account/1?authhash=" + loginResponse.getAuthHash();
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
		String operation = "record/account/2?authhash=" + loginResponse.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		try {
			JsonNode rootNode = mapper.readTree(result);
			String status = rootNode.path("statusMessage").toString();
			StatusMessage statusMessage = mapper.readValue(status, StatusMessage.class);
			Assert.assertTrue(statusMessage.getMessage().equals("Record not found"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetStockItem() throws SerializingException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/stockitem/1?authhash=" + loginResponse.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		try {
			JsonNode rootNode = mapper.readTree(result);
			String status = rootNode.path("statusMessage").toString();
			StatusMessage statusMessage = mapper.readValue(status, StatusMessage.class);
			Assert.assertTrue(statusMessage.getMessage().equals("Record found"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetStockItemsQueryOK() 
			throws SerializingException, JsonProcessingException, UnsupportedEncodingException{
		// /engine/api/VERSION/query?querydef={}&authhash=<hash>
		Query query = makeTestStockItemQueryOK();
		String value = mapper.writeValueAsString(query);
		value = value.replace("className", "class");
		value = URLEncoder.encode(value, "UTF-8");
		String operation = "query?querydef=" + value + "&authhash=" + loginResponse.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		try {
			JsonNode rootNode = mapper.readTree(result);
			JsonNode record = rootNode.path("record");
			String stockItem = record.path("data").toString();
			String source = "{\"Id\":1,\"Name\":\"stock item 1\",\"Date_in_stock\":\"2016-03-07T23:00:00Z\",\"Account\":1,\"Image\":\"109/2016/10/not-giant-enough-letter-a_2.jpg\",\"Costs\":4500,\"Number_in_stock\":345345,\"Weight\":4545.0}";
			Assert.assertTrue(stockItem.equals(source));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetStockItemsQueryError() 
			throws SerializingException, IOException{
		// /engine/api/VERSION/query?querydef={}&authhash=<hash>
		Query query = makeTestStockItemQueryError();
		String value = mapper.writeValueAsString(query);
		value = value.replace("className", "class");
		value = URLEncoder.encode(value,"UTF-8");
		String operation = "query?querydef="+value+"&authhash=" + loginResponse.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		JsonNode rootNode = mapper.readTree(result);
		String error = rootNode.path("message").textValue();
		Assert.assertTrue(error.equals("<<"));
	}

	@Test
	public void testStockItemImageDownload() throws SerializingException {
		// URI: /engine/api/carlos/download/stockitem/1/Image
		// This will return
		// "Image":"109/2016/10/not-giant-enough-letter-a_2.jpg"
		String operation = "download/stockitem/1/Image?authhash=" + loginResponse.getAuthHash();
		ClientResponse response = restClient.doGet(operation);
		String result = response.getEntity(String.class);
		//{"errcode":10,"message":"Illegal request"}
	}	
	
	@Test
	public void testStockItemCreationOK() throws SerializingException, JsonProcessingException{
		// URI: /engine/api/carlos/record/stockitem
		String body = mapper.writeValueAsString(stockItem);
		String operation = "record/stockitem?authhash=" + loginResponse.getAuthHash();
		ClientResponse response = restClient.doPost(body, operation);
		String result = response.getEntity(String.class);
		//{"statusMessage":{"errcode":0,"message":"Data saved"},"timestamp":"2016-03-13T21:54:19Z"}
		System.out.println(result);
	}

	@Test
	public void testStockItemUpdateOK() throws SerializingException, JsonProcessingException {
		// URI: /engine/api/<carlos>/record/<stockitem>/<id>
		stockItem.setName("update stock item");
		String body = mapper.writeValueAsString(stockItem);
		String operation = "record/stockitem/" + stockItem.getId() + "?authhash=" + loginResponse.getAuthHash();
		ClientResponse response = restClient.doPost(body, operation);
		String result = response.getEntity(String.class);
		//{"statusMessage":{"errcode":0,"message":"Data saved"},"timestamp":"2016-03-13T22:04:08Z","key":"1"}
	}
	
	@Test
	public void testStockItemUpdateError() throws SerializingException, JsonProcessingException{
		stockItem.setId(3);
		String body = mapper.writeValueAsString(stockItem);
		String operation= "record/stockitem/1?authhash=" + loginResponse.getAuthHash();
		ClientResponse response = restClient.doPost(body, operation);
		String result = response.getEntity(String.class);
		//
	}
	
	@Test
	public void testStockItemCRUD() 
			throws JsonProcessingException, SerializingException, UnsupportedEncodingException{
		//TODO parameterize stock item object for this cycle
		//create stock item
		testStockItemCreationOK();
		//read stock item
		testGetStockItem();
		//read stock item from query
		testGetStockItemsQueryOK();
		//update stock item
		testStockItemUpdateOK();
		//delete stock item
	}
	
	private Query makeTestStockItemQueryOK(){
		Query queryRequest = new Query();
		
		List<Spec> sortSpecs = new ArrayList<>();
		Spec spec = new Spec();
		spec.setField("name");
		spec.setDirection("desc");
		sortSpecs.add(spec);
		Spec spec2 = new Spec();
		spec2.setField("id");
		spec2.setDirection("asc");
		sortSpecs.add(spec2);
		queryRequest.setSortSpecs(sortSpecs);
		
		queryRequest.setClassName("stockitem");
		
		FilterNode filter = new FilterNode();
		filter.setOperator("and");
		List<Filter> children = new ArrayList<>();
		FilterLeaf leaf1 = new FilterLeaf();
		leaf1.setField("Id");
		leaf1.setOperator("<");
		leaf1.setValue(2);
		children.add(leaf1);
		FilterLeaf leaf2 = new FilterLeaf();
		leaf2.setField("Name");
		leaf2.setOperator("equals");
		leaf2.setValue("stock item 1");
		children.add(leaf2);
		filter.setChildren(children);		
		queryRequest.setFilter(filter);
		
		List<String> resultFields = new ArrayList<>();
		resultFields.add("Id");
		resultFields.add("Name");
		queryRequest.setResultFields(resultFields);
		
		return queryRequest;
	}
	
	private Query makeTestStockItemQueryError(){
		Query queryRequest = new Query();
		queryRequest.setClassName("");
		
		List<Spec> sortSpecs = new ArrayList<>();
		Spec spec = new Spec();
		spec.setField("name");
		spec.setDirection("desc");
		sortSpecs.add(spec);
		Spec spec2 = new Spec();
		spec2.setField("id");
		spec2.setDirection("asc");
		sortSpecs.add(spec2);
		queryRequest.setSortSpecs(sortSpecs);
		
		queryRequest.setClassName("stockitem");
		
		FilterNode filter = new FilterNode();
		filter.setOperator("and");
		List<Filter> children = new ArrayList<>();
		FilterLeaf leaf1 = new FilterLeaf();
		leaf1.setField("Id");
		leaf1.setOperator("<<");
		leaf1.setValue(2);
		children.add(leaf1);
		FilterLeaf leaf2 = new FilterLeaf();
		leaf2.setField("Name");
		leaf2.setOperator("equals");
		leaf2.setValue("stock item 1");
		children.add(leaf2);
		filter.setChildren(children);		
		queryRequest.setFilter(filter);
		
		List<String> resultFields = new ArrayList<>();
		resultFields.add("Id");
		resultFields.add("Name");
		queryRequest.setResultFields(resultFields);
		
		return queryRequest;
	}
	
	private String getLoginRequest(String loginname, String password) throws SerializingException {
		Login login = new Login(loginname, password);
		try {
			return mapper.writeValueAsString(login);
		} catch (IOException e) {
			throw new SerializingException();
		}
	}
	
	private DateTime deserializeDate(String dateString) throws DeserializingException {
		final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		final String UTC_DATE_REGEX = "^\\d{4}-[0-1][0-3]-[0-3]\\d{1}T[0-2]\\d{1}:[0-5]\\d{1}:[0-5]\\d{1}Z$";
		final String UTC_DATE_REGEX_SHORT = "??";
		if (dateString.matches(UTC_DATE_REGEX) || dateString.matches(UTC_DATE_REGEX_SHORT)) {
			if (dateString.matches(UTC_DATE_REGEX)) {
				System.out.println("matches regex");
			}
			if (dateString.matches(UTC_DATE_REGEX_SHORT)) {
				System.out.println("matches regex short");
			}
			dateString = dateString.replace("T", " ");
			dateString = dateString.replace("Z", "");
			System.out.println(dateString);
			try {
				SimpleDateFormat formatUTC = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
				formatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
				Date localTime = formatUTC.parse(dateString);
				System.out.println(">>" + formatUTC.format(localTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		// yyyy-MM-dd'T'HH:mm:ssZ??
		return DateTime.parse("date");
	}

}
