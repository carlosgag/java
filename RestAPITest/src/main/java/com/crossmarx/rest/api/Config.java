package com.crossmarx.rest.api;

public class Config {
	
	public static final String LOCAL_PATH = "D:\\";
	public static final String CERTIFICATE_TYPE = "X.509";
	public static final String APP = "unittest";
	public static final String ENVIRONMENT = "test";
	public static final String VERSION = "carlos";
	public static final String PROTOCOL = "https";
	public static final String HOSTNAME = APP + "." + ENVIRONMENT + ".crossmarx.nl";
	public static final String URL = PROTOCOL + "://" + HOSTNAME + "/engine/api/" + VERSION;
	public static final String CERTIFICATE_FILE = "crossmarx.cer";
	public static final String RESPONSE_MIME_ACCEPTED = "application/json,application/octet-stream";
	public static final String SECURE_PROTOCOL = "TLS";
	
}
