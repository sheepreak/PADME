package application.adminfile.domain;

import org.junit.*;

public class AdminFileTest {

    @Test
    public void testAdminFileNotNull() {
        AdminFile adminFile = new AdminFile();

        Assert.assertNotNull(adminFile);
    }

    @Test
    public void testLastName() {
        AdminFile adminFile = new AdminFile();

        adminFile.setLastName("test");
        Assert.assertEquals(adminFile.getLastName(), "test");
    }
}
