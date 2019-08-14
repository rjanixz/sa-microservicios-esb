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
public class UberDriverNotificationApplication {

	/**
	 * ESB endpoint
	 */
	private static final String ESB_URL = "http://127.0.0.1:8090/esb/services";


	public static void main(String[] args) {
		try {
			register();
		} catch (Exception e) {
			System.out.println("*** No ESB found");
		}
		SpringApplication.run(UberDriverNotificationApplication.class, args);

	}


	public static void register() throws Exception {
		Service uberGPSLocationRequest = new Service();
		uberGPSLocationRequest.setDescription("Service that allows drivers to look for their notifications.");
		uberGPSLocationRequest.setHost("localhost");
		uberGPSLocationRequest.setId("uber-driver-notifications");
		uberGPSLocationRequest.setName("Uber Driver Notifications");
		uberGPSLocationRequest.setPort(8092);
		uberGPSLocationRequest.setRootPath("/uber-driver-notification");

		Method allMethod = new Method();
		allMethod.setPath("/list");
		allMethod.setType(Method.TYPE.GET);

		Method addMethod = new Method();
		addMethod.setPath("/add");
		addMethod.setType(Method.TYPE.GET);
		addMethod.getParameters().add("driverId");
		addMethod.getParameters().add("userId");
		addMethod.getParameters().add("from");

		Method findMethod = new Method();
		findMethod.setPath("/find");
		findMethod.setType(Method.TYPE.GET);
		findMethod.getParameters().add("driverId");

		uberGPSLocationRequest.getMethods().add(allMethod);
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
