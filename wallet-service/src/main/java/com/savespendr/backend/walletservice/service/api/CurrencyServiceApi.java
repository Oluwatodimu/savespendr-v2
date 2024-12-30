package com.savespendr.backend.walletservice.service.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "currency-service")
public interface CurrencyServiceApi {


}
