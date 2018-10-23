package io.thorntail.servicemesh;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Ken Finnigan
 */
@Path("/")
@RequestScoped
public class GreetingResource {

    @GET
    @Path("/greeting")
    @Produces("application/json")
    public Response greeting() throws Exception {
        return Response.ok()
                .entity(new Greeting("Hello CodeOne 2018!!!"))
                .build();
    }

    static class Greeting {
        private final String content;

        public Greeting(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

}
