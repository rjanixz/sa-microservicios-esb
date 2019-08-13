package com.usac.sa.esb.controllers;

import com.usac.sa.esb.ServiceRegistration;
import com.usac.sa.esb.beans.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class ServiceController {

    @RequestMapping(method = RequestMethod.GET, value = "/esb/services")
    @ResponseBody
    public Set<Service> getAllServices() {
        return ServiceRegistration.getInstance().getAll();
    }
}
