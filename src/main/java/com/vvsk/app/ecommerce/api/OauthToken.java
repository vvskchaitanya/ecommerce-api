package com.vvsk.app.ecommerce.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthToken {

	@Autowired
	TokenEndpoint endpoint;

	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@GetMapping("/token")
	public ResponseEntity<OAuth2AccessToken> token(@RequestParam String clientId, @RequestParam String clientSecret,
			@RequestParam String username, @RequestParam String password)
			throws HttpRequestMethodNotSupportedException {
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(clientId,
				clientSecret, AuthorityUtils.NO_AUTHORITIES);
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		params.put("grant_type", "password");
		return endpoint.postAccessToken(authRequest, params);
	}

}
