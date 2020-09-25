package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@Autowired DemoService service;
	
	@GetMapping("/content")
	public DemoContent content() {
		DemoContent response = new DemoContent();
		response.setId(1);
		response.setText("x");
		return response;
	}
	
	@GetMapping("/sample-jdbc-read")
	public DemoContent sampleJdbcRead() {
		DemoContent response = new DemoContent();
		response.setId(1);
		response.setText(service.executeSampleRead().toString());
		return response;
	}
}
