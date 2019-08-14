package com.usac.sa.uber;

import com.usac.sa.model.UberDriverNotification;
import com.usac.sa.model.UberGPSLocationRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This Singleton class emulates the database by providing options to add, remove and find requests
 */
public class UberDriverNotificationRegistration {

    private Set<UberDriverNotification> registeredRequests;

    private static UberDriverNotificationRegistration uberDriverNotificationRegistration = null;

    private UberDriverNotificationRegistration() {
        registeredRequests = new HashSet<>();
    }

    public static UberDriverNotificationRegistration getInstance() {
        if (uberDriverNotificationRegistration == null) {
            uberDriverNotificationRegistration = new UberDriverNotificationRegistration();
        }

        return uberDriverNotificationRegistration;
    }

    public void add(UberDriverNotification service) {
        registeredRequests.add(service);
    }

    public void remove(UberDriverNotification service) {
        registeredRequests.remove(service);
    }

    public Set<UberDriverNotification> getAll() {
        return registeredRequests;
    }

    public List<UberDriverNotification> find(String driverId) {
        return registeredRequests.stream().filter(r -> r.getDriverId().equalsIgnoreCase(driverId)).collect(Collectors.toList());
    }
}
