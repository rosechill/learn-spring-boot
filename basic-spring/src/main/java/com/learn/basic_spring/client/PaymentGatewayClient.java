package com.learn.basic_spring.client;

import lombok.Data;

@Data
public class PaymentGatewayClient {

    private String endpoint;

    private String privateKey;

    private String publicKey;

}
