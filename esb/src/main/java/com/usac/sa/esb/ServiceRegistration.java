package com.usac.sa.esb;

import com.usac.sa.model.Service;

import java.util.HashSet;
import java.util.Set;

public class ServiceRegistration {

    private Set<Service> registeredServices;

    private static ServiceRegistration serviceRegistration = null;

    private  ServiceRegistration() {
        registeredServices = new HashSet<>();
    }

    public static ServiceRegistration getInstance() {
        if (serviceRegistration == null) {
            serviceRegistration = new ServiceRegistration();
        }

        return serviceRegistration;
    }

    public void register(Service service) {
        registeredServices.add(service);
    }

    public void deregister(Service service) {
        registeredServices.remove(service);
    }

    public Set<Service> getAll() {
        return registeredServices;
    }
}
