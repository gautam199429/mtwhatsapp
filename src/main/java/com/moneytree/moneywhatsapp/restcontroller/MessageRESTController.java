package com.moneytree.moneywhatsapp.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.moneytree.moneywhatsapp.requestbody.APIMessageRequestBody;
import com.moneytree.moneywhatsapp.responsebody.APIResponse;
import com.moneytree.moneywhatsapp.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
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


    @GetMapping("/webhook")
    @Operation(
        summary = "WhatsApp Webhook Verification",
        description = "Endpoint for WhatsApp webhook verification. Responds with the challenge parameter if the verify token matches."
    )
    public String webHookGET(@RequestParam(name = "hub.mode") String hub_mode,
                             @RequestParam(name = "hub.challenge") String hub_challenge,
                             @RequestParam(name = "hub.verify_token") String hub_verify_token){
        return hub_challenge;
    }


    @PostMapping("/webhook")
    @Operation(
        summary = "Receive WhatsApp Webhook Events",
        description = "Endpoint to receive webhook events from WhatsApp. Accepts and acknowledges incoming webhook POST requests."
    )
    public ResponseEntity<APIResponse> receivedWebHookPOST(@RequestBody String body){
        return new ResponseEntity<>(new APIResponse("Message processing started in background.", body), HttpStatus.ACCEPTED);
    }
}
