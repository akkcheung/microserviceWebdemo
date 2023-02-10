package com.example.microserviceWebdemo;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
 public class HelloController {
// public class HelloController implements HelloClient {
	
	@Autowired
	private DiscoveryClient discoveryClient;	
	
	@Autowired
	private HelloClient helloClient;
	
	/**
	 * call hello-world api using Spring Discovery Client
	 * 
	 * @return
	 */
	@GetMapping("/hello")
	public ModelAndView hello() {

		var params = new HashMap<String, Object>();

		
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
//		= "http://localhost:8081/hello-worlds"
		= this.discoveryClient.getInstances("eureka-client-service").get(0).getUri().toString() + "/hello-worlds"
		;
		
		ResponseEntity<String> response
		  = restTemplate.getForEntity(fooResourceUrl + "/alex", String.class);
		
//		params.put("name", "kk");
		params.put("name", response.getBody().toString());

//		params.put("name", helloClient.getHelloWorld("alex alex"));
		
		return new ModelAndView("hello", params);
	}
	
	
	/**
	 * call hello-world api using Feign Client
	 * @return
	 */
	@GetMapping("/hello2")
	public ModelAndView hello2() {
		
		var params = new HashMap<String, Object>();

		params.put("name", helloClient.getHelloWorld("alex alex"));
		return new ModelAndView("hello", params);
	
	}


}
