package com.crossarx.rest.api.hiv;

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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.crossmarx.rest.api.exceptions.SecurityConfigurationException;

public class RESTClient {

	private Client client;
	
	public RESTClient() throws SecurityConfigurationException{
		setVerifier();
		client = ClientBuilder.newBuilder()
				.sslContext(getSSLContext())
				.withConfig(getConfig())
				.build();
		
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
		ClientConfig config = new ClientConfig();
		config.register(MultiPartFeature.class);
		config.register(new JacksonFeature());
		return config;
	}

	public Response doPost(String requestBody, String operation){
		WebTarget webTarget = client.target(Config.URL + "/" + operation);
		Invocation.Builder invocationBuilder =  webTarget.request(Config.RESPONSE_MIME_ACCEPTED);
		return invocationBuilder.post(Entity.json(requestBody),Response.class);
	}

	public Response doPost(MultiPart multiPart, String operation) {
		WebTarget webTarget = client.target(Config.URL + "/" + operation);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		return invocationBuilder.post(Entity.entity(multiPart, multiPart.getMediaType()));
	}
	
	public Response doGet(String operation){
		WebTarget webTarget = client.target(Config.URL + "/" + operation);
		Invocation.Builder invocationBuilder =  webTarget.request(Config.RESPONSE_MIME_ACCEPTED);
		return invocationBuilder.get(Response.class);
	}
}
