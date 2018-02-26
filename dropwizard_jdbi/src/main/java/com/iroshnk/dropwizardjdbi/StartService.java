package com.iroshnk.dropwizardjdbi;

import com.iroshnk.dropwizardjdbi.dao.DMLOperations;
import com.iroshnk.dropwizardjdbi.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by HP on 8/11/2017.
 */
public class StartService extends Application<DropwizardJDBIConfiguration>{
    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 2) {
            args = new String[]{"server", "config.yaml"};
        }
        new StartService().run(args);
    }

    @Override
    public void run(DropwizardJDBIConfiguration dropwizardJDBIConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI dbi = factory.build(environment,dropwizardJDBIConfiguration.database,"database");
        DMLOperations dml = new DMLOperations(dbi);
        dml.createTables();

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,sessionId");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter("exposedHeaders", "ETag");
        //cors.setInitParameter("Access-Control-Expose-Headers", "ETag");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(new UserResource(dbi));
        //healthChecks
    }

    @Override
    public void initialize(Bootstrap<DropwizardJDBIConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardJDBIConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardJDBIConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }
}
