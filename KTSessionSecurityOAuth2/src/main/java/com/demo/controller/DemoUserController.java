package com.demo.controller;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userinfo")
public class DemoUserController {

	private final OAuth2AuthorizedClientService auth2AuthorizedClientService;

	public DemoUserController(OAuth2AuthorizedClientService auth2AuthorizedClientService) {
		this.auth2AuthorizedClientService = auth2AuthorizedClientService;
	}
	
	
	
	@GetMapping
	public Map<String,String> userInfo(OAuth2AuthenticationToken oA2Token){
		
		OAuth2AuthorizedClient client = auth2AuthorizedClientService.loadAuthorizedClient(oA2Token.getAuthorizedClientRegistrationId(), 
																							oA2Token.getPrincipal().getName());
		
		OAuth2AccessToken accessToken = client.getAccessToken();
		String tokenValue = accessToken.getTokenValue();
		String issuedAt = accessToken.getIssuedAt().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_DATE_TIME);
		String expiredAt = accessToken.getExpiresAt().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_DATE_TIME);
		String scope = accessToken.getScopes().toString();
		String tokenType = accessToken.getTokenType().getValue();
		
		Map<String,String> response = Map.of("access-token", tokenValue,
											"issued-at", issuedAt,
											"expires-at", expiredAt,
											"scope", scope,
											"type", tokenType);
		return response;
		
	}
	
	
	
}
