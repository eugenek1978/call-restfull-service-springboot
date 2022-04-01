package com.example.consumingrest.greeting;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingServiceImpl implements GreetingService {

    private static final String BASE_GREETING_SERVICE_URL = "http://localhost:5000/";
    private RestTemplate restTemplate;

    public GreetingServiceImpl(RestTemplateBuilder builder, GreetingCallerErrorHandler greetingCallerErrorHandler) {
        this.restTemplate = builder.errorHandler(greetingCallerErrorHandler).build();
    }


    @Override
    public String getHelloWorldMessage() {
        return restTemplate.getForObject(BASE_GREETING_SERVICE_URL,String.class);
    }

    @Override
    public String getGreetingMessage(String name) {
        return restTemplate.getForObject(BASE_GREETING_SERVICE_URL + name,String.class);
    }
}
