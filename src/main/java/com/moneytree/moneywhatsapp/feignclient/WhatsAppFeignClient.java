package com.moneytree.moneywhatsapp.feignclient;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.moneytree.moneywhatsapp.configurations.FeignConfiguration;
import com.moneytree.moneywhatsapp.requestbody.MessageRequest;

@FeignClient(name = "whatsapp-feign-client",
url = "${whatsapp.api.url}",
configuration=FeignConfiguration.class)
public interface WhatsAppFeignClient {

    @PostMapping("/{version}/{phoneNumberId}/messages")
    ResponseEntity<String> sendMessage(
        @PathVariable("version") String version,
        @PathVariable("phoneNumberId") String phoneNumberId,
        @RequestHeader Map<String, String> headerMap,
        @RequestBody MessageRequest messageRequest);

}
