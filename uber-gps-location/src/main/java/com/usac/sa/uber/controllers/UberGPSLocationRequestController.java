package com.usac.sa.uber.controllers;

import com.usac.sa.model.UberGPSLocationRequest;
import com.usac.sa.uber.UberGPSLocationRequestRegistration;
import com.usac.sa.model.UberServiceRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Controller
public class UberGPSLocationRequestController {

    @RequestMapping(method = RequestMethod.GET, value = "/uber-gps-location-request/list")
    @ResponseBody
    public Set<UberGPSLocationRequest> list() {
        return UberGPSLocationRequestRegistration.getInstance().getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uber-gps-location-request/add")
    @ResponseBody
    public UberGPSLocationRequest add(@RequestParam String driverId) {
        UberGPSLocationRequest uberGPSLocationRequest = new UberGPSLocationRequest();
        uberGPSLocationRequest.setDate(new Date());
        uberGPSLocationRequest.setRequestId(randomRequestId());  // generating a request Id
        uberGPSLocationRequest.setDriverId(driverId);
        UberGPSLocationRequestRegistration.getInstance().add(uberGPSLocationRequest);
        return uberGPSLocationRequest;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uber-gps-location-request/find")
    @ResponseBody
    public String find(@RequestParam String requestId) {

        Optional<UberGPSLocationRequest> request = UberGPSLocationRequestRegistration.getInstance().find(requestId);
        if (request.isPresent()) {
            return "{" +
                    "\"RESULT\": \"Your request is being processed\"" +
                    "}";
        } else {
            return "{" +
                    "\"ERROR\": \"Request id doesn't exists.\"" +
                    "}";
        }
    }



    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomRequestId() {
        StringBuilder builder = new StringBuilder();
        int count = 7;
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
