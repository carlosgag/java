package com.crossmarx.rest.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class RESTClient {

	private Client client;
	
	public RESTClient() throws SecurityConfigurationException{
		setVerifier();
		client = Client.create(getConfig());
		
	}
	
	private InputStream getCertificateFromFile() throws SecurityConfigurationException{

		InputStream in;
		try {
			in = getClass().getClassLoader().getResourceAsStream(Config.CERTIFICATE_FILE);
		int data = in.read();
		StringBuilder sb = new StringBuilder();
		while (data != -1) {
			sb.append((char) data);

			data = in.read();
		}
		in.close();
		byte[] bytes = sb.toString().getBytes();
		InputStream transformed = new ByteArrayInputStream(bytes);
		transformed.close();
		return transformed;
		} catch (IOException e) {
			throw new SecurityConfigurationException(e.getMessage(), e.getCause());
		}
	}

	private X509Certificate getCertificate() throws SecurityConfigurationException {

		try {

			CertificateFactory cf = CertificateFactory.getInstance(Config.CERTIFICATE_TYPE);
			X509Certificate caCert = (X509Certificate) cf.generateCertificate(getCertificateFromFile());
			return caCert;
		} catch (CertificateException e) {
			throw new SecurityConfigurationException(e.getMessage(), e.getCause());
		}
	}

	private void setVerifier() {

		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession sslSession) {
				if (hostname.equals(Config.HOSTNAME)) {
					return true;
				}
				return false;
			}
		});
	}

	private SSLContext getSSLContext() throws SecurityConfigurationException {

		TrustManagerFactory tmf;
		try {
			tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			// You don't need the KeyStore instance to come from a file.
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(null);
			ks.setCertificateEntry("caCert", getCertificate());
			tmf.init(ks);

			SSLContext sslContext = SSLContext.getInstance(Config.SECURE_PROTOCOL);
			sslContext.init(null, tmf.getTrustManagers(), null);
			return sslContext;
		} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
			throw new SecurityConfigurationException(e.getMessage(), e.getCause());
		} catch (CertificateException | IOException e) {
			throw new SecurityConfigurationException(e.getMessage(), e.getCause());
		}
	}
	
	private ClientConfig getConfig() throws SecurityConfigurationException{
		ClientConfig config = new DefaultClientConfig();
		HTTPSProperties httpsProperties = new HTTPSProperties(null, getSSLContext());
		config.getProperties()
			.put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, httpsProperties);
		config.getFeatures()
			.put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		return config;
	}

	public ClientResponse doPost(String requestBody, String operation){
		WebResource webResource = client.resource(Config.URL + "/" + operation);
		ClientResponse response = webResource.accept(Config.RESPONSE_MIME_ACCEPTED).
				post(ClientResponse.class, requestBody);
		return response;
	}

	public ClientResponse doPut(String requestBody, String operation){
		WebResource webResource = client.resource(Config.URL + "/" + operation);
		ClientResponse response = webResource.accept(Config.RESPONSE_MIME_ACCEPTED).
				put(ClientResponse.class, requestBody);
		return response;
	}
	
	public ClientResponse doGet(String operation){
		WebResource webResource = client.resource(Config.URL + "/" + operation);
		ClientResponse response = webResource.accept(Config.RESPONSE_MIME_ACCEPTED)
				.get(ClientResponse.class);
		return response;
	}
}
