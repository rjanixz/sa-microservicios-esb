package com.usac.sa.uber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.usac.sa.model.Service;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UberRequestServiceApplication {

	public static final String ESB_URL = "http://127.0.0.1:8090/esb/services";


	public static void main(String[] args) {
		try {
			register();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(UberRequestServiceApplication.class, args);

	}


	public static void register() throws Exception {
		Service uberRequestService = new Service();
		uberRequestService.setDescription("Service that allows the users to request for an Uber");
		uberRequestService.setHost("localhost");
		uberRequestService.setId("uber-request-service");
		uberRequestService.setName("Uber Request Service");
		uberRequestService.setPort(8091);
		uberRequestService.setRootPath("/uber-service-request");


		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(ESB_URL);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonStr = gson.toJson(uberRequestService);
		StringEntity entity = new StringEntity(jsonStr);
		httpPost.setEntity(entity);

		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		CloseableHttpResponse response = client.execute(httpPost);

		client.close();
	}
}
