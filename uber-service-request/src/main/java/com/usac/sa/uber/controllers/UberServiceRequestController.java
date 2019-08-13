package com.usac.sa.uber.controllers;

import com.usac.sa.uber.UberServiceRequestRegistration;
import com.usac.sa.model.UberServiceRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public UberServiceRequest addUberServiceRequest(@RequestBody UberServiceRequest serviceRequest) {
        UberServiceRequestRegistration.getInstance().add(serviceRequest);
        return serviceRequest;
    }
}
