package com.usac.sa.esb;

import com.usac.sa.model.Service;

import java.util.HashSet;
import java.util.Optional;
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

    public void deregister(String serviceId) {
        Optional<Service> service = registeredServices.stream().filter(s -> s.getId().equalsIgnoreCase(serviceId)).findFirst();
        service.ifPresent(value -> registeredServices.remove(value));
    }

    public Set<Service> getAll() {
        return registeredServices;
    }

    public Optional<Service> find(final String rootPath) {
        return registeredServices.stream().filter(service -> service.getRootPath().equalsIgnoreCase(rootPath)).findFirst();
    }
}
