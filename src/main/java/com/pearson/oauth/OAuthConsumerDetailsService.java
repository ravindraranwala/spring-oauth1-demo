package com.pearson.oauth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.common.signature.SignatureSecret;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.ExtraTrustConsumerDetails;

public final class OAuthConsumerDetailsService implements ConsumerDetailsService {
	private final String consumerName = "John";
	private final String consumerKey = "3a4393c3da1a4e316ee66c0cc61c71";
	private final String consumerSecret = "fe1372c074185b19c309964812bb8f3f2256ba514aea8a318";

	@Override
	public ConsumerDetails loadConsumerByConsumerKey(String consumerKey) throws OAuthException {
		if (consumerKey == null)
			throw new OAuthException("No credentials found for the consumer key: " + consumerKey);

		if (!consumerKey.equals(this.consumerKey))
			throw new OAuthException("No credentials found for the consumer key: " + consumerKey);

		// final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("OAUTH"));

		final List<GrantedAuthority> authorities = Collections.emptyList();
		return new OAuthConsumerDetails(consumerName, consumerKey, consumerSecret, authorities);
	}
	
	static class OAuthConsumerDetails implements ExtraTrustConsumerDetails {
		private static final long serialVersionUID = 234098243823485285L;
		private final String consumerName;
		private final String consumerKey;
		private final SignatureSecret signatureSecret;
		private final List<GrantedAuthority> authorities;

		public OAuthConsumerDetails(String consumerName, String consumerKey, String signatureSecret,
				List<GrantedAuthority> authorities) {
			this.consumerName = consumerName;
			this.consumerKey = consumerKey;
			this.signatureSecret = new SharedConsumerSecretImpl(signatureSecret);
			this.authorities = new ArrayList<>(authorities);
		}

		@Override
		public String getConsumerKey() {
			return consumerKey;
		}

		@Override
		public String getConsumerName() {
			return consumerName;
		}

		@Override
		public SignatureSecret getSignatureSecret() {
			return signatureSecret;
		}

		@Override
		public List<GrantedAuthority> getAuthorities() {
			return Collections.unmodifiableList(authorities);
		}

		@Override
		public boolean isRequiredToObtainAuthenticatedToken() {
			return false;
		}
	}
}
