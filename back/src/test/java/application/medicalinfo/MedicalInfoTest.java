package application.medicalinfo;

import application.medicalinfo.domain.MedicalInfo;
import org.junit.Assert;
import org.junit.Test;

public class MedicalInfoTest {

    @Test
    public void testMedicalInfoNotNull() {
        MedicalInfo medicalInfo = new MedicalInfo();

        Assert.assertNotNull(medicalInfo);
    }

    @Test
    public void testMapNotNull() {
        MedicalInfo medicalInfo = new MedicalInfo();

        Assert.assertNotNull(medicalInfo.getInformations());
    }

    @Test
    public void testInformations() {
        //TODO remplir ce test
    }
}
