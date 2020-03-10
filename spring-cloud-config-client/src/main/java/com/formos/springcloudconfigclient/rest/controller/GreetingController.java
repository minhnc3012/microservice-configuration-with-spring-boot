package com.formos.springcloudconfigclient.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formos.springcloudconfigclient.config.model.DbSettings;

@RestController
@RefreshScope //POST: http://localhost:8080/actuator/refresh
public class GreetingController {

	@Value("${my.greeting: default vale}")
	private String greetingMessage;

	@Value("some static message")
	private String staticMessage;

	@Value("${my.list.values}")
	private List<String> listValues;

//	@Value("#{${dbValues}}")
//	private Map<String, String> dbValues;

	@Autowired
	private DbSettings dbSettings;

	private Environment env;
	
	@GetMapping("/greeting")
	public String greeting() {
		return greetingMessage + " - " + staticMessage + " - " + listValues;
	}
	
	@GetMapping("/dbsettings")
	public String getDbSettings() {
		return dbSettings.getConnection() + " - " + dbSettings.getHost() + " - " + dbSettings.getPort();
	}
	
	@GetMapping("/envdetails")
	public String envDetails() {
		return dbSettings.getConnection() + " - " + dbSettings.getHost() + " - " + dbSettings.getPort();
	}
}
