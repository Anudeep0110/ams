package com.example.ams.security;

import com.example.ams.models.OAuthClients;
import com.example.ams.repo.OAuthClientsRepo;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class RegisteredClientRepositoryForSecurity implements RegisteredClientRepository {
    private final OAuthClientsRepo oauthrepo;

    public RegisteredClientRepositoryForSecurity(OAuthClientsRepo repo) {
        this.oauthrepo = repo;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        List<OAuthClients> clients = oauthrepo.findByClientId(clientId);

        if (clients.isEmpty()) {
            return null;
        }
        OAuthClients client = clients.getFirst();
        return RegisteredClient.withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                        .accessTokenTimeToLive(Duration.ofHours(1))
                        .build())
                .build();
    }

    @Override
    public RegisteredClient findById(String clientId) {
        return null;    }

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException();
    }

}
