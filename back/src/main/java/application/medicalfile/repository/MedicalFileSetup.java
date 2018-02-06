package application.medicalfile.repository;

import application.medicalfile.domain.MedicalFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class MedicalFileSetup {

    @EJB
    private MedicalFileRepository repository;

    @PostConstruct
    private void createData() {
        repository.save(new MedicalFile(Boolean.TRUE));
        repository.save(new MedicalFile(Boolean.FALSE));
    }
}
