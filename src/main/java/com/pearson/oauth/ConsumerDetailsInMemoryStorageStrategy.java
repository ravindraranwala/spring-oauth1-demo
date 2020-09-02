package com.pearson.oauth;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth.provider.ConsumerDetails;

public class ConsumerDetailsInMemoryStorageStrategy implements ConsumerDetailsStorageStrategy {
	private static final String consumerName = "John";
	private static final String consumerKey = "3a4393c3da1a4e316ee66c0cc61c71";
	private static final String consumerSecret = "fe1372c074185b19c309964812bb8f3f2256ba514aea8a318";
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
