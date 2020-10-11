package com.pearson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth.provider.DefaultAuthenticationHandler;
import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;
import org.springframework.security.oauth.provider.filter.CoreOAuthProviderSupport;
import org.springframework.security.oauth.provider.nonce.InMemoryNonceServices;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.pearson.oauth.ConsumerDetailsInMemoryStorageStrategy;
import com.pearson.oauth.OAuthConsumerDetailsService;
import com.pearson.oauth.filter.OAuthProviderProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CoreOAuthProviderSupport oauthProviderSupport = new CoreOAuthProviderSupport();
		oauthProviderSupport.setBaseUrl("https://socket-dev5.pearsoned.com");
		http.antMatcher("/**")
				.addFilterAfter(
						new OAuthProviderProcessingFilter(
								new OAuthConsumerDetailsService(new ConsumerDetailsInMemoryStorageStrategy()),
								new InMemoryNonceServices(), new OAuthProcessingFilterEntryPoint(),
								new DefaultAuthenticationHandler(), new InMemoryProviderTokenServices(), oauthProviderSupport),
						BasicAuthenticationFilter.class)
				.authorizeRequests().anyRequest().authenticated().and().csrf().disable();

	}
}
