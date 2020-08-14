package com.pearson.client;

import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.client.RestTemplate;

class OauthOneClient {
	static final String CLIENT_ID = "3a4393c3da1a4e316ee66c0cc61c71";
	static final String CLIENT_SECRET = "fe1372c074185b19c309964812bb8f3f2256ba514aea8a318";

	public static void main(String[] args) {
		final BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
		resourceDetails.setConsumerKey(CLIENT_ID);
		resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(CLIENT_SECRET));

		final RestTemplate restClient = new OAuthRestTemplate(resourceDetails);
		String response = restClient.getForObject("http://localhost:8080/person/1", String.class);
		System.out.println(response);
	}

}
