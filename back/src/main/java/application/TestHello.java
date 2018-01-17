package application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class TestHello {

    @GET
    @Path("/hello")
    public String test() {
        return "hello";
    }

}
