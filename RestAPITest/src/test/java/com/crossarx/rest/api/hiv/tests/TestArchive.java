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
import com.crossarx.rest.api.hiv.entities.Chapter;
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

public class TestArchive {

	private LoginResponse loginResponse;
	private final Integer courseId = 18;
	private final String language = "en";
	private final Integer id = 162;

	@Before
	public void setUp() throws Exception {
		loginResponse = getLoginResponse();
	}
	
	@Test
	public void testArchiveOutput() {
		try {
			System.out.println(getArchive());
		} catch (SecurityConfigurationException | JsonProcessingException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetArchive() {
		try {
			Assert.assertTrue(getArchive().getId() == this.id);
		} catch (IOException | SecurityConfigurationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetArchiveQueryOK() {
		// /engine/api/VERSION/query?querydef={}&authhash=<hash>
		try {
			Query query = makeTestArchiveQueryOK();
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
			String id = dataNode.path("Id").toString();
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
	private Query makeTestArchiveQueryOK() {
		
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

		queryRequest.setClassName("Archive");

		FilterNode filter = new FilterNode();
		filter.setOperator("and");
		List<Filter> children = new ArrayList<>();
		FilterLeaf leaf1 = new FilterLeaf();
		leaf1.setField("Courses");
		leaf1.setOperator("equals");
		leaf1.setValue(this.courseId);
		children.add(leaf1);
		
		filter.setChildren(children);
		queryRequest.setFilter(filter);

		List<String> resultFields = new ArrayList<>();
		resultFields.add("Id");
		resultFields.add("Name");
		queryRequest.setResultFields(resultFields);

		return queryRequest;
	}

	//@Test
	public void testArchiveImageDownload() {
		// URI: /engine/api/<version>/download/<class>/<id>/Image
		String operation = "download/Archive/" + this.id + "/Image?authhash=" + loginResponse.getAuthHash();
		try {
			Response response = Utils.getClient().doGet(operation);
			InputStream in = (InputStream) response.readEntity(InputStream.class);
			//String path = getArchive(id).getImage();
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

	
	private Chapter getArchive()
			throws SecurityConfigurationException, JsonProcessingException, IOException {
		// Operation:/record/<class>/<id>?authhash=<hash>
		String operation = "record/Archive/" + this.id + "?authhash=" + loginResponse.getAuthHash();
		Response response = Utils.getClient().doGet(operation);
		String result = response.readEntity(String.class);
		System.out.println(result);
		JsonNode rootNode = Utils.getMapper().readTree(result);
		JsonNode recordNode = rootNode.path("record");
		JsonNode dataNode = recordNode.path("data");
		return getArchiveFromJson(dataNode);
	}
	
	/**
	 * 
	 * @param dataNode
	 * @return
	 */
	private Chapter getArchiveFromJson(JsonNode dataNode){
		System.out.println(dataNode);
		Chapter chapter = new Chapter();
//		Iterator<JsonNode> it = dataNode.path("available_languages").iterator();
//		List<String> languages = new ArrayList<>();
//		while(it.hasNext()){
//			String value = it.next().asText(); 
//			languages.add(value);
//		}
//		course.setAvailable_languages(languages);
//		course.setBezocht(Integer.valueOf(dataNode.path("bezocht").toString()));
//		course.setCss_class(dataNode.path("css_class").toString());
//		course.setDescription(dataNode.path("description").toString());
//		course.setDownload_completed(Boolean.valueOf(dataNode.path("download_completed").toString()));
//		course.setFree(Boolean.valueOf(dataNode.path("free").toString()));
//		course.setId(Integer.valueOf(dataNode.path("id").toString()));
//		course.setIntroduction(dataNode.path("introduction").toString());
//		course.setName(dataNode.path("name").toString());
//		course.setObjective(dataNode.path("objective").toString());
//		course.setStatus(dataNode.path("status").toString());
//		course.setWebdisplay(Boolean.valueOf(dataNode.path("webdisplay").toString()));
		return chapter;
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
