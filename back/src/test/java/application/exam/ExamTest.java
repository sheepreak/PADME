package application.exam;

import application.examen.domain.Examen;
import org.junit.Assert;
import org.junit.Test;

public class ExamTest {
    @Test
    public void testObservationNotNull() {
        Examen observation = new Examen();

        Assert.assertNotNull(observation);
    }

    @Test
    public void testTreatment() {
        Examen observation = new Examen();
        observation.setDescription("test");

        Assert.assertEquals(observation.getDescription(), "test");
    }
}
