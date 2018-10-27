package io.thorntail.microprofile;

import java.net.URL;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.opentracing.Tracer;
import io.smallrye.opentracing.SmallRyeClientTracingFeature;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * @author Ken Finnigan
 */
@Path("/")
@RequestScoped
public class GreetingResource {

    @Inject
    @ConfigProperty(name = "name", defaultValue = "nobody")
    private String name;

    @Inject
    Tracer tracer;

    @GET
    @Path("/greeting")
    @Produces("application/json")
    @Fallback(fallbackMethod = "greetingFallback")
    public Response greeting() throws Exception {
        NameService nameService = RestClientBuilder
                .newBuilder()
                .baseUrl(new URL("http://localhost:8081"))
                .register(new SmallRyeClientTracingFeature(tracer))
                .build(NameService.class);
        return Response.ok()
                .entity(new Greeting("Hello " + nameService.getName()))
                .build();
    }

    private Response greetingFallback() {
        return Response.ok().entity(new Greeting("Greeting Fallback")).build();
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
