package com.savespendr.backend.merchantservice.config;

import com.savespendr.backend.merchantservice.security.ServiceTokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeignClientConfig implements RequestInterceptor {

    private final ServiceTokenService serviceTokenService;

    @Autowired
    public FeignClientConfig(ServiceTokenService serviceTokenService) {
        this.serviceTokenService = serviceTokenService;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String serviceAccessToken = serviceTokenService.getAccessToken();
        requestTemplate.header("Authorization", String.format("Bearer %s", serviceAccessToken));
    }
}
