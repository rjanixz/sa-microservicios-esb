package com.usac.sa.uber.controllers;

import com.usac.sa.uber.UberServiceRequestRegistration;
import com.usac.sa.uber.beans.UberServiceRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class UberServiceRequestController {

    @RequestMapping(method = RequestMethod.GET, value = "/uber/service-request")
    @ResponseBody
    public Set<UberServiceRequest> getAllServices() {
        return UberServiceRequestRegistration.getInstance().getAll();
    }
}
