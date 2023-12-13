package com.microservice.satu.service;

import com.microservice.satu.dto.PaymentRequest;
import com.microservice.satu.dto.ResponseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class PaymentService {
    @Autowired
    private KafkaTemplate<String, PaymentRequest> kafkaTemplate;

    @Value("${kafka.payment.topic}")
    private String kafkaTopic;

    public ResponseTemplate processPayment(PaymentRequest request) {
        try {
            CompletableFuture<SendResult<String, PaymentRequest>> future = kafkaTemplate.send(kafkaTopic, request).completable();

            return ResponseTemplate.builder()
                    .responseCode(9000)
                    .isSuccess(true)
                    .message("PROCESSING")
                    .payload("")
                    .build();
        } catch (Exception e) {
            return ResponseTemplate.builder()
                    .responseCode(6666)
                    .isSuccess(false)
                    .message(e.getMessage())
                    .payload("")
                    .build();
        }
    }
}
