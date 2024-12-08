package com.savespendr.backend.user_management_service.service.impl;

import com.savespendr.backend.user_management_service.data.dto.request.UserSignupRequest;
import com.savespendr.backend.user_management_service.exception.KeycloakException;
import com.savespendr.backend.user_management_service.service.UserService;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    @Value("${app.keycloak.realm}")
    private String realm;

    @Value("${app.keycloak.groups.user-group-id}")
    private String userGroupId;

    private final Keycloak keycloak;

    @Autowired
    public UserServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public void registerNormalUser(UserSignupRequest signupRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(signupRequest.getFirstName().toLowerCase(Locale.ROOT));
        userRepresentation.setLastName(signupRequest.getLastName().toLowerCase(Locale.ROOT));
        userRepresentation.setUsername(signupRequest.getUsername().toLowerCase(Locale.ROOT));
        userRepresentation.setEmail(signupRequest.getEmail().toLowerCase(Locale.ROOT));
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(signupRequest.getPassword());
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(List.of(credentialRepresentation));

        Response response = usersResource().create(userRepresentation);

        if (response.getStatus() != 201) {
            throw new KeycloakException("user creation failed");
        }

        String location = response.getHeaderString("Location");
        String userId = location.substring(location.lastIndexOf('/') + 1);
        usersResource().get(userId).joinGroup(userGroupId);

        // send welcome mail for verifying email
        // create wallets
    }

    private UsersResource usersResource() {
        return keycloak.realm(realm).users();
    }
}
