package com.usac.sa.uber;

import com.usac.sa.model.UberGPSLocationRequest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * This Singleton class emulates the database by providing options to add, remove and find requests
 */
public class UberGPSLocationRequestRegistration {

    private Set<UberGPSLocationRequest> uberGPSLocationRequests;

    private static UberGPSLocationRequestRegistration uberGPSLocationRequestRegistration = null;

    private UberGPSLocationRequestRegistration() {
        uberGPSLocationRequests = new HashSet<>();
    }

    public static UberGPSLocationRequestRegistration getInstance() {
        if (uberGPSLocationRequestRegistration == null) {
            uberGPSLocationRequestRegistration = new UberGPSLocationRequestRegistration();
        }

        return uberGPSLocationRequestRegistration;
    }

    public void add(UberGPSLocationRequest service) {
        uberGPSLocationRequests.add(service);
    }

    public void remove(UberGPSLocationRequest service) {
        uberGPSLocationRequests.remove(service);
    }

    public Set<UberGPSLocationRequest> getAll() {
        return uberGPSLocationRequests;
    }

    public Optional<UberGPSLocationRequest> find(String requestId) {
        return uberGPSLocationRequests.stream().filter(r -> r.getRequestId().equalsIgnoreCase(requestId)).findFirst();
    }
}
