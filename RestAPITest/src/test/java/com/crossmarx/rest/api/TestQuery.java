package com.crossmarx.rest.api;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
import com.crossmarx.rest.api.entities.StockItem;
import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;
import com.crossmarx.rest.api.exceptions.SerializingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.jersey.api.client.ClientResponse;

public class TestQuery {

	private StockItem stockItem;
	private LoginResponse loginResponse;

	@Before
	public void setUp() throws Exception {
		loginResponse = getLoginResponse();
		TestStockItem testStockItem = new TestStockItem();
		stockItem = testStockItem.createStockItemForTest();
	}

	@Test
	public void testGetStockItemsQueryOK() {
		// /engine/api/VERSION/query?querydef={}&authhash=<hash>
		try {
			Query query = makeTestStockItemQueryOK();
			String value = Utils.getMapper().writeValueAsString(query);
			value = value.replace("className", "class");
			value = URLEncoder.encode(value, "UTF-8");
			String operation = "query?querydef=" + value + "&authhash=" + loginResponse.getAuthHash();
			ClientResponse response = Utils.getClient().doGet(operation);
			String result = response.getEntity(String.class);
			JsonNode rootNode = Utils.getMapper().readTree(result);
			JsonNode recordNode = rootNode.path("record");
			JsonNode dataNode = recordNode.path("data");
			System.out.println(dataNode);
			String id = dataNode.path("Id").toString();
			Assert.assertTrue(id.equals("1"));
		} catch (SecurityConfigurationException | JsonProcessingException | UnsupportedEncodingException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetStockItemsQueryError() {
		// /engine/api/VERSION/query?querydef={}&authhash=<hash>
		Query query = makeTestStockItemQueryError();
		try {
			String value = Utils.getMapper().writeValueAsString(query);
			value = value.replace("className", "class");
			value = URLEncoder.encode(value, "UTF-8");
			String operation = "query?querydef=" + value + "&authhash=" + loginResponse.getAuthHash();
			ClientResponse response = Utils.getClient().doGet(operation);
			String result = response.getEntity(String.class);
			JsonNode rootNode = Utils.getMapper().readTree(result);
			String error = rootNode.path("message").textValue();
			Assert.assertTrue(error.equals("<<"));
		} catch (SecurityConfigurationException | JsonProcessingException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 */
	private Query makeTestStockItemQueryOK() {
		
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

	/**
	 * 
	 * @return
	 */
	private Query makeTestStockItemQueryError() {
		
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
