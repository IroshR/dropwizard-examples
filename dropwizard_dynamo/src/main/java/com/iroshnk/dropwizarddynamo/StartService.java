package com.iroshnk.dropwizarddynamo;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.iroshnk.dropwizarddynamo.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class StartService extends Application<DropwizardDynamoConfiguration> {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 2) {
            args = new String[]{"server", "config.yaml"};
        }
        new StartService().run(args);
    }

    @Override
    public void run(DropwizardDynamoConfiguration config, Environment environment) throws Exception {
        BasicAWSCredentials credentials = new BasicAWSCredentials(config.accessKey, config.secretAccessKey);
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(credentials);
        client.setEndpoint(config.endpoint);

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,sessionId");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter("exposedHeaders", "ETag");
        //cors.setInitParameter("Access-Control-Expose-Headers", "ETag");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(new UserResource(client));
        //healthChecks
    }

    @Override
    public void initialize(Bootstrap<DropwizardDynamoConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardDynamoConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardDynamoConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }
}
