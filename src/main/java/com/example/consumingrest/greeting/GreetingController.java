package com.example.consumingrest.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @GetMapping("/hello")
    public String getHelloWorldMessage (){
            return greetingService.getHelloWorldMessage();
    }

    @GetMapping("/{name}")
    public String getGreetingMessage (@PathVariable String name){
        return greetingService.getGreetingMessage(name);
    }
}
