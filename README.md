# spring-oauth1-demo
oauth 0-legged provider and comsumer using spring security. Consumer sign the request using the given clientId and secret and the provider verifies that signature. We use 0 legged oauth flow since it has minimal impact on performance. Calling endpoints and generating tokens would be costly for our usecase since it is a frequently used rest api endpoint.
