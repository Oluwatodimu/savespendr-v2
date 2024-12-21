package com.savespendr.backend.merchantservice.security;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceTokenService {

    private final String clientId;

    private final String clientSecret;

    private final String realm;

    private final String serverUrl;

    private final Client client;

    @Autowired
    public ServiceTokenService(
            @Value("${app.keycloak.service.client-id}") String clientId,
            @Value("${app.keycloak.service.client-secret}") String clientSecret,
            @Value("${app.keycloak.realm}") String realm,
            @Value("${app.keycloak.server-url}") String serverUrl,
            Client client) {

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.realm = realm;
        this.serverUrl = serverUrl;
        this.client = client;
    }

    public String getAccessToken() {
        Form form = new Form();
        form.param("client_id", clientId);
        form.param("grant_type", "client_credentials");
        form.param("client_secret", clientSecret);

        Response response = client.target(serverUrl)
                .path(String.format("realms/%s/protocol/openid-connect/token", realm))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new WebApplicationException("Failed to fetch token: " + response.getStatus());
        }

        Map<String, Object> responseBody = response.readEntity(new GenericType<>() {});
        return (String) responseBody.get("access_token");
    }
}
