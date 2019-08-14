package com.usac.sa.uber;

import com.usac.sa.model.UberGPSLocationRequest;
import com.usac.sa.model.UberServiceRequest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UberGPSLocationRequestRegistration {

    private Set<UberGPSLocationRequest> registeredRequests;

    private static UberGPSLocationRequestRegistration uberGPSLocationRequestRegistration = null;

    private UberGPSLocationRequestRegistration() {
        registeredRequests = new HashSet<>();
    }

    public static UberGPSLocationRequestRegistration getInstance() {
        if (uberGPSLocationRequestRegistration == null) {
            uberGPSLocationRequestRegistration = new UberGPSLocationRequestRegistration();
        }

        return uberGPSLocationRequestRegistration;
    }

    public void add(UberGPSLocationRequest service) {
        registeredRequests.add(service);
    }

    public void remove(UberGPSLocationRequest service) {
        registeredRequests.remove(service);
    }

    public Set<UberGPSLocationRequest> getAll() {
        return registeredRequests;
    }

    public Optional<UberGPSLocationRequest> find(String requestId) {
        return registeredRequests.stream().filter(r -> r.getRequestId().equalsIgnoreCase(requestId)).findFirst();
    }
}
