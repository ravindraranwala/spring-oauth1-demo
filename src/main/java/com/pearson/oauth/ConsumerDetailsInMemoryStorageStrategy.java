package com.pearson.oauth;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth.provider.ConsumerDetails;

public class ConsumerDetailsInMemoryStorageStrategy implements ConsumerDetailsStorageStrategy {
	private static final String consumerName = "John";
	private static final String consumerKey = "10022d8f-b47b-4de9-921c-b98f703da9fd";
	private static final String consumerSecret = "wablwQBF";
	private static final List<GrantedAuthority> authorities = Collections.emptyList();
	private static final ConsumerDetails consumerDetails = new OAuthConsumerDetails(consumerName, consumerKey,
			consumerSecret, authorities, false);

	@Override
	public ConsumerDetails loadConsumerByKey(String consumerKey) {
		if (this.consumerKey.equals(consumerKey))
			return consumerDetails;
		return null;
	}

}
