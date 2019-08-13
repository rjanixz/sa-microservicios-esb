package com.usac.sa.uber.controllers;

import com.usac.sa.uber.UberServiceRequestRegistration;
import com.usac.sa.model.UberServiceRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class UberServiceRequestController {

    @RequestMapping(method = RequestMethod.GET, value = "/uber-service-request/list")
    @ResponseBody
    public Set<UberServiceRequest> getAllRequestedServices() {
        return UberServiceRequestRegistration.getInstance().getAll();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/uber-service-request/add")
    @ResponseBody
    public UberServiceRequest addUberServiceRequest(@RequestParam String userId, @RequestParam String from, @RequestParam String to) {
        UberServiceRequest uberServiceRequest = new UberServiceRequest();
        uberServiceRequest.setUserId(userId);
        uberServiceRequest.setFrom(from);
        uberServiceRequest.setTo(to);
        UberServiceRequestRegistration.getInstance().add(uberServiceRequest);
        return uberServiceRequest;
    }
}
