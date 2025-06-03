package com.moneytree.moneywhatsapp.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moneytree.moneywhatsapp.requestbody.APIMessageRequestBody;
import com.moneytree.moneywhatsapp.responsebody.APIResponse;
import com.moneytree.moneywhatsapp.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/${spring.application.version}/messages")
@Tag(name = "WhatsApp Messaging", description = "Operations related to WhatsApp message management")
public class MessageRESTController {

    private final MessageService messageService;

    public MessageRESTController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(path = "/send/message",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Send WhatsApp Message",
        description = "Send a message via WhatsApp to the specified recipient."
    )
    public ResponseEntity<APIResponse> sendWhatsAppMessages(
        @RequestBody APIMessageRequestBody data,
        @RequestHeader(name = "X-AUTH-TOKEN") String token) throws Exception{
        return messageService.sendMessage(data);
    }
}
