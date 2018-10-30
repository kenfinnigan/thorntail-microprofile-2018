package io.thorntail.microprofile;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
@Health
public class ConfigHealthCheck implements HealthCheck {

    @Inject
    @ConfigProperty(name = "name", defaultValue = "nobody")
    private String name;

    @Override
    public HealthCheckResponse call() {
        if (name.equals("nobody")) {
            return HealthCheckResponse
                    .named("config-health-check")
                    .withData("name", name)
                    .down()
                    .build();
        }

        return HealthCheckResponse
                .named("config-health-check")
                .withData("name", name)
                .up()
                .build();
    }
}
