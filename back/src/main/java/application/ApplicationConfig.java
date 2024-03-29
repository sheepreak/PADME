package application;

import application.filters.CORSFilter;
import application.filters.JWTTokenNeeded;
import application.hospital.rest.HospitalRestService;
import application.medicalfile.rest.MedicalFileRest;
import application.node.rest.NodeRestService;
import application.patient.rest.PatientRest;
import application.staff.rest.StaffRest;
import org.glassfish.jersey.jackson.JacksonFeature;

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
        c.add(JacksonFeature.class);
        c.add(CORSFilter.class);
        c.add(HospitalRestService.class);
        c.add(StaffRest.class);
        c.add(PatientRest.class);
        c.add(MedicalFileRest.class);
        c.add(NodeRestService.class);
        c.add(JWTTokenNeeded.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}