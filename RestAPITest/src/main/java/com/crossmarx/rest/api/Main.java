package com.crossmarx.rest.api;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.crossmarx.rest.api.entities.Login;
import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;
import com.crossmarx.rest.api.exceptions.SerializingException;

public class Main {

	private static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws SerializingException, SecurityConfigurationException {

		// if (response.getStatus() != 200) {
		// throw new RuntimeException("Failed : HTTP error code : " +
		// response.getStatus());
		// }
	}
	
	private static ObjectMapper getMapper() {
		return mapper;
	}	

	public static  String getLoginRequest() throws SerializingException {
		Login login = new Login("carlos", "oceanHouse1");
		try {
			return getMapper().writeValueAsString(login);
		} catch (IOException e) {
			throw new SerializingException();
		}
	}

}
