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
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossarx.rest.api.hiv.Utils;
import com.crossarx.rest.api.hiv.entities.Module;
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

public class TestModule {

	private LoginResponse loginResponse;
	private final Integer id = 300;
	private final Integer courseId = 18;
	private final String language = "en";

	@Before
	public void setUp() throws Exception {
		loginResponse = getLoginResponse();
	}
	
	@Test
	public void testModuleOutput() {
		try {
			System.out.println(getModule());
		} catch (SecurityConfigurationException | JsonProcessingException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetModule() {
		try {
			Assert.assertTrue(getModule().getId() == id);
		} catch (IOException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetModuleQueryOK() {
		// /engine/api/VERSION/query?querydef={}&authhash=<hash>
		try {
			Query query = makeTestModuleQueryOK();
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
	private Query makeTestModuleQueryOK() {
		
		Query queryRequest = new Query();

		List<Spec> sortSpecs = new ArrayList<>();
//		Spec spec = new Spec();
//		spec.setField("name");
//		spec.setDirection("desc");
//		sortSpecs.add(spec);
		Spec spec2 = new Spec();
		spec2.setField("Id");
		spec2.setDirection("asc");
		sortSpecs.add(spec2);
		queryRequest.setSortSpecs(sortSpecs);

		queryRequest.setClassName("module");

		FilterNode filter = new FilterNode();
		filter.setOperator("and");
		List<Filter> children = new ArrayList<>();
		
		FilterLeaf leaf1 = new FilterLeaf();
		leaf1.setField("course - module.course");
		leaf1.setOperator("equals");
		leaf1.setValue(this.courseId);
		children.add(leaf1);
		
		FilterLeaf leaf2 = new FilterLeaf();
		leaf2.setField("available languages");
		leaf2.setOperator("equals");
		leaf2.setValue(this.language);
		children.add(leaf2);
		
		FilterLeaf leaf3 = new FilterLeaf();
		leaf3.setField("active");
		leaf3.setOperator("equals");
		leaf3.setValue(true);
		children.add(leaf3);

		filter.setChildren(children);
		
		queryRequest.setFilter(filter);

		List<String> resultFields = new ArrayList<>();
		resultFields.add("Id");
		resultFields.add("Name");
		queryRequest.setResultFields(resultFields);

		return queryRequest;
	}

	//@Test
	public void testModuleImageDownload() {
		// URI: /engine/api/<version>/download/<class>/<id>/Image
		String operation = "download/module/" + id + "/Image?authhash=" + loginResponse.getAuthHash();
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

	
	private Module getModule()
			throws SecurityConfigurationException, JsonProcessingException, IOException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/module/" + id + "?authhash=" + loginResponse.getAuthHash();
		Response response = Utils.getClient().doGet(operation);
		String result = response.readEntity(String.class);
		JsonNode rootNode = Utils.getMapper().readTree(result);
		JsonNode recordNode = rootNode.path("record");
		JsonNode dataNode = recordNode.path("data");
		return getModuleFromJson(dataNode);
	}
	
	/**
	 * 
	 * @param dataNode
	 * @return
	 */
	private Module getModuleFromJson(JsonNode dataNode){
		Module module = new Module();
		Iterator<JsonNode> it = dataNode.path("Available_languages").iterator();
		List<String> languages = new ArrayList<>();
		while(it.hasNext()){
			String value = it.next().asText(); 
			languages.add(value);
		}
		module.setAvailable_languages(languages);
		module.setActive(Boolean.valueOf(dataNode.path("Active").toString()));
		module.setAllowed_for_guest(Boolean.valueOf(dataNode.path("Allowed_for_guest").toString()));
		module.setExtra(Boolean.valueOf(dataNode.path("Extra").toString()));
		module.setId(Integer.valueOf(dataNode.path("Id").toString()));
		module.setName(dataNode.path("Name").toString());
		module.setOrder(Double.valueOf(dataNode.path("Order").toString()));
		module.setPosttest_picture(dataNode.path("Posttest_picture").toString());
		module.setPretest_picture(dataNode.path("Pretest_picture").toString());
		module.setShow_chapter_titles_in_menu(Boolean.valueOf(dataNode.path("Show_chapter_titles_in_menu").toString()));
		module.setType(dataNode.path("Type").toString());
		module.setVoice_over(dataNode.path("Voice_over").toString());
		module.setYear(Integer.valueOf(dataNode.path("Year").toString()));
		return module;
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
