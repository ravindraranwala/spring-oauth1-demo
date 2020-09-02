package com.pearson.oauth;

import org.springframework.security.oauth.provider.ConsumerDetails;

/**
 * Defines the mechanism for loading oauth consumer details from a persistence
 * storage. This can be a DB, a file etc. The user of this interface has precise
 * control over how to organize consumer details and store them.
 * 
 * @author ravindra
 *
 */
public interface ConsumerDetailsStorageStrategy {
	/**
	 * Loads the consumer details associated with the given key if there's one.
	 * Otherwise returns <code>null</code>.
	 * 
	 * @param consumerKey consumer key
	 * @return {@link ConsumerDetails} associated with the given key if there's one,
	 *         <code>null</code> otherwise
	 * 
	 */
	ConsumerDetails loadConsumerByKey(String consumerKey);
}
