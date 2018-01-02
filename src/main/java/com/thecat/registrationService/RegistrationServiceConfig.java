package com.thecat.registrationService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.thecat.registrationService.endPoint.RegistrationEndPoint;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class RegistrationServiceConfig  extends Application {
    private Set<Object> singletons = new HashSet<>();

    public RegistrationServiceConfig() {
        singletons.add(new RegistrationEndPoint());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
