package com.pearson.oauth;

import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;

/**
 * A service that provides the details about an oauth consumer.
 * 
 * @author ravindra
 *
 */
public final class OAuthConsumerDetailsService implements ConsumerDetailsService {
	private final ConsumerDetailsStorageStrategy consumerDetailsStorageStrategy;

	/**
	 * Constructs an instance of {@link OAuthConsumerDetailsService} with the given
	 * storage strategy instance.
	 * 
	 * @param consumerDetailsStorageStrategy an implementation of the consumer
	 *                                       details storage strategy. It's the
	 *                                       responsibility of the client to
	 *                                       implement this.
	 */
	public OAuthConsumerDetailsService(ConsumerDetailsStorageStrategy consumerDetailsStorageStrategy) {
		this.consumerDetailsStorageStrategy = consumerDetailsStorageStrategy;
	}

	@Override
	public ConsumerDetails loadConsumerByConsumerKey(String consumerKey) throws OAuthException {
		final ConsumerDetails consumerDetails = consumerDetailsStorageStrategy.loadConsumerByKey(consumerKey);
		if (consumerDetails == null)
			throw new OAuthException("No credentials found for the consumer key: " + consumerKey);

		return consumerDetails;
	}

}
