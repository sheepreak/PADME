package application.hospital;

import application.hospital.repository.HospitalRepository;

public class HospitalRepositoryTest {

    private HospitalRepository repository = new HospitalRepository();

    /*

    public Long save(Hospital hospital) {
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

    public void update(Hospital hospital){
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
