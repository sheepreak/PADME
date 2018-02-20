package application.prescription;

import application.prescription.domain.Prescription;
import org.junit.Assert;
import org.junit.Test;

public class PrescriptionTest {

    @Test
    public void testObservationNotNull() {
        Prescription observation = new Prescription();

        Assert.assertNotNull(observation);
    }

    @Test
    public void testTreatment() {
        Prescription observation = new Prescription();
        observation.setTreatment("test");

        Assert.assertEquals(observation.getTreatment(), "test");
    }
}
