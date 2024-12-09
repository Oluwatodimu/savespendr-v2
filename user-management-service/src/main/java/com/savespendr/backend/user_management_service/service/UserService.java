package com.savespendr.backend.user_management_service.service;

import com.savespendr.backend.user_management_service.data.dto.request.UpdatePasswordRequest;
import com.savespendr.backend.user_management_service.data.dto.request.UserSignupRequest;

public interface UserService {

    void registerNormalUser(UserSignupRequest signupRequest);

    String registerMerchantUser(UserSignupRequest signupRequest);

    void resetPassword(String username);

    void updatePassword(String userId, UpdatePasswordRequest request);
}
