package com.moneytree.moneywhatsapp.service;

import org.springframework.http.ResponseEntity;
import com.moneytree.moneywhatsapp.requestbody.APIMessageRequestBody;
import com.moneytree.moneywhatsapp.responsebody.APIResponse;

public interface MessageService {

    ResponseEntity<APIResponse> sendMessage(APIMessageRequestBody messageRequest);
}
