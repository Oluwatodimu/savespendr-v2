package com.savespendr.backend.merchantservice.service.api;

import com.savespendr.backend.merchantservice.data.dto.BaseResponse;
import com.savespendr.backend.merchantservice.data.dto.request.UserSignupRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-management-service")
public interface UserServiceApi {

    @PostMapping(path = "/users/merchant-signup")
    BaseResponse<String> createMerchantUser(@RequestBody @Valid UserSignupRequest request);
}
