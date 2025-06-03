package com.moneytree.moneywhatsapp.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.moneytree.moneywhatsapp.entitymodels.TblSentMessages;
import com.moneytree.moneywhatsapp.enums.ConfigEnums;
import com.moneytree.moneywhatsapp.enums.MessageType;
import com.moneytree.moneywhatsapp.exceptions.FeignExceptionUtility;
import com.moneytree.moneywhatsapp.feignclient.WhatsAppFeignClient;
import com.moneytree.moneywhatsapp.repository.TblConfigurationRepo;
import com.moneytree.moneywhatsapp.repository.TblSentMessagesRepo;
import com.moneytree.moneywhatsapp.requestbody.APIMessageRequestBody;
import com.moneytree.moneywhatsapp.requestbody.MessageDocument;
import com.moneytree.moneywhatsapp.requestbody.MessageRequest;
import com.moneytree.moneywhatsapp.responsebody.APIResponse;
import com.moneytree.moneywhatsapp.service.MessageService;
import feign.FeignException;

@Service
public class MessageServiceImpl implements MessageService {

    private final TblConfigurationRepo tblConfigurationRepo;

    private final WhatsAppFeignClient whatsAppFeignClient;

    private final TblSentMessagesRepo tblSentMessagesRepo;

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public MessageServiceImpl(TblConfigurationRepo tblConfigurationRepo, WhatsAppFeignClient whatsAppFeignClient, TblSentMessagesRepo tblSentMessagesRepo) {
        this.tblConfigurationRepo = tblConfigurationRepo;
        this.whatsAppFeignClient = whatsAppFeignClient;
        this.tblSentMessagesRepo = tblSentMessagesRepo;
    }

    @Override
    public ResponseEntity<APIResponse> sendMessage(APIMessageRequestBody messageRequest) {
        executor.submit(() -> triggerAsyncSend(messageRequest));
        return new ResponseEntity<>(new APIResponse("Message processing started in background.",messageRequest), HttpStatus.ACCEPTED);
    }

    @Cacheable("accessToken")
    private String getToken() {
        return tblConfigurationRepo.findByConfigKey(ConfigEnums.ACCESS_TOKEN).getConfigValue();
    }

    @Cacheable("phoneNumberId")
    private String getPhoneNumberId() {
        return tblConfigurationRepo.findByConfigKey(ConfigEnums.PHONE_NUMBER_ID).getConfigValue();
    }

    @Cacheable("whatsappApiVersion")
    private String getVersion() {
        return tblConfigurationRepo.findByConfigKey(ConfigEnums.WHATSAPP_API_VERSION).getConfigValue();
    }


    private void triggerAsyncSend(APIMessageRequestBody messageRequest) {
        try {
            for(String phoneNumber : messageRequest.getPhoneNumbers()) {
                MessageRequest messageRequestObj = new MessageRequest();
                messageRequestObj.setTo(phoneNumber);
                if(messageRequest.getType().equals(MessageType.DOCUMENT)){
                    messageRequestObj.setType(MessageType.DOCUMENT.getType());
                    MessageDocument messageDocument = new MessageDocument();
                    messageDocument.setLink(messageRequest.getLink());
                    messageDocument.setCaption(messageRequest.getMessage());
                    messageRequestObj.setDocument(messageDocument);
                }
                else if(messageRequest.getType().equals(MessageType.IMAGE)){
                    messageRequestObj.setType(MessageType.IMAGE.getType());
                    MessageDocument messageDocument = new MessageDocument();
                    messageDocument.setLink(messageRequest.getLink());
                    messageDocument.setCaption(messageRequest.getMessage());
                    messageRequestObj.setImage(messageDocument);
                }
                else if(messageRequest.getType().equals(MessageType.TEXT)){
                    messageRequestObj.setType(MessageType.TEXT.getType());
                }
                else if(messageRequest.getType().equals(MessageType.INTERACTIVE)){
                    messageRequestObj.setType(MessageType.INTERACTIVE.getType());
                    MessageDocument messageDocument = new MessageDocument();
                    messageDocument.setText(messageRequest.getMessage());
                    messageRequestObj.setText(messageDocument);
                }
                else {
                    throw new IllegalArgumentException("Unsupported message type: " + messageRequest.getType());
                }
                String responseMessage = sendMessageToWhatsApp(messageRequestObj);
                TblSentMessages sentMessage = new TblSentMessages();
                sentMessage.setPhoneNumber(phoneNumber);
                sentMessage.setMessage(messageRequest.getMessage());
                sentMessage.setType(messageRequest.getType());
                sentMessage.setLink(messageRequest.getLink());
                sentMessage.setFilename(messageRequest.getFilename());
                sentMessage.setStatus(responseMessage);
                tblSentMessagesRepo.save(sentMessage);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String sendMessageToWhatsApp(MessageRequest messageRequest) {
        try {
            Map<String , String> headers = new HashMap<>();
            headers.put("Authorization","Bearer "+getToken());
            ResponseEntity<String> response = whatsAppFeignClient.sendMessage(getVersion(), getPhoneNumberId(), headers, messageRequest);
            if(response.getStatusCode() == HttpStatus.OK) {
                return "Message sent successfully";
            } else {
                throw new RuntimeException("Failed to send message: " + response.getStatusCode());
            }
        } catch (FeignException e) {
            return FeignExceptionUtility.MetaException(e);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
