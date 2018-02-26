package com.iroshnk.dropwizardmongo;

import com.iroshnk.dropwizardmongo.persistence.MongoPersistenceManager;
import com.iroshnk.dropwizardmongo.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.ws.rs.client.Client;

/**
 * Created by HP on 9/16/2017.
 */
public class StartService extends Application<DropwizardMongoConfiguration> {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 2) {
            args = new String[]{"server", "config.yaml"};
        }

        new StartService().run(args);
    }

    @Override
    public String getName() {
        return "config";
    }

    @Override
    public void initialize(Bootstrap<DropwizardMongoConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardMongoConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardMongoConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    public void run(DropwizardMongoConfiguration configuration, Environment environment) throws Exception {
        Client client = new JerseyClientBuilder(environment).using(configuration.jerseyClient).build(getName());
        MongoPersistenceManager.initialize(configuration.mongoDatabase);

        environment.jersey().register(new UserResource());
    }
}