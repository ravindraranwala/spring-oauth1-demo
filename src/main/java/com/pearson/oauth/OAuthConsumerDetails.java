package com.pearson.oauth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.common.signature.SignatureSecret;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ExtraTrustConsumerDetails;

/**
 * A skeletal implementation of the {@link ConsumerDetails} interface provided
 * by oauth library. For most of the clients using the default behavior, this
 * implementation would be suffice and are encouraged to use this. For others,
 * looking for some specific behavior, will have to write one similar to this by
 * their own according to their needs.
 * 
 * @author ravindra
 *
 */
public final class OAuthConsumerDetails implements ExtraTrustConsumerDetails {
	private static final long serialVersionUID = 234098243823485285L;
	private final String consumerName;
	private final String consumerKey;
	private final SignatureSecret signatureSecret;
	private final List<GrantedAuthority> authorities;
	private final boolean authTokenRequired;

	/**
	 * Constructs a new instance of {@link OAuthConsumerDetails} with the given data
	 * in it.
	 * 
	 * @param consumerName    The name of the consumer
	 * @param consumerKey     The consumer key.
	 * @param signatureSecret The signature secret.
	 * @param authorities     the authorities that are granted to the OAuth
	 *                        consumer.
	 * @param tokenRequired   Whether this consumer is required to obtain an
	 *                        authenticated oauth token.
	 */
	public OAuthConsumerDetails(String consumerName, String consumerKey, String signatureSecret,
			List<GrantedAuthority> authorities, boolean tokenRequired) {
		this.consumerName = consumerName;
		this.consumerKey = consumerKey;
		this.signatureSecret = new SharedConsumerSecretImpl(signatureSecret);
		this.authorities = new ArrayList<>(authorities);
		this.authTokenRequired = tokenRequired;
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
		return authTokenRequired;
	}
}
