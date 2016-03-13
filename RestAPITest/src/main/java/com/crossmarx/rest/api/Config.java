package com.crossmarx.rest.api;

public class Config {
	
	public static String CERTIFICATE_TYPE = "X.509";
	public static String APP = "unittest";
	public static String ENVIRONMENT = "test";
	public static String VERSION = "carlos";
	public static String PROTOCOL = "https";
	public static String HOSTNAME = APP + "." + ENVIRONMENT + ".crossmarx.nl";
	public static String URL = PROTOCOL + "://" + HOSTNAME + "/engine/api/" + VERSION;
	public static String CERTIFICATE_FILE = "crossmarx.cer";
	public static String RESPONSE_MIME_ACCEPTED = "application/json";
	public static String SECURE_PROTOCOL = "TLS";
	
}
