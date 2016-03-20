package com.crossmarx.rest.api;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossmarx.rest.api.entities.Login;
import com.crossmarx.rest.api.entities.LoginResponse;
import com.crossmarx.rest.api.entities.StatusMessage;
import com.crossmarx.rest.api.entities.StockItem;
import com.crossmarx.rest.api.entities.UploadResponse;
import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;
import com.crossmarx.rest.api.exceptions.SerializingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class TestStockItem {

	private LoginResponse loginResponse;

	@Before
	public void setUp() throws Exception {
		loginResponse = getLoginResponse();
	}
	
	@Test
	public void testStockItemOutput() {
		try {
			System.out.println(getStockItem(1));
		} catch (SecurityConfigurationException | JsonProcessingException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetStockItem() {
		try {
			Assert.assertTrue(getStockItem(1).getId() == 1);
		} catch (IOException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStockItemImageUpload() {
		// URI: /engine/api/<version>/upload/<class>/<id>/<field> for stockitem, field=Image
		Integer id = 3;
		String operation = "upload/stockitem/" + id + "/Image?authhash=" + loginResponse.getAuthHash();
		try {
			MultiPart multiPart = new MultiPart();
			multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

			String fileName = "butterfly.jpg";
			FileDataBodyPart fileDataBodyPart = new FileDataBodyPart(
					"file", 
					new File(Config.LOCAL_PATH + fileName), 
					MediaType.APPLICATION_OCTET_STREAM_TYPE);
	    	multiPart.bodyPart(fileDataBodyPart);
	    	
			Response response = Utils.getClient().doPost(multiPart, operation);
			UploadResponse uploadResponse = response.readEntity(UploadResponse.class);
			StatusMessage statusMessage = uploadResponse.getStatusMessage();
			Assert.assertTrue(statusMessage.getErrcode().equals(String.valueOf(ErrorCode.OK.getCode()))
					&& uploadResponse.getKey().equals(String.valueOf(id)));
		} catch (SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStockItemImageDownload() {
		// URI: /engine/api/<version>/download/<class>/<id>/Image
		Integer id = 1;
		String operation = "download/stockitem/" + id + "/Image?authhash=" + loginResponse.getAuthHash();
		try {
			Response response = Utils.getClient().doGet(operation);
			InputStream in = (InputStream) response.readEntity(InputStream.class);
			String path = getStockItem(id).getImage();
			Integer index = path.lastIndexOf("/");
			String fileName = path.substring(index+1, path.length());
			OutputStream outputStream = new FileOutputStream(new File("D:\\" + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			System.out.println(bytes.length);
		} catch (SecurityConfigurationException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStockItemCreationOK() {
		// URI: /engine/api/carlos/record/stockitem
		String operation = "record/stockitem?authhash=" + loginResponse.getAuthHash();
		try {
			String body = Utils.getMapper().writeValueAsString(createStockItemForTest());
			Response response = Utils.getClient().doPost(body, operation);
			String result = response.readEntity(String.class);
			JsonNode rootNode = Utils.getMapper().readTree(result);
			String status = rootNode.path("statusMessage").toString();
			StatusMessage statusMessage = Utils.getMapper().readValue(status, StatusMessage.class);
			Assert.assertTrue(statusMessage.getErrcode().equals(ErrorCode.OK.getCode()));
		} catch (JsonProcessingException | SecurityConfigurationException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStockItemUpdateOK() {
		// URI: /engine/api/<version>/record/<class>/<id>
		try {
			StockItem stockItem = getStockItem(1);
			String newName = "update stock item 11";
			stockItem.setName(newName);
			String operation = "record/stockitem/" + stockItem.getId() + "?authhash=" + loginResponse.getAuthHash();
			String body = Utils.getMapper().writeValueAsString(stockItem);
			Response response = Utils.getClient().doPost(body, operation);
			String result = response.readEntity(String.class);
			JsonNode rootNode = Utils.getMapper().readTree(result);
			String status = rootNode.path("statusMessage").toString();
			StatusMessage statusMessage = Utils.getMapper().readValue(status, StatusMessage.class);
			stockItem = getStockItem(1);
			Assert.assertTrue(statusMessage.getErrcode().equals(ErrorCode.OK.getCode()) &&
					stockItem.getName().equals(newName));
		} catch (JsonProcessingException | SecurityConfigurationException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStockItemUpdateError() {
		String operation = "record/stockitem/1?authhash=" + loginResponse.getAuthHash();
		try {
			StockItem stockItem = getStockItem(1);
			stockItem.setCosts(123);
			String body = Utils.getMapper().writeValueAsString(stockItem);
			body = body.replace("123", "123.00");
			Response response = Utils.getClient().doPost(body, operation);
			String result = response.readEntity(String.class);
			StatusMessage statusMessage = Utils.getMapper().readValue(result, StatusMessage.class);
			Assert.assertTrue(statusMessage.getErrcode().equals(ErrorCode.REQUEST_ERROR));
		} catch (SecurityConfigurationException | JsonProcessingException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStockItemCRUD() {
		// TODO parameterize stock item object for this cycle
		// create stock item
		testStockItemCreationOK();
		// read stock item
		testGetStockItem();
		// update stock item
		testStockItemUpdateOK();
		// delete stock item
	}
	
	private StockItem getStockItem(Integer id)
			throws SecurityConfigurationException, JsonProcessingException, IOException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/stockitem/" + id + "?authhash=" + loginResponse.getAuthHash();
		Response response = Utils.getClient().doGet(operation);
		String result = response.readEntity(String.class);
		JsonNode rootNode = Utils.getMapper().readTree(result);
		JsonNode recordNode = rootNode.path("record");
		JsonNode dataNode = recordNode.path("data");
		return getStockItemFromJson(dataNode);
	}
	
	/**
	 * 
	 * @param dataNode
	 * @return
	 */
	private StockItem getStockItemFromJson(JsonNode dataNode){
		StockItem stockItem = new StockItem();
		stockItem.setAccount(Integer.valueOf((dataNode.path("Account").toString())));
		stockItem.setCosts(Integer.valueOf(dataNode.path("Costs").toString()));
		stockItem.setDate_in_stock(dataNode.path("Date_in_stock").textValue());
		stockItem.setId(Integer.valueOf(dataNode.path("Id").toString()));
		stockItem.setImage(dataNode.path("Image").textValue());
		stockItem.setName(dataNode.path("Name").textValue());
		stockItem.setNumber_in_stock(Integer.valueOf(dataNode.path("Number_in_stock").toString()));
		stockItem.setWeight(Double.valueOf(dataNode.path("Weight").toString()));
		return stockItem;
	}

	/**
	 * 
	 * @return
	 */
	public StockItem createStockItemForTest() {
		StockItem stockItem = new StockItem();
		stockItem.setName("stock item test 1");
		stockItem.setDate_in_stock("2016-03-07T23:00:00Z");
		stockItem.setAccount(1);
		stockItem.setCosts(10);
		stockItem.setNumber_in_stock(2);
		stockItem.setWeight(3.0);
		return stockItem;
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
		String bodyRequest = getLoginRequest("carlos", "oceanHouse1");
		String operation = "login";
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
