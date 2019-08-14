package com.usac.sa.uber.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.usac.sa.model.UberDriverNotification;
import com.usac.sa.uber.UberDriverNotificationRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UberDriverNotificationController {

    @RequestMapping(method = RequestMethod.GET, value = "/uber-driver-notification/list")
    @ResponseBody
    public Set<UberDriverNotification> list() {
        return UberDriverNotificationRegistration.getInstance().getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uber-driver-notification/add")
    @ResponseBody
    public UberDriverNotification add(@RequestParam String driverId, @RequestParam String userId, @RequestParam String from) {
        UberDriverNotification uberDriverNotification = new UberDriverNotification();
        uberDriverNotification.setDate(new Date());
        uberDriverNotification.setDriverId(driverId);
        uberDriverNotification.setUserId(userId);
        uberDriverNotification.setFrom(from);

        UberDriverNotificationRegistration.getInstance().add(uberDriverNotification);
        return uberDriverNotification;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uber-driver-notification/find")
    @ResponseBody
    public String find(@RequestParam String driverId) {

        List<UberDriverNotification> request = UberDriverNotificationRegistration.getInstance().find(driverId);
        if (request.isEmpty()) {
            return "{" +
                    "\"ERROR\": \"Requested driver id doesn't have any notifications.\"" +
                    "}";

        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(request);
        }
    }
}
