package com.usac.sa.uber;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.usac.sa.model.Method;
import com.usac.sa.model.Service;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UberGPSLocationRequestApplication {

	/**
	 * ESB endpoint
	 */
	private static final String ESB_URL = "http://127.0.0.1:8090/esb/services";


	public static void main(String[] args) {
		try {
			register();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(UberGPSLocationRequestApplication.class, args);

	}


	public static void register() throws Exception {
		Service uberGPSLocationRequest = new Service();
		uberGPSLocationRequest.setDescription("Service that allows admins to locate vehicles");
		uberGPSLocationRequest.setHost("localhost");
		uberGPSLocationRequest.setId("uber-gps-location-request");
		uberGPSLocationRequest.setName("Uber GPS Location request");
		uberGPSLocationRequest.setPort(8093);
		uberGPSLocationRequest.setRootPath("/uber-gps-location-request");

		Method listMethod = new Method();
		listMethod.setPath("/list");
		listMethod.setType(Method.TYPE.GET);

		Method addMethod = new Method();
		addMethod.setPath("/add");
		addMethod.setType(Method.TYPE.GET);
		addMethod.getParameters().add("driverId");

		Method findMethod = new Method();
		findMethod.setPath("/find");
		findMethod.setType(Method.TYPE.GET);
		findMethod.getParameters().add("requestId");

		uberGPSLocationRequest.getMethods().add(listMethod);
		uberGPSLocationRequest.getMethods().add(addMethod);
		uberGPSLocationRequest.getMethods().add(findMethod);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(ESB_URL);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonStr = gson.toJson(uberGPSLocationRequest);
		StringEntity entity = new StringEntity(jsonStr);
		httpPost.setEntity(entity);

		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		CloseableHttpResponse response = client.execute(httpPost);

		client.close();
	}
}
