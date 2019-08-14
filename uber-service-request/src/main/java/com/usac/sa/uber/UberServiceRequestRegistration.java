package com.usac.sa.uber;

import com.usac.sa.model.UberServiceRequest;

import java.util.HashSet;
import java.util.Set;

/**
 * This Singleton class emulates the database by providing options to add, remove and find requests
 */
public class UberServiceRequestRegistration {

    private Set<UberServiceRequest> registeredRequests;

    private static UberServiceRequestRegistration uberServiceRequestRegistration = null;

    private UberServiceRequestRegistration() {
        registeredRequests = new HashSet<>();
    }

    public static UberServiceRequestRegistration getInstance() {
        if (uberServiceRequestRegistration == null) {
            uberServiceRequestRegistration = new UberServiceRequestRegistration();
        }

        return uberServiceRequestRegistration;
    }

    public void add(UberServiceRequest service) {
        registeredRequests.add(service);
    }

    public void remove(UberServiceRequest service) {
        registeredRequests.remove(service);
    }

    public Set<UberServiceRequest> getAll() {
        return registeredRequests;
    }
}
