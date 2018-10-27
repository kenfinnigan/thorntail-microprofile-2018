package io.thorntail.microprofile;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;

/**
 * @author Ken Finnigan
 */
@Path("/")
@RequestScoped
public class GreetingResource {

    @GET
    @Path("/greeting")
    @Produces("application/json")
    @Fallback(fallbackMethod = "greetingFallback")
    public Response greeting() throws Exception {
        return Response.ok()
                .entity(new Greeting("Hello Voxxed!!"))
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
