package com.crossmarx.rest.api;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossmarx.rest.api.entities.Filter;
import com.crossmarx.rest.api.entities.FilterLeaf;
import com.crossmarx.rest.api.entities.FilterNode;
import com.crossmarx.rest.api.entities.Login;
import com.crossmarx.rest.api.entities.LoginResponse;
import com.crossmarx.rest.api.entities.Query;
import com.crossmarx.rest.api.entities.Spec;
import com.crossmarx.rest.api.entities.StatusMessage;
import com.crossmarx.rest.api.entities.StockItem;
import com.crossmarx.rest.api.entities.StockItemResponse;
import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;
import com.crossmarx.rest.api.exceptions.SerializingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.jersey.api.client.ClientResponse;

import sun.misc.IOUtils;

public class TestStockItem {

	private StockItem stockItem;
	private LoginResponse loginResponse;

	@Before
	public void setUp() throws Exception {
		loginResponse = getLoginResponse();
		stockItem = createStockItemForTest();
	}
	
	@Test
	public void testStockItemOutput() {
		String operation = "record/stockitem/1?authhash=" + loginResponse.getAuthHash();
		try {
			ClientResponse response = Utils.getClient().doGet(operation);
			String result = response.getEntity(String.class);
			System.out.println(result);
//			response = Utils.getClient().doGet(operation);
//			StockItemResponse result2 = response.getEntity(StockItemResponse.class);
//			System.out.println(result2);
		} catch (SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetStockItem() {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/stockitem/1?authhash=" + loginResponse.getAuthHash();
		try {
			ClientResponse response = Utils.getClient().doGet(operation);
			String result = response.getEntity(String.class);
			JsonNode rootNode = Utils.getMapper().readTree(result);
			JsonNode recordNode = rootNode.path("record");
			JsonNode dataNode = recordNode.path("data");
			System.out.println(dataNode);
			String id = dataNode.path("Id").toString();
			Assert.assertTrue(id.equals("1"));
		} catch (IOException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStockItemImageDownload() {
		// URI: /engine/api/carlos/download/stockitem/1/Image
		// This will return
		// "Image":"109/2016/10/not-giant-enough-letter-a_2.jpg"
		String operation = "download/stockitem/1/Image?authhash=" + loginResponse.getAuthHash();
		ClientResponse response;
		try {
			response = Utils.getClient().doGet(operation);
			InputStream in = (InputStream) response.getEntityInputStream();
			OutputStream outputStream = new FileOutputStream(new File("D:\test"));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			// String result = ;
			// System.out.println(result);
			// {"errcode":10,"message":"Illegal request"}
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
			String body = Utils.getMapper().writeValueAsString(stockItem);
			ClientResponse response = Utils.getClient().doPost(body, operation);
			String result = response.getEntity(String.class);
			// {"statusMessage":{"errcode":0,"message":"Data
			// saved"},"timestamp":"2016-03-13T21:54:19Z"}
			System.out.println(result);
		} catch (JsonProcessingException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStockItemUpdateOK() {
		// URI: /engine/api/<carlos>/record/<stockitem>/<id>
		stockItem.setName("update stock item");
		System.out.println(stockItem.getId());
		String operation = "record/stockitem/" + stockItem.getId() + "?authhash=" + loginResponse.getAuthHash();
		try {
			String body = Utils.getMapper().writeValueAsString(stockItem);
			ClientResponse response = Utils.getClient().doPost(body, operation);
			String result = response.getEntity(String.class);
			System.out.println(result);
			// {"statusMessage":{"errcode":0,"message":"Data
			// saved"},"timestamp":"2016-03-13T22:04:08Z","key":"1"}
		} catch (JsonProcessingException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStockItemUpdateError() {
		String operation = "record/stockitem/1?authhash=" + loginResponse.getAuthHash();
		try {
			stockItem.setId(3);
			String body = Utils.getMapper().writeValueAsString(stockItem);
			ClientResponse response = Utils.getClient().doPost(body, operation);
			String result = response.getEntity(String.class);
		} catch (SecurityConfigurationException | JsonProcessingException e) {
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

	/**
	 * 
	 * @return
	 */
	public StockItem createStockItemForTest() {
		StockItem stockItem = new StockItem();
		stockItem.setName("stock item test 1");
		stockItem.setDate_in_stock("2016-03-07T23:00:00Z");
		stockItem.setAccount(1);
		stockItem.setCosts(1.0);
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
		ClientResponse response = Utils.getClient().doPost(bodyRequest, operation);
		return response.getEntity(LoginResponse.class);
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
