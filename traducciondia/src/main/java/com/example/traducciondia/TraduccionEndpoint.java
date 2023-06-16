package com.example.traducciondia;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetTraduccionRequest;
import io.spring.guides.gs_producing_web_service.GetTraduccionResponse;

@Endpoint
public class TraduccionEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private TraduccionRepository traduccionRepository;

    public TraduccionEndpoint(TraduccionRepository countryRepository) {
        this.traduccionRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTraduccionRequest")
    @ResponsePayload
    public GetTraduccionResponse getCountry(@RequestPayload GetTraduccionRequest request) {
        GetTraduccionResponse response = new GetTraduccionResponse();
        response.setDia(traduccionRepository.findDia(request.getNombre()));
        return response;
    }
}
