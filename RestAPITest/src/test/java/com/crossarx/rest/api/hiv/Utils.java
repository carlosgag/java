package com.crossarx.rest.api.hiv;

import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author usuario
 *
 */
public class Utils {
	private static ObjectMapper mapper;
	private static RESTClient restClient;

	/**
	 * 
	 * @throws SecurityConfigurationException
	 */
	private Utils() throws SecurityConfigurationException {
		mapper = new ObjectMapper();
		restClient = new RESTClient();
	}

	/**
	 * 
	 * @return
	 */
	public static ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		return mapper;
	}

	/**
	 * 
	 * @return
	 * @throws SecurityConfigurationException
	 */
	public static RESTClient getClient() throws SecurityConfigurationException {
		if (restClient == null) {
			restClient = new RESTClient();
		}
		return restClient;
	}
}
