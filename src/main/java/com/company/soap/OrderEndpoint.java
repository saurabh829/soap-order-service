package com.company.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class OrderEndpoint {

    private static final String NAMESPACE_URI = "http://company.com/order";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "OrderRequest")
    @ResponsePayload
    public String processOrder(@RequestPayload String request) {

        // NOTE:
        // We are intentionally returning raw XML for now.
        // JAXB binding will be added later (Week 2/3).

        return """
            <OrderResponse xmlns="http://company.com/order">
                <status>SUCCESS</status>
            </OrderResponse>
        """;
    }
}
