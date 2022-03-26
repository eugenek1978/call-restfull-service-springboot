package com.example.consumingrest.greeting;

import java.io.IOException;

public class GreetingCallerServerError extends IOException {
    public GreetingCallerServerError(String message) {
        super(message);
    }
}
