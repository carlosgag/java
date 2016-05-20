package com.crossarx.rest.api.hiv;

public class Config {
	
	public static final String LOCAL_PATH = "D:\\";
	public static final String CERTIFICATE_TYPE = "X.509";
	public static final String APP = "hiv";
	//public static final String ENVIRONMENT = "hef";
	public static final String VERSION = "dingo";
	public static final String PROTOCOL = "https";
	public static final String HOSTNAME = APP + "." + /*" + ENVIRONMENT + ".*/"crossmarx.nl";
	public static final String URL = PROTOCOL + "://" + HOSTNAME + "/engine/api/" + VERSION;
	public static final String CERTIFICATE_FILE = "crossmarx.cer";
	public static final String RESPONSE_MIME_ACCEPTED = "application/json,application/octet-stream";
	public static final String SECURE_PROTOCOL = "TLS";
	
}
