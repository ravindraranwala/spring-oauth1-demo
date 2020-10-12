package com.pearson.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

class OauthOneClient {
	static final String CLIENT_ID = "10022d8f-b47b-4de9-921c-b98f703da9fd";
	static final String CLIENT_SECRET = "wablwQBF";

	public static void main(String[] args) throws MalformedURLException {
		final BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
		resourceDetails.setConsumerKey(CLIENT_ID);
		resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(CLIENT_SECRET));

		// Send oauth credentials inside HTTP headers.
		final RestTemplate restClient = new OAuthRestTemplate(resourceDetails);
		String requestUrl = "http://socket-dev5.pearsoned.com/person";
		String response = restClient.getForObject(requestUrl, String.class);
		System.out.println(response);

		// Send oauth credentials as form parameters.
		final OauthConsumerSupportWrapper consumerSupport = new OauthConsumerSupportWrapper();
		final String newUrl = new StringBuilder(requestUrl).append("?name=bloch").toString();
		final Map<String, Set<CharSequence>> oAuthParameters = consumerSupport.loadOAuthParameters(resourceDetails,
				new URL(newUrl), null, HttpMethod.POST.toString(), null);
		System.out.println(oAuthParameters);

		final MultiValueMap<String, CharSequence> map = new LinkedMultiValueMap<>();
		for (Map.Entry<String, Set<CharSequence>> entry : oAuthParameters.entrySet())
			map.put(entry.getKey(), new ArrayList<>(entry.getValue()));

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		final HttpEntity<MultiValueMap<String, CharSequence>> request = new HttpEntity<>(map, headers);

		final RestTemplate restTmpl = new RestTemplate();
		String formResponse = restTmpl.postForObject(newUrl, request, String.class);
		System.out.println(formResponse);

	}
}
