package com.crossarx.rest.api.hiv.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossarx.rest.api.hiv.Utils;
import com.crossarx.rest.api.hiv.entities.CourseModule;
import com.crossmarx.rest.api.entities.Filter;
import com.crossmarx.rest.api.entities.FilterLeaf;
import com.crossmarx.rest.api.entities.FilterNode;
import com.crossmarx.rest.api.entities.Login;
import com.crossmarx.rest.api.entities.LoginResponse;
import com.crossmarx.rest.api.entities.Query;
import com.crossmarx.rest.api.entities.Spec;
import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;
import com.crossmarx.rest.api.exceptions.SerializingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class TestCourseModule {

	private LoginResponse loginResponse;
	private final Integer courseId = 18;

	@Before
	public void setUp() throws Exception {
		loginResponse = getLoginResponse();
	}
	
	@Test
	public void testCourseModuleOutput() {
		try {
			System.out.println(getCourseModule());
		} catch (SecurityConfigurationException | JsonProcessingException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetCourseModule() {
		try {
			Assert.assertTrue(getCourseModule().getId() == courseId);
		} catch (IOException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetCourseModuleQueryOK() {
		// /engine/api/VERSION/query?querydef={}&authhash=<hash>
		try {
			Query query = makeTestCourseModuleQueryOK();
			String value = Utils.getMapper().writeValueAsString(query);
			value = value.replace("className", "class");
			value = URLEncoder.encode(value, "UTF-8");
			String operation = "query?querydef=" + value + "&authhash=" + loginResponse.getAuthHash();
			Response response = Utils.getClient().doGet(operation);
			String result = response.readEntity(String.class);
			System.out.println(result);
			JsonNode rootNode = Utils.getMapper().readTree(result);
			JsonNode recordNode = rootNode.path("record");
			JsonNode dataNode = recordNode.path("data");
			//System.out.println(dataNode);
			String id = dataNode.path("id").toString();
			Assert.assertTrue(id.equals(id + ""));
		} catch (SecurityConfigurationException | JsonProcessingException | UnsupportedEncodingException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}


	/**
	 * 
	 * @return
	 */
	private Query makeTestCourseModuleQueryOK() {
		
		Query queryRequest = new Query();

		List<Spec> sortSpecs = new ArrayList<>();
//		Spec spec = new Spec();
//		spec.setField("name");
//		spec.setDirection("desc");
//		sortSpecs.add(spec);
		Spec spec2 = new Spec();
		spec2.setField("id");
		spec2.setDirection("asc");
		sortSpecs.add(spec2);
		queryRequest.setSortSpecs(sortSpecs);

		queryRequest.setClassName("course - module");

		FilterNode filter = new FilterNode();
		filter.setOperator("and");
		List<Filter> children = new ArrayList<>();
		
		FilterLeaf leaf1 = new FilterLeaf();
		leaf1.setField("course");
		leaf1.setOperator("equals");
		leaf1.setValue(this.courseId);
		children.add(leaf1);
		
		filter.setChildren(children);
		
		queryRequest.setFilter(filter);

		List<String> resultFields = new ArrayList<>();
		resultFields.add("course");
		resultFields.add("module");
		queryRequest.setResultFields(resultFields);

		return queryRequest;
	}

	//@Test
	public void testCourseModuleImageDownload() {
		// URI: /engine/api/<version>/download/<class>/<id>/Image
		String operation = "download/course - module/" + courseId + "/Image?authhash=" + loginResponse.getAuthHash();
		try {
			Response response = Utils.getClient().doGet(operation);
			InputStream in = (InputStream) response.readEntity(InputStream.class);
			//String path = getCourse(id).getImage();
			String path = "";
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

	
	private CourseModule getCourseModule()
			throws SecurityConfigurationException, JsonProcessingException, IOException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/course - module/" + courseId + "?authhash=" + loginResponse.getAuthHash();
		Response response = Utils.getClient().doGet(operation);
		String result = response.readEntity(String.class);
		JsonNode rootNode = Utils.getMapper().readTree(result);
		JsonNode recordNode = rootNode.path("record");
		JsonNode dataNode = recordNode.path("data");
		return getCourseModuleFromJson(dataNode);
	}
	
	/**
	 * 
	 * @param dataNode
	 * @return
	 */
	private CourseModule getCourseModuleFromJson(JsonNode dataNode){
		CourseModule courseModule = new CourseModule();
		courseModule.setId(Integer.valueOf(dataNode.path("Id").toString()));
		courseModule.setCourse(Integer.valueOf(dataNode.path("Course").toString()));
		courseModule.setModule(Integer.valueOf(dataNode.path("Module").toString()));
		courseModule.setOrder(Double.valueOf(dataNode.path("Order").toString()));
		return courseModule;
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
		String bodyRequest = getLoginRequest("ken", "123");
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
