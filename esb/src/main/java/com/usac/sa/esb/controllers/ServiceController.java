package com.usac.sa.esb.controllers;

import com.usac.sa.esb.ServiceRegistration;
import com.usac.sa.model.Method;
import com.usac.sa.model.Service;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
public class ServiceController {

    @RequestMapping(method = RequestMethod.GET, value = "/esb/services")
    @ResponseBody
    public Set<Service> getAllServices() {
        return ServiceRegistration.getInstance().getAll();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/esb/services")
    @ResponseBody
    public Service register(@RequestBody Service service) {
        ServiceRegistration.getInstance().register(service);
        return service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/esb/service/*/*")
    @ResponseBody
    public String invoke(@RequestParam Map<String, String> params, HttpServletRequest request) {
        String requestedPath = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        System.out.println("* - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - * - ");
        System.out.println("Requested path: " + requestedPath);
        params.forEach((k, v )-> {
            System.out.println("Params: ");
            System.out.println(k + " -> " + v);
        });

        String servicePath = requestedPath.replace("/esb/service", ""); // removing the path to access this service
        System.out.println("Service path: " + servicePath);

        // at this point whe should have a path in this format /*/*
        // the first part is the the root path
        // and the second is the method path
        String rootPath = servicePath.substring(0, servicePath.lastIndexOf("/"));
        String methodPath = servicePath.substring(servicePath.lastIndexOf("/"));

        System.out.println("Root path: " + rootPath);
        System.out.println("Method path: " + methodPath);

        // looking for the service with the root path
        Optional<Service> service = ServiceRegistration.getInstance().find(rootPath);

        if (service.isPresent()) {
            // Service exists, now looking for the method
            Optional<Method> method = service.get().find(methodPath);

            if (method.isPresent()) {

                // everything is ok, performing the request...

                URIBuilder uriBuilder = new URIBuilder()
                        .setScheme("http")
                        .setHost(service.get().getHost())
                        .setPort(service.get().getPort())
                        .setPath(servicePath);

                params.forEach(uriBuilder::addParameter);

                try {
                    HttpGet httpGet = new HttpGet(uriBuilder.build());
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    CloseableHttpResponse response = httpClient.execute(httpGet);
                    String result =  EntityUtils.toString(response.getEntity());
                    httpClient.close();
                    System.out.println("RESULT");
                    System.out.println(result);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                    return "{" +
                            "\"ERROR\": \"" + e.getMessage() + "\"" +
                            "}";
                }
            } else {
                return "{" +
                        "\"ERROR\": \"El methodo solicitado no ha sido registrado\"" +
                        "}";
            }
        } else {
            return "{" +
                    "\"ERROR\": \"El servicio solicitado no existe o no está disponible\"" +
                    "}";
        }
    }
}
