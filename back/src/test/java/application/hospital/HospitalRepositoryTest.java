package application.hospital;

import application.hospital.domain.HospitalImpl;
import application.hospital.repository.HospitalImplRepository;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class HospitalRepositoryTest {

    private HospitalImplRepository repository = new HospitalImplRepository();

    /*

    public Long save(HospitalImpl hospital) {
        entityManager.persist(hospital);
        return hospital.getId();
    }

    public void delete(Long id) throws NoSuchEntityException{
        Hospital hospital = null;
        hospital = find(id);
        if(hospital!=null)
            entityManager.remove(hospital);
        else throw new NoSuchEntityException();
    }

    public void update(HospitalImpl hospital){
        entityManager.merge(hospital);
    }
     */
//    @Test
//    public void testListHospital(){
//        //todo mocking db
//        assert(false);
//    }
//
//    @Test
//    public void testListHospitalWithZeroEntityHospital(){
//        //todo mocking db
//        assert(false);
//    }
//
//    @Test
//    public void testFindHospital(){
//        //todo mocking db
//        assert(false);
//    }
//
//    @Test
//    public void testFindWithWrongHospitalId(){
//        //todo mocking db fix id 64 not used
//        assert(null == repository.find(64L));
//    }

}
