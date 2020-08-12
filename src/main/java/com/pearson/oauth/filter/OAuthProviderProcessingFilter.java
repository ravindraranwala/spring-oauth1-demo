package com.pearson.oauth.filter;

import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.OAuthAuthenticationHandler;
import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.nonce.OAuthNonceServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;

public class OAuthProviderProcessingFilter extends ProtectedResourceProcessingFilter {

	public OAuthProviderProcessingFilter(ConsumerDetailsService oAuthConsumerDetailsService,
			OAuthNonceServices oAuthNonceServices, OAuthProcessingFilterEntryPoint oAuthProcessingFilterEntryPoint,
			OAuthAuthenticationHandler oAuthAuthenticationHandler,
			OAuthProviderTokenServices oAuthProviderTokenServices) {
		super();
		setAuthenticationEntryPoint(oAuthProcessingFilterEntryPoint);
		setAuthHandler(oAuthAuthenticationHandler);
		setConsumerDetailsService(oAuthConsumerDetailsService);
		setNonceServices(oAuthNonceServices);
		setTokenServices(oAuthProviderTokenServices);
		// setIgnoreMissingCredentials(false); // die if OAuth params are not included
	}

}
