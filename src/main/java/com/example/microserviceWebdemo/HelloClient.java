package com.example.microserviceWebdemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eureka-client-service")
public interface HelloClient {
	@RequestMapping("/hello-worlds/{name}")
	String getHelloWorld(@PathVariable String name);
}
