package com.vvsk.app.ecommerce.config;

import java.util.Arrays;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.vvsk.app.ecommerce.entity.Client;
import com.vvsk.app.ecommerce.repository.ClientRepository;

@Service("clientDetails")
public class ClientDetailsServiceImplementation implements ClientDetailsService {
	@Autowired
	ClientRepository clientRepository;

	@PostConstruct
	public void addClients() {
		if (!clientRepository.findById("ecommerce").isPresent()) {
			Client client = new Client();
			client.setId("ecommerce");
			client.setSecret("ecommerce");
			client.setGrantTypes("password,refresh_token,client_credentials");
			client.setScope("read,write");
			client.setAccessTokenValidity(900);
			client.setRefreshTokenValidity(1800);
			clientRepository.save(client);
		}
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Optional<Client> client = clientRepository.findById(clientId);
		if (client.isPresent()) {
			BaseClientDetails details = new BaseClientDetails();
			details.setClientId(client.get().getId());
			details.setClientSecret(client.get().getSecret());
			details.setAuthorizedGrantTypes(Arrays.asList(client.get().getGrantTypes().split(",")));
			details.setScope(Arrays.asList(client.get().getScope().split(",")));
			details.setAuthorities(AuthorityUtils.NO_AUTHORITIES);
			details.setAccessTokenValiditySeconds(client.get().getAccessTokenValidity());
			details.setRefreshTokenValiditySeconds(client.get().getRefreshTokenValidity());
			return details;
		} else
			throw new ClientRegistrationException("Client Details not found");

	}

}
