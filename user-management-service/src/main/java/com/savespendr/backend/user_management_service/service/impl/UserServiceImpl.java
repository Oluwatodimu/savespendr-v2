package com.savespendr.backend.user_management_service.service.impl;

import com.savespendr.backend.user_management_service.controller.UserController;
import com.savespendr.backend.user_management_service.data.dto.request.UpdatePasswordRequest;
import com.savespendr.backend.user_management_service.data.dto.request.UserSignupRequest;
import com.savespendr.backend.user_management_service.exception.KeycloakException;
import com.savespendr.backend.user_management_service.service.UserService;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Value("${app.keycloak.realm}")
    private String realm;

    @Value("${app.keycloak.groups.user-group-id}")
    private String userGroupId;

    @Value("${app.keycloak.groups.merchant-group-id}")
    private String merchantGroupId;

    private final Keycloak keycloak;

    @Autowired
    public UserServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public void registerNormalUser(UserSignupRequest signupRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(signupRequest.getFirstName().toLowerCase(Locale.ROOT).strip());
        userRepresentation.setLastName(signupRequest.getLastName().toLowerCase(Locale.ROOT).strip());
        userRepresentation.setUsername(signupRequest.getUsername().toLowerCase(Locale.ROOT).strip());
        userRepresentation.setEmail(signupRequest.getEmail().toLowerCase(Locale.ROOT).strip());
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(signupRequest.getPassword().strip());
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(List.of(credentialRepresentation));

        Response response = getUsersResource().create(userRepresentation);

        if (response.getStatus() != 201) {
            log.error("user creation failed: {}", response.getStatus());
            throw new KeycloakException(String.format("user creation failed with code: %s", response.getStatus()));
        }

        String location = response.getHeaderString("Location");
        String userId = location.substring(location.lastIndexOf('/') + 1);
        getUsersResource().get(userId).joinGroup(userGroupId);
        getUsersResource().get(userId).sendVerifyEmail();
    }

    @Override
    public String registerMerchantUser(UserSignupRequest signupRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(signupRequest.getFirstName().toLowerCase(Locale.ROOT).strip());
        userRepresentation.setLastName(signupRequest.getLastName().toLowerCase(Locale.ROOT).strip());
        userRepresentation.setUsername(signupRequest.getUsername().toLowerCase(Locale.ROOT).strip());
        userRepresentation.setEmail(signupRequest.getEmail().toLowerCase(Locale.ROOT).strip());
        userRepresentation.setEmailVerified(true);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(signupRequest.getPassword().strip());
        credentialRepresentation.setTemporary(true);
        userRepresentation.setCredentials(List.of(credentialRepresentation));
        userRepresentation.setRequiredActions(List.of("UPDATE_PASSWORD"));

        Response response = getUsersResource().create(userRepresentation);

        if (response.getStatus() != 201) {
            log.error("merchant creation failed: {}", response.getStatus());
            throw new KeycloakException(String.format("user creation failed with code: %s", response.getStatus()));
        }

        String location = response.getHeaderString("Location");
        String userId = location.substring(location.lastIndexOf('/') + 1);
        getUsersResource().get(userId).joinGroup(merchantGroupId);
        getUsersResource().get(userId).executeActionsEmail(List.of("UPDATE_PASSWORD"));
        return userId;
    }

    @Override
    public void resetPassword(String username) {
        UserRepresentation user = getUsersResource()
                .searchByUsername(username, true)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("user with username: %s not found", username)));
        getUsersResource().get(user.getId()).executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }

    @Override
    public void updatePassword(String userId, UpdatePasswordRequest request) {
        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
            throw new BadRequestException("passwords have to match");
        }

        UserResource userResource = getUsersResource().get(userId);
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(request.getConfirmPassword());
        credentialRepresentation.setTemporary(false);
        userResource.resetPassword(credentialRepresentation);
    }

    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }
}
