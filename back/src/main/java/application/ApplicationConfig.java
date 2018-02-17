package application;

import application.filters.CORSFilter;
import application.node.rest.NodeRestService;
import org.glassfish.jersey.jackson.JacksonFeature;
import application.hospital.rest.HospitalRestService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rs")
public class ApplicationConfig extends Application {
    private final Set<Class<?>> classes;

    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(TestHello.class);
        c.add(JacksonFeature.class);
        c.add(CORSFilter.class);
        c.add(HospitalRestService.class);
        c.add(NodeRestService.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}