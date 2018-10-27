package io.thorntail.microprofile;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * @author Ken Finnigan
 */
@RegisterRestClient
@Path("/api")
public interface NameService {
    @Path("/name")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String getName();
}
