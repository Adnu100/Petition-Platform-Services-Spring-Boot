package org.sslp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckController {

    @GetMapping(value = "/health")
    public String healthCheck() {
        return "{\"status\": \"UP\"}";
    }

}
