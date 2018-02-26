package com.iroshnk.dropwizarde;

import com.iroshnk.dropwizarde.persistence.PersistenceManager;
import com.iroshnk.dropwizarde.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by HP on 8/11/2017.
 */
public class StartService extends Application<DropwizardEConfiguration> {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 2) {
            args = new String[]{"server", "config.yaml"};
        }
        new StartService().run(args);
    }

    @Override
    public void run(DropwizardEConfiguration dropwizardEConfiguration, Environment environment) throws Exception {
        PersistenceManager.initialize(dropwizardEConfiguration.database);

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,sessionId");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter("exposedHeaders", "ETag");
        //cors.setInitParameter("Access-Control-Expose-Headers", "ETag");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(new UserResource());
        //healthChecks
    }

    @Override
    public void initialize(Bootstrap<DropwizardEConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardEConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardEConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }
}
