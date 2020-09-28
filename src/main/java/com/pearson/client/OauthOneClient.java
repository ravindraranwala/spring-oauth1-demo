package com.pearson.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

class OauthOneClient {
	static final String CLIENT_ID = "3a4393c3da1a4e316ee66c0cc61c71";
	static final String CLIENT_SECRET = "fe1372c074185b19c309964812bb8f3f2256ba514aea8a318";

	public static void main(String[] args) throws MalformedURLException {
		final BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
		resourceDetails.setConsumerKey(CLIENT_ID);
		resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(CLIENT_SECRET));

		// Send oauth credentials inside HTTP headers.
		final RestTemplate restClient = new OAuthRestTemplate(resourceDetails);
		String requestUrl = "http://localhost:8080/person";
		String response = restClient.getForObject(requestUrl, String.class);
		System.out.println(response);

		// Send oauth credentials as form parameters.
		final OauthConsumerSupportWrapper consumerSupport = new OauthConsumerSupportWrapper();
		final String httpMethod = "POST";
		final Map<String, Set<CharSequence>> oAuthParameters = consumerSupport.loadOAuthParameters(resourceDetails,
				new URL(requestUrl), null, httpMethod, null);
		System.out.println(oAuthParameters);

		final MultiValueMap<String, CharSequence> map = new LinkedMultiValueMap<>();
		for (Map.Entry<String, Set<CharSequence>> entry : oAuthParameters.entrySet())
			map.put(entry.getKey(), new ArrayList<>(entry.getValue()));

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		final HttpEntity<MultiValueMap<String, CharSequence>> request = new HttpEntity<>(map, headers);

		final RestTemplate restTmpl = new RestTemplate();
		String formResponse = restTmpl.postForObject("http://localhost:8080/person", request, String.class);
		System.out.println(formResponse);

	}
}
