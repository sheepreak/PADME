package application.observation;

import application.observation.domain.Observation;
import org.junit.Assert;
import org.junit.Test;

public class ObservationTest {

    @Test
    public void testObservationNotNull() {
        Observation observation = new Observation();

        Assert.assertNotNull(observation);
    }

    @Test
    public void testComment() {
        Observation observation = new Observation();
        observation.setComment("test");

        Assert.assertEquals(observation.getComment(), "test");
    }
}
