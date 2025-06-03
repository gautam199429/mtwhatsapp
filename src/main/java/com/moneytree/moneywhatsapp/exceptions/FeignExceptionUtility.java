package com.moneytree.moneywhatsapp.exceptions;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import com.google.gson.Gson;
import com.moneytree.moneywhatsapp.responsebody.MessageResponse;
import feign.FeignException;

public class FeignExceptionUtility {

    public static String MetaException(FeignException ex){
        Optional<ByteBuffer> responseBodyOpt = ex.responseBody();
        String responseBodyString = responseBodyOpt
                .map(bb -> StandardCharsets.UTF_8.decode(bb).toString())
                .orElse("");
        MessageResponse body = new Gson().fromJson(responseBodyString, MessageResponse.class);
        return body.getError().getError_data().getDetails();
    }
}
